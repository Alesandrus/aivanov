package ru.job4j.jdbc;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XMLTransformer {

    public void transformWithXSLT() {
        String pack = this.getClass().getPackage().getName();
        pack = pack.replaceAll("\\.", "\\\\");
        File styleSheet = new File(".\\chapter_008_SQL_JDBC\\src\\main\\java\\" + pack + "\\styles.xsl");
        File fileSource = new File(".\\chapter_008_SQL_JDBC\\src\\main\\java\\" + pack + "\\1.xml");
        File fileResult = new File(".\\chapter_008_SQL_JDBC\\src\\main\\java\\" + pack + "\\2.xml");
        StreamSource styleSource = new StreamSource(styleSheet);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            try {
                transformer.transform(new StreamSource(fileSource), new StreamResult(fileResult));
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }
}
