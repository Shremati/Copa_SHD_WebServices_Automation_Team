package MODULES.WAVE3.DisplayBookingService.API_Tests;


import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking_credit_card_search;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.create_booking_credit_card_search_partial_cc_number;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.issue_ticket_credit_card_search;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.issue_ticket_credit_card_search_partial_cc_number;
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

public class Credit_card_search_partial_cc_number extends FrameworkConstants
{
    public static String SOAPRequest;



    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        //        PreRequisite for Scenario ------> Create Booking

        create_booking_credit_card_search_partial_cc_number Prerequisite = new create_booking_credit_card_search_partial_cc_number();
        Prerequisite.run();

        issue_ticket_credit_card_search_partial_cc_number Prerequisite2 = new issue_ticket_credit_card_search_partial_cc_number();
        Prerequisite2.run();

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


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Credit_card_search_partial_cc_number.xml"));
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
        XSSFRow InputRow=sheet.getRow(10);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Credit_card_search_partial_cc_number.xml";

        XMLParser.updateAttributeValueatIndex("read:CreditCardInfo", "CardNumber", InputRow.getCell(18).getStringCellValue().substring(0,8),filepath1,0);


        wb.close();

    }

}
