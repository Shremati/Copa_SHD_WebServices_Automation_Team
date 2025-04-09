package MODULES.WAVE3.Checkin.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.Checkin.PreRequisites.Add_APIS_one_pax_and_baggage;
import MODULES.WAVE3.Checkin.PreRequisites.Display_APIS_one_pax_and_baggage;
import MODULES.WAVE3.Checkin.PreRequisites.Issue_booking_one_pax_baggage;
import MODULES.WAVE3.Checkin.PreRequisites.create_booking_service_singlepax;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import frameworkconstants.*;
import reports.ExtentLogger;

public class checkin_one_pax_and_baggage extends FrameworkConstants
{
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        ExtentLogger.info("Prerequisite 1");
        create_booking_service_singlepax Prerequisite = new create_booking_service_singlepax();
        Prerequisite.run();

        ExtentLogger.info("Prerequisite 2");
        Issue_booking_one_pax_baggage Prerequisite1 = new Issue_booking_one_pax_baggage();
        Prerequisite1.run();

        ExtentLogger.info("Prerequisite 3");
        Display_APIS_one_pax_and_baggage Prerequisite2 = new Display_APIS_one_pax_and_baggage();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 4");
        Add_APIS_one_pax_and_baggage Prerequisite3  = new Add_APIS_one_pax_and_baggage();
        Prerequisite3.run();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getCheckin());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getCheckin())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Not contains \"Success\" in response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("SEATS ASSIGNED"),"Not contains \"SEATS ASSIGNED\" in response");
        ExtentLogger.info("Assertion passed - contains \"SEATS ASSIGNED\"");

        Assert.assertTrue(response.getBody().asString().contains("BaggageInfo"),"Not contains \"BaggageInfo\" in response");
        ExtentLogger.info("Assertion passed - contains \"BaggageInfo\"");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"Checkin\\checkin_one_pax_and_baggage.xml"));
        writer.write(response.asPrettyString());
        writer.close();

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
        XSSFSheet sheet = wb.getSheet("CheckIn");
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=getRequestDirectory()+"Checkin\\checkin_one_pax_and_baggage.xml";


        XMLParser.updateAttributeValue("com1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("com1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("com1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }
}
