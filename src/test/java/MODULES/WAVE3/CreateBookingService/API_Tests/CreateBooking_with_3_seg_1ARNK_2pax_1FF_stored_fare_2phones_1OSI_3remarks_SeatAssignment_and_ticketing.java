package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.CreateBookingService.PostCheck.Issue_Ticket_CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;
import org.json.XML;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        UpdatePayload();

//                       ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "CreateBookingService\\CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),
                "Do not contain Success");
        ExtentLogger.info("Assertion passed - contain Success");

        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID"),
                "Do not contain BookingReferenceID");
        ExtentLogger.info("Assertion passed - contain BookingReferenceID");

        Assert.assertTrue(response.getBody().asString().contains("Telephone"),
                "Do not contain Telephone");
        ExtentLogger.info("Assertion passed - contain Telephone");

        Assert.assertTrue(response.getBody().asString().contains("BaseFare"),
                "Do not contain BaseFare");
        ExtentLogger.info("Assertion passed - contain BaseFare");

        Assert.assertTrue(response.getBody().asString().contains("<ns3:Remark>HAVE A NICE PARTY</ns3:Remark>"),
                "Do not contain HAVE A NICE PARTY");
        ExtentLogger.info("Assertion passed - contain HAVE A NICE PARTY");

        Assert.assertTrue(response.getBody().asString().contains("<air1:Email>modificados@gmail.com</air1:Email>"),
                "Do not contain modificados@gmail.com");
        ExtentLogger.info("Assertion passed - contain modificados@gmail.com");

        Assertions.AssertWarning(response, false);
        ExtentLogger.info("Assertion passed - Do not have warning");

        Assertions.AssertResponseTime(response, ResponseTime);

        excelwriter();

        Issue_Ticket_CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing Postrequest = new Issue_Ticket_CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing();
        Postrequest.run();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow = sheet.getRow(20);


        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing.xml";

        // Segment 1
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("air1:SeatRequest", "SeatNumber", InputRow.getCell(23).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("air1:SeatRequest", "SeatNumber", InputRow.getCell(24).getStringCellValue(), getTemp_requestPath(), 1);

        // Segment 2
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()), getTemp_requestPath(), 2);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(), 2);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(7).getStringCellValue(), getTemp_requestPath(), 2);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(8).getStringCellValue(), getTemp_requestPath(), 2);
        XMLParser.updateAttributeValueatIndex("air1:SeatRequest", "SeatNumber", InputRow.getCell(25).getStringCellValue(), getTemp_requestPath(), 2);
        XMLParser.updateAttributeValueatIndex("air1:SeatRequest", "SeatNumber", InputRow.getCell(26).getStringCellValue(), getTemp_requestPath(), 3);

        // Segment 3 ARNK
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(9).getNumericCellValue()), getTemp_requestPath(), 1);

        //FFNumber
        XMLParser.updateAttributeValue("air1:CustLoyalty", "MembershipID", InputRow.getCell(36).getStringCellValue(), getTemp_requestPath());
        //Name of the FFPax
        XMLParser.SetTagtextatIndex("com:GivenName", InputRow.getCell(37).getStringCellValue(), getTemp_requestPath(), 0); //Taking 1st pax as its
        XMLParser.SetTagtextatIndex("com:Surname", InputRow.getCell(38).getStringCellValue(), getTemp_requestPath(), 0);

        XMLParser.updateAttributeValue("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMdd(InputRow.getCell(19).getNumericCellValue()), getTemp_requestPath());

        wb.close();


    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(20);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);

        InputRow.getCell(17).setCellValue(PNR);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }
}
