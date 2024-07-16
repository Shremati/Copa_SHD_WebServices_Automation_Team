package MODULES.WAVE3.ModifyBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.ModifyBookingService.PreRequisites.create_booking_itinerary_changes;
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
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Itinerary_changes extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        create_booking_itinerary_changes Prerequisite = new create_booking_itinerary_changes();
        Prerequisite.run();


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
                .post(getModifybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"ModifyBookingService\\ItineraryChanges.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("RPH=\"3\""));  //New segment gets added

        Assertions.AssertWarning(response,false);
        Assertions.AssertResponseTime(response,ResponseTime);

//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("ModifyBookingService");
        XSSFRow InputRow=sheet.getRow(2);

        String filepath1;
        filepath1=getRequestDirectory()+"ModifyBookingservice\\ItineraryChanges.xml";


        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(9).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("n5:BookingReferenceID","ID",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),0);

//      We are reconfirming the 1st Segment by giving 1st segment details and status=27

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","ArrivalDateTime",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime",InputRow.getCell(12).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);



//We are cancelling the 2nd segment by giving 2nd segment details and status =1

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","ArrivalDateTime",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime",InputRow.getCell(13).getStringCellValue(),getTemp_requestPath(),1);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),1);

//Now we are adding 2 new segments by giving status =11

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(14).getNumericCellValue()),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(17).getStringCellValue(),getTemp_requestPath(),2);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(18).getNumericCellValue()),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(19).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(20).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),3);

//      <!--we include the original reservation to check if both reservations(SHARES and this AirReservation) are in sync-->

        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),4);

        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ArrivalDateTime",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),0);


        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","FlightNumber",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),5);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),5);

        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ArrivalDateTime",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),1);


        wb.close();


    }




}
