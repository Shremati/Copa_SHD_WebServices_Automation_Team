package MODULES.WAVE3.DisplayBookingService.API_Tests;


import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.*;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Display_active_fare_quote_and_fare_quote_history extends FrameworkConstants
{
    public static String SOAPRequest;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        create_booking_display_active_fare_quote_and_fare_quote_history Prerequisite = new create_booking_display_active_fare_quote_and_fare_quote_history();
        Prerequisite.run();

        display_fare_quote Prerequisite2 = new display_fare_quote();
        Prerequisite2.run();

        modify_ticket_display_active_fare_quote_and_fare_quote_history Prerequisite3 = new modify_ticket_display_active_fare_quote_and_fare_quote_history();
        Prerequisite3.run(); // even though modificationtype=5 ,we are not changing/modifying any value


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

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("HistoryItems"));
        Assert.assertTrue(response.getBody().asString().contains("FARE QUOTE"));

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Display_active_fare_quote_and_fare_quote_history.xml"));
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
        XSSFRow InputRow=sheet.getRow(15);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Display_active_fare_quote_and_fare_quote_history.xml";


       XMLParser.updateAttributeValueatIndex("read:UniqueID", "ID", InputRow.getCell(10).getStringCellValue(),filepath1,0);

        wb.close();

    }

}
