package MODULES.WAVE3.Availability.API_Tests;

import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

import frameworkconstants.*;

import static io.restassured.RestAssured.given;

public class Empty_OriginDestinationInformation extends FrameworkConstants
{

    public static String SOAPRequest;


    public static void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException
    {


        FileInputStream fileInputStream = new FileInputStream(getRequestDirectory()+"Availability\\Empty_OriginDestinationInformation.xml");
        SOAPRequest= IOUtils.toString(fileInputStream, "UTF-8");

        Response response = given()
                .baseUri(getBaseURL())
                .header("Content-Type", "text/xml")
                .body(SOAPRequest)
                .when()
                .post(getAvailability())
                .then()
                .statusCode(200)
                .and()
                .log().all().extract().response();



        BufferedWriter writer = new BufferedWriter(new FileWriter(getResponseDirectory()+"Availability\\Empty_OriginDestinationInformation.xml"));
        writer.write(response.asPrettyString());
        writer.close();


    }


}
