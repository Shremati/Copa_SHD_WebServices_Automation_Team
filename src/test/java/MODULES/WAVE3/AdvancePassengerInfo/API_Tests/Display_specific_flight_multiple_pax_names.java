package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_booking_Specific_flight_multiple_pax_names;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import org.testng.Assert;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import GENERICS.Assertions;
import reports.ExtentLogger;

import static io.restassured.RestAssured.given;

public class Display_specific_flight_multiple_pax_names extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        ExtentLogger.info("Prerequisite 1");
        Create_booking_Specific_flight_multiple_pax_names Prerequisite = new Create_booking_Specific_flight_multiple_pax_names();
        Prerequisite.run();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getAuthorizationservice());

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\Display_specific_flight_multiple_pax_names.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("RecordID=\"1\">0:APIS INCOMPLETE"),"Not contains RecordID=\"1\">0:APIS INCOMPLETE");
        ExtentLogger.info("Assertion passed - contains RecordID=\"1\">0:APIS INCOMPLETE");

        Assert.assertTrue(response.getBody().asString().contains("RecordID=\"2\">0:APIS INCOMPLETE"),"Not contains RecordID=\"2\">0:APIS INCOMPLETE");
        ExtentLogger.info("Assertion passed - contains RecordID=\"2\">0:APIS INCOMPLETE");

        Assert.assertTrue(response.getBody().asString().contains("RecordID=\"3\">0:APIS INCOMPLETE"),"Not contains RecordID=\"3\">0:APIS INCOMPLETE");
        ExtentLogger.info("Assertion passed - contains RecordID=\"3\">0:APIS INCOMPLETE");

        Assertions.AssertWarning(response,false);
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

        XSSFRow InputRow=sheet.getRow(19);

        String filepath1;
        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Display_specific_flight_multiple_pax_names.xml";

        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID", "ID", InputRow.getCell(7).getStringCellValue(),filepath1, 0);
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID", "ID", InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID", "ID", InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(), 2);

        XMLParser.updateAttributeValue("air:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:OperatingAirline", "FlightNumber", InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());


        wb.close();

    }

}
