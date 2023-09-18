package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Stored_fare_Ticketing_item_Invalid_form_of_payment extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        create_booking_1seg_1pax_stored_fare_1telephone_ticketing Prerequisite1 = new create_booking_1seg_1pax_stored_fare_1telephone_ticketing();
        Prerequisite1.Execute();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        Assert.assertTrue(response.getBody().asString().contains("<ns5:Success/>"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:BookingReferenceID"));
        Assert.assertTrue(response.getBody().asString().contains("Invalid form of payment"));

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"CreateBookingService\\Stored_fare_Ticketing_item_Invalid_form_of_payment.xml"));
        writer.write(response.asPrettyString());
        writer.close();

//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

        excelwriter();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow=sheet.getRow(34);
        XSSFRow InputRow1 = sheet.getRow(1);

        String filepath1;
        filepath1=getRequestDirectory()+"CreateBookingService\\Stored_fare_Ticketing_item_Invalid_form_of_payment.xml";


        XMLParser.updateAttributeValue("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air:Ticketing","TicketTimeLimit", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(19).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:FareBasisCode","NotValidBefore",Utils.getDate_YYYYMMdd(InputRow.getCell(20).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:FareBasisCode","NotValidAfter",Utils.getDate_YYYYMMdd(InputRow.getCell(21).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:Date","Date",Utils.getDate_YYYYMMdd(InputRow.getCell(19).getNumericCellValue()),getTemp_requestPath());

        // adding valid ticket number from prerequisite

        XMLParser.updateAttributeValue("air1:OriginalIssueInfo","DateOfIssue",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(19).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:OriginalIssueInfo","TicketDocumentNbr",InputRow1.getCell(18).getStringCellValue(),getTemp_requestPath());

        XMLParser.updateAttributeValue("air1:OriginalOriginDestination","OriginCityCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:OriginalOriginDestination","DestinationCityCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());


        wb.close();

    }

    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(34);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\Stored_fare_Ticketing_item_Invalid_form_of_payment.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);

        InputRow.getCell(17).setCellValue(PNR);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }


}
