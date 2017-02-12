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

import by.malinouski.xmlparse.handler.PeriodicalHandler;
import by.malinouski.xmlparse.periodic.Newspaper;
import by.malinouski.xmlparse.periodic.Periodical;

public class PeriodicalsSAXBuilder {
    static final Logger logger = LogManager.getLogger(PeriodicalsSAXBuilder.class); 
    private Set<Periodical> periodicals;
    private PeriodicalHandler handler;
    private XMLReader reader;
    
    public PeriodicalsSAXBuilder() {
        handler = new PeriodicalHandler();
        
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            logger.error("SAX parser error: " + e.getMessage());
        }
    }
    
    public Set<Periodical> getPeriodicals() {
        return periodicals;
    }

    public void buildSetPeriodicals(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.error("SAX parser error: " + e);
        } catch (IOException e) {
            logger.error("IO stream error: " + e);
        }
        
        periodicals = handler.getPeriodicals();
    }
}
