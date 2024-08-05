package MODULES.WAVE3.DepartureControlService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.DepartureControlService.PreRequisites.Add_BoardPoint_Message;
import MODULES.WAVE3.DepartureControlService.PreRequisites.Add_BoardPoint_Message_AddUpdateDelete;
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

public class add_Update_Delete_board_point_messages extends FrameworkConstants
{
    public static String SOAPRequest;
    public static String Message1;
    public static String Message2;
    public static String Message3;
    static RequestSpecification requestSpecification;
    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        ExtentLogger.info("Prerequisite 1");
        Add_BoardPoint_Message_AddUpdateDelete Prerequisite = new Add_BoardPoint_Message_AddUpdateDelete();
        Prerequisite.run();  //Prerequisite to add Board Point Messages

        Message1= Prerequisite.getMessage(0);  //Fetching those messages which have been added to request
        Message2= Prerequisite.getMessage(1);
        Message3= Prerequisite.getMessage(2);

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getAuthorizationservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getDeparturecontrolservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DepartureControlService\\add_Update_Delete_board_point_messages.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Not contains success in response");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertFalse(response.getBody().asString().contains(Message1),"Not contains " + Message1 +" in response");  //As we are deleting the messages , so asserting false. After deleting the messages for RPH 1 and 2 should not be there.
        ExtentLogger.info("Assertion passed - contains " + Message1);

        Assert.assertFalse(response.getBody().asString().contains(Message2),"Not contains " + Message2 +" in response");  //As we are deleting the messages , so asserting false. After deleting the messages for RPH 1 and 2 should not be there.
        ExtentLogger.info("Assertion passed - contains " + Message2);

        Assert.assertTrue(response.getBody().asString().contains("NEW MESSAGE TESTCASE SIX"),"Not contains NEW MESSAGE TESTCASE SIX in response"); //Asserting whether new Message has been added or not
        ExtentLogger.info("Assertion passed - contains NEW MESSAGE TESTCASE SIX");

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
        XSSFSheet sheet = wb.getSheet("DepartureControlService");
        XSSFRow InputRow=sheet.getRow(14);

        String filepath1;
        filepath1=getRequestDirectory()+"DepartureControlService\\add_Update_Delete_board_point_messages.xml";


        XMLParser.updateAttributeValueatIndex("dep1:FlightLegInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("dep1:FlightLegInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }




}
