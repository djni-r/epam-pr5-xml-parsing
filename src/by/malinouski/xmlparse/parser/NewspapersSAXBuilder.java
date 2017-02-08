/**
 * Epam External Training
 * Task 5
 * XML SXD Parsing
 */
package by.malinouski.xmlparse.parser;

import java.io.IOException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.malinouski.xmlparse.handler.NewspaperHandler;
import by.malinouski.xmlparse.papers.Newspaper;

public class NewspapersSAXBuilder {
    static final Logger LOGGER = LogManager.getLogger(NewspapersSAXBuilder.class); 
    private Set<Newspaper> newspapers;
    private NewspaperHandler handler;
    private XMLReader reader;
    
    public NewspapersSAXBuilder() {
        // создание SAX-анализатора
        handler = new NewspaperHandler();
        
        try {
            // создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOGGER.error("SAX parser error: " + e.getMessage());
        }
    }
    
    public Set<Newspaper> getNewspapers() {
        return newspapers;
    }

    public void buildSetNewspapers(String fileName) {
        try {
            // разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.error("SAX parser error: " + e);
        } catch (IOException e) {
            System.err.print("IO stream error: " + e);
        }
        
        newspapers = handler.getNewspapers();
    }
}
