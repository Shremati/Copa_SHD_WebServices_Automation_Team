package MODULES.WAVE3.FlifoService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
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

public class Flifo_for_a_flight_with_crossing_date extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        UpdatePayload_1();

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .body(SOAPRequest)
                .when()
                .post(getScreentextservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();


//We need to give the same flight, which is used in update.xml

        UpdatePayload_2();

//    ******** Read the updated request and send it to fetch the response *********

       fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .body(SOAPRequest)
                .when()
                .post(getFlifo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "FlifoService\\Flifo_for_codeshare_flight.xml"));
        writer.write(response.asPrettyString());
        writer.close();


//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

    }


    public static void UpdatePayload_2() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("FlifoService");
        XSSFRow InputRow = sheet.getRow(4);

        String filepath1;
        filepath1 = getRequestDirectory() + "FlifoService\\Flifo_for_a_flight_with_crossing_date.xml";

        XMLParser.updateAttributeValue("com:Source", "AirlineVendorID", InputRow.getCell(2).getStringCellValue(), filepath1);
        XMLParser.SetTagtext("air:FlightNumber", InputRow.getCell(5).getStringCellValue(), getTemp_requestPath());
        XMLParser.SetTagtext("air:DepartureDate",Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()), getTemp_requestPath());


        wb.close();

    }

    public static void UpdatePayload_1() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("FlifoService");
        XSSFRow InputRow = sheet.getRow(4);

        String filepath1;
        filepath1 = getRequestDirectory() + "FlifoService\\Flifo_for_a_flight_with_crossing_date_update.xml";

        XMLParser.updateAttributeValue("com:Source", "AirlineVendorID", InputRow.getCell(2).getStringCellValue(), filepath1);
        XMLParser.SetTagtext("scr1:ScreenEntry", InputRow.getCell(11).getStringCellValue(), getTemp_requestPath());


        wb.close();
    }
}