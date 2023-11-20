package com.teamvectora.elixirapi.manager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLManager {
    public final String rootPath = "properties.xml";

    public XMLManager() {
        File file = new File(rootPath);

        if (!file.exists()) {
            createDefaultFile();
        }
    }

    private void createDefaultFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("properties");
            document.appendChild(rootElement);

            Element sql_properties = document.createElement("sql_properties");
            Element user = document.createElement("user");
            user.appendChild(document.createTextNode("root'@'%'"));
            Element password = document.createElement("password");
            Element url = document.createElement("url");
            url.appendChild(document.createTextNode("jdbc:mysql://localhost:3306/"));

            sql_properties.appendChild(user);
            sql_properties.appendChild(password);
            sql_properties.appendChild(url);

            rootElement.appendChild(sql_properties);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(rootPath));

            transformer.transform(source, result);
            System.out.println("passou");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Element getElement(String tagName) {
        try {
            File file = new File(rootPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList nodeList = document.getElementsByTagName(tagName);
            if (nodeList.getLength() > 0) {
                return (Element) nodeList.item(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editElement(String tagName, String newValue) {
        try {
            File file = new File(rootPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList nodeList = document.getElementsByTagName(tagName);
            if (nodeList.getLength() > 0) {
                Node node = nodeList.item(0);
                node.setTextContent(newValue);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new File(rootPath));
                transformer.transform(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
