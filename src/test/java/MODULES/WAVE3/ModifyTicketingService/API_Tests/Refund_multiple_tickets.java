package MODULES.WAVE3.ModifyTicketingService.API_Tests;


import GENERICS.Assertions;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.ModifyTicketingService.PreRequisites.*;
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

public class Refund_multiple_tickets extends FrameworkConstants
{
    public static String SOAPRequest;
    public static String TicketNumber_1;
    public static String TicketNumber_2;
    public static String PNR;
    static RequestSpecification requestSpecification;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        int i=0;
        boolean flightFound=false;

        create_booking_refund_multiple_tickets Prerequisite = new create_booking_refund_multiple_tickets();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite");


        issue_ticket_refund_multiple_tickets Prerequisite2 = new issue_ticket_refund_multiple_tickets();
        Prerequisite2.run();
        ExtentLogger.info("Prerequisite2");

        modify_ticket_refund_multiple_tickets Prerequisite3 = new modify_ticket_refund_multiple_tickets();
        Prerequisite3.run();
        ExtentLogger.info("Prerequisite3");


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getModifyticketingservice());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getModifyticketingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"ModifyTicketingService\\Refund_multiple_tickets.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("<ModifyResult TicketNumber=\""+TicketNumber_1+"\" RecordLocator=\""+PNR+"\" RequestType=\"Refund\">"),
                "Does not contain \"ModifyResult TicketNumber\" in the response");
        ExtentLogger.info("Assertion passed - contains \"ModifyResult TicketNumber\"");

        Assert.assertTrue(response.getBody().asString().contains("<ModifyResult TicketNumber=\""+TicketNumber_2+"\" RecordLocator=\""+PNR+"\" RequestType=\"Refund\">"),
                "Does not contain \"ModifyResult TicketNumber\" in the response");
        ExtentLogger.info("Assertion passed - contains \"ModifyResult TicketNumber\"");


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
        XSSFSheet sheet = wb.getSheet("ModifyTicketingService");
        XSSFRow InputRow=sheet.getRow(2);

        String filepath1;
        filepath1=getRequestDirectory()+"ModifyTicketingService\\Refund_multiple_tickets.xml";

//        Updating Ticket Numbers
        XMLParser.SetTagtextatIndex("air:TicketNumber", InputRow.getCell(16).getStringCellValue(),filepath1,0);
        XMLParser.SetTagtextatIndex("air:TicketNumber", InputRow.getCell(17).getStringCellValue(),getTemp_requestPath(),1);

//        Updating PNR
        XMLParser.updateAttributeValueatIndex("air:RecordLocator","ID", InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),0);

       TicketNumber_1 = InputRow.getCell(16).getStringCellValue();
       TicketNumber_2 = InputRow.getCell(17).getStringCellValue();
       PNR = InputRow.getCell(10).getStringCellValue();

        wb.close();

    }

}
