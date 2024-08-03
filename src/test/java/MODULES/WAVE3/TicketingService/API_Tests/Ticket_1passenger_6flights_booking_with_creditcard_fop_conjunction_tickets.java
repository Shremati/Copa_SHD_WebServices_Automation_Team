package MODULES.WAVE3.TicketingService.API_Tests;


import GENERICS.Assertions;
import GENERICS.XMLParser;
import MODULES.WAVE3.TicketingService.PreRequisites.create_booking_issue_ticket_for_a_booking_with_an_infant;
import MODULES.WAVE3.TicketingService.PreRequisites.create_booking_ticket_1passenger_6flights_booking_with_creditcard_fop_conjunction_tickets;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Ticket_1passenger_6flights_booking_with_creditcard_fop_conjunction_tickets extends FrameworkConstants {
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        create_booking_ticket_1passenger_6flights_booking_with_creditcard_fop_conjunction_tickets Prerequisite = new create_booking_ticket_1passenger_6flights_booking_with_creditcard_fop_conjunction_tickets();
        Prerequisite.run();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getAuthorizationservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .when()
                .post(getTicketing())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "TicketingService\\Ticket_1passenger_6flights_booking_with_creditcard_fop_conjunction_tickets.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),
                "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("TicketInfo TicketNumber"),
                "DO not contain TicketInfo TicketNumber");
        ExtentLogger.info("Assertion passed - contains TicketInfo TicketNumber");

        Assert.assertTrue(response.getBody().asString().contains("ConjunctionNumber"),
                "Do not contain ConjunctionNumber");
        ExtentLogger.info("Assertion passed - contains ConjunctionNumber");

        Assertions.AssertWarning(response, false);
        ExtentLogger.info("Assertion passed - Do not have warning");

        Assertions.AssertResponseTime(response, ResponseTime);

//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("TicketingService");
        XSSFRow InputRow = sheet.getRow(2);

        String filepath1;
        filepath1 = getRequestDirectory() + "TicketingService\\Ticket_1passenger_6flights_booking_with_creditcard_fop_conjunction_tickets.xml";

        XMLParser.SetTagtextatIndex("tic1:RecordLocator", InputRow.getCell(10).getStringCellValue(), filepath1, 0);


        wb.close();

    }

}
