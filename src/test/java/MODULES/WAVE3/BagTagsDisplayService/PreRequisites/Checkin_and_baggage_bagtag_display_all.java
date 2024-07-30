package MODULES.WAVE3.BagTagsDisplayService.PreRequisites;

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

import static io.restassured.RestAssured.given;

public class Checkin_and_baggage_bagtag_display_all extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification        .body(SOAPRequest)
                .when()
                .post(getCheckin())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("SEATS ASSIGNED"));

        Assertions.AssertWarning(response,false);
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
        XSSFSheet sheet = wb.getSheet("BagTags");
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\BagTagsDisplayService\\PreRequisites\\Checkin_and_baggage_bagtag_display_all.xml";



        XMLParser.updateAttributeValue("com1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("com1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("com1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }

}
