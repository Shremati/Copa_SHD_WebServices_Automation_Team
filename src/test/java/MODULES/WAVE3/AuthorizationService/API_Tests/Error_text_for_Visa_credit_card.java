package MODULES.WAVE3.AuthorizationService.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.xwpf.usermodel.Document;
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

public class Error_text_for_Visa_credit_card extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        ExtentLogger.info("Base URL : "+getBaseURL()+getAuthorizationservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getAuthorizationservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "AuthorizationService\\Error_text_for_Visa_credit_card.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),
                "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("AuthorizationCode"),
                "Do not contain AuthorizationCode");
        ExtentLogger.info("Assertion passed - contains AuthorizationCode");

        Assert.assertTrue(response.getBody().asString().contains("INVALID FORMAT"),
                "Do not contain INVALID FORMAT");
        ExtentLogger.info("Assertion passed - contains INVALID FORMAT");

        Assertions.AssertWarning(response, true);
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
        XSSFSheet sheet = wb.getSheet("AuthorizationService");
        XSSFRow InputRow = sheet.getRow(2);

        String filepath1;
        filepath1 = getRequestDirectory() + "AuthorizationService\\Error_text_for_Visa_credit_card.xml";


        XMLParser.updateAttributeValueatIndex("air:CreditCardAuthorization", "Amount", InputRow.getCell(1).getStringCellValue(), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("air:CreditCardAuthorization", "CurrencyCode", InputRow.getCell(2).getStringCellValue(), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("air:CreditCard", "CardCode", InputRow.getCell(3).getStringCellValue(), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("air:CreditCard", "CardNumber", InputRow.getCell(4).getStringCellValue(), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("air:CreditCard", "ExpireDate", InputRow.getCell(5).getStringCellValue(), filepath1, 0);


        wb.close();

    }
}
