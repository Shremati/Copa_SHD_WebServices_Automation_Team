package MODULES.WAVE3.TicketControlService.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import MODULES.WAVE3.TicketControlService.PreRequisites.*;
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

public class Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control extends FrameworkConstants
{
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
//        int i=0;
//        boolean flightFound=false;

        Create_booking_Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control Prerequisite = new Create_booking_Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control();
        Prerequisite.run();
        //        do{
//            if(i > 3){
//                Assert.fail("No flights are having seats");
//            }
//            flightFound = Prerequisite.run(i++);
//
//        }while(!flightFound);
        ExtentLogger.info("Prerequisite 1");

        ExtentLogger.info("Prerequisite 2");
        Issue_ticket_Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control Prerequisite2 = new Issue_ticket_Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 3");
        Display_ticket_Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control Prerequisite3 = new Display_ticket_Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control();
        Prerequisite3.run();


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getTicketcontroloservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification.body(SOAPRequest)
                .when()
                .post(getTicketcontroloservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"TicketControlService\\Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Expected Success but not found");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("PARTNER ALREADY HAS CONTROL"),"Do not contains PARTNER ALREADY HAS CONTROL");
        ExtentLogger.info("Assertion passed - contains PARTNER ALREADY HAS CONTROL");

        Assertions.AssertWarning(response,true);
        ExtentLogger.info("Assertion passed - response has warning");

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
        XSSFSheet sheet = wb.getSheet("TicketControlService");
        XSSFRow InputRow=sheet.getRow(12);

        String filepath1;
        filepath1=getRequestDirectory()+"TicketControlService\\Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control.xml";

        XMLParser.updateAttributeValueatIndex("tic1:TicketDocument","TicketDocumentNbr", InputRow.getCell(20).getStringCellValue(),filepath1,0);

        wb.close();

    }

}
