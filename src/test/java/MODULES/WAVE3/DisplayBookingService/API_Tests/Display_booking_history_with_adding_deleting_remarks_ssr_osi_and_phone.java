package MODULES.WAVE3.DisplayBookingService.API_Tests;


import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.DisplayBookingService.PreRequisites.*;
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

public class Display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
//        Only 1 modification is done here under category other

        create_booking_display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone Prerequisite = new create_booking_display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone();
        Prerequisite.run();

        display_booking_display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone Prerequisite2 = new display_booking_display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone();
        Prerequisite2.run();

        modify_ticket_display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone Prerequisite3 = new modify_ticket_display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone();
        Prerequisite3.run(); //ModificationType="5" , so we change other things , we are modifying or to be specific we are deleting the older remarks and adding new remark



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
                .post(getDisplaybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        Assert.assertTrue(response.getBody().asString().contains("Success"));
        Assert.assertTrue(response.getBody().asString().contains("BookingHistory"));
        Assert.assertTrue(response.getBody().asString().contains("Operation=\"Add\">OTRO REMARK"));

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DisplayBookingService\\Display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone.xml"));
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
        XSSFSheet sheet = wb.getSheet("DisplayBookingService");
        XSSFRow InputRow=sheet.getRow(18);

        String filepath1;
        filepath1=getRequestDirectory()+"DisplayBookingService\\Display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone.xml";

        XMLParser.updateAttributeValueatIndex("read:UniqueID","ID", InputRow.getCell(10).getStringCellValue(),filepath1,0);

        wb.close();

    }

}

//<air1:Remark>SOUTH PARK</air1:Remark>
//<air1:Remark>REMARK ONE</air1:Remark>
//<air1:Remark>REMARK TWO</air1:Remark>
//<air1:Remark>REMARK THREE</air1:Remark>
//<air1:Remark>FOR MODIFY TRAVELER INFO TEST</air1:Remark>

//This below piece of tag in modify request will delete the remarks and add new remarks

//<!--Remarks-->
//<air1:Remarks>
//<air1:Remark Operation="Delete">REMARK ONE</air1:Remark>
//<air1:Remark Operation="Delete">REMARK TWO</air1:Remark>
//<air1:Remark Operation="Delete">REMARK THREE</air1:Remark>
//<air1:Remark Operation="Add">OTRO REMARK</air1:Remark>
//</air1:Remarks>