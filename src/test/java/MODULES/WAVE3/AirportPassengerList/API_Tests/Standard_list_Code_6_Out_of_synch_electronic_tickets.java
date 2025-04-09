package MODULES.WAVE3.AirportPassengerList.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.AirportPassengerList.PreRequisites.CreateBooking_StandardList_Code_6_out_of_sync;
import MODULES.WAVE3.AirportPassengerList.PreRequisites.Issue_Ticket_Standard_list_Code_6;
import MODULES.WAVE3.AirportPassengerList.PreRequisites.Modify_booking_StandardList_Code_6_out_of_sync;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;


public class Standard_list_Code_6_Out_of_synch_electronic_tickets extends FrameworkConstants
{
    public static String SOAPRequest;
    public static String PNR;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        CreateBooking_StandardList_Code_6_out_of_sync PreRequisite1 = new CreateBooking_StandardList_Code_6_out_of_sync();
        PreRequisite1.run();
        ExtentLogger.info("PreRequisite1");

        Issue_Ticket_Standard_list_Code_6 PreRequisite2 = new Issue_Ticket_Standard_list_Code_6();
        PreRequisite2.run();
        ExtentLogger.info("PreRequisite2");

        Modify_booking_StandardList_Code_6_out_of_sync PreRequisite3 = new Modify_booking_StandardList_Code_6_out_of_sync();
        PreRequisite3.run();
        ExtentLogger.info("PreRequisite3");

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getAirportpassengerlist());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getAirportpassengerlist())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AirportPassengerList\\Standard_list_Code_6_Out_of_sync_electronic_tickets.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("FlightInfo"), "Does not contain \"FlightInfo\" in the response");
        ExtentLogger.info("Assertion passed - contains \"FlightInfo\"");

        Assert.assertTrue(response.getBody().asString().contains("ID=\""+PNR+"\""), "Does not contain \"ID=\\"+PNR+"\"\" in the response");
        ExtentLogger.info("Assertion passed - contains \"ID=\\"+PNR+"\"\"");

        Assertions.AssertWarning(response,false);
        ExtentLogger.info("Assertion passed - do not have warning");

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
        XSSFSheet sheet = wb.getSheet("AirportPassengerList");
        XSSFRow InputRow=sheet.getRow(7);

        String filepath1;
        filepath1=getRequestDirectory()+"AirportPassengerList\\Standard_list_Code_6_Out_of_sync_electronic_tickets.xml";


//We need to give the modified itenary details

        XMLParser.updateAttributeValue("air1:FlightInfo","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(11).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air1:FlightInfo","FlightNumber",InputRow.getCell(12).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(13).getStringCellValue(),getTemp_requestPath());

        PNR = InputRow.getCell(7).getStringCellValue();
        wb.close();

    }
}
