package MODULES.WAVE3.AirportPassengerList.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.AirportPassengerList.PreRequisites.Create_Booking_26;
import MODULES.WAVE3.AirportPassengerList.PreRequisites.Create_Booking_with_Large_Parties;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class Large_parties extends FrameworkConstants
{
    public static String SOAPRequest;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        Create_Booking_with_Large_Parties Prerequisite =new Create_Booking_with_Large_Parties();
        Prerequisite.run();

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
                .post(getAirportpassengerlist())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AirportPassengerList\\Large_parties.xml"));
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
        XSSFSheet sheet = wb.getSheet("AirportPassengerList");
        XSSFRow InputRow=sheet.getRow(12);
        String filepath1;
        filepath1=getRequestDirectory()+"AirportPassengerList\\Large_parties.xml";



        XMLParser.updateAttributeValue("air1:FlightInfo","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air1:FlightInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:ListFunction","ListType",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath());


        wb.close();

    }
}
