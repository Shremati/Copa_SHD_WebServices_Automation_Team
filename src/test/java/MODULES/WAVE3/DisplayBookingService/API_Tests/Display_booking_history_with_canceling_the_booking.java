package MODULES.WAVE3.DisplayBookingService.API_Tests;


import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.*;
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

public class Display_booking_history_with_canceling_the_booking extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        //        PreRequisite for Scenario ------> Create Booking

        create_booking_display_booking_history_with_canceling_the_booking Prerequisite = new create_booking_display_booking_history_with_canceling_the_booking();
        Prerequisite.run(); //excel gets updated

        modify_ticket_display_booking_history_with_canceling_the_booking Prerequisite3 = new modify_ticket_display_booking_history_with_canceling_the_booking();
        Prerequisite3.run();


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


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Display_booking_history_with_canceling_the_booking.xml"));
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
        XSSFRow InputRow=sheet.getRow(17);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Display_booking_history_with_canceling_the_booking.xml";

        XMLParser.updateAttributeValueatIndex("read:UniqueID","ID", InputRow.getCell(10).getStringCellValue(),filepath1,0);
//        XMLParser.updateAttributeValueatIndex("read:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
//        XMLParser.SetTagtextatIndex("read:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath(),0);
//        XMLParser.SetTagtextatIndex("com:GivenName", InputRow.getCell(13).getStringCellValue(),getTemp_requestPath(),0);
//        XMLParser.SetTagtextatIndex("com:Surname", InputRow.getCell(14).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }

}
