package MODULES.WAVE3.FlifoService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
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

public class Flifo_for_Two_flights extends FrameworkConstants {

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
                .post(getFlifo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"FlifoService\\Flifo_for_Two_flights.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertFalse(response.getBody().asString().contains("Warnings"));

        Assertions.AssertWarning(response,false);
        Assertions.AssertResponseTime(response,ResponseTime);

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
        XSSFSheet sheet = wb.getSheet("FlifoService");
        XSSFRow InputRow=sheet.getRow(2);

        String filepath1;
        filepath1=getRequestDirectory()+"FlifoService\\Flifo_for_Two_flights.xml";

        XMLParser.updateAttributeValue("com:Source","AirlineVendorID",InputRow.getCell(2).getStringCellValue(),filepath1);

        XMLParser.SetTagtextatIndex("air:DepartureDate",Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("air:FlightNumber",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air:DepartureAirport","LocationCode",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air:ArrivalAirport","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.SetTagtextatIndex("air:DepartureDate",Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("air:FlightNumber",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air:DepartureAirport","LocationCode",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),1);


        wb.close();

    }
}
