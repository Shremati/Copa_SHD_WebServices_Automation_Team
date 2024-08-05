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
import org.xml.sax.SAXException;
import reports.ExtentLogger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class Issue_booking_add_passenger_message extends FrameworkConstants {

    public static String SOAPRequest;
    static RequestSpecification requestSpecification;
    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {


        UpdatePayload();

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
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
                .post(getTicketing())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();
        ExtentLogger.logXMLResponse(response.asPrettyString());

        ExtentLogger.info("Response Time: "+response.getTimeIn(TimeUnit.MILLISECONDS) + "milliseconds");
        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

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

        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\Checkin\\PreRequisites\\Issue_booking_add_passenger_message.xml";

        XMLParser.updateAttributeValue("tic1:EDS_TicketingRQ","TimeStamp", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(15).getNumericCellValue()),filepath1);
        XMLParser.SetTagtextatIndex("tic1:RecordLocator",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),0);

        wb.close();

    }

}
