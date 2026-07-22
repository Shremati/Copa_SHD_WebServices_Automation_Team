package MODULES.WAVE3.AirportPassengerList.PreRequisites;

import GENERICS.*;

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
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class create_booking_for_one_pax_code_25 extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;


    public boolean run(int i) throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload(i);

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getCreatebookingservice());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());


        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        if(!(response.getBody().asString().contains("Success") )){
            return false;
        }

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        Assert.assertFalse(response.getBody().asString().contains("Sell Itinerary Process Failed to Complete Successfully :  (1) FLT NOOP FOR FLT/DATE"));
        ExtentLogger.info("Response contains \"Sell Itinerary Process Failed to Complete Successfully :  (1) FLT NOOP FOR FLT/DATE\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - do not have warning");

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
        XSSFSheet sheet = wb.getSheet("AirportPassengerList");

        XSSFRow InputRow=sheet.getRow(18);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\AirportPassengerList\\PreRequisites\\create_booking_for_one_pax_code_25.xml";


        XMLParser.updateAttributeValue("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
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
        XSSFSheet sheet = wb.getSheet("AirportPassengerList");
        XSSFRow InputRow=sheet.getRow(18);

        InputRow.getCell(2).setCellValue(availableFlights.get(InputRow.getCell(3).getStringCellValue() + "-" + InputRow.getCell(4).getStringCellValue()).get(i));

        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());

        InputRow.getCell(7).setCellValue(PNR);

        String Givenname = XMLParser.GetTagText("GivenName",getTemp_responsePath());
        String Surname = XMLParser.GetTagText("Surname",getTemp_responsePath());



        InputRow.getCell(17).setCellValue(Givenname);
        InputRow.getCell(18).setCellValue(Surname);


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
