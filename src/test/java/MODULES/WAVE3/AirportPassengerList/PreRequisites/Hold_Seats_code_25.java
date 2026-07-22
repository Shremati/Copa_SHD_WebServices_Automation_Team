package MODULES.WAVE3.AirportPassengerList.PreRequisites;

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
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Hold_Seats_code_25 extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getCheckin());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getCheckin())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("SEATS ASSIGNED"), "Do not contain SEATS ASSIGNED");
        ExtentLogger.info("Assertion passed - contains SEATS ASSIGNED");

        Assert.assertFalse(response.getBody().asString().contains("Sell Itinerary Process Failed to Complete Successfully :  (1) FLT NOOP FOR FLT/DATE"));
        ExtentLogger.info("Response contains \"Sell Itinerary Process Failed to Complete Successfully :  (1) FLT NOOP FOR FLT/DATE\"");

        Assertions.AssertWarning(response,true);
        ExtentLogger.info("Assertion passed - do not have warning");

        Assertions.AssertResponseTime(response,ResponseTime);

//                ********* Clearing Temp_Request.xml *********

        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();

    }



    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("AirportPassengerList");

        XSSFRow InputRow=sheet.getRow(18);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\AirportPassengerList\\PreRequisites\\Hold_Seats_code_25.xml";


        XMLParser.updateAttributeValue("com1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue() ,filepath1);
        XMLParser.updateAttributeValue("com1:DepartureInformation","DateOfDeparture",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("com1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());


        String givenName = InputRow.getCell(17).getStringCellValue();
        String surname = InputRow.getCell(18).getStringCellValue();
        String tempPath = getTemp_requestPath();

        Files.copy(
                Paths.get(filepath1),
                Paths.get(tempPath),
                StandardCopyOption.REPLACE_EXISTING
        );
// Small delay avoids file lock collision
        try { Thread.sleep(100); } catch (InterruptedException e) {}

        XMLParser.updateTagValue("GivenName", givenName, tempPath);

        try { Thread.sleep(100); } catch (InterruptedException e) {}

        XMLParser.updateTagValue("Surname", surname, tempPath);


        wb.close();

    }

}
