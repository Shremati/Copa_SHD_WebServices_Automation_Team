package MODULES.WAVE3.SynchronizeTicketService.API_Tests;


import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.create_booking_adjust_flight_number_and_flight_date;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.display_booking_adjust_flight_number_and_flight_date;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.issue_ticket_adjust_flight_number_and_flight_date;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.modify_booking_adjust_flight_number_and_flight_date;
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

public class sync_ticket_adjust_flight_number_and_flight_date extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        //        PreRequisite for Scenario ------> Create Booking

        create_booking_adjust_flight_number_and_flight_date Prerequisite = new create_booking_adjust_flight_number_and_flight_date();
        Prerequisite.run(); //excel gets updated

//        display_booking_adjust_flight_number_and_flight_date Prerequisite2 = new display_booking_adjust_flight_number_and_flight_date();
//        Prerequisite2.run();

        //        PreRequisite for Scenario ------> Issue Ticket

        issue_ticket_adjust_flight_number_and_flight_date Prerequisite3 = new issue_ticket_adjust_flight_number_and_flight_date();
        Prerequisite3.run(); //generates ticket number

//        display_booking_adjust_flight_number_and_flight_date Prerequisite4 = new display_booking_adjust_flight_number_and_flight_date();
//        Prerequisite4.run();

        modify_booking_adjust_flight_number_and_flight_date Prerequisite5 = new modify_booking_adjust_flight_number_and_flight_date();
        Prerequisite5.run();


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
                .post(getSynchronizeticketservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"TicketControlService\\sync_ticket_adjust_flight_number_and_flight_date.xml"));
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
        XSSFSheet sheet = wb.getSheet("SynchronizeTicketService");
        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=getRequestDirectory()+"SynchronizeTicketService\\sync_ticket_adjust_flight_number_and_flight_date.xml";

        XMLParser.updateAttributeValue("tic:BookingTicketingRefID","ID", InputRow.getCell(20).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("tic:OriginalAirlineInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("tic:BookingTicketingRefID","FlightNumber", InputRow.getCell(2).getStringCellValue(),filepath1);
//        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(9).getNumericCellValue()),getTemp_requestPath(),0);


        wb.close();

    }


}
