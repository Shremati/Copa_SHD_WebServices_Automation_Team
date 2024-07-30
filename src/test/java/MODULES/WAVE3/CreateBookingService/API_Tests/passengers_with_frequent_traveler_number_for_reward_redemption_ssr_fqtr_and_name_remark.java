package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.CreateBookingService.PostCheck.passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark_issue_ticket;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;
import org.testng.Assert;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

import static io.restassured.RestAssured.given;

public class passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark extends FrameworkConstants {
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        UpdatePayload();

//                       ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);


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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "CreateBookingService\\passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID"));
        Assert.assertTrue(response.getBody().asString().contains("<ns3:Remark>FOR SSR FQTR PLUS NAME REMARK TESTING</ns3:Remark>"));

        Assertions.AssertWarning(response, false);
        Assertions.AssertResponseTime(response, ResponseTime);

        excelwriter();

        passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark_issue_ticket postCheck = new passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark_issue_ticket();
        postCheck.run();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow = sheet.getRow(15);


        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark.xml";

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 0);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(7).getStringCellValue(), getTemp_requestPath(), 1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(8).getStringCellValue(), getTemp_requestPath(), 1);


        wb.close();


    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(15);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);
        InputRow.getCell(17).setCellValue(PNR);


        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }
}
