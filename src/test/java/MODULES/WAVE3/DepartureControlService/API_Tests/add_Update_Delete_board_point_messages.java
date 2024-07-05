package MODULES.WAVE3.DepartureControlService.API_Tests;

import GENERICS.Assertions;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.DepartureControlService.PreRequisites.Add_BoardPoint_Message;
import MODULES.WAVE3.DepartureControlService.PreRequisites.Add_BoardPoint_Message_AddUpdateDelete;
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

public class add_Update_Delete_board_point_messages extends FrameworkConstants
{
    public static String SOAPRequest;
    public static String Message1;
    public static String Message2;
    public static String Message3;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Add_BoardPoint_Message_AddUpdateDelete Prerequisite = new Add_BoardPoint_Message_AddUpdateDelete();
        Prerequisite.run();  //Prerequisite to add Board Point Messages

        Message1= Prerequisite.getMessage(0);  //Fetching those messages which have been added to request
        Message2= Prerequisite.getMessage(1);
        Message3= Prerequisite.getMessage(2);

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
                .post(getDeparturecontrolservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"DepartureControlService\\add_Update_Delete_board_point_messages.xml"));
        writer.write(response.asPrettyString());
        writer.close();

        Assert.assertTrue(response.getBody().asString().contains("Success"));

        Assert.assertFalse(response.getBody().asString().contains(Message1));  //As we are deleting the messages , so asserting false. After deleting the messages for RPH 1 and 2 should not be there.
        Assert.assertFalse(response.getBody().asString().contains(Message2));  //As we are deleting the messages , so asserting false. After deleting the messages for RPH 1 and 2 should not be there.

        Assert.assertTrue(response.getBody().asString().contains("NEW MESSAGE TESTCASE SIX")); //Asserting whether new Message has been added or not

        Assertions.AssertWarning(response,false);
        Assertions.AssertResponseTime(response,ResponseTime);

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
        XSSFSheet sheet = wb.getSheet("DepartureControlService");
        XSSFRow InputRow=sheet.getRow(14);

        String filepath1;
        filepath1=getRequestDirectory()+"DepartureControlService\\add_Update_Delete_board_point_messages.xml";


        XMLParser.updateAttributeValueatIndex("dep1:FlightLegInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1,0);
        XMLParser.updateAttributeValueatIndex("dep1:FlightLegInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }




}
