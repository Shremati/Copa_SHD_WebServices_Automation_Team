package MODULES.WAVE3.FlifoService.PreRequisites;

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
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Update_Flifo_flight_cancelled extends FrameworkConstants {

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

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getScreentextservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        if(response.getBody().asString().contains("*"))
            ExtentLogger.info("Assertion passed - contains *");
        else if(response.getBody().asString().contains("RPLCD FLWG MSG"))
            ExtentLogger.info("Assertion passed - contains \"RPLCD FLWG MSG\"");



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
        XSSFSheet sheet = wb.getSheet("FlifoService");

        XSSFRow InputRow=sheet.getRow(17);


        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\FlifoService\\PreRequisites\\Update_Flifo_flight_cancelled.xml";

        XMLParser.updateAttributeValue("com:Source","AirlineVendorID",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.SetTagtextatIndex("scr1:ScreenEntry",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),0);

        wb.close();

    }


}
