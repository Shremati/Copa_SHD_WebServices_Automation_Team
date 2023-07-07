package MODULES.WAVE3.SynchronizeTicketService.PreRequisites;

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

public class modify_booking_adjust_flight_number_and_flight_date extends FrameworkConstants
{

    public static String SOAPRequest;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//                       ********** Reading the xml request file **********

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


//        excelwriter();

    }



    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("SynchronizeTicketService");

        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\SynchronizeTicketService\\PreRequisites\\modify_booking_adjust_flight_number_and_flight_date.xml";


        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),1);

        XMLParser.updateAttributeValue("air1:BookingReferenceID","ID", InputRow.getCell(20).getStringCellValue(),getTemp_requestPath());

        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(9).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ArrivalDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(10).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","FlightNumber",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(12).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(13).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(14).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ArrivalDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(15).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","FlightNumber",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(17).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(18).getStringCellValue(),getTemp_requestPath(),1);

        XMLParser.updateAttributeValue("n1:BookingReferenceID","ID", InputRow.getCell(20).getStringCellValue(),getTemp_requestPath());

        wb.close();
    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("SynchronizeTicketService");
        XSSFRow InputRow=sheet.getRow(3);


        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
        InputRow.getCell(9).setCellValue(PNR);

//        InputRow.getCell(10).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","DepartureDateTime",getTemp_responsePath(),0));
//        InputRow.getCell(11).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","FlightNumber",getTemp_responsePath(),0));
//        InputRow.getCell(12).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","ArrivalDateTime",getTemp_responsePath(),0));
//        InputRow.getCell(13).setCellValue(XMLParser.GetAttributeValueatIndex("DepartureAirport","LocationCode",getTemp_responsePath(),0));
//        InputRow.getCell(14).setCellValue(XMLParser.GetAttributeValueatIndex("ArrivalAirport","LocationCode",getTemp_responsePath(),0));
//
//        InputRow.getCell(15).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","DepartureDateTime",getTemp_responsePath(),1));
//        InputRow.getCell(16).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","FlightNumber",getTemp_responsePath(),1));
//        InputRow.getCell(17).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","ArrivalDateTime",getTemp_responsePath(),1));
//        InputRow.getCell(18).setCellValue(XMLParser.GetAttributeValueatIndex("DepartureAirport","LocationCode",getTemp_responsePath(),1));
//        InputRow.getCell(19).setCellValue(XMLParser.GetAttributeValueatIndex("ArrivalAirport","LocationCode",getTemp_responsePath(),1));

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
