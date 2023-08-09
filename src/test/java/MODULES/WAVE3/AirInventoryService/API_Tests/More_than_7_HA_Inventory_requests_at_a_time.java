package MODULES.WAVE3.AirInventoryService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
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
        XSSFRow InputRow=sheet.getRow(3); //Taking scenario create booking for 1 pax

        String filepath1;
        filepath1=getRequestDirectory()+"AirInventoryService\\More than 7 HA Inventory requests at a time.xml";



        XMLParser.SetTagtextatIndex("air1:FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1,0);
        XMLParser.SetTagtextatIndex("air1:Date", Utils.getDate_YYYYMMdd(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath(),0);

        int j=5,k=6;
        for(int i=1;i<=7;i++)
        {

            XMLParser.SetTagtextatIndex("air1:FlightNumber",InputRow.getCell(k).getStringCellValue(),getTemp_requestPath(),i);
            XMLParser.SetTagtextatIndex("air1:Date",Utils.getDate_YYYYMMdd(InputRow.getCell(j).getNumericCellValue()),getTemp_requestPath(),i);
            j=j+4;
            k=k+4;

        }

        wb.close();

    }




}
