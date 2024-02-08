package MODULES.WAVE3.PassengerListService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.PassengerListService.PreRequisites.Create_Booking_Request;
import MODULES.WAVE3.PassengerListService.PreRequisites.create_booking_display_pass_inbound_connection;
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

public class Pax_List_Request_Positive_Non_Revenue extends FrameworkConstants {
    public static String SOAPRequest;
    public static String PNR;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Create_Booking_Request Prerequisite = new Create_Booking_Request();
        Prerequisite.run();

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
                .post(getPassengerlistservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("BookingReferenceID=\""+PNR+"\""));

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"PassengerListService\\Pax_List_Request_Positive_Non_Revenue.xml"));
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
        XSSFSheet sheet = wb.getSheet("PassengerListService");
        XSSFRow InputRow=sheet.getRow(5);

        String filepath1;
        filepath1=getRequestDirectory()+"PassengerListService\\Pax_List_Request_Positive_Non_Revenue.xml";


        XMLParser.SetTagtextatIndex("read:FlightNumber",InputRow.getCell(1).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("read:DepartureAirport","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("read:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()),getTemp_requestPath(),0);
        PNR = InputRow.getCell(9).getStringCellValue();
        wb.close();

    }

}
