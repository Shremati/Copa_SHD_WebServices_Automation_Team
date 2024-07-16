package MODULES.WAVE3.FlifoService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.FlifoService.PreRequisites.Assign_aircraft_showing_Aircraft_No_and_Air_Equip_Type;
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

public class Showing_Aircraft_Tail_Number_and_the_Air_Equip_Type_in_Flifo_Service_response extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        Assign_aircraft_showing_Aircraft_No_and_Air_Equip_Type Prerequisite1 = new Assign_aircraft_showing_Aircraft_No_and_Air_Equip_Type();
        Prerequisite1.run();

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
                .post(getFlifo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"FlifoService\\Showing_Aircraft_Tail_Number_and_the_Air_Equip_Type_in_Flifo_Service_response.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("FlightInfoDetails"));
        Assert.assertTrue(response.getBody().asString().contains("FlightLegInfo"));

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
        XSSFSheet sheet = wb.getSheet("FlifoService");
        XSSFRow InputRow=sheet.getRow(14);

        String filepath1;
        filepath1=getRequestDirectory()+"FlifoService\\Showing_Aircraft_Tail_Number_and_the_Air_Equip_Type_in_Flifo_Service_response.xml";

        XMLParser.updateAttributeValue("air:Airline","Code",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.SetTagtext("air:FlightNumber", InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());
        XMLParser.SetTagtext("air:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()), getTemp_requestPath());


        wb.close();

    }

}
