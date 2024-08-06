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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class display_data_page extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getReferenceservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .when()
                .post(getReferenceservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "ReferenceService\\display_data_page.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("CM DATA PAGE      TIEMPOS ABORDAJE      CAT:A01 SUB:B01 PGE:C01"),
                "Do not contain CM DATA PAGE      TIEMPOS ABORDAJE      CAT:A01 SUB:B01 PGE:C01");
        ExtentLogger.info("Assertion passed - contains CM DATA PAGE TIEMPOS ABORDAJE CAT:A01 SUB:B01 PGE:C01");

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
        XSSFRow InputRow = sheet.getRow(8);

        String filepath1;
        filepath1 = getRequestDirectory() + "ReferenceService\\display_data_page.xml";

        XMLParser.updateAttributeValue("com:Source", "AirlineVendorID", InputRow.getCell(1).getStringCellValue(), filepath1);
        XMLParser.updateAttributeValue("eds:ReferenceRequest", "Category", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("eds:ReferenceRequest", "Subject", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("eds:ReferenceRequest", "Page", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath());

        wb.close();

    }
}
