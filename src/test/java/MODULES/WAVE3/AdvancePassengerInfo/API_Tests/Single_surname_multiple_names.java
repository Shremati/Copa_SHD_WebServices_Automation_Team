package MODULES.WAVE3.AdvancePassengerInfo.API_Tests;

import GENERICS.RESTWrapper;
import GENERICS.XMLParser;
import MODULES.WAVE3.AdvancePassengerInfo.PreRequisites.create_booking_multiplepax_with_same_surname;
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
import frameworkconstants.*;

public class Single_surname_multiple_names extends FrameworkConstants
{
    public static String SOAPRequest;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        create_booking_multiplepax_with_same_surname Prerequisite = new create_booking_multiplepax_with_same_surname();
        Prerequisite.run();  //Booking with 3 pax with same surname


        UpdatePayload();

//    ******** Read the updated request and send it to fetch the response *********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);

        Response response = RESTWrapper.postResponse(getBaseURL(),getAdvancepassengerinfo(),SOAPRequest);


        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"AdvancePassengerInfo\\Single_surname_multiple_names.xml"));
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
        XSSFRow InputRow=sheet.getRow(2);

        String filepath1;
        filepath1=getRequestDirectory()+"AdvancePassengerInfo\\Single_surname_multiple_names.xml";


        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),filepath1,0);
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.updateAttributeValueatIndex("air1:BookingReferenceID","ID",InputRow.getCell(7).getStringCellValue(),getTemp_requestPath(),2);

        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(8).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(9).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(11).getStringCellValue(),getTemp_requestPath(),1);
        XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(12).getStringCellValue(),getTemp_requestPath(),2);
        XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(13).getStringCellValue(),getTemp_requestPath(),2);


        wb.close();

    }



}
