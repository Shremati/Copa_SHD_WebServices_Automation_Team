package MODULES.WAVE3.FlifoService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
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

public class Flifo_by_dept_time_one_flt_matches_dept_time extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getFlifo());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getFlifo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"FlifoService\\Flifo_by_dept_time_one_flt_matches_dept_time.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertFalse(response.getBody().asString().contains("Warnings"), "Does not contain \"Warnings\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Warnings\"");

        Assert.assertTrue(response.getBody().asString().contains("FlightInfoDetails"), "Does not contain \"FlightInfoDetails\" in the response");
        ExtentLogger.info("Assertion passed - contains \"FlightInfoDetails\"");

        Assert.assertTrue(response.getBody().asString().contains("FlightLegInfo"), "Does not contain \"FlightLegInfo\" in the response");
        ExtentLogger.info("Assertion passed - contains \"FlightLegInfo\"");

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
        XSSFSheet sheet = wb.getSheet("FlifoService");
        XSSFRow InputRow=sheet.getRow(9);

        String filepath1;
        filepath1=getRequestDirectory()+"FlifoService\\Flifo_by_dept_time_one_flt_matches_dept_time.xml";

        XMLParser.updateAttributeValue("com:Source","AirlineVendorID",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.SetTagtext("air:FlightNumber", InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValueatIndex("air:DepartureDate", "DepartureTime", Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air:DepartureAirport", "LocationCode",InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("air:ArrivalAirport", "LocationCode", InputRow.getCell(7).getStringCellValue(), getTemp_requestPath(), 0);


        wb.close();

    }

}
