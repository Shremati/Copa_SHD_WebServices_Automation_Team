//package GENERICS;
//
//import com.github.javafaker.Faker;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathFactory;
//import java.io.File;
//
///**
// * Utility to update GivenName and Surname in XML using Faker.
// * Namespace-agnostic, works even if the XML uses prefixes like ns3 or com.
// */
//public class XMLFakerUtil {
//
//    private static final Faker faker = new Faker();
//
//    /**
//     * Updates GivenName and Surname nodes in the XML file at xmlFilePath
//     *
//     * @param xmlFilePath Path to XML file
//     * @throws Exception Throws exception if parsing or saving fails
//     */
//    public static void updateNames(String xmlFilePath) throws Exception {
//        File file = new File(xmlFilePath);
//
//        // 1️⃣ Parse XML
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        dbFactory.setNamespaceAware(true); // Important for namespaces
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(file);
//        doc.getDocumentElement().normalize();
//
//        // 2️⃣ XPath setup
//        XPath xPath = XPathFactory.newInstance().newXPath();
//
//        // 3️⃣ Namespace-agnostic search for nodes
//        Node givenNode = (Node) xPath.evaluate("//*[local-name()='GivenName']", doc, XPathConstants.NODE);
//        Node surnameNode = (Node) xPath.evaluate("//*[local-name()='Surname']", doc, XPathConstants.NODE);
//
//        // 4️⃣ Update nodes with Faker
//        if (givenNode != null) {
//            givenNode.setTextContent(faker.name().firstName());
//        } else {
//            System.out.println("GivenName node not found!");
//        }
//
//        if (surnameNode != null) {
//            surnameNode.setTextContent(faker.name().lastName());
//        } else {
//            System.out.println("Surname node not found!");
//        }
//
//        // 5️⃣ Save changes back to file
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        DOMSource source = new DOMSource(doc);
//        StreamResult result = new StreamResult(file);
//        transformer.transform(source, result);
//
//        System.out.println("XML updated successfully!");
//    }
//
//
//}
//package GENERICS;
//
//import com.github.javafaker.Faker;
//import org.w3c.dom.*;
//import javax.xml.parsers.*;
//import javax.xml.transform.*;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import javax.xml.xpath.*;
//import java.io.File;
////
//public class XMLFakerUtil {
//
//    /**
//     * Updates GivenName and Surname in XML and returns generated values
//     */
//    public static String[] updateNames(String xmlFilePath) throws Exception {
//
//        Faker faker = new Faker(); // fresh instance
//
//        File file = new File(xmlFilePath);
//
//        // 1️⃣ Parse XML
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        dbFactory.setNamespaceAware(true);
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(file);
//        doc.getDocumentElement().normalize();
//
//        // 2️⃣ XPath
//        XPath xPath = XPathFactory.newInstance().newXPath();
//
//        Node givenNode = (Node) xPath.evaluate(
//                "//*[local-name()='GivenName']",
//                doc,
//                XPathConstants.NODE
//        );
//
//        Node surnameNode = (Node) xPath.evaluate(
//                "//*[local-name()='Surname']",
//                doc,
//                XPathConstants.NODE
//        );
//
//        // 3️⃣ Generate values
//        String firstName = faker.name().firstName();
//        String lastName = faker.name().lastName();
//
//        System.out.println("Generated (WRITE): " + firstName + " " + lastName);
//
//        // 4️⃣ Update XML
//        if (givenNode != null) {
//            givenNode.setTextContent(firstName);
//        } else {
//            System.out.println("GivenName node not found!");
//        }
//
//        if (surnameNode != null) {
//            surnameNode.setTextContent(lastName);
//        } else {
//            System.out.println("Surname node not found!");
//        }
//
//        // 5️⃣ Write safely using temp file
//        File tempFile = new File(xmlFilePath + ".tmp");
//
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//        transformer.transform(new DOMSource(doc), new StreamResult(tempFile));
//
//        // Replace original file
//        if (!file.delete()) {
//            throw new RuntimeException("Failed to delete original file");
//        }
//        if (!tempFile.renameTo(file)) {
//            throw new RuntimeException("Failed to rename temp file");
//        }
//
//        return new String[]{firstName, lastName};
//    }
//}
package GENERICS;

import com.github.javafaker.Faker;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//
public class XMLFakerUtil {
    public static List<String[]> updateAllNames(String xmlFilePath) throws Exception {

        Faker faker = new Faker();
        List<String[]> generatedNames = new ArrayList<>();

        File file = new File(xmlFilePath);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList personNodes = (NodeList) xPath.evaluate(
                "//*[local-name()='PersonName']",
                doc,
                XPathConstants.NODESET
        );

        for (int i = 0; i < personNodes.getLength(); i++) {

            Node person = personNodes.item(i);

            Node givenNameNode = (Node) xPath.evaluate(
                    ".//*[local-name()='GivenName']",
                    person,
                    XPathConstants.NODE
            );

            Node surnameNode = (Node) xPath.evaluate(
                    ".//*[local-name()='Surname']",
                    person,
                    XPathConstants.NODE
            );

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            // Store generated values
            generatedNames.add(new String[]{firstName, lastName});

            System.out.println("Generated Person " + (i + 1) + ": " + firstName + " " + lastName);

            if (givenNameNode != null) {
                givenNameNode.setTextContent(firstName);
            }

            if (surnameNode != null) {
                surnameNode.setTextContent(lastName);
            }
        }

        // Save using temp file
        File tempFile = new File(xmlFilePath + ".tmp");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(new DOMSource(doc), new StreamResult(tempFile));

        if (!file.delete()) {
            throw new RuntimeException("Failed to delete original file");
        }
        if (!tempFile.renameTo(file)) {
            throw new RuntimeException("Failed to rename temp file");
        }

        return generatedNames; // ✅ THIS fixes your error
    }
}