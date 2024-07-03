package MODULES.WAVE3.SynchronizeTicketService.PreRequisites;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
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
                .filter(new AllureRestAssured())
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

        Assertions.AssertWarning(response,false);
        Assertions.AssertResponseTime(response,ResponseTime);

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
        XSSFSheet sheet = wb.getSheet("SynchronizeTicketService");

        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\SynchronizeTicketService\\PreRequisites\\Modify_Booking.xml";

//        1st part of request i.e. <air1:AirItinerary> tag contains the segment to remove and then the segment to add
//        1st <air1:OriginDestinationOption> is the segment to cancel and status is 1.
//        Here we are providing details of 1st segment because we want to cancel that and provide new one

        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(7).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","ResBookDesigCode",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(1).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);

//      2nd <air1:OriginDestinationOption> is the new segment details that we want to add instead of older one
//      Here we are keeping the market same , in case of change we can provide a valid available market
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(13).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","ResBookDesigCode",InputRow.getCell(17).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(14).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),1);

// <!--we include the original reservation to check if both reservations(SHARES and this AirReservation) are in sync-->
//        2nd part of request i.e. <air:AirReservation> will contain the original booking itenary data
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(7).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","FlightNumber",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ResBookDesigCode",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(1).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ArrivalDateTime",InputRow.getCell(20).getStringCellValue(),getTemp_requestPath(),0);


        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(8).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","FlightNumber",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ResBookDesigCode",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),3);
        XMLParser.updateAttributeValueatIndex("n1:FlightSegment","ArrivalDateTime",InputRow.getCell(21).getStringCellValue(),getTemp_requestPath(),1);


        XMLParser.updateAttributeValue("air1:BookingReferenceID","ID", InputRow.getCell(12).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("n1:BookingReferenceID","ID", InputRow.getCell(12).getStringCellValue(),getTemp_requestPath());



        wb.close();

    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("SynchronizeTicketService");
        XSSFRow InputRow=sheet.getRow(1);



        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
        InputRow.getCell(12).setCellValue(PNR); //New PNR getting generated and overwriting the older PNR

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
