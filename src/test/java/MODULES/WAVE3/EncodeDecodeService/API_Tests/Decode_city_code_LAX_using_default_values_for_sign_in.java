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

public class Decode_city_code_LAX_using_default_values_for_sign_in extends FrameworkConstants {

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "EncodeDecodeService\\Decode_city_code_LAX_using_default_values_for_sign_in.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("ConversionRequest=\"lax\""),
                "Do not contain ConversionRequest=\"lax\"");
        ExtentLogger.info("Assertion passed - contains ConversionRequest=\"lax\"");

        Assert.assertTrue(response.getBody().asString().contains("CityAirportConversion"),
                "Do not contain CityAirportConversion");
        ExtentLogger.info("Assertion passed - contains CityAirportConversion");

        Assert.assertTrue(response.getBody().asString().contains("<CityName>LOS ANGELES</CityName>"),
                "Do not contain <CityName>LOS ANGELES</CityName>");
        ExtentLogger.info("Assertion passed - contains <CityName>LOS ANGELES</CityName>");

        Assert.assertTrue(response.getBody().asString().contains("<CountryName>UNITED STATES OF AMERICA</CountryName>"),
                "Do not contain <CountryName>UNITED STATES OF AMERICA</CountryName>");
        ExtentLogger.info("Assertion passed - contains <CountryName>UNITED STATES OF AMERICA</CountryName>");

        Assertions.AssertWarning(response, false);
        ExtentLogger.info("Assertion passed - Do not have warnings");

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
        XSSFRow InputRow = sheet.getRow(12);

        String filepath1;
        filepath1 = getRequestDirectory() + "EncodeDecodeService\\Decode_city_code_LAX_using_default_values_for_sign_in.xml";


        XMLParser.SetTagtextatIndex("con:CityAirportConversion", InputRow.getCell(3).getStringCellValue(), filepath1, 0);

        wb.close();

    }

}
