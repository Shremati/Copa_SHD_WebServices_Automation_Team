package MODULES.WAVE3.Checkin.PreRequisites;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
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

public class Modify_APIS_Checkin_3_Pax extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : "+getBaseURL()+getAdvancepassengerinfo());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response=requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getAdvancepassengerinfo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");
        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("APIS COMPLETE"),"Not contains \"APIS COMPLETE\" in response");
        ExtentLogger.info("Assertion passed - contains \"APIS COMPLETE\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - Do not contain Warning");

        Assertions.AssertResponseTime(response,ResponseTime);

//                     ********* Clearing Temp_Request.xml *********

        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();


    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CheckIn");

        XSSFRow InputRow=sheet.getRow(6);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\CheckIn\\PreRequisites\\Modify_APIS_Checkin_3_Pax.xml";

        XMLParser.updateAttributeValue("air:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("com:OperatingAirline","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());


        //Pax 1
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID", InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(18).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(19).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),3);

        //Pax 2
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID", InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(18).getStringCellValue(),getTemp_requestPath(),4);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(19).getStringCellValue(),getTemp_requestPath(),5);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),6);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),7);

        //Pax 3
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID", InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(18).getStringCellValue(),getTemp_requestPath(),8);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(19).getStringCellValue(),getTemp_requestPath(),9);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),10);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),11);

        wb.close();

    }

}
