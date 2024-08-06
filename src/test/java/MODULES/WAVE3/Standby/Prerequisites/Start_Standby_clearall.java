package MODULES.WAVE3.Standby.Prerequisites;

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

public class Start_Standby_clearall extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getStandby());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getStandby())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");
        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Not contains \"Success\" in response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("StandbyInfo"),"Not contains \"StandbyInfo\" in response");
        ExtentLogger.info("Assertion passed - contains \"StandbyInfo\"");

        Assert.assertTrue(response.getBody().asString().contains("PassengerCountInfo"),"Not contains \"PassengerCountInfo\" in response");
        ExtentLogger.info("Assertion passed - contains \"PassengerCountInfo\"");

        Assert.assertTrue(response.getBody().asString().contains("BookingClassInfo"),"Not contains \"BookingClassInfo\" in response");
        ExtentLogger.info("Assertion passed - contains \"BookingClassInfo\"");

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
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("Standby");
        XSSFRow InputRow=sheet.getRow(4);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\Standby\\PreRequisites\\Start_standby.xml";

        XMLParser.updateAttributeValue("air1:CarrierInfo","FlightNumber",InputRow.getCell(1).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("air1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:DepartureInformation","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }
}
