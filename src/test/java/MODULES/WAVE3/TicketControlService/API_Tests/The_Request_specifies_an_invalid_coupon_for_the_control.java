package MODULES.WAVE3.TicketControlService.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import MODULES.WAVE3.AirportPassengerList.PreRequisites.Create_Booking_with_4_PAX_36;
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

public class The_Request_specifies_an_invalid_coupon_for_the_control extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        int i=0;
        boolean flightFound=false;

        //We are searching all the available flights in a do while loop
        Create_Booking_The_Request_specifies_an_invalid_coupon_for_the_control Prerequisite1 = new Create_Booking_The_Request_specifies_an_invalid_coupon_for_the_control();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite1.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite 1");
//        Create_Booking_The_Request_specifies_an_invalid_coupon_for_the_control Prerequisite = new Create_Booking_The_Request_specifies_an_invalid_coupon_for_the_control();
//        Prerequisite.run();

        ExtentLogger.info("Prerequisite 2");
        issue_ticket_The_Request_specifies_an_invalid_coupon_for_the_control Prerequisite2 = new issue_ticket_The_Request_specifies_an_invalid_coupon_for_the_control();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 3");
        display_ticket_The_Request_specifies_an_invalid_coupon_for_the_control Prerequisite3 = new display_ticket_The_Request_specifies_an_invalid_coupon_for_the_control();
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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"TicketControlService\\The_Request_specifies_an_invalid_coupon_for_the_control.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Expected Success but not found");
        ExtentLogger.info("Assertion passed - contains Success");

        Assertions.AssertWarning(response,true);
        ExtentLogger.info("Assertion passed - do not have warning");

        Assert.assertTrue(response.getBody().asString().contains("INVLD  COUPON STATUS"),"Expected INVLD  COUPON STATUS, but not found");
        ExtentLogger.info("Assertion passed - contains INVLD  COUPON STATUS");

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
        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=getRequestDirectory()+"TicketControlService\\The_Request_specifies_an_invalid_coupon_for_the_control.xml";

        XMLParser.updateAttributeValueatIndex("tic1:TicketDocument","TicketDocumentNbr", InputRow.getCell(20).getStringCellValue(),filepath1,0);

        wb.close();

    }

}


