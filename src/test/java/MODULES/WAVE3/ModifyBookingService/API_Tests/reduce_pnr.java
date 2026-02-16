package MODULES.WAVE3.ModifyBookingService.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;

import MODULES.WAVE3.ModifyBookingService.PreRequisites.create_booking_cancel_booking;
import MODULES.WAVE3.ModifyBookingService.PreRequisites.create_booking_reduce_pnr;
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

public class reduce_pnr extends FrameworkConstants {
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        //        PreRequisite for Scenario ------> Create Booking
//        create_booking_reduce_pnr Prerequisite = new create_booking_reduce_pnr();
//
//        ExtentLogger.info("Prerequisite 1");
//        Prerequisite.run();
        int i=0;
        boolean flightFound=false;

////We are searching all the available flights in a do while loop
        create_booking_reduce_pnr Prerequisite = new create_booking_reduce_pnr();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite");

//       <!-- Required data: the names to remove from the reservation in TravelerInfo -->
//         <!-- Indicate the passenger from the lowest to highest RPH -->

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getModifybookingservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .when()
                .post(getModifybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "ModifyBookingService\\ReducePNR.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),
                "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("<GivenName>ALONDRA</GivenName>"),
                "Do not contain <GivenName>ALONDRA</GivenName>");
        ExtentLogger.info("Assertion passed - contains <GivenName>ALONDRA</GivenName>");

        Assert.assertTrue(response.getBody().asString().contains("<GivenName>PETER</GivenName>"),
                "DO not contain <GivenName>PETER</GivenName>");
        ExtentLogger.info("Assertion passed - contains <GivenName>PETER</GivenName>");

        Assertions.AssertWarning(response, false);
        ExtentLogger.info("Assertion passed - Do not have Success");

        Assertions.AssertResponseTime(response, ResponseTime);

//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("ModifyBookingService");
        XSSFRow InputRow = sheet.getRow(4);

        String filepath1;
        filepath1 = getRequestDirectory() + "ModifyBookingservice\\DividePNR.xml";


        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID", "ID", InputRow.getCell(9).getStringCellValue(), filepath1, 0);
        XMLParser.updateAttributeValueatIndex("n5:BookingReferenceID", "ID", InputRow.getCell(9).getStringCellValue(), getTemp_requestPath(), 0);

//        <!-- we include the original reservation to check if both reservations(SHARES and this AirReservation) are in sync -->

        XMLParser.updateAttributeValueatIndex("n1:FlightSegment", "DepartureDateTime", InputRow.getCell(12).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment", "ArrivalDateTime", InputRow.getCell(10).getStringCellValue(), getTemp_requestPath(), 0);

        XMLParser.updateAttributeValueatIndex("n1:FlightSegment", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath(), 0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport", "LocationCode", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath(), 0);


        wb.close();

    }


}
