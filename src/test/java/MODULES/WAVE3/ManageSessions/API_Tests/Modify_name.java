package MODULES.WAVE3.ManageSessions.API_Tests;

import GENERICS.XMLParser;
import MODULES.WAVE3.ManageSessions.PreRequisites.Create_Booking;
import MODULES.WAVE3.ManageSessions.PreRequisites.Modify_Booking;
import frameworkconstants.FrameworkConstants;
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

public class Modify_name extends FrameworkConstants {
    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        Create_Booking Prerequisite1 = new Create_Booking();
        Prerequisite1.run();

        Modify_Booking Prerequisite2 = new Modify_Booking();
        Prerequisite2.run();


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
                .post(getModifybookingservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"ManageSessions\\Modify_name.xml"));
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
        XSSFSheet sheet = wb.getSheet("ManageSessions");
        XSSFRow InputRow=sheet.getRow(1);

        String filepath1;

        filepath1=getRequestDirectory()+"ManageSessions\\Modify_name.xml";

        XMLParser.updateAttributeValueatIndex("air:OTA_AirBookModifyRQ","TransactionIdentifier",InputRow.getCell(3).getStringCellValue(),filepath1,0);


//        XMLParser.updateAttributeValueatIndex("dis1:TicketDocument","TicketDocumentNbr",InputRow.getCell(10).getStringCellValue(),getTemp_requestPath(),1);


        wb.close();

    }


}
