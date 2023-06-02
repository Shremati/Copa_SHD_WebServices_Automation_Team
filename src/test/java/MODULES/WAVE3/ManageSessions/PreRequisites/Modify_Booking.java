package MODULES.WAVE3.ManageSessions.PreRequisites;

import GENERICS.Utils;
import GENERICS.XMLParser;
import frameworkconstants.FrameworkConstants;
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

public class Modify_Booking extends FrameworkConstants {


    public static String SOAPRequest;


    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {


        UpdatePayload();

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);



        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .body(SOAPRequest)
                .when()
                .post(getModifybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

//                ********* Clearing Temp_Request.xml *********

        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();


        excelwriter();


    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("ManageSessions");

        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\ManageSessions\\PreRequisites\\Modify_Booking.xml";

        XMLParser.updateAttributeValue("n1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("n1:FlightSegment","ArrivalDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(2).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:BookingReferenceID","ID", InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("n5:BookingReferenceID","ID", InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());







//
//
//        XMLParser.updateAttributeValue("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());
//        XMLParser.updateAttributeValue("tic1:EDS_TicketingRQ","TimeStamp",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(4).getNumericCellValue()),filepath1);
//        XMLParser.SetTagtextatIndex("tic1:RecordLocator", InputRow.getCell(8).getStringCellValue(),filepath1,0);
//
//        XMLParser.updateAttributeValue("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
////        XMLParser.updateAttributeValueatIndex("air:Ticketing", "TicketTimeLimit", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(3).getNumericCellValue()), getTemp_requestPath(), 0);
//
//        //        XMLParser.updateAttributeValue("air1:FlightSegment","DepartureDateTime",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
////        XMLParser.updateAttributeValue("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
////        XMLParser.updateAttributeValue("air1:FlightSegment","ResBookDesigCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());
////        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
////        XMLParser.updateAttributeValue("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());
//
////        XMLParser.updateAttributeValue("air1:FareBasisCode","LocationCode",InputRow.getCell(4).getStringCellValue(),filepath);

        wb.close();

    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("ManageSessions");
        XSSFRow InputRow=sheet.getRow(1);



        String TransactionIdentifie = XMLParser.GetAttributeValue("ns6:OTA_AirBookRS","TransactionIdentifier",getTemp_responsePath());
//        String Givenname = XMLParser.GetTagText("GivenName",getTemp_responsePath());
//        String Surname = XMLParser.GetTagText("Surname",getTemp_responsePath());


        InputRow.getCell(3).setCellValue(TransactionIdentifie);
//        InputRow.getCell(8).setCellValue(Givenname);
//        InputRow.getCell(9).setCellValue(Surname);


        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

//          ********* Clearing Temp_Response.xml *********

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(getTemp_responsePath()));
        writer.write("");
        writer.close();

    }

}
