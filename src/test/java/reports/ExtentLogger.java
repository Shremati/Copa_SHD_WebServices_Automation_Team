package reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;


import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public final class
ExtentLogger {

    static String reportName;

    private ExtentLogger() {
    }

    public static void pass(String message) {
        ExtentManager.getTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    public static void setReportName(String rname) {
        reportName = rname;
    }

    public static String getReportName() {
        return reportName;
    }

    public static void info(String message) {
        ExtentManager.getTest().info(message);
    }

    public static void logJSONResponse(String message) {
        info("Response Below: ");
        ExtentManager.getTest().info(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }

    public static void logXMLResponse(String message) {
        info("Response Details below: ");
        Markup m = MarkupHelper.createCodeBlock(message);
        ExtentManager.getTest().info(m);
    }

    public static void logXMLRequest(String message) {
        info("Request Details below: ");
        message = removeSpacesInXml(message);
        Markup m = MarkupHelper.createCodeBlock(message);
        ExtentManager.getTest().info(m);
    }

    public static void logXMLRequest(Document doc) {
        info("Request Details below: ");
        try {
            StringWriter writer = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String request = writer.getBuffer().toString();
            Markup m = MarkupHelper.createCodeBlock(request);
            ExtentManager.getTest().info(m);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void logXMLRequest(RequestSpecification requestSpecification) {
        info("Request Details below: ");
        RequestSpecificationImpl specImpl = (RequestSpecificationImpl) requestSpecification;

        StringBuilder sb = new StringBuilder();

        // Extract base URI, base path, and query parameters
        sb.append("Base URI: ").append(specImpl.getBaseUri()).append("\n");
        sb.append("Base Path: ").append(specImpl.getBasePath()).append("\n");
        sb.append("Query Params: ").append(specImpl.getQueryParams()).append("\n");

        // Extract headers
        Headers headers = specImpl.getHeaders();
        sb.append("Headers: \n");
        for (Header header : headers) {
            sb.append("  ").append(header.getName()).append(": ").append(header.getValue()).append("\n");
        }

        // Extract body
        Object body = specImpl.getBody();
        sb.append("Body: ").append(body != null ? body.toString() : "null").append("\n");

        info("Response Details below: ");
        Markup m = MarkupHelper.createCodeBlock(sb.toString());
        ExtentManager.getTest().info(m);

        /*QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        info("Base URL: "+query.getBaseUri());
        if(query.getBody() !=null) {
            Markup m = MarkupHelper.createCodeBlock(query.getBody().toString());
            ExtentManager.getTest().info(m);
            for (Header h : query.getHeaders())
                info(h.getName() + " : " + h.getValue());

            Map<String, String> map = new HashMap<>();
            map = query.getQueryParams();

            for (String s : map.keySet()) {
                String key = s.toString();
                String val = map.get(s).toString();
                info(key + " : " + val);
            }
        }*/

    }

    public static void logJSONRequest(RequestSpecification requestSpecification) {
        info("Request Details below: ");
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        info(query.getBaseUri());
        String message = query.getBody();
        ExtentManager.getTest().info(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
        for (Header h : query.getHeaders())
            info(h.getName() + " : " + h.getValue());

        Map<String, String> map = new HashMap<>();
        map = query.getQueryParams();

        for (String s : map.keySet()) {
            String key = s.toString();
            String val = map.get(s).toString();
            info(key + " : " + val);
        }
    }

    public static String removeSpacesInXml(String xml) {
        try {
            String compactXml = xml.replaceAll(">\\s+<", "><");
            // Parse the XML string into a Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(compactXml)));

            // Transform the Document back into a formatted string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}