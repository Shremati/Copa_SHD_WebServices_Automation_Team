package MODULES.WAVE3.FlightDepartureInfoService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Flight_has_two_legs_and_notopen_status extends FrameworkConstants {
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        UpdatePayload();

        Response response = null;

//        boolean flightFound = false;
//
//        int i = 0;
//        do{
//            UpdatePayload(i);
//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getFlightdepartureinfoservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

                 response = requestSpecification.body(SOAPRequest)
                .when()
                .post(getFlightdepartureinfoservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());


//            if(response.getBody().asString().contains("Success") && response.getBody().asString().contains("Status=\"NotOpen\"")){
//                flightFound = true;
//            }
//
//            i++;
//
//            if(i > 4){
//                Assert.fail("No flights are having seats");
//            }
//        }
//        while(!flightFound);
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "FlightDepartureInfoService\\Flight_has_two_legs_and_notopen_status.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("Status=\"NotOpen\""), "Do not contain Status=\"NotOpen\"");
        ExtentLogger.info("Assertion passed - contains Status=\"NotOpen\"");

        Assert.assertFalse(response.getBody().asString().contains("Status=\"Open\""), "Do not contain Status=\"Open\"");
        ExtentLogger.info("Assertion passed - contains Status=\"Open\"");

        Assertions.AssertWarning(response, false);
        ExtentLogger.info("Assertion passed - do not have warning");

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
        XSSFSheet sheet = wb.getSheet("FlightDepartureInfoService");
        XSSFRow InputRow = sheet.getRow(1);

        String filepath1;
        filepath1 = getRequestDirectory() + "FlightDepartureInfoService\\Flight_has_two_legs_and_notopen_status.xml";


        XMLParser.SetTagtextatIndex("read:FlightNumber", InputRow.getCell(1).getStringCellValue(), filepath1, 0);
//        XMLParser.SetTagtextatIndex("read:FlightNumber",  availableFlights.get(InputRow.getCell(2).getStringCellValue() + "-" + InputRow.getCell(3).getStringCellValue()).get(i), filepath1,0);
        XMLParser.updateAttributeValue("read:DepartureAirport", "LocationCode", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("read:DepartureAirport", "CodeContext", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());
        XMLParser.SetTagtextatIndex("read:DepartureDate", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()), getTemp_requestPath(), 0);

        wb.close();

    }


}
