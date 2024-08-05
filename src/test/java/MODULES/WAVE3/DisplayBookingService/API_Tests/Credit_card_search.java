package MODULES.WAVE3.DisplayBookingService.API_Tests;


import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking_credit_card_search;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking_display_confirmed_booking_list;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.issue_ticket_credit_card_search;
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

public class Credit_card_search extends FrameworkConstants
{
    public static String SOAPRequest;
    public static String PNR;
    static RequestSpecification requestSpecification;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        //        PreRequisite for Scenario ------> Create Booking

        create_booking_credit_card_search Prerequisite = new create_booking_credit_card_search();
        Prerequisite.run();
        ExtentLogger.info("Prerequisite");

        issue_ticket_credit_card_search Prerequisite2 = new issue_ticket_credit_card_search();
        Prerequisite2.run();
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


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Credit_card_search.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Errors"), "Does not contain \"Errors\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Errors\"");

        Assert.assertTrue(response.getBody().asString().contains("Invalid Request. Cannot determine search type"), "Does not contain \"Invalid Request. Cannot determine search type\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Invalid Request. Cannot determine search type\"");

        //  Assert.assertTrue(response.getBody().asString().contains("AirReservation BookingReferenceID=\""+PNR+"\""));

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
        XSSFRow InputRow=sheet.getRow(9);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Credit_card_search.xml";

        XMLParser.updateAttributeValueatIndex("read:CreditCardInfo", "CardNumber", InputRow.getCell(18).getStringCellValue(),filepath1,0);
        PNR = InputRow.getCell(10).getStringCellValue();

        wb.close();

    }

}
