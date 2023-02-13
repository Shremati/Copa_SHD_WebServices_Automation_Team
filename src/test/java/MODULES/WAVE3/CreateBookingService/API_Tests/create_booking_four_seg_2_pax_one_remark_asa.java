package MODULES.WAVE3.CreateBookingService.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
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
import frameworkconstants.*;

import static io.restassured.RestAssured.given;

public class create_booking_four_seg_2_pax_one_remark_asa extends FrameworkConstants
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
                .body(SOAPRequest)
                .when()
                .post(getCreatebookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"CreateBookingService\\create_booking_four_seg_2_pax_one_remark_asa.xml"));
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
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow=sheet.getRow(3); //Taking scenario create booking for 1 pax

        String filepath1;
        filepath1=getRequestDirectory()+"CreateBookingService\\create_booking_four_seg_2_pax_one_remark_asa.xml";

        XMLParser.updateAttributeValue("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air1:FlightSegment","FlightNumber",InputRow.getCell(2).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:DepartureAirport","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());
        XMLParser.updateAttributeValue("com:ArrivalAirport","LocationCode",InputRow.getCell(4).getStringCellValue(),getTemp_requestPath());

        int a=7,b=8,c=9,d=10;
        for(int i=1;i<3;i++)
        {
            XMLParser.updateAttributeValueatIndex("air1:FlightSegment","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(a).getNumericCellValue()),getTemp_requestPath(),i);
            XMLParser.updateAttributeValueatIndex("air1:FlightSegment","FlightNumber",InputRow.getCell(b).getStringCellValue(),getTemp_requestPath(),i);
            XMLParser.updateAttributeValueatIndex("com:DepartureAirport","LocationCode",InputRow.getCell(c).getStringCellValue(),getTemp_requestPath(),i);
            XMLParser.updateAttributeValueatIndex("com:ArrivalAirport","LocationCode",InputRow.getCell(d).getStringCellValue(),getTemp_requestPath(),i);
            a+=6;b+=6;c+=6;d+=6;
        }

        int e=5,f=6;
        for(int i=0;i<3;i++)
        {
            XMLParser.SetTagtextatIndex("com:GivenName",InputRow.getCell(e).getStringCellValue(),getTemp_requestPath(),i);
            XMLParser.SetTagtextatIndex("com:Surname",InputRow.getCell(f).getStringCellValue(),getTemp_requestPath(),i);
            e+=6;f+=6;
        }


//        XMLParser.updateAttributeValue("air1:FareBasisCode","NotValidBefore",Utils.Date_YYYYMMdd(InputRow.getCell(25).getNumericCellValue()),getTemp_requestPath());
//        XMLParser.updateAttributeValue("air1:FareBasisCode","NotValidAfter",Utils.Date_YYYYMMdd(InputRow.getCell(26).getNumericCellValue()),getTemp_requestPath());
//        XMLParser.updateAttributeValue("air1:FlightSegment","NumberInParty",InputRow.getCell(27).getStringCellValue(),getTemp_requestPath());

        wb.close();

    }
}
