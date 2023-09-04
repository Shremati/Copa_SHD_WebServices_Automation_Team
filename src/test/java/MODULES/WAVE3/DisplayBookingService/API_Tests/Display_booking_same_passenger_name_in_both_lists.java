package MODULES.WAVE3.DisplayBookingService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.Create_Booking_1_Confirmed_Booking_multiple_name_entries_in_list_on_same_booking;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.Create_Booking_2_Waitlist_Booking_multiple_name_entries_in_list_on_same_booking;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Display_booking_same_passenger_name_in_both_lists extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Create_Booking_1_Confirmed_Booking_multiple_name_entries_in_list_on_same_booking Prerequisite = new Create_Booking_1_Confirmed_Booking_multiple_name_entries_in_list_on_same_booking();
        Prerequisite.run();

        Create_Booking_2_Waitlist_Booking_multiple_name_entries_in_list_on_same_booking Prerequisite1 = new Create_Booking_2_Waitlist_Booking_multiple_name_entries_in_list_on_same_booking();
        Prerequisite1.run();


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
                .post(getDisplaybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Display_booking_same_passenger_name_in_both_lists.xml"));
        writer.write(response.asPrettyString());
        writer.close();



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
        XSSFSheet sheet = wb.getSheet("DisplayBookingService");
        XSSFRow InputRow=sheet.getRow(31);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Display_booking_same_passenger_name_in_both_lists.xml";

        XMLParser.SetTagtext("read:FlightNumber", InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.SetTagtext("read:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath() );
        XMLParser.updateAttributeValue("read:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());

        wb.close();

    }

}
