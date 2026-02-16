package MODULES.WAVE3.SynchronizeTicketService.PreRequisites;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import frameworkconstants.FrameworkConstants;
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

import static io.restassured.RestAssured.given;

public class Create_Booking2 extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public boolean run(int i) throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload(i);

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        ExtentLogger.info("Base URL : "+getBaseURL()+getCreatebookingservice());

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

        if(!(response.getBody().asString().contains("Success"))){
            return false;

        }

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),"Not contains \"Success\" in response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

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
        XSSFSheet sheet = wb.getSheet("SynchronizeTicketService");

        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\SynchronizeTicketService\\PreRequisites\\Create_Booking2.xml";

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(7).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(1).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",availableFlights.get(InputRow.getCell(1).getStringCellValue()+"-"+InputRow.getCell(2).getStringCellValue()).get(i),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","ResBookDesigCode",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),0);


        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(8).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",availableFlights.get(InputRow.getCell(4).getStringCellValue()+"-"+InputRow.getCell(5).getStringCellValue()).get(i),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","ResBookDesigCode",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),1);

        XMLParser.updateAttributeValueatIndex("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(9).getNumericCellValue()), getTemp_requestPath(), 0);
        wb.close();
    }


    public static void excelwriter(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("SynchronizeTicketService");
        XSSFRow InputRow=sheet.getRow(3);



        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
        InputRow.getCell(12).setCellValue(PNR);

        String flight1 = XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","FlightNumber",getTemp_responsePath(),0);
        InputRow.getCell(3).setCellValue(flight1);
        String flight2 = XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","FlightNumber",getTemp_responsePath(),1);
        InputRow.getCell(6).setCellValue(flight2);

        String ArrivalDate1 = XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","ArrivalDateTime",getTemp_responsePath(),0);
        InputRow.getCell(20).setCellValue(ArrivalDate1);
        String ArrivalDate2 = XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","ArrivalDateTime",getTemp_responsePath(),1);
        InputRow.getCell(21).setCellValue(ArrivalDate2);


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
