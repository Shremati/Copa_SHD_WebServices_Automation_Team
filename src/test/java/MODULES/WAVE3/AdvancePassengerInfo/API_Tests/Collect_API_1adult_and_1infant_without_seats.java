package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.XMLParser;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Add_APIS_Collect_API_1adult_and_1infant_without_seats;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_booking_for_1adult_and_1infant_without_seat;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Display_APIS_Collect_API_1adult_and_1infant_without_seats;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import GENERICS.Assertions;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Collect_API_1adult_and_1infant_without_seats extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        ExtentLogger.info("Prerequisite 1");
        Create_booking_for_1adult_and_1infant_without_seat Prerequisite1 = new Create_booking_for_1adult_and_1infant_without_seat();
        Prerequisite1.run();

        ExtentLogger.info("Prerequisite 2");
        Display_APIS_Collect_API_1adult_and_1infant_without_seats Prerequisite2 = new Display_APIS_Collect_API_1adult_and_1infant_without_seats();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 3");
        Add_APIS_Collect_API_1adult_and_1infant_without_seats Prerequisite3 = new Add_APIS_Collect_API_1adult_and_1infant_without_seats();
        Prerequisite3.run(); //Here AgencyNames are taken from DisplayAPIS response

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getAuthorizationservice());

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\Collect_API_1adult_and_1infant_without_seats.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("RecordID=\"1\">0:APIS COMPLETE"),"Not contains RecordID=\"2\">0:APIS COMPLETE");
        ExtentLogger.info("Assertion passed - contains RecordID=\"1\">0:APIS COMPLETE");

        Assert.assertTrue(response.getBody().asString().contains("RecordID=\"2\">0:APIS COMPLETE"),"Not contains RecordID=\"2\">0:APIS COMPLETE");
        ExtentLogger.info("Assertion passed - contains RecordID=\"2\">0:APIS COMPLETE");

        Assertions.AssertWarning(response,false);
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
        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Collect_API_1adult_and_1infant_without_seats.xml";

        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(), 1);

        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),3);

        wb.close();

    }

}
