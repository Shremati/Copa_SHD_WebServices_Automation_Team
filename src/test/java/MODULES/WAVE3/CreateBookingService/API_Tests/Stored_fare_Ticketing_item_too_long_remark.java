package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.CreateBookingService.PreRequest.Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing;
import MODULES.WAVE3.CreateBookingService.PreRequest.Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing_issue_ticket;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Stored_fare_Ticketing_item_too_long_remark extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        int i=0;
        boolean flightFound=false;

        //We are searching all the available flights in a do while loop
        Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing Prerequisite1 = new Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite1.run(i++);

        }while(!flightFound);
//        Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing Prerequisite = new Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing();
        ExtentLogger.info("Prerequisite 1");
//        Prerequisite.run();

        Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing_issue_ticket Prerequisite2 = new Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing_issue_ticket();
        ExtentLogger.info("Prerequisite 2");
        Prerequisite2.run();

        flightFound = false;
        Response response = null;
        i = 0;

        do{

            UpdatePayload(i);

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getCreatebookingservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest); 

         response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
            if((response.getBody().asString().contains("Success"))){
                flightFound = true;
            }

            i++;

            if(i > 4){
                Assert.fail("No flights are having seats");
            }
        }
        while(!flightFound);
        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "CreateBookingService\\Stored_fare_Ticketing_item_too_long_remark.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertFalse(response.getBody().asString().contains("Sell Itinerary Process Failed to Complete Successfully :  (1) FLT NOOP FOR FLT/DATE"));
        ExtentLogger.info("Response contains \"Sell Itinerary Process Failed to Complete Successfully :  (1) FLT NOOP FOR FLT/DATE\"");

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Do not contain Success");
        ExtentLogger.info("Assertion passed - contain Success");

        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID"), "Do not contain BookingReferenceID");
        ExtentLogger.info("Assertion passed - contain BookingReferenceID");

        Assert.assertTrue(response.getBody().asString().contains("Error Response to Add Free-Flow Remark Transaction"),
                "Do not contain Error Response to Add Free-Flow Remark Transaction");
        ExtentLogger.info("Assertion passed - contain Error Response to Add Free-Flow Remark Transaction");

        Assertions.AssertWarning(response, true);


        Assertions.AssertResponseTime(response, ResponseTime);


//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

        excelwriter(i);

    }


    public static void UpdatePayload(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(36);
        XSSFRow InputRow1 = sheet.getRow(1);

        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\Stored_fare_Ticketing_item_too_long_remark.xml";


        XMLParser.updateAttributeValue("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1);
        XMLParser.updateAttributeValue("air1:FlightSegment", "FlightNumber", availableFlights.get(InputRow.getCell(3).getStringCellValue()+"-"+InputRow.getCell(4).getStringCellValue()).get(i), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(19).getNumericCellValue()), getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:FareBasisCode", "NotValidBefore", Utils.getDate_YYYYMMdd(InputRow.getCell(20).getNumericCellValue()), getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:FareBasisCode", "NotValidAfter", Utils.getDate_YYYYMMdd(InputRow.getCell(21).getNumericCellValue()), getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:Date", "Date", Utils.getDate_YYYYMMdd(InputRow.getCell(19).getNumericCellValue()), getTemp_requestPath());

        // adding valid ticket number which from prerequisite

        XMLParser.updateAttributeValue("air1:OriginalIssueInfo", "DateOfIssue", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(19).getNumericCellValue()), getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:OriginalIssueInfo", "TicketDocumentNbr", InputRow1.getCell(18).getStringCellValue(), getTemp_requestPath());

        XMLParser.updateAttributeValue("air1:OriginalOriginDestination", "OriginCityCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:OriginalOriginDestination", "DestinationCityCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath());


        wb.close();

    }

    public static void excelwriter(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(36);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\Stored_fare_Ticketing_item_too_long_remark.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);

        InputRow.getCell(17).setCellValue(PNR);

        String flight = XMLParser.GetAttributeValue("ns3:FlightSegment", "FlightNumber", filepath);
        InputRow.getCell(2).setCellValue(flight);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }


}
