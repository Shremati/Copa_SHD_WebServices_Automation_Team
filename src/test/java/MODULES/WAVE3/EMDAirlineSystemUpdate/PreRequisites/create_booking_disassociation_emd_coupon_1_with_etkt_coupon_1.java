package MODULES.WAVE3.EMDAirlineSystemUpdate.PreRequisites;

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

public class create_booking_disassociation_emd_coupon_1_with_etkt_coupon_1 extends FrameworkConstants
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
                .post(getCreatebookingservice())
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
        XSSFSheet sheet = wb.getSheet("EMDAirlineSystemUpdate");

        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\EMDAirlineSystemUpdate\\PreRequisites\\create_booking_disassociation_emd_coupon_1_with_etkt_coupon_1.xml";


        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air:Ticketing","TicketTimeLimit",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(5).getNumericCellValue()),getTemp_requestPath(),0);


        wb.close();
    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("EMDAirlineSystemUpdate");
        XSSFRow InputRow=sheet.getRow(3);


        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
        InputRow.getCell(22).setCellValue(PNR);

        InputRow.getCell(23).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","DepartureDateTime",getTemp_responsePath(),0));
        InputRow.getCell(24).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","FlightNumber",getTemp_responsePath(),0));
        InputRow.getCell(25).setCellValue(XMLParser.GetAttributeValueatIndex("ns3:FlightSegment","ArrivalDateTime",getTemp_responsePath(),0));
        InputRow.getCell(26).setCellValue(XMLParser.GetAttributeValueatIndex("DepartureAirport","LocationCode",getTemp_responsePath(),0));
        InputRow.getCell(27).setCellValue(XMLParser.GetAttributeValueatIndex("ArrivalAirport","LocationCode",getTemp_responsePath(),0));


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
