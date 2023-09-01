package MODULES.WAVE3.ModifyInventoryService.API_Tests;

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

public class ModifyInventory_Request_with_AuthorizationLevel_and_MaxSeatsAllotted_for_CM_carrier extends FrameworkConstants
{
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

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
                .post(getModifyinventoryservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"ModifyInventoryService\\ModifyInventory_Request_with_AuthorizationLevel_and_MaxSeatsAllotted_for_CM_carrier.xml"));
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
        XSSFSheet sheet = wb.getSheet("ModifyInventoryService");
        XSSFRow InputRow=sheet.getRow(3);

        String filepath1;
        filepath1=getRequestDirectory()+"Modifyinventoryservice\\ModifyInventory_Request_with_AuthorizationLevel_and_MaxSeatsAllotted_for_CM_carrier.xml";



        XMLParser.SetTagtextatIndex("air1:FlightNumber",InputRow.getCell(1).getStringCellValue(),filepath1,0);
        XMLParser.SetTagtextatIndex("air1:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(2).getNumericCellValue()),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:BoardPoint","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.SetTagtextatIndex("air1:ResBookDesigCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("air1:AuthorizationLevelValue", InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),0);


        XMLParser.SetTagtextatIndex("air1:FlightNumber",InputRow.getCell(1).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("air1:DepartureDate", Utils.getDate_YYYYMMdd(InputRow.getCell(2).getNumericCellValue()),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:BoardPoint","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:OffPoint","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.SetTagtextatIndex("air1:ResBookDesigCode",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("air1:MaximumSeat", InputRow.getCell(6).getStringCellValue(),getTemp_requestPath(),0);

        wb.close();

    }




}
