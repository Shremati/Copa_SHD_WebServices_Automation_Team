package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Check_for_invalid_AirItinerary_in_request extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        UpdatePayload();

//                       ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);


        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"CreateBookingService\\Check_for_invalid_AirItinerary_in_request.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Validationcheck();

        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();


    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow=sheet.getRow(21);


        String filepath1;
        filepath1 = getRequestDirectory() + "CreateBookingService\\Check_for_invalid_AirItinerary_in_request.xml";

        // Segment 1
        XMLParser.updateAttributeValue("air1:FareBasisCode", "NotValidBefore", Utils.getDate_YYYYMMdd(InputRow.getCell(20).getNumericCellValue()), filepath1);
        XMLParser.updateAttributeValue("air1:FareBasisCode", "NotValidAfter", Utils.getDate_YYYYMMdd(InputRow.getCell(21).getNumericCellValue()), getTemp_requestPath());

        wb.close();


    }

    public static void Validationcheck() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Validate message in response ************

        String filepath;
        filepath = getResponseDirectory() + "CreateBookingService\\Check_for_invalid_AirItinerary_in_request.xml";

        String Expected = XMLParser.GetTagText("Error",filepath);
        String Actual = "Message Contains No OriginDestinationOptions";

        Assert.assertEquals(Actual,Expected);

    }


}
