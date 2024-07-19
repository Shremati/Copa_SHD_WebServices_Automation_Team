package MODULES.WAVE3.ScreenTextService.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import MODULES.WAVE3.ScreenTextService.PreRequisites.stateful_screenText_getToken;
import MODULES.WAVE3.ScreenTextService.PostRequest.stateful_screenText_releaseToken;
import MODULES.WAVE3.ScreenTextService.PreRequisites.stateful_screenText_send_entry_pre;
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

public class stateful_screenText_send_entry extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        stateful_screenText_getToken PreRequisite1 = new stateful_screenText_getToken();
        PreRequisite1.run();

        stateful_screenText_send_entry_pre PreRequisite2 = new stateful_screenText_send_entry_pre();
        PreRequisite2.run();

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
                .post(getScreentextservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"ScreenTextService\\stateful_screenText_send_entry.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("<ns4:TextData>D$</ns4:TextData>"));

        Assertions.AssertWarning(response,false);
        Assertions.AssertResponseTime(response,ResponseTime);


//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

        stateful_screenText_releaseToken PostRequest = new stateful_screenText_releaseToken();
        PostRequest.run();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("ScreenTextService");
        XSSFRow InputRow=sheet.getRow(4);

        String filepath1;
        filepath1=getRequestDirectory()+"ScreenTextService\\stateful_screenText_send_entry.xml";

        XMLParser.updateAttributeValue("scr1:OTA_ScreenTextRQ","TransactionIdentifier",InputRow.getCell(3).getStringCellValue(),filepath1);
        XMLParser.SetTagtextatIndex("scr1:ScreenEntry",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }
}
