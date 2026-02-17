package MODULES.WAVE3.SeatMapService.API_Tests;

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

public class Request_is_for_more_greater_than_max_allowed_5 extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        boolean flightFound = false;
        Response response = null;
        int i = 0;

        do{

            UpdatePayload(i);

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getSeatmapservice());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

         response = requestSpecification.body(SOAPRequest)
                .body(SOAPRequest)
                .when()
                .post(getSeatmapservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
            if(response.getBody().asString().contains("Success") && response.getBody().asString().contains("CabinType=\"Economy\"") && response.getBody().asString().contains("CabinType=\"Business\"")){
                flightFound = true;
            }


            i++;

            if(i > 4){
                Assert.fail("No flights are having seats");
            }
        }
        while(!flightFound);
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"SeatMapService\\Request_is_for_more_greater_than_max_allowed_5.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("CabinType=\"Business\""), "Does not contain \"CabinType=\"Business\"\" in the response");
        ExtentLogger.info("Assertion passed - contains \"CabinType=\"Business\"\"");

        Assert.assertTrue(response.getBody().asString().contains("CabinType=\"Economy\""), "Does not contain \"CabinType=\"Economy\"\" in the response");
        ExtentLogger.info("Assertion passed - contains \"CabinType=\"Economy\"\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - do not have warning");

        Assertions.AssertResponseTime(response,ResponseTime);

//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();


    }


    public static void UpdatePayload(int i) throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("SeatMapService");
        XSSFRow InputRow=sheet.getRow(6);

        String filepath1;
        filepath1=getRequestDirectory()+"SeatMapService\\Request_is_for_more_greater_than_max_allowed_5.xml";

        //Segment 1
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
//        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber", availableFlights.get(InputRow.getCell(3).getStringCellValue() + "-" + InputRow.getCell(4).getStringCellValue()).get(i),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);

        //Segment 2
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()),getTemp_requestPath(),1);
//        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber", availableFlights.get(InputRow.getCell(7).getStringCellValue() + "-" + InputRow.getCell(8).getStringCellValue()).get(i),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),1);

        //Segment 3
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(9).getNumericCellValue()),getTemp_requestPath(),2);
//        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber", availableFlights.get(InputRow.getCell(11).getStringCellValue() + "-" + InputRow.getCell(12).getStringCellValue()).get(i),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(12).getStringCellValue(),getTemp_requestPath(),2);

        //Segment 4
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(13).getNumericCellValue()),getTemp_requestPath(),3);
//        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber",InputRow.getCell(14).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber", availableFlights.get(InputRow.getCell(15).getStringCellValue() + "-" + InputRow.getCell(16).getStringCellValue()).get(i),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),3);

        //Segment 5
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(17).getNumericCellValue()),getTemp_requestPath(),4);
//        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber",InputRow.getCell(18).getStringCellValue(),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber", availableFlights.get(InputRow.getCell(19).getStringCellValue() + "-" + InputRow.getCell(20).getStringCellValue()).get(i),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(19).getStringCellValue(),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(20).getStringCellValue(),getTemp_requestPath(),4);

        //Segment 6
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(21).getNumericCellValue()),getTemp_requestPath(),5);
//        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber",InputRow.getCell(22).getStringCellValue(),getTemp_requestPath(),5);
        XMLParser.updateAttributeValueatIndex("air:FlightSegmentInfo","FlightNumber", availableFlights.get(InputRow.getCell(23).getStringCellValue() + "-" + InputRow.getCell(24).getStringCellValue()).get(i),getTemp_requestPath(),5);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(23).getStringCellValue(),getTemp_requestPath(),5);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(24).getStringCellValue(),getTemp_requestPath(),5);

        wb.close();

    }

}
