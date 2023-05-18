package MODULES.WAVE3.ModifyBookingService.API_Tests;

import GENERICS.XMLParser;
import MODULES.WAVE3.ModifyBookingService.PreRequisites.create_booking_reduce_pnr;
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

public class reduce_pnr extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        //        PreRequisite for Scenario ------> Create Booking

        create_booking_reduce_pnr Prerequisite = new create_booking_reduce_pnr();
        Prerequisite.run(); //excel gets updated


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



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"ModifyBookingService\\ReducePNR.xml"));
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
        XSSFSheet sheet = wb.getSheet("ModifyBookingSer_CancelBooking");
        XSSFRow InputRow=sheet.getRow(1); //Taking scenario create booking for 1 pax

        String filepath1;
        filepath1=getRequestDirectory()+"ModifyBookingservice\\DividePNR.xml";


        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(9).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","FlightNumber",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ArrivalDateTime",InputRow.getCell(12).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(13).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(14).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("n5:BookingReferenceID","ID",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),0);
//        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),1);
//        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","FlightNumber",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),1);
//        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ArrivalDateTime",InputRow.getCell(17).getStringCellValue(),getTemp_requestPath(),1);
//        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(18).getStringCellValue(),getTemp_requestPath(),1);
//        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(19).getStringCellValue(),getTemp_requestPath(),1);

        wb.close();

    }




}
