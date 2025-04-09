package MODULES.WAVE3.ReferenceService.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
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

public class display_page_list extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getReferenceservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getReferenceservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "ReferenceService\\display_page_list.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("CM PAGE LIST      ABORDAJE    CAT:A01 SUB:B01"),
                "Do not contain CM PAGE LIST      ABORDAJE    CAT:A01 SUB:B01");
        ExtentLogger.info("Assertion passed - contains CM PAGE LIST      ABORDAJE    CAT:A01 SUB:B01");

        Assert.assertTrue(response.getBody().asString().contains("1 C01 TIEMPOS ABORDAJE"),
                "Do not contain 1 C01 TIEMPOS ABORDAJE");
        ExtentLogger.info("Assertion passed - contains 1 C01 TIEMPOS ABORDAJE");

        Assert.assertTrue(response.getBody().asString().contains("2 C02 ASIGNACION ASIENTOS"),
                "Do not contain 2 C02 ASIGNACION ASIENTOS");
        ExtentLogger.info("Assertion passed - contains 2 C02 ASIGNACION ASIENTOS");

        Assert.assertTrue(response.getBody().asString().contains("3 C03 ABORDAJE WCI"),
                "Do not contain 3 C03 ABORDAJE WCI");
        ExtentLogger.info("Assertion passed - contains 3 C03 ABORDAJE WCI");

        Assert.assertTrue(response.getBody().asString().contains("4 C04 REPORTE ABORDAJE"),
                "Do not contain 4 C04 REPORTE ABORDAJE");
        ExtentLogger.info("Assertion passed - contains 4 C04 REPORTE ABORDAJE");

        Assert.assertTrue(response.getBody().asString().contains("5 C05 REACOM.PESO/BALANCE"),
                "Do not contain 5 C05 REACOM.PESO/BALANCE");
        ExtentLogger.info("Assertion passed - contains 5 C05 REACOM.PESO/BALANCE");

        Assert.assertTrue(response.getBody().asString().contains("6 C06 NECESIDADES ABORDAJE"),
                "Do not contain 6 C06 NECESIDADES ABORDAJE");
        ExtentLogger.info("Assertion passed - contains 6 C06 NECESIDADES ABORDAJE");

        Assert.assertTrue(response.getBody().asString().contains("7 C07 VUELOS AREA REMOTA"),
                "Do not contain 7 C07 VUELOS AREA REMOTA");
        ExtentLogger.info("Assertion passed - contains 7 C07 VUELOS AREA REMOTA");

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
        XSSFSheet sheet = wb.getSheet("ReferenceService");
        XSSFRow InputRow = sheet.getRow(4);

        String filepath1;
        filepath1 = getRequestDirectory() + "ReferenceService\\display_page_list.xml";

        XMLParser.updateAttributeValue("com:Source", "AirlineVendorID", InputRow.getCell(1).getStringCellValue(), filepath1);
        XMLParser.updateAttributeValue("eds:ReferenceRequest", "Category", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("eds:ReferenceRequest", "Subject", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());

        wb.close();

    }

}
