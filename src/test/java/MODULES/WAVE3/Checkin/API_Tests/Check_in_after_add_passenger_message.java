package MODULES.WAVE3.Checkin.API_Tests;
import GENERICS.Utils;
import GENERICS.XMLParser;
import MODULES.WAVE3.Checkin.PreRequisites.*;
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


public class Check_in_after_add_passenger_message extends FrameworkConstants
{

    public static String SOAPRequest;

    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        Create_booking_add_passenger_message Prerequisite1 = new Create_booking_add_passenger_message();
        Prerequisite1.run();

        Issue_booking_add_passenger_message Prerequisite2 = new Issue_booking_add_passenger_message();
        Prerequisite2.run();

        Adding_passenger_message Prerequisite3 = new Adding_passenger_message();
        Prerequisite3.run();

        Display_APIS_add_passenger_message Prerequisite4 = new Display_APIS_add_passenger_message();
        Prerequisite4.run();

        Add_APIS_add_passenger_message Prerequisite5 = new Add_APIS_add_passenger_message();
        Prerequisite5.run();


        UpdatePayload();



//    ******** Read the updated request and send it to fetch the response *********



        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest = IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);



        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(SOAPRequest)
                .when()
                .post(getCheckin())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory() + "Checkin\\Check_in_after_add_passenger_message.xml"));
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
        XSSFSheet sheet = wb.getSheet("CheckIn");
        XSSFRow InputRow=sheet.getRow(8);


        String filepath1;
        filepath1=getRequestDirectory()+"Checkin\\Check_in_after_add_passenger_message.xml";


        XMLParser.updateAttributeValue("com1:CarrierInfo","FlightNumber",InputRow.getCell(2).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValue("com1:DepartureInformation","DateOfDeparture", Utils.getDate_YYYYMMddThhmmss(InputRow.getCell(1).getNumericCellValue()),getTemp_requestPath());
        XMLParser.updateAttributeValue("com1:DepartureInformation","LocationCode",InputRow.getCell(3).getStringCellValue(),getTemp_requestPath());


        wb.close();

    }
}
