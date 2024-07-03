package MODULES.WAVE3.DisplayBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.Create_booking_1_1st_flt_in_one_booking_and_2nd_flt_in_another_booking;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.Create_booking_2_1st_flt_in_one_booking_and_2nd_flt_in_another_booking;
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

public class Display_confirmed_booking_list_1st_flt_in_one_booking_and_2nd_flt_in_another_booking extends FrameworkConstants {

    public static String SOAPRequest;
    public static String PNR1;
    public static String PNR2;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        //        PreRequisite for Scenario ------> Create Booking

        Create_booking_1_1st_flt_in_one_booking_and_2nd_flt_in_another_booking Prerequisite1 = new Create_booking_1_1st_flt_in_one_booking_and_2nd_flt_in_another_booking();
        Prerequisite1.run();

        Create_booking_2_1st_flt_in_one_booking_and_2nd_flt_in_another_booking Prerequisite = new Create_booking_2_1st_flt_in_one_booking_and_2nd_flt_in_another_booking();
        Prerequisite.run();


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
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


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "DisplayBookingService\\Display_confirmed_booking_list_1st_flt_in_one_booking_and_2nd_flt_in_another_booking.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("AirReservation BookingReferenceID=\""+PNR1+"\""));
        Assert.assertTrue(response.getBody().asString().contains("AirReservation BookingReferenceID=\""+PNR2+"\""));

        Assertions.AssertWarning(response,false);
        Assertions.AssertResponseTime(response,ResponseTime);


//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("DisplayBookingService");
        XSSFRow InputRow = sheet.getRow(35);

        String filepath1;
        filepath1 = getRequestDirectory() + "DisplayBookingService\\Display_confirmed_booking_list_1st_flt_in_one_booking_and_2nd_flt_in_another_booking.xml";

        XMLParser.SetTagtextatIndex("read:FlightNumber", InputRow.getCell(2).getStringCellValue(), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("read:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.SetTagtextatIndex("read:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath(), 0);
        XMLParser.SetTagtextatIndex("com:Surname", InputRow.getCell(14).getStringCellValue(), getTemp_requestPath(), 0);

        PNR1 = InputRow.getCell(10).getStringCellValue();
        PNR2 = InputRow.getCell(15).getStringCellValue();

        wb.close();

    }
}


