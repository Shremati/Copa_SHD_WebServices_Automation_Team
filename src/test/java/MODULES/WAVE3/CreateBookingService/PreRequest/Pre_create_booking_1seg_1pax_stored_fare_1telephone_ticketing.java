package MODULES.WAVE3.CreateBookingService.PreRequest;

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
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        UpdatePayload();
//                       ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getCreatebookingservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "CreateBookingService\\Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),
                "Do not contain Success");
        ExtentLogger.info("Assertion passed - contain Success");

        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID"),
                "Do not contain BookingReferenceID");
        ExtentLogger.info("Assertion passed - contain BookingReferenceID");

        Assert.assertTrue(response.getBody().asString().contains("Telephone"),
                "Do not contain Telephone");
        ExtentLogger.info("Assertion passed - contain Telephone");

        Assert.assertTrue(response.getBody().asString().contains("BaseFare"),
                "Do not contain BaseFare");
        ExtentLogger.info("Assertion passed - contain BaseFare");

        Assertions.AssertWarning(response, false);
        ExtentLogger.info("Assertion passed - Do not have warning");

        Assertions.AssertResponseTime(response, ResponseTime);

        excelwriter();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow = sheet.getRow(29);

        String filepath1;
        filepath1 = ".\\src\\test\\java\\MODULES\\WAVE3\\CreateBookingService\\PreRequest\\Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing.xml";


        XMLParser.updateAttributeValue("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1);
        XMLParser.updateAttributeValue("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath());

        wb.close();
    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(29);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\Pre_create_booking_1seg_1pax_stored_fare_1telephone_ticketing.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);
        InputRow.getCell(17).setCellValue(PNR);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }
}
