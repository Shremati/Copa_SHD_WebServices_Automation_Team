package MODULES.WAVE3.DisplayBookingService.API_Tests;


import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking1_display_confirmed_and_waitlist_booking_list;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking2_display_confirmed_and_waitlist_booking_list;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking_display_confirmed_booking_list;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.issue_ticket_display_confirmed_booking_list;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
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

public class Display_confirmed_and_waitlist_booking_list extends FrameworkConstants
{
    public static String SOAPRequest;
    public static String PNR1;
    public static String PNR2;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        //        PreRequisite for Scenario ------> Create Booking

        create_booking1_display_confirmed_and_waitlist_booking_list Prerequisite = new create_booking1_display_confirmed_and_waitlist_booking_list();
        Prerequisite.run(); //Confirmed Booking
        ExtentLogger.info("Prerequisite");

        create_booking2_display_confirmed_and_waitlist_booking_list Prerequisite2 = new create_booking2_display_confirmed_and_waitlist_booking_list();
        Prerequisite2.run();//Waitlist Booking
        ExtentLogger.info("Prerequisite2");

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getDisplaybookingservice());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .body(SOAPRequest)
                .when()
                .post(getDisplaybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Display_confirmed_and_waitlist_booking_list.xml"));
        writer.write(response.asPrettyString());
        writer.close();


        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("AirReservation BookingReferenceID=\""+PNR1+"\""), "Does not contain \"AirReservation BookingReferenceID=\""+PNR1+"\" in the response");
        ExtentLogger.info("Assertion passed - contains \"AirReservation BookingReferenceID=\""+PNR1+"\"");

        Assert.assertTrue(response.getBody().asString().contains("AirReservation BookingReferenceID=\""+PNR2+"\""), "Does not contain \"AirReservation BookingReferenceID=\""+PNR2+"\" in the response");
        ExtentLogger.info("Assertion passed - contains \"AirReservation BookingReferenceID=\""+PNR2+"\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - do not have warning");

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
        XSSFSheet sheet = wb.getSheet("DisplayBookingService");
        XSSFRow InputRow=sheet.getRow(5);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Display_confirmed_and_waitlist_booking_list.xml";

        XMLParser.SetTagtextatIndex("read:FlightNumber", InputRow.getCell(2).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("read:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("read:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("com:Surname", InputRow.getCell(20).getStringCellValue(),getTemp_requestPath(),0);

        PNR1 = InputRow.getCell(10).getStringCellValue();
        PNR2 = InputRow.getCell(15).getStringCellValue();

        wb.close();

    }

}
