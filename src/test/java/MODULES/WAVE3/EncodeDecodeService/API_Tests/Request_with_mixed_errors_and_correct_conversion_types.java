package MODULES.WAVE3.EncodeDecodeService.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Request_with_mixed_errors_and_correct_conversion_types extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(SOAPRequest)
                .when()
                .post(getEncodedecodeservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"EncodeDecodeService\\Request_with_mixed_errors_and_correct_conversion_types.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("Warnings"));
        Assert.assertTrue(response.getBody().asString().contains("Invalid country in message"));
        Assert.assertTrue(response.getBody().asString().contains("FR * FRANCE (2) FU    **NO MATCHING ITEM**"));
        Assert.assertTrue(response.getBody().asString().contains("Conversion"));

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
        XSSFSheet sheet = wb.getSheet("EncodeDecodeService");
        XSSFRow InputRow=sheet.getRow(10);

        String filepath1;
        filepath1=getRequestDirectory()+"EncodeDecodeService\\Request_with_mixed_errors_and_correct_conversion_types.xml";


        XMLParser.SetTagtextatIndex("con:CountryConversion",InputRow.getCell(2).getStringCellValue(),filepath1,0);
        XMLParser.SetTagtextatIndex("con:CountryConversion",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("con:CountryConversion",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),2);

        XMLParser.SetTagtextatIndex("con:CityAirportConversion",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("con:CityAirportConversion",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);

        XMLParser.SetTagtextatIndex("con:AirlineConversion",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }


}
