package MODULES.WAVE3.Checkin.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.Checkin.PreRequisites.*;
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

public class Check_in_specific_pax_in_group extends FrameworkConstants {
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Create_booking_specific_pax_in_group Prerequsite1 = new Create_booking_specific_pax_in_group();
        Prerequsite1.run();

        Display_booking_specific_pax_in_group Prerequsite2 = new Display_booking_specific_pax_in_group();
        Prerequsite2.run();

        Modify_booking_specific_pax_in_group Prerequsite3 = new Modify_booking_specific_pax_in_group();
        Prerequsite3.run();

        Issue_booking_specific_pax_in_group Prerequsite4 = new Issue_booking_specific_pax_in_group();
        Prerequsite4.run();

        Display_APIS_details_specific_pax_in_group Prerequsite5 = new Display_APIS_details_specific_pax_in_group();
        Prerequsite5.run();

        Add_APIS_details_specific_pax_in_group Prerequsite6 = new Add_APIS_details_specific_pax_in_group();
        Prerequsite6.run();


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(SOAPRequest)
                .when()
                .post(getCheckin())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "Checkin\\Check_in_specific_pax_in_group.xml"));
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
        XSSFSheet sheet = wb.getSheet("CheckIn");
        XSSFRow InputRow=sheet.getRow(9); //Taking scenario create booking for 1 pax

        String filepath1;
        filepath1=getRequestDirectory()+"Checkin\\Check_in_specific_pax_in_group.xml";


        XMLParser.updateAttributeValue("com1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("com1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("com1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }
}
