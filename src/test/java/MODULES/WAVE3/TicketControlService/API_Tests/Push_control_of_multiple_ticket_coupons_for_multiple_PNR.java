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

public class Push_control_of_multiple_ticket_coupons_for_multiple_PNR extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        int i=0;
        boolean flightFound=false;

        Create_booking_Push_control_of_multiple_ticket_coupons_for_multiple_PNR1 Prerequisite = new Create_booking_Push_control_of_multiple_ticket_coupons_for_multiple_PNR1();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite.run(i++);

        }while(!flightFound);
        ExtentLogger.info("Prerequisite 1");

        ExtentLogger.info("Prerequisite 2");
        issue_ticket_Push_control_of_multiple_ticket_coupons_for_multiple_PNR1 Prerequisite2 = new issue_ticket_Push_control_of_multiple_ticket_coupons_for_multiple_PNR1();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 3");
        display_ticket_Push_control_of_multiple_ticket_coupons_for_multiple_PNR1 Prerequisite3 = new display_ticket_Push_control_of_multiple_ticket_coupons_for_multiple_PNR1();
        Prerequisite3.run();

        Create_booking_Push_control_of_multiple_ticket_coupons_for_multiple_PNR2 Prerequisite4 = new Create_booking_Push_control_of_multiple_ticket_coupons_for_multiple_PNR2();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite4.run(i++);

        }while(!flightFound);
        ExtentLogger.info("Prerequisite 4");

        ExtentLogger.info("Prerequisite 5");
        issue_ticket_Push_control_of_multiple_ticket_coupons_for_multiple_PNR2 Prerequisite5 = new issue_ticket_Push_control_of_multiple_ticket_coupons_for_multiple_PNR2();
        Prerequisite5.run();

        ExtentLogger.info("Prerequisite 6");
        display_ticket_Push_control_of_multiple_ticket_coupons_for_multiple_PNR2 Prerequisite6 = new display_ticket_Push_control_of_multiple_ticket_coupons_for_multiple_PNR2();
        Prerequisite6.run();

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"TicketControlService\\Push_control_of_multiple_ticket_coupons_for_multiple_PNR.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Expected Success but not found");
        ExtentLogger.info("Assertion passed - contains Success");

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
        XSSFSheet sheet = wb.getSheet("TicketControlService");
        XSSFRow InputRow=sheet.getRow(10);

        String filepath1;
        filepath1=getRequestDirectory()+"TicketControlService\\Push_control_of_multiple_ticket_coupons_for_multiple_PNR.xml";

        XMLParser.updateAttributeValueatIndex("tic1:TicketDocument","TicketDocumentNbr", InputRow.getCell(20).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("tic1:TicketDocument","TicketDocumentNbr", InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("tic1:TicketDocument","TicketDocumentNbr", InputRow.getCell(23).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("tic1:TicketDocument","TicketDocumentNbr", InputRow.getCell(24).getStringCellValue(),getTemp_requestPath(),3);

        wb.close();

    }

}



