package MODULES.WAVE3.DisplayBookingService.API_Tests;


import GENERICS.Assertions;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking_display_a_host_airline_booking;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking_display_booking_by_both_record_locator_and_eticket_number;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.issue_ticket_display_booking_by_both_record_locator_and_eticket_number;
import MODULES.WAVE3.TicketingService.PreRequisites.issue_ticket_reissue_add_collect_with_credit_card;
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

public class Display_booking_by_both_record_locator_and_eticket_number extends FrameworkConstants
{
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        //        PreRequisite for Scenario ------> Create Booking
    int i=0;
    boolean flightFound=false;

//We are searching all the available flights in a do while loop
        create_booking_display_booking_by_both_record_locator_and_eticket_number Prerequisite = new create_booking_display_booking_by_both_record_locator_and_eticket_number();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite");


        issue_ticket_display_booking_by_both_record_locator_and_eticket_number Prerequisite2 = new issue_ticket_display_booking_by_both_record_locator_and_eticket_number();
        Prerequisite2.run();
        ExtentLogger.info("Prerequisite2");

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


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Display_booking_by_both_record_locator_and_eticket_number.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("OriginDestinationOption"), "Does not contain \"OriginDestinationOption\" in the response");
        ExtentLogger.info("Assertion passed - contains \"OriginDestinationOption\"");

        Assert.assertTrue(response.getBody().asString().contains("PriceInfo"), "Does not contain \"PriceInfo\" in the response");
        ExtentLogger.info("Assertion passed - contains \"PriceInfo\"");

        Assert.assertTrue(response.getBody().asString().contains("TravelerInfo"), "Does not contain \"TravelerInfo\" in the response");
        ExtentLogger.info("Assertion passed - contains \"TravelerInfo\"");

        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID"), "Does not contain \"BookingReferenceID\" in the response");
        ExtentLogger.info("Assertion passed - contains \"BookingReferenceID\"");

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
        XSSFRow InputRow=sheet.getRow(2);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Display_booking_by_both_record_locator_and_eticket_number.xml";

        XMLParser.updateAttributeValueatIndex("read:UniqueID", "ID", InputRow.getCell(10).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("read:TicketNumber", "TicketDocumentNbr", InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }

}
