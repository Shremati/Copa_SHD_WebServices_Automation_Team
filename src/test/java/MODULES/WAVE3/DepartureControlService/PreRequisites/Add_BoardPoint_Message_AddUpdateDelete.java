package MODULES.WAVE3.DepartureControlService.PreRequisites;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static frameworkconstants.FrameworkConstants.*;
import static frameworkconstants.FrameworkConstants.getTemp_requestPath;
import static io.restassured.RestAssured.given;

public class Add_BoardPoint_Message_AddUpdateDelete
{
    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public  boolean run(int i) throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        UpdatePayload(i);  //Adding 3 messages as per request

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getDeparturecontrolservice());

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

        if(!(response.getBody().asString().contains("Success") &&
                response.getBody().asString().contains("MessageInfo"))){
            return false;

        }

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Not contains success in response");
        ExtentLogger.info("Assertion passed - contains Success");

        Assert.assertTrue(response.getBody().asString().contains("MessageInfo"),"Not contains MessageInfo in response");
        ExtentLogger.info("Assertion passed - contains MessageInfo");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - Do not contain Warning");

        Assertions.AssertResponseTime(response,ResponseTime);

//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();

        excelwriter(i);
        return true;

    }


    public static void UpdatePayload(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("DepartureControlService");
        XSSFRow InputRow=sheet.getRow(14);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\DepartureControlService\\PreRequisites\\Add_BoardPoint_Message.xml";


        XMLParser.updateAttributeValueatIndex("dep1:FlightLegInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("dep1:FlightLegInfo","FlightNumber",availableFlights.get(InputRow.getCell(7).getStringCellValue()+ "-"+InputRow.getCell(8).getStringCellValue()).get(i),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }

    public String getMessage(int RPH) throws ParserConfigurationException, IOException, TransformerException, SAXException
    {
        String filepath;
        filepath=".\\src\\test\\java\\MODULES\\WAVE3\\DepartureControlService\\PreRequisites\\Add_BoardPoint_Message.xml";
        System.out.println("ns4:MessageInfo RPH=\"0"+(RPH+1)+"\"");
        return XMLParser.GetTagTextatIndex("air1:MessageInfo",filepath,RPH);
    }

    public static void excelwriter(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("DepartureControlService");
        XSSFRow InputRow = sheet.getRow(14);

//        String filepath;
//        filepath =  ".\\src\\test\\java\\MODULES\\WAVE3\\DepartureControlService\\PreRequisites\\Add_BoardPoint_Message.xml";

        String flight = XMLParser.GetAttributeValue("ns6:FlightLegInfo", "FlightNumber", getTemp_responsePath());

        InputRow.getCell(2).setCellValue(flight);

        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

//          ********* Clearing Temp_Response.xml *********

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(getTemp_responsePath()));
        writer.write("");
        writer.close();

    }
}
