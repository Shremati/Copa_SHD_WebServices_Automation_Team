package MODULES.WAVE3.ManageSessions.API_Tests;

import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.ManageSessions.PreRequisites.*;
import frameworkconstants.FrameworkConstants;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.message.ParameterizedNoReferenceMessageFactory;
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

public class Create_a_booking_for_a_group extends FrameworkConstants {
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Get_Token Prerequisite1 = new Get_Token();
        Prerequisite1.run();
//        System.out.println(PNR);

        Create_Booking_for_Group Prerequisite2 = new Create_Booking_for_Group();
        Prerequisite2.run();

        Create_Booking_for_Group_PAX Prerequisite3 = new Create_Booking_for_Group_PAX();
        Prerequisite3.run();


        UpdatePayload();

//               ********** Reading the xml request file **********

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



        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer.write(response.asPrettyString());
        writer.close();

//                ********* Clearing Temp_Request.xml *********

        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();


        excelwriter();


    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("ManageSessions");

        XSSFRow InputRow=sheet.getRow(2);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\ManageSessions\\PreRequisites\\Create_Booking_for_Group.xml";

//        XMLParser.updateAttributeValue("air1:Ticketing","TicketTimeLimit", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(2).getNumericCellValue()),filepath1);
        XMLParser.updateAttributeValue("air:OTA_AirBookModifyRQ","TransactionIdentifier",InputRow.getCell(3).getStringCellValue(),filepath1);


        wb.close();

    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("ManageSessions");
        XSSFRow InputRow=sheet.getRow(2);



        String PNR = XMLParser.GetAttributeValue("ns5:OTA_AirBookRS","TransactionIdentifier",getTemp_responsePath());

        System.out.print(PNR);
        InputRow.getCell(3).setCellValue(PNR);
        System.out.print(InputRow);



        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

//          ********* Clearing Temp_Response.xml *********

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(getTemp_responsePath()));
        writer.write("");
        writer.close();

    }

}
