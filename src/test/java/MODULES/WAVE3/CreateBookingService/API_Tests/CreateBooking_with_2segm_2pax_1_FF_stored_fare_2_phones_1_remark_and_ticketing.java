package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.CreateBookingService.PostCheck.CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing_issue_ticket;
import MODULES.WAVE3.CreateBookingService.PostCheck.create_booking_1seg_1pax_stored_fare_1telephone_ticketing_issue_ticket;
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

import static io.restassured.RestAssured.given;

public class CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing extends FrameworkConstants {


    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        UpdatePayload();

//                       ********** Reading the xml request file **********

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


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"CreateBookingService\\CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:BookingReferenceID"));
        Assert.assertTrue(response.getBody().asString().contains("<ns3:Telephone LocationCode=\"PTY\" PhoneUseType=\"3\" AreaCityCode=\"555\" PhoneNumber=\"555-0129\"/>"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:FareBasisCodes"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:BaseFare"));
        Assert.assertTrue(response.getBody().asString().contains("<ns3:Telephone LocationCode=\"PTY\" PhoneUseType=\"3\" AreaCityCode=\"555\" PhoneNumber=\"555-0128\"/>"));

        Assertions.AssertWarning(response,false);
        Assertions.AssertResponseTime(response,ResponseTime);

        excelwriter();

        CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing_issue_ticket PostRequest = new CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing_issue_ticket();
        PostRequest.run();

    }



    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow=sheet.getRow(17);


        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing.xml";

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(7).getStringCellValue(), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(8).getStringCellValue(), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(),1);
        XMLParser.updateAttributeValue("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMdd(InputRow.getCell(19).getNumericCellValue()), getTemp_requestPath());
        //FFNumber
        XMLParser.updateAttributeValue("air1:CustLoyalty", "MembershipID",  InputRow.getCell(36).getStringCellValue(), getTemp_requestPath());
        //Name of the FFPax
        XMLParser.SetTagtextatIndex("com:GivenName", InputRow.getCell(37).getStringCellValue(), getTemp_requestPath(),0); //Taking 1st pax as its
        XMLParser.SetTagtextatIndex("com:Surname", InputRow.getCell(38).getStringCellValue(), getTemp_requestPath(),0);

        wb.close();


    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(17);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);

        InputRow.getCell(17).setCellValue(PNR);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }

}
