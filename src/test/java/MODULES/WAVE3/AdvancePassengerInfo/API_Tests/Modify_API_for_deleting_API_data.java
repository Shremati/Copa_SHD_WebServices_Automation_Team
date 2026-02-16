package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.RESTWrapper;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;

import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.*;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;
import GENERICS.Assertions;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Modify_API_for_deleting_API_data extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        ExtentLogger.info("Prerequisite 1");
//        Create_Booking_Update_and_Delete_API_data Prerequisite1 = new Create_Booking_Update_and_Delete_API_data();
//        Prerequisite1.run();
        int i=0;
        boolean flightFound=false;

        Create_Booking_Update_and_Delete_API_data Prerequisite1 = new   Create_Booking_Update_and_Delete_API_data();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite1.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite 2");
        Display_API_Update_Delete_api_data_1 Prerequisite2 = new Display_API_Update_Delete_api_data_1();
        Prerequisite2.run(); //APIS INCOMPLETE

        ExtentLogger.info("Prerequisite 3");
        Modify_API_for_updating_API_data Prerequisite3 = new Modify_API_for_updating_API_data();
        Prerequisite3.run(); //APIS COMPLETE

        ExtentLogger.info("Prerequisite 4");
        Prerequisite2.run();//For update response, 'APIS Complete' message should available in display response.

        UpdatePayload(); //ModifyAPI request for deleting API

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getAdvancepassengerinfo());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getAdvancepassengerinfo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\Modify_API_for_deleting_API_data.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        ExtentLogger.info("PostRequest 1");
        Display_API_Update_Delete_api_data_2 PostRequest = new Display_API_Update_Delete_api_data_2();
        PostRequest.run();

        //Assertion given inside PostRequest

//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("AdvancePassengerInfo");
        XSSFRow InputRow=sheet.getRow(4);

        String filepath1;
        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Modify_API_for_deleting_API_data.xml";

        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);


        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements", "AgencyName", InputRow.getCell(15).getStringCellValue(), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements", "AgencyName", InputRow.getCell(16).getStringCellValue(), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements", "AgencyName", InputRow.getCell(15).getStringCellValue(), getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements", "AgencyName", InputRow.getCell(16).getStringCellValue(), getTemp_requestPath(),3);
        wb.close();

    }
}
