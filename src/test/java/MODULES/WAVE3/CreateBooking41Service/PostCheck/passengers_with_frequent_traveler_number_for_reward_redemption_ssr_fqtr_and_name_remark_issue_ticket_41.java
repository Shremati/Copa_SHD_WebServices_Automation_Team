package MODULES.WAVE3.CreateBooking41Service.PostCheck;

import GENERICS.XMLParser;
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

import static io.restassured.RestAssured.given;

public class passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark_issue_ticket_41 extends FrameworkConstants
{

    public static String SOAPRequest;

    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {
        UpdatePayload();

//                       ********** Reading the xml request file **********

        FileInputStream fileInputStream = new FileInputStream(getTemp_requestPath());
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");
        SOAPRequest = SOAPRequest.substring(SOAPRequest.indexOf('\n') + 1);


        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .body(SOAPRequest)
                .when()
                .post(getIssueticketservice())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();

        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"CreateBookingService\\passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark_issue_ticket.xml"));
        writer.write(response.asPrettyString());
        writer.close();


        excelwriter();

    }



    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");

        XSSFRow InputRow=sheet.getRow(15);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\CreateBookingService\\PostCheck\\passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark_issue_ticket.xml";


        XMLParser.SetTagtextatIndex("tic1:RecordLocator", InputRow.getCell(17).getStringCellValue(),filepath1,0);

        wb.close();
    }


    public static void excelwriter() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        //        ********** Writing TestData into Excel ************

        File xlsxFile = new File(getTestData());
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("CreateBookingService");
        XSSFRow InputRow=sheet.getRow(15);

        String filepath;
        filepath = getResponseDirectory()+"CreateBookingService\\passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark_issue_ticket.xml";

        String TicketNumber = XMLParser.GetTagText("ns4:FormAndSerialNumber",filepath);
        InputRow.getCell(18).setCellValue(TicketNumber);


        FileOutputStream out = new FileOutputStream(new File(getTestData()));
        wb.write(out);
        out.close();

        wb.close();

    }
}
