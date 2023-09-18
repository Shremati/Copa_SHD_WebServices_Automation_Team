package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.CreateBookingService.PostCheck.create_booking_2seg_2pax_stored_fare_issue_ticket_41;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

import static io.restassured.RestAssured.given;

public class create_booking_2seg_2pax_stored_fare extends FrameworkConstants
{

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
        Assert.assertTrue(response.getBody().asString().contains("ns3:FareBasisCodes"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:BaseFare"));
        Assert.assertTrue(response.getBody().asString().contains("NotValidBefore"));
        Assert.assertTrue(response.getBody().asString().contains("NotValidAfter"));
        Assert.assertTrue(response.getBody().asString().contains("<ns3:FareBaggageAllowance FlightSegmentRPH=\"1\" UnitOfMeasureQuantity=\"3\" UnitOfMeasure=\"PC\"/>"));
        Assert.assertTrue(response.getBody().asString().contains("<ns3:FareBaggageAllowance FlightSegmentRPH=\"2\" UnitOfMeasureQuantity=\"1\" UnitOfMeasure=\"PC\"/>"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:TourCode"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:FareBaggageAllowance"));
        Assert.assertTrue(response.getBody().asString().contains("Invalid ISO country code for Bankers Rate."));


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"CreateBookingService\\create_booking_2seg_2pax_stored_fare.xml"));
        writer.write(response.asPrettyString());
        writer.close();


        excelwriter();

        create_booking_2seg_2pax_stored_fare_issue_ticket_41 postCheck = new create_booking_2seg_2pax_stored_fare_issue_ticket_41();
        postCheck.run();


    }



    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow=sheet.getRow(8);


        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\create_booking_2seg_2pax_stored_fare.xml";

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(),1);

        XMLParser.updateAttributeValue("air1:Date","Date",Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath());

        wb.close();


    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(8);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\create_booking_2seg_2pax_stored_fare.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);
        InputRow.getCell(17).setCellValue(PNR);


        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }
}
