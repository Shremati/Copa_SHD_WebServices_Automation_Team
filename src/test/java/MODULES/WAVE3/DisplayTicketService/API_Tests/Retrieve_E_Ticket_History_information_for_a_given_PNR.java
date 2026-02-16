package MODULES.WAVE3.DisplayTicketService.API_Tests;


import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;

import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_booking_delete_API_data_Address;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Create_NonTicketed_PNR;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Create_booking_display_history_info_for_given_two_tkts;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Issue_NonTicketed_PNR;
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

public class Retrieve_E_Ticket_History_information_for_a_given_PNR extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
//        Create_NonTicketed_PNR Prerequisite1 = new Create_NonTicketed_PNR();
//        Prerequisite1.run();
//        ExtentLogger.info("Prerequisite1");

        int i=0;
        boolean flightFound=false;

//We are searching all the available flights in a do while loop
        Create_NonTicketed_PNR Prerequisite = new Create_NonTicketed_PNR();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite");


        Issue_NonTicketed_PNR Prerequisite2 = new Issue_NonTicketed_PNR();
        Prerequisite2.run();
        ExtentLogger.info("Prerequisite2");
        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getDisplayticketservices());

        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification
                .body(SOAPRequest)
                .when()
                .post(getDisplayticketservices())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");

        Assert.assertTrue(response.getBody().asString().contains("Success"), "Does not contain \"Success\" in the response");
        ExtentLogger.info("Assertion passed - contains \"Success\"");

        Assert.assertTrue(response.getBody().asString().contains("HistoryInfos"), "Does not contain \"HistoryInfos\" in the response");
        ExtentLogger.info("Assertion passed - contains \"HistoryInfos\"");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayTicketService\\Retrieve_E_Ticket_History_information_for_a_given_PNR.xml"));
        writer.write(response.asPrettyString());
        writer.close();


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
        XSSFSheet sheet = wb.getSheet("DisplayTicketService");
        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayTicketService\\Retrieve_E_Ticket_History_information_for_a_given_PNR.xml";

        XMLParser.SetTagtext("RecordLocator",InputRow.getCell(8).getStringCellValue(),filepath1);
        wb.close();

    }
}