package MODULES.WAVE3.Standby.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.Standby.Prerequisites.*;
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

public class STB_03_Clear_Standby extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        ExtentLogger.info("Prerequisite 1");
        Create_Booking_Non_Revenue_Pax Prerequisite1 = new Create_Booking_Non_Revenue_Pax();
        Prerequisite1.run();

        ExtentLogger.info("Prerequisite 2");
        Issue_ticket_non_revenue_pax Prerequisite2 = new Issue_ticket_non_revenue_pax();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 3");
        Display_Non_Revenue_pax Prerequisite3 = new Display_Non_Revenue_pax();
        Prerequisite3.run();

        ExtentLogger.info("Prerequisite 4");
        Modify_APIS_Non_Revenue_pax Prerequisite4 = new Modify_APIS_Non_Revenue_pax();
        Prerequisite4.run();

        ExtentLogger.info("Prerequisite 5");
        Check_in_Non_Revenue_pax Prerequisite5 = new Check_in_Non_Revenue_pax();
        Prerequisite5.run();

        ExtentLogger.info("Prerequisite 6");
        Start_Standby_clear_standby Prerequisite6 = new Start_Standby_clear_standby();
        Prerequisite6.run();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getStandby());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getStandby())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");
        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"Standby\\STB_03_Clear_Standby.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Not contains \"Success\" in response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("PassengerDetails"),"Not contains \"PassengerDetails\" in response");
        ExtentLogger.info("Assertion passed - contains \"PassengerDetails\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - Do not contain Warning");

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
        XSSFSheet sheet = wb.getSheet("Standby");
        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=getRequestDirectory()+"Standby\\STB_03_Clear_Standby.xml";

        XMLParser.updateAttributeValue("air1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air1:CarrierInfo","FlightNumber",InputRow.getCell(1).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:DepartureInformation","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:CarrierInfo","ResBookDesigCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }
}
