package MODULES.WAVE3.FlifoService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
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

public class Required_data_missing_flight_number extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
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


        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("Flight number or departure time is required"), "Does not contain \"Flight number or departure time is required\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Flight number or departure time is required\"");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"FlifoService\\Required_data_missing_flight_number.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assertions.AssertWarning(response,true);
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
        XSSFRow InputRow=sheet.getRow(11);

        String filepath1;
        filepath1=getRequestDirectory()+"FlifoService\\Required_data_missing_flight_number.xml";

        XMLParser.updateAttributeValue("air:Airline","Code",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.SetTagtext("air:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()), getTemp_requestPath());
        XMLParser.updateAttributeValueatIndex("air:DepartureAirport", "LocationCode",InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("air:ArrivalAirport", "LocationCode", InputRow.getCell(7).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValue("air:OTA_AirFlifoRQ","TimeStamp", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());


        wb.close();

    }

}
