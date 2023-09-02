package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.RESTWrapper;
import GENERICS.XMLParser;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.create_booking_service_onepax;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.create_booking_service_singlepax;
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
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import frameworkconstants.*;

public class Passengers_in_different_booking_in_single_request extends FrameworkConstants
{

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        create_booking_service_onepax Prerequisite1 = new create_booking_service_onepax();
        Prerequisite1.run(); //1st PNR generated in column 7

        create_booking_service_singlepax Prerequisite2 = new create_booking_service_singlepax();
        Prerequisite2.run(); //2nd PNR generated in column 14


        UpdatePayload();


//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = RESTWrapper.postResponse(getBaseURL(),getAdvancepassengerinfo(),SOAPRequest);


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\Passengers_in_different_booking_in_single_request.xml"));
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
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;

        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Passengers_in_different_booking_in_single_request.xml";


        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),filepath1,0);
        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),0);

        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(14).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),1);


        wb.close();

    }



}
