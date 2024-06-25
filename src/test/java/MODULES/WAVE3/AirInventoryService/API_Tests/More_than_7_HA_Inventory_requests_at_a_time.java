package MODULES.WAVE3.AirInventoryService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.XML;
import org.testng.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import frameworkconstants.*;

public class More_than_7_HA_Inventory_requests_at_a_time extends FrameworkConstants
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
                .post(getAirinventoryservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AirInventoryService\\More_than_7_HA_Inventory_requests_at_a_time.xml"));
        writer.write(response.asPrettyString());
        writer.close();


        Assert.assertTrue(response.getBody().asString().contains("Success"));

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
        XSSFSheet sheet = wb.getSheet("AirInventoryService");
        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=getRequestDirectory()+"AirInventoryService\\More than 7 HA Inventory requests at a time.xml";


        XMLParser.SetTagtextatIndex("air1:Date", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
        XMLParser.SetTagtextatIndex("air1:FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:OriginLocation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:DestinationLocation","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);


        int dindex=5,findex=6,oindex=7,deindex=8;
        for(int i=1;i<=7;i++)
        {
            XMLParser.SetTagtextatIndex("air1:Date",Utils.getDate_YYYYMMdd(InputRow.getCell(dindex).getNumericCellValue()),getTemp_requestPath(),i);
            XMLParser.SetTagtextatIndex("air1:FlightNumber",InputRow.getCell(findex).getStringCellValue(),getTemp_requestPath(),i);
            XMLParser.updateAttributeValueatIndex("air1:OriginLocation","LocationCode",InputRow.getCell(oindex).getStringCellValue(),getTemp_requestPath(),i);
            XMLParser.updateAttributeValueatIndex("air1:DestinationLocation","LocationCode",InputRow.getCell(deindex).getStringCellValue(),getTemp_requestPath(),i);

            dindex=dindex+4;
            findex=findex+4;
            oindex=oindex+4;
            deindex=deindex+4;
        }

        wb.close();

    }




}
