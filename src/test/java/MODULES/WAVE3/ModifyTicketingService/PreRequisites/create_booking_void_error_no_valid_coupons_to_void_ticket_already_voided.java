package MODULES.WAVE3.ModifyTicketingService.PreRequisites;

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

public class create_booking_void_error_no_valid_coupons_to_void_ticket_already_voided extends FrameworkConstants
{

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;


    public boolean run(int i) throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload(i);

//                       ********** Reading the xml request file **********

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

        if(!(response.getBody().asString().contains("Success") &&
                response.getBody().asString().contains("BookingReferenceID"))){
            return false;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID"), "Does not contain \"BookingReferenceID\" in the response");
        ExtentLogger.info("Assertion passed - contains \"BookingReferenceID\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - do not have warning");

        Assertions.AssertResponseTime(response,ResponseTime);


//                ********* Clearing Temp_Request.xml *********

        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();

        excelwriter(i);
        return true;
    }



    public static void UpdatePayload(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("ModifyTicketingService");

        XSSFRow InputRow=sheet.getRow(5);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\ModifyTicketingService\\PreRequisites\\create_booking_void_a_ticket.xml";


        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
//        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air:Ticketing","TicketTimeLimit",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.updateAttributeValue("air1:FlightSegment", "FlightNumber", availableFlights.get(InputRow.getCell(3).getStringCellValue() + "-" + InputRow.getCell(4).getStringCellValue()).get(i), getTemp_requestPath());


        wb.close();
    }


    public static void excelwriter(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("ModifyTicketingService");
        XSSFRow InputRow=sheet.getRow(5);

        InputRow.getCell(2).setCellValue(availableFlights.get(InputRow.getCell(3).getStringCellValue() + "-" + InputRow.getCell(4).getStringCellValue()).get(i));

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
        InputRow.getCell(10).setCellValue(PNR);

        InputRow.getCell(11).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","DepartureDateTime",getTemp_responsePath(),0));
        InputRow.getCell(12).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","FlightNumber",getTemp_responsePath(),0));
        InputRow.getCell(13).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","ArrivalDateTime",getTemp_responsePath(),0));
        InputRow.getCell(14).setCellValue(XMLParser.GetAttributeValueatIndex("DepartureAirport","LocationCode",getTemp_responsePath(),0));
        InputRow.getCell(15).setCellValue(XMLParser.GetAttributeValueatIndex("ArrivalAirport","LocationCode",getTemp_responsePath(),0));


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
