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

public class HA_and_OA_Inventory_requests_with_optional_parameters extends FrameworkConstants
{
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();
//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest); 

        Response response=requestSpecification.body(SOAPRequest)
                .when()
                .post(getAirinventoryservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AirInventoryService\\HA_and_OA_Inventory_requests_with_optional_parameters.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));

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
        XSSFSheet sheet = wb.getSheet("AirInventoryService");
        XSSFRow InputRow=sheet.getRow(12);

        String filepath1;
        filepath1=getRequestDirectory()+"AirInventoryService\\HA_and_OA_Inventory_requests_with_optional_parameters.xml";


        XMLParser.SetTagtextatIndex("air1:FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1,0);
        XMLParser.SetTagtextatIndex("air1:Date", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("air1:OriginLocation",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("air1:DestinationLocation",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.SetTagtextatIndex("air1:FlightNumber",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("air1:Date", Utils.getDate_YYYYMMdd(InputRow.getCell(5).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:OriginLocation","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:DestinationLocation","LocationCode",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),1);

        wb.close();

    }




}
