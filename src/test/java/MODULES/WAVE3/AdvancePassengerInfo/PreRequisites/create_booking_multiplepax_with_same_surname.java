package MODULES.WAVE3.AdvancePassengerInfo.PreRequisites;

import GENERICS.RESTWrapper;
import GENERICS.Utils;
import GENERICS.XMLParser;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;
import frameworkconstants.*;

public class create_booking_multiplepax_with_same_surname extends FrameworkConstants
{

    public static String SOAPRequest;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//                       ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);


        Response response = RESTWrapper.postResponse(getBaseURL(),getCreatebookingservice(),SOAPRequest);

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
        XSSFSheet sheet = wb.getSheet("AdvancePassengerInfo");

        XSSFRow InputRow=sheet.getRow(2);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\AdvancePassengerInfo\\PreRequisites\\create_booking_multiplepax_with_same_surname.xml";

        XMLParser.updateAttributeValue("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("air1:FlightSegment","ResBookDesigCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());

//        XMLParser.updateAttributeValue("air1:FareBasisCode","LocationCode",InputRow.getCell(4).getStringCellValue(),filepath);

        wb.close();
    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("AdvancePassengerInfo");
        XSSFRow InputRow=sheet.getRow(2);



        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
        InputRow.getCell(7).setCellValue(PNR);

        InputRow.getCell(8).setCellValue(XMLParser.GetTagTextatIndex("GivenName",getTemp_responsePath(),0));
        InputRow.getCell(9).setCellValue(XMLParser.GetTagTextatIndex("Surname",getTemp_responsePath(),0));
        InputRow.getCell(10).setCellValue(XMLParser.GetTagTextatIndex("GivenName",getTemp_responsePath(),1));
        InputRow.getCell(11).setCellValue(XMLParser.GetTagTextatIndex("Surname",getTemp_responsePath(),1));
        InputRow.getCell(12).setCellValue(XMLParser.GetTagTextatIndex("GivenName",getTemp_responsePath(),2));
        InputRow.getCell(13).setCellValue(XMLParser.GetTagTextatIndex("Surname",getTemp_responsePath(),2));



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
