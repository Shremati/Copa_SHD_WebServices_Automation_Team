package MODULES.WAVE3.EMDAirlineSystemUpdate.API_Tests;


import GENERICS.Assertions;
import GENERICS.XMLParser;
import MODULES.WAVE3.EMDAirlineSystemUpdate.PreRequisites.create_booking_association_emd_coupon_1_with_etkt_coupon1_pos_info;
import MODULES.WAVE3.EMDAirlineSystemUpdate.PreRequisites.issue_ticket_association_emd_coupon_1_with_etkt_coupon1_pos_info;
import MODULES.WAVE3.ModifyTicketingService.PreRequisites.create_booking_void_a_ticket;
import MODULES.WAVE3.ModifyTicketingService.PreRequisites.issue_ticket_void_a_ticket;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Association_emd_coupon_1_with_etkt_coupon1_pos_info extends FrameworkConstants
{
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        //        PreRequisite for Scenario ------> Create Booking
        ExtentLogger.info("Prerequisite 1");
        create_booking_association_emd_coupon_1_with_etkt_coupon1_pos_info Prerequisite = new create_booking_association_emd_coupon_1_with_etkt_coupon1_pos_info();
        Prerequisite.run();

        //        PreRequisite for Scenario ------> Issue Ticket
        ExtentLogger.info("Prerequisite 2");
        issue_ticket_association_emd_coupon_1_with_etkt_coupon1_pos_info Prerequisite2 = new issue_ticket_association_emd_coupon_1_with_etkt_coupon1_pos_info();
        Prerequisite2.run();


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        ExtentLogger.info("Base URL : "+getBaseURL()+getEmdairlinesystemupdateservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getEmdairlinesystemupdateservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        Assert.assertTrue(response.getBody().asString().contains("<ns4:Success/>"),"Not contains \"Success\" in response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - Do not contain Warning");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"EMDAirlineSystemUpdate\\Association_emd_coupon_1_with_etkt_coupon1_pos_info.xml"));
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
        XSSFSheet sheet = wb.getSheet("EMDAirlineSystemUpdate");
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=getRequestDirectory()+"EMDAirlineSystemUpdate\\Association_emd_coupon_1_with_etkt_coupon1_pos_info.xml";

        XMLParser.updateAttributeValueatIndex("emd1:TicketDocument", "TicketDocumentNbr", InputRow.getCell(28).getStringCellValue(),filepath1,0);


        wb.close();

    }




}
