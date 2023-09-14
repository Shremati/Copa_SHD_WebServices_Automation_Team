package MODULES.WAVE3.ConnectionFlightInfo.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.Checkin.PreRequisites.Create_Booking_Non_Revenue_Pax;
import MODULES.WAVE3.Checkin.PreRequisites.Issue_ticket_non_revenue_pax;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Get_inbound_flight_info extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

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
                .post(getConnectionFlightInfo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"ConnectionFlightInfo\\Get_inbound_flight_info.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Validationcheck();

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
        XSSFSheet sheet = wb.getSheet("ConnectionFlightInfo");
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=getRequestDirectory()+"ConnectionFlightInfo\\Get_inbound_flight_info.xml";


        XMLParser.updateAttributeValue("con1:FlightLegInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(4).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("con1:FlightLegInfo","FlightNumber",InputRow.getCell(1).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }

    public static void Validationcheck() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Validate message in response ************

        String filepath;
        filepath = getResponseDirectory() + "ConnectionFlightInfo\\Get_inbound_flight_info.xml";

        String Expected = XMLParser.GetTagText("ns6:Remarks",filepath);
        String Actual = "CM INBOUND CONNECTIONS TO";
        Assert.assertTrue(Expected.contains(Actual));

    }
}
