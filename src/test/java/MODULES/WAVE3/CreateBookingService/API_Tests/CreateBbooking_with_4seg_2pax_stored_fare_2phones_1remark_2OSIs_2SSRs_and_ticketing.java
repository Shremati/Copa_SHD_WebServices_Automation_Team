package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.CreateBookingService.PostCheck.IssueTicket_CreateBbooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing;
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

public class CreateBbooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing extends FrameworkConstants {


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

        Assert.assertTrue(response.getBody().asString().contains("<ns5:Success/>"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:BookingReferenceID"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:Telephone"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:BaseFare"));
        Assert.assertTrue(response.getBody().asString().contains("TicketTimeLimit"));
        Assert.assertTrue(response.getBody().asString().contains("<ns3:SpecialServiceRequest TravelerRefNumberRPHList=\"1\" FlightRefNumberRPHList=\"1\" SSRCode=\"WCHR\" ServiceQuantity=\"1\" Status=\"11\">")); //Add 2
        Assert.assertTrue(response.getBody().asString().contains("<ns3:Remark>HAVE A NICE PARTY</ns3:Remark>"));
        Assert.assertTrue(response.getBody().asString().contains("<ns3:SpecialServiceRequest TravelerRefNumberRPHList=\"2\" FlightRefNumberRPHList=\"1\" SSRCode=\"WCHR\" ServiceQuantity=\"1\" Status=\"11\">")); //Add 2

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"CreateBookingService\\CreateBooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing.xml"));
        writer.write(response.asPrettyString());
        writer.close();


        excelwriter();

        IssueTicket_CreateBbooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing PostRequest = new IssueTicket_CreateBbooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing();
        PostRequest.run();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow=sheet.getRow(18);


        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\CreateBbooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing.xml";

        // Segment 1
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(),0);

        // Segment 2
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(6).getStringCellValue(), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(7).getStringCellValue(), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(8).getStringCellValue(), getTemp_requestPath(),1);

        // Segment 3
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(9).getNumericCellValue()), getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(10).getStringCellValue(), getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(11).getStringCellValue(), getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(12).getStringCellValue(), getTemp_requestPath(),2);

        // Segment 4
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(13).getNumericCellValue()), getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(14).getStringCellValue(), getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(15).getStringCellValue(), getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(16).getStringCellValue(), getTemp_requestPath(),3);


        XMLParser.updateAttributeValue("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMdd(InputRow.getCell(19).getNumericCellValue()), getTemp_requestPath());

        wb.close();


    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(18);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\CreateBooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);

        InputRow.getCell(17).setCellValue(PNR);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }

}
