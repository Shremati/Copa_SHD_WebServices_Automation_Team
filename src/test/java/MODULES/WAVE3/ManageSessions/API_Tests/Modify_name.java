package MODULES.WAVE3.ManageSessions.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import MODULES.WAVE3.ManageSessions.PreRequisites.Create_Booking;
import MODULES.WAVE3.ManageSessions.PreRequisites.Modify_Booking;
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

public class Modify_name extends FrameworkConstants {
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        Create_Booking Prerequisite1 = new Create_Booking();

        ExtentLogger.info("Prerequisite 1");
        Prerequisite1.run();//Creating 3 pax RPH=1,2,3

        Modify_Booking Prerequisite2 = new Modify_Booking(); //We are changing/modifying the names of above 3 pax

        ExtentLogger.info("Prerequisite 2");
        Prerequisite2.run();

        UpdatePayload();


//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getModifybookingservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getModifybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "ManageSessions\\Modify_name.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),
                "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("LISA"),
                "Do not contain LISA");
        ExtentLogger.info("Assertion passed - contains LISA");

        Assert.assertTrue(response.getBody().asString().contains("GIRLFRIEND"),
                "Do not contain GIRLFRIEND");
        ExtentLogger.info("Assertion passed - contains GIRLFRIEND");

        Assert.assertTrue(response.getBody().asString().contains("MINI"),
                "Do not contain MINI");
        ExtentLogger.info("Assertion passed - contains MINI");

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
        XSSFSheet sheet = wb.getSheet("ManageSessions");
        XSSFRow InputRow = sheet.getRow(1);

        String filepath1;

        filepath1 = getRequestDirectory() + "ManageSessions\\Modify_name.xml";

        XMLParser.updateAttributeValueatIndex("air:OTA_AirBookModifyRQ", "TransactionIdentifier", InputRow.getCell(3).getStringCellValue(), filepath1, 0);

        wb.close();

    }


}
