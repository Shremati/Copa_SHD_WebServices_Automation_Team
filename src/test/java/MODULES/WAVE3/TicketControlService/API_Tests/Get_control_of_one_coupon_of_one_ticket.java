package MODULES.WAVE3.TicketControlService.API_Tests;


import GENERICS.XMLParser;
import MODULES.WAVE3.ModifyTicketingService.PreRequisites.create_booking_refund_error_cancell_all_segments_prior_refund;
import MODULES.WAVE3.ModifyTicketingService.PreRequisites.issue_ticket_refund_error_cancell_all_segments_prior_refund;
import MODULES.WAVE3.TicketControlService.PreRequisites.create_booking_get_control_of_one_coupon_of_one_ticket;
import MODULES.WAVE3.TicketControlService.PreRequisites.issue_ticket_get_control_of_one_coupon_of_one_ticket;
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

public class Get_control_of_one_coupon_of_one_ticket extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        //        PreRequisite for Scenario ------> Create Booking

        create_booking_get_control_of_one_coupon_of_one_ticket Prerequisite = new create_booking_get_control_of_one_coupon_of_one_ticket();
        Prerequisite.run(); //excel gets updated

        //        PreRequisite for Scenario ------> Issue Ticket

        issue_ticket_get_control_of_one_coupon_of_one_ticket Prerequisite2 = new issue_ticket_get_control_of_one_coupon_of_one_ticket();
        Prerequisite2.run(); //generates ticket number


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
                .post(getTicketcontroloservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"TicketControlService\\Get_control_of_one_coupon_of_one_ticket.xml"));
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
        XSSFSheet sheet = wb.getSheet("TicketControlService");
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=getRequestDirectory()+"TicketControlService\\Get_control_of_one_coupon_of_one_ticket.xml";

        XMLParser.updateAttributeValueatIndex("tic1:TicketDocument","TicketDocumentNbr", InputRow.getCell(20).getStringCellValue(),filepath1,0);



        wb.close();

    }




}
