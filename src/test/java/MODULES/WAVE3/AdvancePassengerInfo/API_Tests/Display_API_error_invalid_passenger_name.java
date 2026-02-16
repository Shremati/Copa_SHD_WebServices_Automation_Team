package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_booking_error_invalid_passenger_name;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_booking_pax_types_2_adts_1_infant_without_seat;
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

public class Display_API_error_invalid_passenger_name extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
//        ExtentLogger.info("Prerequisite 1");
//        Create_booking_error_invalid_passenger_name Prerequisite = new Create_booking_error_invalid_passenger_name();
//        Prerequisite.run();
        int i=0;
        boolean flightFound=false;
        ExtentLogger.info("Prerequisite1");

//We are searching all the available flights in a do while loop
        Create_booking_error_invalid_passenger_name Prerequisite = new  Create_booking_error_invalid_passenger_name();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite.run(i++);

        }while(!flightFound);
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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\DisplayAPI_error_Invalid_passenger_name.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("PASSENGER NOT FOUND"),"Not contains \"PASSENGER NOT FOUND\" in response");
        ExtentLogger.info("Assertion passed - contains \"PASSENGER NOT FOUND\"");

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

        XSSFRow InputRow=sheet.getRow(12);

        String filepath1;
        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Display_API_error_invalid_passenger_name.xml";

        XMLParser.updateAttributeValue("air1:BookingReferenceID", "ID", InputRow.getCell(7).getStringCellValue(),filepath1);

        wb.close();

    }

}
