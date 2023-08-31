package MODULES.WAVE3.TicketingService.API_Tests;


import GENERICS.XMLParser;
import MODULES.WAVE3.EMDAirlineSystemUpdate.PreRequisites.associate_coupon_disassociation_emd_coupon_1_with_etkt_coupon_1;
import MODULES.WAVE3.EMDAirlineSystemUpdate.PreRequisites.create_booking_disassociation_emd_coupon_1_with_etkt_coupon_1;
import MODULES.WAVE3.EMDAirlineSystemUpdate.PreRequisites.issue_ticket_disassociation_emd_coupon_1_with_etkt_coupon_1;
import MODULES.WAVE3.TicketingService.PreRequisites.create_booking_reissue_add_collect_with_credit_card;
import MODULES.WAVE3.TicketingService.PreRequisites.issue_ticket_reissue_add_collect_with_credit_card;
import MODULES.WAVE3.TicketingService.PreRequisites.modify_ticket_reissue_add_collect_with_credit_card;
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

public class Reissue_add_collect_with_credit_card extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        create_booking_reissue_add_collect_with_credit_card Prerequisite = new create_booking_reissue_add_collect_with_credit_card();
        Prerequisite.run();


        issue_ticket_reissue_add_collect_with_credit_card Prerequisite2 = new issue_ticket_reissue_add_collect_with_credit_card();
        Prerequisite2.run();


        modify_ticket_reissue_add_collect_with_credit_card Prerequisite3 = new modify_ticket_reissue_add_collect_with_credit_card();
        Prerequisite3.run(); // modificationtype=5 , flight itenary is getting changed


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
                .post(getTicketing())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"TicketingService\\Reissue_add_collect_with_credit_card.xml"));
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
        XSSFSheet sheet = wb.getSheet("TicketingService");
        XSSFRow InputRow=sheet.getRow(4);

        String filepath1;
        filepath1=getRequestDirectory()+"TicketingService\\Reissue_add_collect_with_credit_card.xml";

        XMLParser.SetTagtextatIndex("tic1:RecordLocator", InputRow.getCell(10).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValue("tic1:ExchangeInfo","ExchangeDocumentNumber",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath());



        wb.close();

    }


}
