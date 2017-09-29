package ru.job4j.jdbc;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XMLSumParser {
    public long sunAndParseStAX(String file) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        long sum = 0;
        try {
            XMLStreamReader parser = inputFactory.createXMLStreamReader(new FileInputStream(file));
            while (parser.hasNext()) {
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getLocalName().equals("entry")) {
                        long number = Long.parseLong(parser.getAttributeValue(null, "field"));
                        sum += number;
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
