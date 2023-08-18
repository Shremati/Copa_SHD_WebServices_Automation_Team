package MODULES.WAVE3.BagTagsDisplayService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.BagTagsDisplayService.PreRequisites.*;
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

public class Bag_Tag_Display_OA extends FrameworkConstants {

    public static String SOAPRequest;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Create_booking_service Prerequisite = new Create_booking_service();
        Prerequisite.run();

        Issue_ticket Prerequisite1 = new Issue_ticket();
        Prerequisite1.run();

        Display_APIS Prerequisite2 = new Display_APIS();
        Prerequisite2.run();

        Add_APIS Prerequisite3 = new Add_APIS();
        Prerequisite3.run();

        Checkin_and_baggage Prerequisite4 = new Checkin_and_baggage();
        Prerequisite4.run();


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
                .post(getBagtags())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"BagTagDisplayService\\Bag_Tag_Display_OA.xml"));
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
        XSSFSheet sheet = wb.getSheet("BagTags");
        XSSFRow InputRow=sheet.getRow(2);

        String filepath1;
        filepath1=getRequestDirectory()+"BagTagDisplayService\\Bag_Tag_Display_OA.xml";


        XMLParser.updateAttributeValue("bag1:FlightLegInfo", "DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("bag1:FlightLegInfo", "FlightNumber", InputRow.getCell(2).getStringCellValue(), getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport", "LocationCode", InputRow.getCell(3).getStringCellValue(), getTemp_requestPath());


        wb.close();
    }



}



