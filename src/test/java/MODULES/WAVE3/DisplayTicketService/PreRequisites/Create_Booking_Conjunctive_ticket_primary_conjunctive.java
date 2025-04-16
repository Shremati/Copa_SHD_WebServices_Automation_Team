package MODULES.WAVE3.DisplayTicketService.PreRequisites;

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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Create_Booking_Conjunctive_ticket_primary_conjunctive extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getCreatebookingservice());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

//                ********* Clearing Temp_Request.xml *********

        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();

        excelwriter();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("DisplayTicketService");

        XSSFRow InputRow=sheet.getRow(14);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\DisplayTicketService\\PreRequisites\\Create_Booking_Conjunctive_ticket_primary_conjunctive.xml";

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(2).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(14).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(17).getStringCellValue(),getTemp_requestPath(),1);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(18).getNumericCellValue()),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(19).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(20).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),2);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(22).getNumericCellValue()),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(23).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(24).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(25).getStringCellValue(),getTemp_requestPath(),3);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(26).getNumericCellValue()),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(27).getStringCellValue(),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(28).getStringCellValue(),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(29).getStringCellValue(),getTemp_requestPath(),4);

        XMLParser.updateAttributeValueatIndex("air:Ticketing","TicketTimeLimit",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(3).getNumericCellValue()),getTemp_requestPath(),0);

        wb.close();

    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("DisplayTicketService");
        XSSFRow InputRow=sheet.getRow(14);



        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());

        InputRow.getCell(8).setCellValue(PNR);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

//          ********* Clearing Temp_Response.xml *********

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(getTemp_responsePath()));
        writer.write("");
        writer.close();

    }

}
