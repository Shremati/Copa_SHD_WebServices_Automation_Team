package MODULES.WAVE3.DisplayTicketService.API_Tests;

import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Booking_bulk_ticket;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Booking_multiple_tickets;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Issue_bulk_ticket;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Issue_multiple_tickets;
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

public class Bulk_Ticket extends FrameworkConstants
{

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Booking_bulk_ticket Prerequisite1 = new Booking_bulk_ticket();
        Prerequisite1.run();
        ExtentLogger.info("Prerequisite1");

        Issue_bulk_ticket Prerequisite2 = new Issue_bulk_ticket();
        Prerequisite2.run();
        ExtentLogger.info("Prerequisite2");

        UpdatePayload();


//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getDisplayticketservices());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .body(SOAPRequest)
                .when()
                .post(getDisplayticketservices())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter
                (new FileWriter(getResponseDirectory()+"DisplayTicketService\\Multiple_Tickets.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("FareAmountType=\"Bulk\""), "Does not contain FareAmountType=\"Bulk\"");
        ExtentLogger.info("Assertion passed - contains FareAmountType=\"Bulk\"");


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
        XSSFSheet sheet = wb.getSheet("DisplayTicketService");
        XSSFRow InputRow=sheet.getRow(5);

        String filepath1;

        filepath1=getRequestDirectory()+"DisplayTicketService\\Bulk_Ticket.xml";

        XMLParser.updateAttributeValueatIndex("dis1:TicketDocument","TicketDocumentNbr",InputRow.getCell(9).getStringCellValue(),filepath1,0);

        wb.close();

    }



}
