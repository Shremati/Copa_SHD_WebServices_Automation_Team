package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.CreateBookingService.PostCheck.create_booking_1seg_1pax_stored_fare_time_limit_issue_ticket_41;
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

public class create_booking_1seg_1pax_stored_fare_time_limit extends FrameworkConstants
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


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"CreateBookingService\\create_booking_1seg_1pax_stored_fare_time_limit.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:BookingReferenceID"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:Telephone"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:FareBasisCodes"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:BaseFare"));
        Assert.assertTrue(response.getBody().asString().contains("NotValidBefore"));
        Assert.assertTrue(response.getBody().asString().contains("NotValidAfter"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:FareBaggageAllowance"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:SalesLocation"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:FareBaggageAllowance"));
        Assert.assertTrue(response.getBody().asString().contains("<ns4:TourCode>IT6ZZ1TOURUSA</ns4:TourCode>"));
        Assert.assertTrue(response.getBody().asString().contains("TicketTimeLimit"));
        Assert.assertTrue(response.getBody().asString().contains("ns3:FareBaggageAllowance"));
        Assert.assertTrue(response.getBody().asString().contains("<Cash CashIndicator=\"true\"/>"));

        Assertions.AssertWarning(response,false);
        Assertions.AssertResponseTime(response,ResponseTime);

        excelwriter();

        create_booking_1seg_1pax_stored_fare_time_limit_issue_ticket_41 postCheck = new create_booking_1seg_1pax_stored_fare_time_limit_issue_ticket_41();
        postCheck.run();


    }



    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow=sheet.getRow(7);


        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\create_booking_1seg_1pax_stored_fare_time_limit.xml";

        XMLParser.updateAttributeValue("air1:FlightSegment", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1);
        XMLParser.updateAttributeValue("air1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(19).getNumericCellValue()), getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:FareBasisCode", "NotValidAfter", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:FareBasisCode", "NotValidBefore", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath());

        wb.close();


    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow = sheet.getRow(7);

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\create_booking_1seg_1pax_stored_fare_time_limit.xml";

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID", "ID", filepath);
        InputRow.getCell(17).setCellValue(PNR);


        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }
}
