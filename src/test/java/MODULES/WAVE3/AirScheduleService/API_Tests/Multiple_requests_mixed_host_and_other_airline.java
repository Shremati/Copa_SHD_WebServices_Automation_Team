package MODULES.WAVE3.AirScheduleService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
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

public class Multiple_requests_mixed_host_and_other_airline extends FrameworkConstants {

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
                .post(getAirscheduleservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        Assert.assertTrue(response.getBody().asString().contains("<ns7:Success/>"));
        Assert.assertTrue(response.getBody().asString().contains("<ns7:FlightDetails>"));

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AirScheduleService\\Multiple_requests_mixed_host_and_other_airline.xml"));
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
        XSSFSheet sheet = wb.getSheet("AirScheduleService");
        XSSFRow InputRow=sheet.getRow(8);
//
        String filepath1;
        filepath1=getRequestDirectory()+"AirScheduleService\\Multiple_requests_mixed_host_and_other_airline.xml";


        XMLParser.SetTagtextatIndex("air1:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:DepartureAirport","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:ArrivalAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("air1:FlightNumber", InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.SetTagtextatIndex("air1:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(5).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:DepartureAirport","LocationCode",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:ArrivalAirport","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("air1:FlightNumber", InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),1);

        XMLParser.SetTagtextatIndex("air1:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(9).getNumericCellValue()),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:DepartureAirport","LocationCode",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:ArrivalAirport","LocationCode",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.SetTagtextatIndex("air1:FlightNumber", InputRow.getCell(12).getStringCellValue(),getTemp_requestPath(),2);

        wb.close();

    }
}
