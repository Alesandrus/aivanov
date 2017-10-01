package ru.job4j.jdbc;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class XMLSumParser for parse data from XML-file (2.xml) and sum all fields.
 * @author Alexander Ivanov
 * @since 01.10.2017
 * @version 1.0
 */
public class XMLSumParser {
    /**
     * Sum all fields.
     * @return sum.
     */
    public long parseAndSum() {
        File file = new File(".");
        String path = file.getAbsolutePath();
        path = path.substring(0, path.length() - 1)
                .replaceAll("\\\\", "/");
        path = path + "chapter_008_SQL_JDBC/src/main/java/ru/job4j/jdbc/2.xml";
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        long sum = 0;
        try {
            XMLStreamReader parser = inputFactory.createXMLStreamReader(new FileInputStream(path));
            while (parser.hasNext()) {
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getLocalName().equals("entry")) {
                        long number = Long.parseLong(parser.getAttributeValue(null, "field"));
                        sum += number;
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
