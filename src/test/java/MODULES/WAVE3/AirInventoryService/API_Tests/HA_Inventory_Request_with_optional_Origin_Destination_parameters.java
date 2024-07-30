package MODULES.WAVE3.AirInventoryService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;
import org.testng.Assert;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class HA_Inventory_Request_with_optional_Origin_Destination_parameters extends FrameworkConstants {
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(SOAPRequest);
        ExtentLogger.logXMLRequest(SOAPRequest); 
        Response response = requestSpecification.when()
                .post(getAirinventoryservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "AirInventoryService\\HA_Inventory_Request_with_optional_Origin_Destination_parameters.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        ExtentLogger.info("Checking for Success Message, Warnings in response & Response Time");
        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assertions.AssertWarning(response, false);
        Assertions.AssertResponseTime(response, ResponseTime);


//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("AirInventoryService");
        XSSFRow InputRow = sheet.getRow(11);

        String filepath1;
        filepath1 = getRequestDirectory() + "AirInventoryService\\HA_Inventory_Request_with_optional_Origin_Destination_parameters.xml";


        XMLParser.SetTagtextatIndex("air1:FlightNumber", InputRow.getCell(2).getStringCellValue(), filepath1, 0);
        XMLParser.SetTagtextatIndex("air1:Date", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()), getTemp_requestPath(), 0);
        XMLParser.SetTagtext("air1:OriginLocation", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());
        XMLParser.SetTagtext("air1:DestinationLocation", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath());

        wb.close();

    }


}
