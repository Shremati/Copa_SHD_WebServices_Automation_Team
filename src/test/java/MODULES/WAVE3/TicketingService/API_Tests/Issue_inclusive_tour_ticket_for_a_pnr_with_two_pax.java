package MODULES.WAVE3.TicketingService.API_Tests;


import GENERICS.Assertions;
import GENERICS.XMLParser;
import MODULES.WAVE3.TicketingService.PreRequisites.create_booking_issue_inclusive_tour_ticket_for_a_pnr_with_two_pax;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Issue_inclusive_tour_ticket_for_a_pnr_with_two_pax extends FrameworkConstants {
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        create_booking_issue_inclusive_tour_ticket_for_a_pnr_with_two_pax Prerequisite = new create_booking_issue_inclusive_tour_ticket_for_a_pnr_with_two_pax();
        Prerequisite.run();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getTicketing());

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "TicketingService\\Issue_inclusive_tour_ticket_for_a_pnr_with_two_pax.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        if (response.getBody().asString().contains("NumberOfTickets=\"0001\"")) {
            System.out.println("\nTicketing status: Fully ticketed");
        } else {
            System.out.println("\nTicketing status: Partially ticketed");
        }
        Assert.assertTrue(response.getBody().asString().contains("NumberOfTickets=\"0001\""),
                "Do not contain NumberOfTickets=\"0001\"");
        ExtentLogger.info("Assertion passed - contains NumberOfTickets=\"0001\"");

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("FareAmountType=\"IT\""),
                "Do not contain FareAmountType=\"IT\"");
        ExtentLogger.info("Assertion passed - contains FareAmountType=\"IT\"");

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
        XSSFRow InputRow = sheet.getRow(6);

        String filepath1;
        filepath1 = getRequestDirectory() + "TicketingService\\Issue_inclusive_tour_ticket_for_a_pnr_with_two_pax.xml";

        XMLParser.SetTagtextatIndex("tic1:RecordLocator", InputRow.getCell(10).getStringCellValue(), filepath1, 0);


        wb.close();

    }


}
