package MODULES.WAVE3.QueueService.API_Tests;


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
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Transfer_Queue_Today_to_End_Date extends FrameworkConstants {

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
                .post(getQueueservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        Assert.assertTrue(response.getBody().asString().contains("Success"));

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"QueueService\\Display_All_Queue_Cities.xml"));
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
        XSSFSheet sheet = wb.getSheet("QueueService");
        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=getRequestDirectory()+"QueueService\\Transfer_Queue_Today_to_End_Date.xml";

        XMLParser.updateAttributeValue("com:Source","AirlineVendorID",InputRow.getCell(1).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValueatIndex("que1:TransferFromQueue","PseudoCityCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("que1:TransferFromQueue","QueueNumber",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("que1:TransferFromQueue","End", Utils.getDate_YYYYMMdd(InputRow.getCell(3).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("que1:TransferToQueue","QueueNumber",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }
}
