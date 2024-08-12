package MODULES.WAVE3.Boarding.API_Tests;

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
import org.testng.Assert;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Start_BoardPassenger_function_using_BoardingOption_as_Sequence extends FrameworkConstants
{
    public static String SOAPRequest;
    public static String DCSSeqNumber;
    static RequestSpecification requestSpecification;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getBoarding());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getBoarding())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"Boarding\\Start_BoardPassenger_function_using_BoardingOption_as_Sequence.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("BoardingInformation"), "Does not contain \"BoardingInformation\" in the response");
        ExtentLogger.info("Assertion passed - contains \"BoardingInformation\"");

        Assert.assertTrue(response.getBody().asString().contains("BoardingOption=\"Sequence\""), "Does not contain \"BoardingOption=\\\"Sequence\\\" in the response");
        ExtentLogger.info("Assertion passed - contains \"BoardingOption=\"Sequence\"");

        Assert.assertTrue(response.getBody().asString().contains("FlightNumber"), "Does not contain \"FlightNumber\" in the response");
        ExtentLogger.info("Assertion passed - contains \"FlightNumber\"");

        Assert.assertTrue(response.getBody().asString().contains("DateOfDeparture"), "Does not contain \"DateOfDeparture\" in the response");
        ExtentLogger.info("Assertion passed - contains \"DateOfDeparture\"");

        Assert.assertTrue(response.getBody().asString().contains("LocationCode"), "Does not contain \"LocationCode\" in the response");
        ExtentLogger.info("Assertion passed - contains \"LocationCode\"");

        Assert.assertTrue(response.getBody().asString().contains("DCS_SequenceNumber=\"" + DCSSeqNumber + "\""),
                  "Does not contain \"DCS_SequenceNumber=\\\""+DCSSeqNumber+"\"\" in the response");
        ExtentLogger.info("Assertion passed - contains \"DCS_SequenceNumber=" + DCSSeqNumber+"\"");

        Assertions.AssertWarning(response,true);
        ExtentLogger.info("Assertion passed - do not have warning");

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
        XSSFSheet sheet = wb.getSheet("Boarding");
        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=getRequestDirectory()+"Boarding\\Start_BoardPassenger_function_using_BoardingOption_as_Sequence.xml";


        XMLParser.updateAttributeValue("air1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("air1:DepartureInformation","DateOfDeparture",Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air:PassengerFlightInfo","DCS_SequenceNumber",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());
        DCSSeqNumber = InputRow.getCell(5).getStringCellValue();

        wb.close();

    }
}
