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

public class display_subject_list extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getAuthorizationservice());

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "ReferenceService\\display_subject_list.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("CM SUBJECT LIST    POLITICAS ATO/SAB    CAT:A01"),
                "DO not contain CM SUBJECT LIST    POLITICAS ATO/SAB    CAT:A01");
        ExtentLogger.info("Assertion passed - contains CM SUBJECT LIST    POLITICAS ATO/SAB    CAT:A01");

        Assert.assertTrue(response.getBody().asString().contains("1 B01 ABORDAJE"),
                "Do not contain 1 B01 ABORDAJE");
        ExtentLogger.info("Assertion passed - contains 1 B01 ABORDAJE");

        Assert.assertTrue(response.getBody().asString().contains("2 B03 CHECK-IN"),
                "Do not contain 2 B03 CHECK-IN");
        ExtentLogger.info("Assertion passed - contains 2 B03 CHECK-IN");

        Assert.assertTrue(response.getBody().asString().contains("3 B04 SERVICIO A BORDO"),
                "Do not contain 3 B04 SERVICIO A BORDO");
        ExtentLogger.info("Assertion passed - contains 3 B04 SERVICIO A BORDO");

        Assert.assertTrue(response.getBody().asString().contains("4 B05 ANUNCIOS"),
                "Do not contain 4 B05 ANUNCIOS");
        ExtentLogger.info("Assertion passed - contains 4 B05 ANUNCIOS");

        Assert.assertTrue(response.getBody().asString().contains("5 B06 GENERAL-IROPS"),
                "Do not contain 5 B06 GENERAL-IROPS");
        ExtentLogger.info("Assertion passed - contains 5 B06 GENERAL-IROPS");

        Assert.assertTrue(response.getBody().asString().contains("6 B07 SERVICIO-IROPS"),
                "Do not contain 6 B07 SERVICIO-IROPS");
        ExtentLogger.info("Assertion passed - contains 6 B07 SERVICIO-IROPS");

        Assert.assertTrue(response.getBody().asString().contains("7 B08 SOBREVENTA-IROPS"),
                "Do not contain 7 B08 SOBREVENTA-IROPS");
        ExtentLogger.info("Assertion passed - contains 7 B08 SOBREVENTA-IROPS");

        Assert.assertTrue(response.getBody().asString().contains("8 B09 COMPENSACIONES-IROPS"),
                "Do not contain 8 B09 COMPENSACIONES-IROPS");
        ExtentLogger.info("Assertion passed - contains 8 B09 COMPENSACIONES-IROPS");

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
        XSSFRow InputRow = sheet.getRow(3);

        String filepath1;
        filepath1 = getRequestDirectory() + "ReferenceService\\display_subject_list.xml";

        XMLParser.updateAttributeValue("com:Source", "AirlineVendorID", InputRow.getCell(1).getStringCellValue(), filepath1);
        XMLParser.updateAttributeValue("eds:ReferenceRequest", "Category", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());

        wb.close();

    }

}
