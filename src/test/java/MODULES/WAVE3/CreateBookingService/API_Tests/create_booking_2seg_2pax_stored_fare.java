package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.CreateBookingService.PostCheck.IssueTicket_create_booking_2seg_2pax_stored_fare_issue_ticket;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class create_booking_2seg_2pax_stored_fare extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        UpdatePayload();

//                       ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getCreatebookingservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest); 

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "CreateBookingService\\create_booking_2seg_2pax_stored_fare.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertFalse(response.getBody().asString().contains("Sell Itinerary Process Failed to Complete Successfully :  (1) FLT NOOP FOR FLT/DATE"));
        ExtentLogger.info("Response contains \"Sell Itinerary Process Failed to Complete Successfully :  (1) FLT NOOP FOR FLT/DATE\"");

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Do not contain Success");
        ExtentLogger.info("Assertion passed - contain Success");

        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID"), "Do not contain BookingReferenceID");
        ExtentLogger.info("Assertion passed - contain BookingReferenceID");

        Assert.assertTrue(response.getBody().asString().contains("Telephone"), "Do not contain Telephone");
        ExtentLogger.info("Assertion passed - contain Telephone");

        Assert.assertTrue(response.getBody().asString().contains("FareBasisCodes"), "Do not contain FareBasisCodes");
        ExtentLogger.info("Assertion passed - contain FareBasisCodes");

        Assert.assertTrue(response.getBody().asString().contains("BaseFare"), "Do not contain BaseFare");
        ExtentLogger.info("Assertion passed - contain BaseFare");

        Assert.assertTrue(response.getBody().asString().contains("NotValidBefore"), "Do not contain NotValidBefore");
        ExtentLogger.info("Assertion passed - contain NotValidBefore");

        Assert.assertTrue(response.getBody().asString().contains("NotValidAfter"), "Do not contain NotValidAfter");
        ExtentLogger.info("Assertion passed - contain NotValidAfter");

        Assert.assertTrue(response.getBody().asString().contains("<ns3:FareBaggageAllowance FlightSegmentRPH=\"1\" UnitOfMeasureQuantity=\"3\" UnitOfMeasure=\"PC\"/>"), "Do not have FlightSegmentRPH");
        ExtentLogger.info("Assertion passed - contain FlightSegmentRPH");

        Assert.assertTrue(response.getBody().asString().contains("<ns3:TravelerRefNumber RPH=\"1\" SurnameRefNumber=\"1\"/>"),  "Do not contain TravelerRefNumber");
        ExtentLogger.info("Assertion passed - contain TravelerRefNumber");

        Assert.assertTrue(response.getBody().asString().contains("TourCode"), "Do not contain TourCode");
        ExtentLogger.info("Assertion passed - contain TourCode");

        Assert.assertTrue(response.getBody().asString().contains("FareBaggageAllowance"), "Do not contain FareBaggageAllowance");
        ExtentLogger.info("Assertion passed - contain FareBaggageAllowance");

        Assert.assertTrue(response.getBody().asString().contains("Invalid ISO country code for Bankers Rate."), "Do not contain Invalid ISO country code for Bankers Rate");
        ExtentLogger.info("Assertion passed - contain Invalid ISO country code for Bankers Rate");

        Assertions.AssertWarning(response, true);
        ExtentLogger.info("Assertion passed - Do not have warning");

        Assertions.AssertResponseTime(response, ResponseTime);

        excelwriter();

//        IssueTicket_create_booking_2seg_2pax_stored_fare_issue_ticket postCheck = new IssueTicket_create_booking_2seg_2pax_stored_fare_issue_ticket();
//        postCheck.run();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow = sheet.getRow(8);


        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\create_booking_2seg_2pax_stored_fare.xml";

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 0);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 1);

        XMLParser.updateAttributeValue("air1:Date", "Date", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath());

        wb.close();


    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(8);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\create_booking_2seg_2pax_stored_fare.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);
        InputRow.getCell(17).setCellValue(PNR);


        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }
}
