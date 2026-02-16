package MODULES.WAVE3.ManageSessions.API_Tests;

import GENERICS.Assertions;
import GENERICS.XMLParser;
import java.nio.charset.StandardCharsets;
import MODULES.WAVE3.ManageSessions.PreRequisites.*;
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

public class Create_a_booking_for_two_segments extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Get_Token_03 Prerequisite1 = new Get_Token_03();
        Prerequisite1.run();
        ExtentLogger.info("Prerequisite1");
//
//        Add_session_segments Prerequisite2 = new Add_session_segments();
//        Prerequisite2.run();
//        ExtentLogger.info("Prerequisite2");

        int i=0;
        boolean flightFound=false;

//We are searching all the available flights in a do while loop
        Add_session_segments Prerequisite2 = new  Add_session_segments();
        do{
            if(i > 3){
                Assert.fail("No flights are having seats");
            }
            flightFound = Prerequisite2.run(i++);

        }while(!flightFound);

        ExtentLogger.info("Prerequisite2");

        Add_session_pax_data_03 Prerequisite3 = new Add_session_pax_data_03();
        Prerequisite3.run();
        ExtentLogger.info("Prerequisite3");

        Finalise_booking Prerequisite4 = new Finalise_booking();
        Prerequisite4.run();
        ExtentLogger.info("Prerequisite4");


        UpdatePayload();

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);
        ExtentLogger.info("Base URL : " + getBaseURL() + getManagesessions());


        requestSpecification = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured());
        ExtentLogger.logXMLRequest(SOAPRequest);

        Response response = requestSpecification.body(SOAPRequest)
                .body(SOAPRequest)
                .when()
                .post(getManagesessions())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());
        ExtentLogger.info("Response Time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"ManageSessions\\Create_a_booking_for_two_segments_ReleaseToken.xml"));
        writer.write(response.asPrettyString());
        writer.close();


        Assert.assertTrue(response.getBody().asString().contains("Success"), "Do not contain Success");
        ExtentLogger.info("Assertion passed - contains Success");

        Assertions.AssertWarning(response, false);
        ExtentLogger.info("Assertion passed - Do not have warning");

        Assertions.AssertResponseTime(response, ResponseTime);

//                ********* Clearing Temp_Request.xml *********

        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();


    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("ManageSessions");

        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=getRequestDirectory()+"ManageSessions\\Create_a_booking_for_two_segments_ReleaseToken.xml";

        XMLParser.updateAttributeValue("ses:EDS_SessionRQ","TransactionIdentifier",InputRow.getCell(3).getStringCellValue(),filepath1);


        wb.close();

    }


}
