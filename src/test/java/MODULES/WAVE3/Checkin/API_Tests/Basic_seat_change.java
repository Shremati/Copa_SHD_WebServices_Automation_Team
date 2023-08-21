package MODULES.WAVE3.Checkin.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.Checkin.PreRequisites.*;
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

import static GENERICS.XMLParser.updateAttributeValue;
import static io.restassured.RestAssured.given;

public class Basic_seat_change extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {


//        PreRequisite for Scenario ------> Create Booking

        create_booking_service_onepax Prerequisite = new create_booking_service_onepax();
        Prerequisite.run();

        Issue_ticket_for_basic_seat_change Prerequisite1 = new Issue_ticket_for_basic_seat_change();
        Prerequisite1.run();
        
        Checkin_1pax_assigning_seat Prerequisite2 = new Checkin_1pax_assigning_seat();
        Prerequisite2.run();


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = given()
                .baseUri(getBaseURL())
                .basePath(getCheckin())
                .header("Content-Type", "text/xml")
                .log().body()
                .filter(new AllureRestAssured())
                .body(SOAPRequest)
                .when()
                .post()
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"Checkin\\Basic_seat_change.xml"));
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
        XSSFSheet sheet = wb.getSheet("CheckIn");
        XSSFRow InputRow=sheet.getRow(10);

        String filepath1;
        filepath1=getRequestDirectory()+"Checkin\\Basic_seat_change.xml";



        updateAttributeValue("com1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1);
        updateAttributeValue("com1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        updateAttributeValue("com1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.SetTagtext("com:GivenName", InputRow.getCell(8).getStringCellValue(), getTemp_requestPath());
        XMLParser.SetTagtext("com:Surname", InputRow.getCell(9).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com1:SeatBoardingInfo", "SeatNumber", InputRow.getCell(20).getStringCellValue(),getTemp_requestPath());
        wb.close();

    }


}
