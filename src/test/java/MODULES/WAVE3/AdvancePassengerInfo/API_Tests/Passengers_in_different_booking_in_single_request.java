package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.RESTWrapper;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.create_booking_service_onepax;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.create_booking_service_singlepax;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;
import GENERICS.Assertions;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import frameworkconstants.*;
import reports.ExtentLogger;

public class Passengers_in_different_booking_in_single_request extends FrameworkConstants
{

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        ExtentLogger.info("Prerequisite 1");
        create_booking_service_onepax Prerequisite1 = new create_booking_service_onepax();
        Prerequisite1.run(); //1st PNR generated in column 7

        ExtentLogger.info("Prerequisite 2");
        create_booking_service_singlepax Prerequisite2 = new create_booking_service_singlepax();
        Prerequisite2.run(); //2nd PNR generated in column 14


        UpdatePayload();


//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getAdvancepassengerinfo());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getAdvancepassengerinfo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\Passengers_in_different_booking_in_single_request.xml"));
        writer.write(response.asPrettyString());
        writer.close();


        Assert.assertTrue(response.getBody().asString().contains("RecordID=\"1\">0:APIS INCOMPLETE"),"Not contains in response RecordID=\"1\">0:APIS INCOMPLETE");
        ExtentLogger.info("Assertion passed - contains RecordID=\\\"1\\\">0:APIS INCOMPLETE\" ");

        Assert.assertTrue(response.getBody().asString().contains("RecordID=\"2\">0:APIS INCOMPLETE"),"Not contains in response RecordID=\"1\">0:APIS INCOMPLETE");
        ExtentLogger.info("Assertion passed - contains RecordID=\\\"2\\\">0:APIS INCOMPLETE\"");

        Assertions.AssertWarning(response,true);
        ExtentLogger.info("Assertion passed - Do not contain Warning");

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
        XSSFSheet sheet = wb.getSheet("AdvancePassengerInfo");
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;

        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Passengers_in_different_booking_in_single_request.xml";


        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),filepath1,0);
        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(14).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),1);


        wb.close();

    }



}
