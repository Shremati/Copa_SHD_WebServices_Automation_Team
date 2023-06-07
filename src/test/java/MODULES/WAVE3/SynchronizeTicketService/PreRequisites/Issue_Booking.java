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

public class Issue_Booking extends FrameworkConstants {


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
                .post(getIssueticket())
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

        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\SynchronizeTicketService\\PreRequisites\\Issue_Booking.xml";


//        XMLParser.updateAttributeValue("tic1:EDS_TicketingRQ","TimeStamp",Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(4).getNumericCellValue()),filepath1);
        XMLParser.SetTagtextatIndex("tic1:RecordLocator", InputRow.getCell(5).getStringCellValue(),filepath1,0);


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


//        InputRow.getCell(9).setCellValue(230 + XMLParser.GetAttributeValueatIndex("ns4:TicketInfo","TicketNumber",getTemp_responsePath(),0));
////        InputRow.getCell(10).setCellValue(XMLParser.GetAttributeValueatIndex("ns4:TicketInfo","TicketNumber",getTemp_responsePath(),1));
////        String  Ticket_One = XMLParser.GetAttributeValue("ns3:BookingReferenceID","ID",getTemp_responsePath());
////        String  Ticket_Two = XMLParser.GetTagText("GivenName",getTemp_responsePath());
//
//
//
////        InputRow.getCell(7).setCellValue(Ticket_One);
////        InputRow.getCell(8).setCellValue(Ticket_Two);
        String TransactionIdentifie = XMLParser.GetAttributeValue("ns4:TicketingResults","RecordLocator",getTemp_responsePath());
//        String Givenname = XMLParser.GetTagText("GivenName",getTemp_responsePath());
//        String Surname = XMLParser.GetTagText("Surname",getTemp_responsePath());


        InputRow.getCell(5).setCellValue(TransactionIdentifie);
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
