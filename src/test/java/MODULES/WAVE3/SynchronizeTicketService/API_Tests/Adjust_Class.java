package MODULES.WAVE3.SynchronizeTicketService.API_Tests;


import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.*;
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

public class Adjust_Class extends FrameworkConstants {
   public static String SOAPRequest;

   public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
   {

       Create_Booking2 Prerequisite1 = new Create_Booking2();
       Prerequisite1.run();

       Issue_Booking2 Prerequisite2 = new Issue_Booking2();
       Prerequisite2.run();

       Modify_Booking2 Prerequisite3 = new Modify_Booking2();
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
               .post(getSynchronizeticketservice())
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




   }


   public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
   {

//        ********** Reading Testdata from Excel ************
       FileInputStream fis=new FileInputStream(new File(getTestData()));
       XSSFWorkbook wb = new XSSFWorkbook(fis);
       XSSFSheet sheet = wb.getSheet("SynchronizeTicketService");

       XSSFRow InputRow=sheet.getRow(3);

       String filepath1;
       filepath1=getRequestDirectory()+"SynchronizeTicketService\\Adjust_Class.xml";

       XMLParser.updateAttributeValue("tic:BookingTicketingRefID","ID",InputRow.getCell(5).getStringCellValue(),filepath1);



       wb.close();

   }


}


