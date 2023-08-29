package MODULES.WAVE3.Standby.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.Checkin.API_Tests.check_in_non_revenue_pax;
import MODULES.WAVE3.Checkin.Checkin;
import MODULES.WAVE3.Standby.Prerequisites.*;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class STB_03_Clear_Standby extends FrameworkConstants {

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Create_Booking_Non_Revenue_Pax Prerequisite1 = new Create_Booking_Non_Revenue_Pax();
        Prerequisite1.run();

        Issue_ticket_non_revenue_pax Prerequisite2 = new Issue_ticket_non_revenue_pax();
        Prerequisite2.run();

        Display_Non_Revenue_pax Prerequisite3 = new Display_Non_Revenue_pax();
        Prerequisite3.run();

        Modify_APIS_Non_Revenue_pax Prerequisite4 = new Modify_APIS_Non_Revenue_pax();
        Prerequisite4.run();

        Check_in_Non_Revenue_pax Prerequisite5 = new Check_in_Non_Revenue_pax();
        Prerequisite5.run();

        Enable_Standby Prerequisite6 = new Enable_Standby();
        Prerequisite6.run();

        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(SOAPRequest)
                .when()
                .post(getStandby())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"Standby\\STB_03_Clear_Standby.xml"));
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
        XSSFSheet sheet = wb.getSheet("Standby");
        XSSFRow InputRow=sheet.getRow(3); //The given seat should be availble

        String filepath1;
        filepath1=getRequestDirectory()+"Standby\\STB_03_Clear_Standby.xml";

        XMLParser.updateAttributeValue("air1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMdd(InputRow.getCell(4).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air1:CarrierInfo","FlightNumber",InputRow.getCell(1).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:DepartureInformation","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:CarrierInfo","ResBookDesigCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }
}
