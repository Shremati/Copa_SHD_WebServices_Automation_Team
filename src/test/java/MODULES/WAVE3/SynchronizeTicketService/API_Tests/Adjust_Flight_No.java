package MODULES.WAVE3.SynchronizeTicketService.API_Tests;


import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.Create_Booking;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.Display_Booking_adjust_flight_no;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.Issue_Booking;
import MODULES.WAVE3.SynchronizeTicketService.PreRequisites.Modify_Booking;
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

     public class Adjust_Flight_No  extends FrameworkConstants {

        public static String SOAPRequest;

        public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
        {

            Create_Booking Prerequisite1 = new Create_Booking();
            Prerequisite1.run();  //flight needs to contain 2 seats as 2 pax are used


            Issue_Booking Prerequisite2 = new Issue_Booking();
            Prerequisite2.run();

            Modify_Booking Prerequisite3 = new Modify_Booking();
            Prerequisite3.run();// We are modifying the 1st segment, to be specific , we are cancelling 1st segment using status as 1 and instead of that we are using a new segment keeping market same and modifying flight no and date

            Display_Booking_adjust_flight_no Prerequisite4 = new Display_Booking_adjust_flight_no();
            Prerequisite4.run();


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



            BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"SynchronizeTicketService\\Adjust_Flight_No.xml"));
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

            XSSFRow InputRow=sheet.getRow(1);

            String filepath1;
            filepath1=getRequestDirectory()+"SynchronizeTicketService\\Adjust_Flight_No.xml";


            XMLParser.updateAttributeValue("tic:BookingTicketingRefID","ID",InputRow.getCell(12).getStringCellValue(),filepath1);
            XMLParser.updateAttributeValue("tic:OriginalAirlineInfo","DepartureDateTime", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(7).getNumericCellValue()),getTemp_requestPath());
            XMLParser.updateAttributeValue("tic:OriginalAirlineInfo","FlightNumber",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());

            wb.close();

        }

    }


