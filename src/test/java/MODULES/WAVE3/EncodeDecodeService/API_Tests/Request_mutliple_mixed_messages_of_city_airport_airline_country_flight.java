package MODULES.WAVE3.EncodeDecodeService.API_Tests;

import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
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

import static io.restassured.RestAssured.given;

public class Request_mutliple_mixed_messages_of_city_airport_airline_country_flight extends FrameworkConstants
{
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

        Assert.assertTrue(response.getBody().asString().contains("<ns4:Success/>"));
        Assert.assertTrue(response.getBody().asString().contains("UNITED AIRLINES INC"));


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"EncodeDecodeService\\Request_multiple_mixed_messages_of_city_airport_airline_country_flight.xml"));
        writer.write(response.asPrettyString());
        writer.close();



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
        XSSFRow InputRow=sheet.getRow(4);

        String filepath1;
        filepath1=getRequestDirectory()+"EncodeDecodeService\\Request_mutliple_mixed_messages_of_city_airport_airline_country_flight.xml";


        XMLParser.SetTagtextatIndex("con:CountryConversion",InputRow.getCell(2).getStringCellValue(),filepath1,0);
        XMLParser.SetTagtextatIndex("con:CountryConversion",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("con:CountryConversion",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.SetTagtextatIndex("con:CityAirportConversion",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("con:CityAirportConversion",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("con:CityAirportConversion",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),1);

        XMLParser.SetTagtextatIndex("con:AirlineConversion",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }

}
