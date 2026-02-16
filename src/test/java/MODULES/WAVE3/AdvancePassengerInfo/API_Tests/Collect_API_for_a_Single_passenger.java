package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.RESTWrapper;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_Booking_Collect_API_for_a_Single_passenger;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_booking_Display_API_requirements_for_Secure_Flight;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Display_API_Collect_API_for_a_single_pax;
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

public class Collect_API_for_a_Single_passenger extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        ExtentLogger.info("Prerequisite 1");
//        Create_Booking_Collect_API_for_a_Single_passenger Prerequisite1 = new Create_Booking_Collect_API_for_a_Single_passenger();
//        Prerequisite1.run();
        int i=0;
        boolean flightFound=false;

        Create_Booking_Collect_API_for_a_Single_passenger Prerequisite1 = new  Create_Booking_Collect_API_for_a_Single_passenger();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite1.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite 2");
        Display_API_Collect_API_for_a_single_pax Prerequisite2 = new Display_API_Collect_API_for_a_single_pax();
        Prerequisite2.run();

        UpdatePayload();

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\Collect_API_for_a_Single_passenger.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("RecordID=\"1\">0:APIS COMPLETE"),"Not contains in response RecordID=\"1\">0:APIS COMPLETE");
        ExtentLogger.info("Assertion passed - contains RecordID=\"1\">0:APIS COMPLETE");

        Assertions.AssertWarning(response,true);
        ExtentLogger.info("Assertion passed - Do not contain Warning");

        Assertions.AssertResponseTime(response,ResponseTime);

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
        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Collect_API_for_a_Single_passenger.xml";

        XMLParser.updateAttributeValue("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),filepath1);

        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements", "AgencyName", InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements", "AgencyName", InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(), 1);


        wb.close();

    }
}
