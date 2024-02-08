package MODULES.WAVE3.DisplayTicketService.API_Tests;

import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Booking_multiple_tickets;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Create_booking_conjunctive_tkt_primary;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Issue_booking_conjunctive_ticket_primary;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Issue_multiple_tickets;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Conjunctive_ticket_primary extends FrameworkConstants {

    public static String SOAPRequest;
    public static String Primary;
    public static String Conjunctive;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        Create_booking_conjunctive_tkt_primary Prerequisite1 = new Create_booking_conjunctive_tkt_primary();
        Prerequisite1.run();

        Issue_booking_conjunctive_ticket_primary Prerequisite2 = new Issue_booking_conjunctive_ticket_primary();
        Prerequisite2.run();

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
                .post(getDisplayticketservices())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        //Getting ticketnumber from excelwriter
        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("<ns6:FormAndSerialNumber>" + Primary +"</ns6:FormAndSerialNumber>"));
        Assert.assertTrue(response.getBody().asString().contains("<ns6:FormAndSerialNumber>" + Conjunctive +"</ns6:FormAndSerialNumber>"));

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayTicketService\\Conjunctive_ticket_primary.xml"));
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
        XSSFSheet sheet = wb.getSheet("DisplayTicketService");
        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayTicketService\\Conjunctive_ticket_primary.xml";

        String primary = InputRow.getCell(9).getStringCellValue();
        String conjunctive = InputRow.getCell(10).getStringCellValue().substring(11,13);
        String TicketDocumentNbr = primary + "-" + conjunctive;

        XMLParser.updateAttributeValue("dis1:TicketDocument","TicketDocumentNbr",TicketDocumentNbr,filepath1);

        Primary = InputRow.getCell(9).getStringCellValue().substring(3,13);
        Conjunctive = InputRow.getCell(10).getStringCellValue().substring(3,13);

        wb.close();

    }

}
