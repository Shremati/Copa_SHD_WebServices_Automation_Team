package MODULES.WAVE3.DisplayTicketService.API_Tests;

import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Create_booking_conjunctive_tkt_conjunctive;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Issue_booking_conjunctive_tkt_conjunctive;
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

public class Conjunctive_ticket_conjunctive extends FrameworkConstants {

    public static String SOAPRequest;
    public static String Conjunctive_Ticket1;
    public static String Conjunctive_Ticket2;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Create_booking_conjunctive_tkt_conjunctive Prerequisite1 = new Create_booking_conjunctive_tkt_conjunctive();
        Prerequisite1.run();
        ExtentLogger.info("Prerequisite1");

        Issue_booking_conjunctive_tkt_conjunctive Prerequisite2 = new Issue_booking_conjunctive_tkt_conjunctive();
        Prerequisite2.run();
        ExtentLogger.info("Prerequisite2");


        UpdatePayload();


//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getDisplayticketservices());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getDisplayticketservices())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");


        //Getting ticketnumber from excelwriter
        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\"");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("<ns6:FormAndSerialNumber>" + Conjunctive_Ticket1 +"</ns6:FormAndSerialNumber>"), "Does not contain FormAndSerialNumber");
        ExtentLogger.info("Assertion passed - contains " + Conjunctive_Ticket1);

        Assert.assertTrue(response.getBody().asString().contains("<ns6:FormAndSerialNumber>" + Conjunctive_Ticket2 +"</ns6:FormAndSerialNumber>"), "Does not contain FormAndSerialNumber");
        ExtentLogger.info("Assertion passed - contains " + Conjunctive_Ticket2);

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayTicketService\\Conjunctive_ticket_conjunctive.xml"));
        writer.write(response.asPrettyString());
        writer.close();



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
        XSSFRow InputRow=sheet.getRow(9);

        String filepath1;

        filepath1=getRequestDirectory()+"DisplayTicketService\\Conjunctive_ticket_conjunctive.xml";

        String conjunctive1 = InputRow.getCell(9).getStringCellValue();
        String conjunctive2 = InputRow.getCell(10).getStringCellValue().substring(11,13);
        String TicketDocumentNbr = conjunctive1 + "-" + conjunctive2;

        XMLParser.updateAttributeValue("dis1:TicketDocument","TicketDocumentNbr",TicketDocumentNbr,filepath1);

        Conjunctive_Ticket1 = InputRow.getCell(9).getStringCellValue().substring(3,13);
        Conjunctive_Ticket2 = InputRow.getCell(10).getStringCellValue().substring(3,13);

        wb.close();

    }


}
