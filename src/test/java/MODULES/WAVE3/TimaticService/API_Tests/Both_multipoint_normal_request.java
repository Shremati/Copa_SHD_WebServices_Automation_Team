package MODULES.WAVE3.TimaticService.API_Tests;

import GENERICS.Assertions;
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

public class Both_multipoint_normal_request extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getTimaticservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getTimaticservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"TimaticService\\Both_multipoint_normal_request.xml"));
        writer.write(response.asPrettyString());
        writer.close();


        Assert.assertTrue(response.getBody().asString().contains("<ns5:Success/>"),"Not contains \"Success\" in response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

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
        XSSFSheet sheet = wb.getSheet("TimaticService");
        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=getRequestDirectory()+"TimaticService\\Both_multipoint_normal_request.xml";

        XMLParser.updateAttributeValue("com:Source","AirlineVendorID",InputRow.getCell(1).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValueatIndex("air:Country","Code",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air:Country","Code",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air:Country","Code",InputRow.getCell(12).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air:Country","Code",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("air:Country","Code",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("air:Country","Code",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),5);
        XMLParser.updateAttributeValueatIndex("air:Country","Code",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),6);
        XMLParser.updateAttributeValue("eds:CountryOfResidence","LocationCode",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValueatIndex("eds:DestinationLocation","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("eds:DestinationLocation","LocationCode",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),2); //because index 1 has no attribute
        XMLParser.updateAttributeValueatIndex("eds:TransitLocation","LocationCode",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),2);

        wb.close();

    }

}
