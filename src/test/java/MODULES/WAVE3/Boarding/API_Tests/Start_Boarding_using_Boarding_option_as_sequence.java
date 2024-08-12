package MODULES.WAVE3.Boarding.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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
import java.util.concurrent.TimeUnit;

import frameworkconstants.*;
import reports.ExtentLogger;

import static io.restassured.RestAssured.given;

public class Start_Boarding_using_Boarding_option_as_sequence extends FrameworkConstants
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

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"Boarding\\Start_Boarding_using_Boarding_option_as_sequence.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("BoardingInformation"), "Does not contain \"BoardingInformation\" in the response");
        ExtentLogger.info("Assertion passed - contains \"BoardingInformation\"");

        Assert.assertTrue(response.getBody().asString().contains("BoardingOption=\"Sequence\""), "Does not contain \"BoardingOption=\\\"Sequence\\\"\" in the response");
        ExtentLogger.info("Assertion passed - contains \"BoardingOption=\\\"Sequence\\\"\"");

        Assertions.AssertWarning(response,false);
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

        FileInputStream fis=new FileInputStream(new File(".\\src\\test\\java\\TestData\\Scenario_TestData.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("Boarding");
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=getRequestDirectory()+"Boarding\\Start_Boarding_using_Boarding_option_as_sequence.xml";

        XMLParser.updateAttributeValue("air1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("air1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }

}
