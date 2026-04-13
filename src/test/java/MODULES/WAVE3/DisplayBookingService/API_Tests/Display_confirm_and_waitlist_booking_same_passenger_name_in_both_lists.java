package MODULES.WAVE3.DisplayBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.Create_Booking_1_Waitlist_Booking_multiple_name_entries_in_list_on_same_booking;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.Create_Booking_2_Waitlist_Booking_multiple_name_entries_in_list_on_same_booking;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.Create_booking_1_Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.Create_booking_2_Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists extends FrameworkConstants {

    public static String SOAPRequest;
    public static String PNR1;
    public static String PNR2;
    static RequestSpecification requestSpecification;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

//        Create_booking_1_Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists Prerequisite = new Create_booking_1_Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists();
//        Prerequisite.run();  //Confirmed Booking
//        ExtentLogger.info("Prerequisite");

        int i=0;
        boolean flightFound=false;

//We are searching all the available flights in a do while loop
        Create_booking_1_Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists Prerequisite = new Create_booking_1_Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite1");

        Create_booking_2_Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists Prerequisite1 = new Create_booking_2_Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists();

        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite1.run(i++);

        }while(!flightFound);


        ExtentLogger.info("Prerequisite1");

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getDisplaybookingservice());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getDisplaybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

//        Assert.assertTrue(response.getBody().asString().contains("AirReservation BookingReferenceID=\""+PNR1+"\""), "Does not contain \"AirReservation BookingReferenceID=\""+PNR1+"\" in the response");
//        ExtentLogger.info("Assertion passed - contains \"AirReservation BookingReferenceID=\""+PNR1+"\"");

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
        XSSFRow InputRow=sheet.getRow(31);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Display_confirm_and_waitlist_booking_same_passenger_name_in_both_lists.xml";

        XMLParser.SetTagtext("read:FlightNumber", InputRow.getCell(6).getStringCellValue(),filepath1);
        XMLParser.SetTagtext("read:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(5).getNumericCellValue()),getTemp_requestPath() );
        XMLParser.updateAttributeValue("read:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());

        // PNR1 = InputRow.getCell(10).getStringCellValue();
        PNR2 = InputRow.getCell(15).getStringCellValue();
        wb.close();

    }

}
