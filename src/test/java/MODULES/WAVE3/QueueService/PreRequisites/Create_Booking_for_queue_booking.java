package MODULES.WAVE3.QueueService.PreRequisites;

import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;
import GENERICS.*;
import frameworkconstants.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.FileInputStream;

import static io.restassured.RestAssured.given;

public class Create_Booking_for_queue_booking extends FrameworkConstants {

    public static String SOAPRequest;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException{

        UpdatePayload();

//               ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
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
        XSSFSheet sheet = wb.getSheet("QueueService");

        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\QueueService\\PreRequisites\\Create_Booking_for_queue_booking.xml";

        XMLParser.updateAttributeValue("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(4).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air1:FlightSegment","FlightNumber",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(6).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport","LocationCode",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath());


        wb.close();

    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("QueueService");
        XSSFRow InputRow=sheet.getRow(3);



        String PNR = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
        InputRow.getCell(9).setCellValue(PNR);

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
