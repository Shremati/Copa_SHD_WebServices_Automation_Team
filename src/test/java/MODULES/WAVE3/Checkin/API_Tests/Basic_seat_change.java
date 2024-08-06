package MODULES.WAVE3.Checkin.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.Checkin.PreRequisites.*;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static GENERICS.XMLParser.updateAttributeValue;
import static io.restassured.RestAssured.given;

public class Basic_seat_change extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {


//        PreRequisite for Scenario ------> Create Booking
        ExtentLogger.info("Prerequisite 1");
        create_booking_service_onepax Prerequisite = new create_booking_service_onepax();
        Prerequisite.run();

        ExtentLogger.info("Prerequisite 2");
        Issue_ticket_for_basic_seat_change Prerequisite1 = new Issue_ticket_for_basic_seat_change();
        Prerequisite1.run();

        ExtentLogger.info("Prerequisite 3");
        Display_APIS_Basic_seat_change Prerequisite2 = new Display_APIS_Basic_seat_change();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 4");
        Modify_APIS_Basic_seat_change Prerequisite3 = new Modify_APIS_Basic_seat_change();
        Prerequisite3.run();

        ExtentLogger.info("Prerequisite 5");
        Checkin_1pax_assigning_seat Prerequisite4 = new Checkin_1pax_assigning_seat();
        Prerequisite4.run();


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getCheckin());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .basePath(getCheckin())
                .header("Content-Type", "text/xml")
                .log().body()
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post()
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");
        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"Checkin\\Basic_seat_change.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Not contains \"Success\" in response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("SEATS ASSIGNED"),"Not contains \"SEATS ASSIGNED\" in response");
        ExtentLogger.info("Assertion passed - contains \"SEATS ASSIGNED\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - Do not contain Warning");

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
        XSSFSheet sheet = wb.getSheet("CheckIn");
        XSSFRow InputRow=sheet.getRow(10);

        String filepath1;
        filepath1=getRequestDirectory()+"Checkin\\Basic_seat_change.xml";



        updateAttributeValue("com1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1);
        updateAttributeValue("com1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        updateAttributeValue("com1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com1:SeatBoardingInfo", "SeatNumber", InputRow.getCell(20).getStringCellValue(),getTemp_requestPath());
        wb.close();

    }


}
