package ru.job4j.jdbc;

import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class XMLCreator {
    private String tableName;
    private String fieldName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void createXMLwithDOM(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", fieldName, tableName));

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("entries");
            document.appendChild(root);

            while (resultSet.next()) {
                int fieldNumber = resultSet.getInt(1);
                Element entry = document.createElement("entry");
                root.appendChild(entry);
                Element field = document.createElement("field");
                entry.appendChild(field);
                Text text = document.createTextNode(String.valueOf(fieldNumber));
                field.appendChild(text);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(createFile());
            transformer.transform(source, streamResult);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void createXMLwithStAX(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", fieldName, tableName));

        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter streamWriter =
                    outputFactory.createXMLStreamWriter(Files.newOutputStream(createFile().toPath()));
            streamWriter.writeStartDocument();
            streamWriter.writeStartElement("entries");
            while (resultSet.next()) {
                int fieldNumber = resultSet.getInt(1);
                streamWriter.writeStartElement("entry");
                streamWriter.writeStartElement("field");
                streamWriter.writeCharacters(String.valueOf(fieldNumber));
                streamWriter.writeEndElement();
                streamWriter.writeEndElement();
            }
            streamWriter.writeEndElement();
            streamWriter.writeEndDocument();
            streamWriter.flush();
            streamWriter.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createXMLwithJDOM(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", fieldName, tableName));

        org.jdom2.Document document = new org.jdom2.Document();
        org.jdom2.Element root = new org.jdom2.Element("entries");
        document.setRootElement(root);

        while (resultSet.next()) {
            int fieldNumber = resultSet.getInt(1);
            org.jdom2.Element entry = new org.jdom2.Element("entry");
            org.jdom2.Element field = new org.jdom2.Element("field");
            field.addContent(String.valueOf(fieldNumber));
            entry.addContent(field);
            root.addContent(entry);
        }

        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        try {
            outputter.output(document, new FileOutputStream(createFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createXMLwithDOM4J(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", fieldName, tableName));

        org.dom4j.Document document = DocumentHelper.createDocument();
        org.dom4j.Element root = document.addElement("entries");
        while (resultSet.next()) {
            int fieldNumber = resultSet.getInt(1);
            root.addElement("entry").addElement("field").addText(String.valueOf(fieldNumber));
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(createFile()), format);
            writer.write(document);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File createFile() {
        String pack = this.getClass().getPackage().getName();
        pack = pack.replaceAll("\\.", "\\\\");
        return new File(".\\chapter_008_SQL_JDBC\\src\\main\\java\\" + pack + "\\1.xml");
    }
}
