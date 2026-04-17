package GENERICS;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLParser
{
//              *********  Parse XML file  ***********

    public static void updateAttributeValue(String Tag,String Attribute,String New_Value,String fpath) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);

        XMLTags.item(0).getAttributes().getNamedItem(Attribute).setNodeValue(New_Value);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");


        DOMSource source = new DOMSource(document);

        File myFile = new File(".\\src\\test\\java\\GENERICS\\Temp_Request.xml");
        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);

    }

    public static void updateAttributeValueatIndex(String Tag,String Attribute, String New_Value,String fpath,int index) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);
        XMLTags.item(index).getAttributes().getNamedItem(Attribute).setNodeValue(New_Value);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");


        DOMSource source = new DOMSource(document);

        File myFile = new File(".\\src\\test\\java\\GENERICS\\Temp_Request.xml");
        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);

    }
    public static void updateTagValue(String tagName, String newValue, String fpath) {
        try {
            File xmlFile = new File(fpath);
            if (!xmlFile.exists() || xmlFile.length() == 0) {
                throw new IOException("Temp_Request.xml is empty or missing: " + fpath);
            }

            // Parse the XML safely
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document;
            try (FileInputStream fis = new FileInputStream(xmlFile)) {
                document = builder.parse(fis);
            }

            // XPath search for tag
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodes = (NodeList) xPath.evaluate(
                    "//*[local-name()='" + tagName + "']",
                    document,
                    XPathConstants.NODESET
            );

            if (nodes.getLength() == 0) {
                System.out.println("Warning: " + tagName + " node not found in " + fpath);
            } else {
                for (int i = 0; i < nodes.getLength(); i++) {
                    nodes.item(i).setTextContent(newValue);
                }
                System.out.println(tagName + " updated successfully to: " + newValue);
            }

            // Write changes back to the same temp file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = transformerFactory.newTransformer();
            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transf.setOutputProperty(OutputKeys.INDENT, "yes");
            transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");

            try (FileOutputStream fos = new FileOutputStream(xmlFile)) {
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(fos);
                transf.transform(source, result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating " + tagName + ": " + e.getMessage());
        }
    }
    public static void SetTagtextatIndex(String Tag,String New_Value,String fpath,int Index) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);
        XMLTags.item(Index).setTextContent(New_Value);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");


        DOMSource source = new DOMSource(document);

        File myFile = new File(".\\src\\test\\java\\GENERICS\\Temp_Request.xml");
        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);

    }

    public static String GetAttributeValue(String Tag,String Attribute,String fpath) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);

        return XMLTags.item(0).getAttributes().getNamedItem(Attribute).getNodeValue();

    }

    public static String GetAttributeValueatIndex(String Tag,String Attribute,String fpath,int Index) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);

        return XMLTags.item(Index).getAttributes().getNamedItem(Attribute).getNodeValue();

    }

    public static String GetTagText(String Tag,String fpath) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);

        return XMLTags.item(0).getTextContent();

    }

    public static String GetTagTextatIndex(String Tag,String fpath,int Index) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);

        return XMLTags.item(Index).getTextContent();

    }

    public static void updateValue(String Tag,String New_Value,String fpath) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);

//        XMLTags.item(0).getAttributes().getNamedItem(Attribute).setNodeValue(New_Value);
        XMLTags.item(0).setNodeValue(New_Value);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


        DOMSource source = new DOMSource(document);

        File myFile = new File(".\\src\\test\\java\\GENERICS\\Temp_Request.xml");
        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);

    }

    public static void SetTagtext(String Tag,String New_Value,String fpath) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);
        XMLTags.item(0).setTextContent(New_Value);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


        DOMSource source = new DOMSource(document);

        File myFile = new File(".\\src\\test\\java\\GENERICS\\Temp_Request.xml");
        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);

    }

    public static int getNoOfTags(String Tag,String fpath) throws IOException, SAXException, ParserConfigurationException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);

        return XMLTags.getLength();
    }

    public static void updateChildAttributeUnderParent(String parentTag,int parentTagIndex,String childTag,String Attribute,String New_Value,String fpath) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList parentXMLTags =  document.getElementsByTagName(parentTag);
        Node parentXMLTag = parentXMLTags.item(parentTagIndex);

        NodeList childNodes = parentXMLTag.getChildNodes();
        Node childXMLTag=null;
        for(int i=0;i<childNodes.getLength();i++)
        {
            if(childNodes.item(i).getNodeName().equalsIgnoreCase(childTag))
            {
                childXMLTag = childNodes.item(i);
                break;
            }
        }
        if(childXMLTag!=null)
            childXMLTag.getAttributes().getNamedItem(Attribute).setNodeValue(New_Value);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
        DOMSource source = new DOMSource(document);

        File myFile = new File(".\\src\\test\\java\\GENERICS\\Temp_Request.xml");
        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);

    }

    public static String getChildTagTextForParentAtIndex(String parentTag,int parentTagIndex,String childTag,String fpath) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList parentXMLTags =  document.getElementsByTagName(parentTag);
        Node parentXMLTag = parentXMLTags.item(parentTagIndex);

        NodeList childNodes = parentXMLTag.getChildNodes();
        String text=null;
        for(int i=0;i<childNodes.getLength();i++)
        {
            if(childNodes.item(i).getNodeName().equalsIgnoreCase(childTag))
            {
                text = childNodes.item(i).getTextContent();
                break;
            }
        }
        return text;

    }

    public static void SetTagtextatAllIndexes(String Tag,String New_Value,String fpath) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fpath));

        NodeList XMLTags =null;
        XMLTags = document.getElementsByTagName(Tag);

        for(int i=0;i<XMLTags.getLength();i++)
        {
            XMLTags.item(i).setTextContent(New_Value);
        }


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");


        DOMSource source = new DOMSource(document);

        File myFile = new File(".\\src\\test\\java\\GENERICS\\Temp_Request.xml");
        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);

    }

}
