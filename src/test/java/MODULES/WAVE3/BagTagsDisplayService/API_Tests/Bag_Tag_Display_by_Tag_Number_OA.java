package MODULES.WAVE3.BagTagsDisplayService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.BagTagsDisplayService.PreRequisites.*;
import MODULES.WAVE3.BagTagsDisplayService.PreRequisites.Create_booking_service;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Bag_Tag_Display_by_Tag_Number_OA extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        ExtentLogger.info("Prerequisite 1");
        Create_booking_display_by_tag_number_OA Prerequisite = new Create_booking_display_by_tag_number_OA();
        Prerequisite.run();

        ExtentLogger.info("Prerequisite 2");
        Issue_ticket_display_by_tag_number_OA Prerequisite1 = new Issue_ticket_display_by_tag_number_OA();
        Prerequisite1.run();

        ExtentLogger.info("Prerequisite 3");
        Display_APIS_display_bagtag_number_OA Prerequisite2 = new Display_APIS_display_bagtag_number_OA();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 4");
        Add_APIS_display_bagtag_number_OA Prerequisite3 = new Add_APIS_display_bagtag_number_OA();
        Prerequisite3.run();

        ExtentLogger.info("Prerequisite 5");
        Checkin_and_baggage_display_bagtag_number_OA Prerequisite4 = new Checkin_and_baggage_display_bagtag_number_OA();
        Prerequisite4.run();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getAuthorizationservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest); 

        Response response = requestSpecification.body(SOAPRequest)
                .when()
                .post(getBagtags())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "BagTagDisplayService\\Bag_Tag_Display_by_Tag_Number_OA.xml"));
        writer.write(response.asPrettyString());
        writer.close();

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
        XSSFSheet sheet = wb.getSheet("BagTags");
        XSSFRow InputRow = sheet.getRow(4);

        String filepath1;
        filepath1 = getRequestDirectory() + "BagTagDisplayService\\Bag_Tag_Display_by_Tag_Number_OA.xml";


        XMLParser.updateAttributeValueatIndex("bag1:FlightLegInfo", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1, 0);
        XMLParser.updateAttributeValue("bag1:FlightLegInfo", "ArrivalDateTime", InputRow.getCell(13).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValueatIndex("bag1:FlightLegInfo", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("bag1:FlightLegInfo", "RPH", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(5).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(), 0);

        XMLParser.updateAttributeValueatIndex("bag1:FlightLegInfo", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(17).getNumericCellValue()), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValue("bag1:FlightLegInfo", "ArrivalDateTime", InputRow.getCell(18).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValueatIndex("bag1:FlightLegInfo", "FlightNumber", InputRow.getCell(16).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("bag1:FlightLegInfo", "RPH", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(14).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(15).getStringCellValue(), getTemp_requestPath(), 1);

        wb.close();
    }

}
