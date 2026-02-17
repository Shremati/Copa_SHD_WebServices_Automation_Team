package MODULES.WAVE3.BagTagsDisplayService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.BagTagsDisplayService.PreRequisites.*;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;
import org.testng.Assert;
import java.nio.charset.StandardCharsets;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Display_Bag_Tag_By_Tag_Number extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {

//        ExtentLogger.info("Prerequisite 1");
//        Create_booking_bagtag_by_tag_number Prerequisite = new Create_booking_bagtag_by_tag_number();
//        Prerequisite.run();
        int i=0;
        boolean flightFound=false;
        ExtentLogger.info("Prerequisite1");

//We are searching all the available flights in a do while loop
        Create_booking_bagtag_by_tag_number Prerequisite = new  Create_booking_bagtag_by_tag_number();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite 2");
        Issue_ticket_bagtag_by_tag_number Prerequisite1 = new Issue_ticket_bagtag_by_tag_number();
        Prerequisite1.run();

        ExtentLogger.info("Prerequisite 3");
        Display_APIS_bagtag_by_tag_number Prerequisite2 = new Display_APIS_bagtag_by_tag_number();
        Prerequisite2.run();

        ExtentLogger.info("Prerequisite 4");
        Add_APIS_bagtag_by_tag_number Prerequisite3 = new Add_APIS_bagtag_by_tag_number();
        Prerequisite3.run();

        ExtentLogger.info("Prerequisite 5");
        Checkin_and_baggage_bagtag_by_tag_number Prerequisite4 = new Checkin_and_baggage_bagtag_by_tag_number();
        Prerequisite4.run();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getAuthorizationservice());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest); 

        Response response = requestSpecification.body(SOAPRequest)
                .when()
                .post(getBagtags())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "BagTagDisplayService\\Display_Bag_Tag_By_Tag_Number.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"),
                "Do not contain Success");
        ExtentLogger.info("Assertion passed - contain Success");

        Assert.assertTrue(response.getBody().asString().contains("BagTagDetails"),
                "Do not contain BagTagDetails");
        ExtentLogger.info("Assertion passed - contain BagTagDetails");

        Assert.assertFalse(response.getBody().asString().contains("No BagTags Found"),
                "Do not contain No BagTags Found");
        ExtentLogger.info("Assertion passed - contain No BagTags Found");

        Assertions.AssertWarning(response, false);
        ExtentLogger.info("Assertion passed - Does not contain warning");

        Assertions.AssertResponseTime(response, ResponseTime);

//                ********* Clearing Temp_Request.xml *********
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.flush();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        //        ********** Reading Testdata from Excel ************

        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("BagTags");
        XSSFRow InputRow = sheet.getRow(3);

        String filepath1;
        filepath1 = getRequestDirectory() + "BagTagDisplayService\\Display_Bag_Tag_By_Tag_Number.xml";


        XMLParser.updateAttributeValue("bag1:FlightLegInfo", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()), filepath1);
        XMLParser.updateAttributeValue("bag1:FlightLegInfo", "ArrivalDateTime", InputRow.getCell(13).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("bag1:FlightLegInfo", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("bag1:FlightLegInfo", "RPH", InputRow.getCell(4).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport", "LocationCode", InputRow.getCell(5).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport", "LocationCode", InputRow.getCell(6).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:OperatingAirline", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());

        wb.close();
    }

}
