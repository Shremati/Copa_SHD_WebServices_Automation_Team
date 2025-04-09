package MODULES.WAVE3.EncodeDecodeService.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
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

public class Request_with_mixed_errors_and_correct_conversion_types extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getEncodedecodeservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .when()
                .post(getEncodedecodeservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "EncodeDecodeService\\Request_with_mixed_errors_and_correct_conversion_types.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("Warnings"), "Do not contain Warnings");
        ExtentLogger.info("Assertion passed - contains Warnings");

        Assert.assertTrue(response.getBody().asString().contains("Invalid country in message"),
                "Do not have Invalid country in message");
        ExtentLogger.info("Assertion passed - contains Invalid country in message");

        Assert.assertTrue(response.getBody().asString().contains("FR * FRANCE (2) FU    **NO MATCHING ITEM**"),
                "Do not have FR * FRANCE (2) FU    **NO MATCHING ITEM**");
        ExtentLogger.info("Assertion passed - contains FR * FRANCE (2) FU    **NO MATCHING ITEM**");

        Assert.assertTrue(response.getBody().asString().contains("Conversion"), "DO not contain Conversion");
        ExtentLogger.info("Assertion passed - contains Conversion");

        Assertions.AssertWarning(response, true);
        ExtentLogger.info("Assertion passed - Do not have Warning");

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
        XSSFSheet sheet = wb.getSheet("EncodeDecodeService");
        XSSFRow InputRow = sheet.getRow(10);

        String filepath1;
        filepath1 = getRequestDirectory() + "EncodeDecodeService\\Request_with_mixed_errors_and_correct_conversion_types.xml";


        XMLParser.SetTagtextatIndex("con:CountryConversion", InputRow.getCell(2).getStringCellValue(), filepath1, 0);
        XMLParser.SetTagtextatIndex("con:CountryConversion", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.SetTagtextatIndex("con:CountryConversion", InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(), 2);

        XMLParser.SetTagtextatIndex("con:CityAirportConversion", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.SetTagtextatIndex("con:CityAirportConversion", InputRow.getCell(7).getStringCellValue(), getTemp_requestPath(), 1);

        XMLParser.SetTagtextatIndex("con:AirlineConversion", InputRow.getCell(5).getStringCellValue(), getTemp_requestPath(), 0);


        wb.close();

    }


}
