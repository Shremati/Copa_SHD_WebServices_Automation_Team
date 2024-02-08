package MODULES.WAVE3.DisplayTicketService.API_Tests;

import GENERICS.XMLParser;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.create_booking_service_onepax;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.create_booking_service_singlepax;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Booking_multiple_tickets;
import MODULES.WAVE3.DisplayTicketService.PreRequisites.Issue_multiple_tickets;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Multiple_Tickets extends FrameworkConstants
{

    public static String SOAPRequest;
    public static String TicketNumber_1;
    public static String TicketNumber_2;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Booking_multiple_tickets Prerequisite1 = new Booking_multiple_tickets();
        Prerequisite1.run();

        Issue_multiple_tickets Prerequisite2 = new Issue_multiple_tickets();
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
        Assert.assertTrue(response.getBody().asString().contains(TicketNumber_1));
        Assert.assertTrue(response.getBody().asString().contains(TicketNumber_2));

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayTicketService\\Multiple_Tickets.xml"));
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
        XSSFRow InputRow=sheet.getRow(4);

        String filepath1;

        filepath1=getRequestDirectory()+"DisplayTicketService\\Multiple_Tickets.xml";

        XMLParser.updateAttributeValueatIndex("dis1:TicketDocument","TicketDocumentNbr",InputRow.getCell(9).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("dis1:TicketDocument","TicketDocumentNbr",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),1);

        TicketNumber_1 = InputRow.getCell(9).getStringCellValue();
        TicketNumber_2 = InputRow.getCell(10).getStringCellValue();

        wb.close();

    }

}
