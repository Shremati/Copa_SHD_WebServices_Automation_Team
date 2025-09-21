package MODULES.WAVE3.TicketControlService.PreRequisites;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Pre_create_booking_missing_airline_code extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();
//                       ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getCreatebookingservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());

        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");
        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"TicketControlService\\Pre_create_booking_missing_airline_code.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Expected Success but not found");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID"),"Do not contain BookingReferenceID");
        ExtentLogger.info("Assertion passed - contains BookingReferenceID");

        Assert.assertTrue(response.getBody().asString().contains("Telephone"),"Do not contain Telephone");
        ExtentLogger.info("Assertion passed - contains Telephone");

        Assert.assertTrue(response.getBody().asString().contains("BaseFare"),"Do not contain BaseFare");
        ExtentLogger.info("Assertion passed - contains BaseFare");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - Do not contain Warning");

        Assertions.AssertResponseTime(response,ResponseTime);

        excelwriter();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("TicketControlService");

        XSSFRow InputRow=sheet.getRow(5);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\TicketControlService\\PreRequisites\\Pre_create_booking_missing_airline_code.xml";


        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 0);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(7).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(8).getStringCellValue(), getTemp_requestPath(), 1);

        XMLParser.updateAttributeValue("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(33).getNumericCellValue()), getTemp_requestPath());

        wb.close();
    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("TicketControlService");
        XSSFRow InputRow=sheet.getRow(5);

        String filepath;
        filepath = getResponseDirectory()+"TicketControlService\\Pre_create_booking_missing_airline_code.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);
        InputRow.getCell(9).setCellValue(PNR);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }
}
