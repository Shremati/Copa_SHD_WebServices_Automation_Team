package GENERICS;

import org.w3c.dom.Document;
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
import java.io.File;
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
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


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
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


        DOMSource source = new DOMSource(document);

        File myFile = new File(".\\src\\test\\java\\GENERICS\\Temp_Request.xml");
        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);

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
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


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




}
