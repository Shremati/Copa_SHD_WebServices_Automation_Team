package MODULES.WAVE3.DepartureControlService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.DepartureControlService.PreRequisites.create_booking_Assign_aircraft_with_generic_and_specific_seat_reaccommodation;
import MODULES.WAVE3.ModifyBookingService.PreRequisites.create_booking_cancel_booking;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class test_assign_aircraft_with_generic_specific_seat_reaccommodation extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        //        PreRequisite for Scenario ------> Create Booking

//        create_booking_Assign_aircraft_with_generic_and_specific_seat_reaccommodation Prerequisite = new create_booking_Assign_aircraft_with_generic_and_specific_seat_reaccommodation();
//        Prerequisite.run(); //excel gets updated



        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(SOAPRequest)
                .when()
                .post(getDeparturecontrolservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DepartureControlService\\assign_aircraft_with_generic_specific_seat_reaccommodation.xml"));
        writer.write(response.asPrettyString());
        writer.close();

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
        XSSFSheet sheet = wb.getSheet("DepartureControlService");
        XSSFRow InputRow=sheet.getRow(2); //Taking scenario create booking for 1 pax

        String filepath1;
        filepath1=getRequestDirectory()+"DepartureControlService\\assign_aircraft_with_generic_specific_seat_reaccommodation.xml";

        XMLParser.updateAttributeValueatIndex("ns3:BookingReferenceID","ID",InputRow.getCell(10).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("ns3:FlightSegment","DepartureDateTime",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("ns3:FlightSegment","FlightNumber",InputRow.getCell(12).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("ns3:FlightSegment","ArrivalDateTime",InputRow.getCell(13).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("DepartureAirport","LocationCode",InputRow.getCell(14).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("ArrivalAirport","LocationCode",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }

}
