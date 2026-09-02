//package GENERICS;

//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;

//public class XMLReaderUtil {
//
//    public static String getTagValue(String xmlFilePath, String tagName) throws Exception {
//        try {
//            File file = new File(xmlFilePath);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            dbFactory.setNamespaceAware(true);
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(file);
//            doc.getDocumentElement().normalize();
//
//            NodeList nodeList = doc.getElementsByTagNameNS("*", tagName);
//            if (nodeList.getLength() > 0) {
//                return nodeList.item(0).getTextContent();
//            } else {
//                System.out.println(tagName + " node not found!");
//                return null;
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//package GENERICS;
//
//import org.w3c.dom.*;
//import javax.xml.parsers.*;
//import javax.xml.xpath.*;
//import java.io.File;
//
//public class XMLReaderUtil {
//
//    /**
//     * Reads a tag value (namespace-agnostic)
//     */
//    public static String getTagValue(String xmlFilePath, String tagName) throws Exception {
//
//        File file = new File(xmlFilePath);
//
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        dbFactory.setNamespaceAware(true);
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//
//        Document doc = dBuilder.parse(file);
//        doc.getDocumentElement().normalize();
//
//        XPath xPath = XPathFactory.newInstance().newXPath();
//
//        Node node = (Node) xPath.evaluate(
//                "//*[local-name()='" + tagName + "']",
//                doc,
//                XPathConstants.NODE
//        );
//
//        if (node != null) {
//            return node.getTextContent();
//        } else {
//            System.out.println(tagName + " node not found!");
//            return null;
//        }
//    }
//}
package GENERICS;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReaderUtil {

    /**
     * Returns all GivenName + Surname pairs
     */
    public static List<String[]> getAllNames(String xmlFilePath) throws Exception {

        List<String[]> namesList = new ArrayList<>();

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

            String givenName = xPath.evaluate(".//*[local-name()='GivenName']", person);
            String surname = xPath.evaluate(".//*[local-name()='Surname']", person);

            namesList.add(new String[]{givenName, surname});
        }

        return namesList;
    }
}