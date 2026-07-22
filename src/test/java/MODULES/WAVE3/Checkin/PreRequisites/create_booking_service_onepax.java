package MODULES.WAVE3.Checkin.PreRequisites;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import GENERICS.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import frameworkconstants.*;
import reports.ExtentLogger;

public class create_booking_service_onepax extends FrameworkConstants
{

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public boolean run(int i) throws IOException, ParserConfigurationException, TransformerException, SAXException
    {


        UpdatePayload(i);

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
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
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");
        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

//        Assert.assertTrue(response.getBody().asString().contains("Success"), "Do not contain Success");
//        ExtentLogger.info("Assertion passed - contains Success");

        if(!(response.getBody().asString().contains("Success") )){
            return false;
        }

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
        XSSFSheet sheet = wb.getSheet("CheckIn");

        XSSFRow InputRow=sheet.getRow(10);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\Checkin\\PreRequisites\\create_booking_service_onepax.xml";

        XMLParser.updateAttributeValue("air1:FlightSegment","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
//        XMLParser.updateAttributeValue("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:FlightSegment","ResBookDesigCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment", "FlightNumber", availableFlights.get(InputRow.getCell(3).getStringCellValue() + "-" + InputRow.getCell(4).getStringCellValue()).get(i), getTemp_requestPath(), 0);


        try {
            // Update all persons
            List<String[]> generatedNames = XMLFakerUtil.updateAllNames(getTemp_requestPath());

            System.out.println("===== Generated Names =====");
            for (int k = 0; k < generatedNames.size(); k++) {
                System.out.println("Generated Person " + (k + 1) + ": "
                        + generatedNames.get(k)[0] + " " + generatedNames.get(k)[1]);
            }

            // Read all persons from XML
            List<String[]> xmlNames = XMLReaderUtil.getAllNames(getTemp_requestPath());

            System.out.println("===== Names Read From XML =====");
            for (int j = 0; j < xmlNames.size(); j++) {
                System.out.println("XML Person " + (j + 1) + ": "
                        + xmlNames.get(j)[0] + " " + xmlNames.get(j)[1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        wb.close();

    }


    public static void excelwriter(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CheckIn");
        XSSFRow InputRow=sheet.getRow(10);

        InputRow.getCell(2).setCellValue(availableFlights.get(InputRow.getCell(3).getStringCellValue() + "-" + InputRow.getCell(4).getStringCellValue()).get(i));

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
//        String Givenname = XMLParser.GetTagText("GivenName",getTemp_responsePath());
//        String Surname = XMLParser.GetTagText("Surname",getTemp_responsePath());

        String Givenname = XMLParser.GetTagText("GivenName",getTemp_responsePath());
        String Surname = XMLParser.GetTagText("Surname",getTemp_responsePath());

        InputRow.getCell(8).setCellValue(Givenname);
        InputRow.getCell(9).setCellValue(Surname);

        InputRow.getCell(7).setCellValue(PNR);
//        InputRow.getCell(8).setCellValue(Givenname);
//        InputRow.getCell(9).setCellValue(Surname);


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
