package MODULES.WAVE3.AdvancePassengerInfo.PreRequisites;

import GENERICS.XMLParser;
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

public class Add_APIS_Collect_API_1adult_and_1infant_without_seats extends FrameworkConstants {

    public static String SOAPRequest;


    public void run() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {

        UpdatePayload();

//               ********** Reading the xml request file **********

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

        AssertWarning(response,false);
        AssertResponseTime(response,1000L);

//                     ********* Clearing Temp_Request.xml *********

        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemp_responsePath()));
        writer = Files.newBufferedWriter(Paths.get(getTemp_requestPath()));
        writer.write("");
        writer.close();

    }


    public static void UpdatePayload() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

//        ********** Reading Testdata from Excel ************
        FileInputStream fis=new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("AdvancePassengerInfo");

        XSSFRow InputRow=sheet.getRow(8);

        String filepath1;
        filepath1=".\\src\\test\\java\\MODULES\\WAVE3\\AdvancePassengerInfo\\PreRequisites\\Add_APIS_Collect_API_1adult_and_1infant_without_seats.xml";

        XMLParser.updateAttributeValue("air1:BookingReferenceID","ID", InputRow.getCell(7).getStringCellValue(),filepath1);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(15).getStringCellValue(),getTemp_requestPath(),0);
        XMLParser.updateAttributeValueatIndex("air1:AgencyRequirements","AgencyName", InputRow.getCell(16).getStringCellValue(),getTemp_requestPath(),1);

        wb.close();

    }

}
