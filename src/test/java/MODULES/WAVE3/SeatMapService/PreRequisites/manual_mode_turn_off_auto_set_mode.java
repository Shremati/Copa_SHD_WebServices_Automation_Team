package MODULES.WAVE3.SeatMapService.PreRequisites;

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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class manual_mode_turn_off_auto_set_mode extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;


    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        UpdatePayload();

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getScreentextservice());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .body(SOAPRequest)
                .when()
                .post(getScreentextservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        Assert.assertTrue(response.getBody().asString().contains("OPERATING IN MANUAL MODE" )|| response.getBody().asString().contains("FLIGHT OPERATING IN MANUAL MODE"), "Does not contain \"OPERATING IN MANUAL MODE\" or \"FLIGHT OPERATING IN MANUAL MODE\" in the response");
        ExtentLogger.info("Assertion passed - contains \"OPERATING IN MANUAL MODE\" or \"FLIGHT OPERATING IN MANUAL MODE\"");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

//                     ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();

    }

    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("SeatMapService");

        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\SeatMapService\\PreRequisites\\manual_mode_turn_off_auto_set_mode.xml";

        XMLParser.SetTagtext("scr1:ScreenEntry", InputRow.getCell(26).getStringCellValue(),filepath1);


        wb.close();

    }

}
