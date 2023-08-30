package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.XMLParser;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_Booking_Collect_API_for_a_Single_passenger;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Create_booking_delete_API_data_Address;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Display_API_delete_API_address;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.Modify_API_delete_API_data_Address;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Delete_API_data_Address extends FrameworkConstants {

    public static String SOAPRequest;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        Create_booking_delete_API_data_Address Prerequisite = new Create_booking_delete_API_data_Address();
        Prerequisite.run();

        Display_API_delete_API_address Prerequisite1 = new Display_API_delete_API_address();
        Prerequisite1.run();

        Modify_API_delete_API_data_Address Prerequisite2 = new Modify_API_delete_API_data_Address();
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
                .post(getAdvancepassengerinfo())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\Collect_API_for_a_Single_passenger.xml"));
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
        XSSFSheet sheet = wb.getSheet("AdvancePassengerInfo");
        XSSFRow InputRow=sheet.getRow(5);

        String filepath1;
        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Delete_API_data_Address.xml";

        XMLParser.updateAttributeValue("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("air1:BookingInfo","ResBookDesigCode",InputRow.getCell(5).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName",InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName",InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),0);


        wb.close();

    }

}
