/**
 * Epam External Training
 * Task 5
 * XML SXD Parsing
 */
package by.malinouski.xmlparse.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.malinouski.xmlparse.periodic.Magazine;
import by.malinouski.xmlparse.periodic.Newspaper;
import by.malinouski.xmlparse.periodic.Periodical;
import by.malinouski.xmlparse.periodicenum.PeriodicalEnum;

/*
 * Based on "Java Programming Methods" (Blinou, Ramanchyk) 2013
 */
public class PeriodicalsStAXBuilder {
    static final Logger logger = LogManager.getLogger(PeriodicalsStAXBuilder.class);
    private HashSet<Periodical> periodicals = new HashSet<>();
    private XMLInputFactory inputFactory;

    public PeriodicalsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }
    
    public Set<Periodical> getPeriodicals() {
        return periodicals;
    }
    
    public void buildSetPeriodicals(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
               
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    switch (reader.getLocalName()) {
                        case "newspaper":
                            periodicals.add(buildPeriodical(reader, new Newspaper()));
                            break;
                        case "magazine":
                            periodicals.add(buildPeriodical(reader, new Magazine()));
                            break;
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("StAX parsing error! " + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("File " + fileName + " not found! " + e);
        } catch (IOException e) {
            logger.error("Error while closing InputStream" + e);
        }
    }

    protected Periodical buildPeriodical(XMLStreamReader reader, Periodical periodical) throws XMLStreamException {
      
        periodical.setIssn(reader.getAttributeValue(null, PeriodicalEnum.ISSN.getValue()));
        if (reader.getAttributeCount() == 2) {
            periodical.setPeriod(Periodical.Period.valueOf(
                    reader.getAttributeValue(null, PeriodicalEnum.PERIOD.getValue()).toUpperCase()));
        }
        while (reader.hasNext()) {
            switch (reader.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    switch (PeriodicalEnum.valueOf(name.toUpperCase())) {
                        case TITLE:
                            periodical.setTitle(getXMLText(reader));
                            break;
                        case VOLUME:
                            periodical.setVolume(Integer.parseInt(getXMLText(reader)));
                            break;
                        case COLORED:
                            ((Newspaper) periodical).setColored(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case GLOSSY:
                            ((Magazine) periodical).setGlossy(Boolean.valueOf(getXMLText(reader)));
                            break;
                    default:
                        break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PeriodicalEnum.valueOf(reader.getLocalName().toUpperCase()) == PeriodicalEnum.NEWSPAPER ||
                        PeriodicalEnum.valueOf(reader.getLocalName().toUpperCase()) == PeriodicalEnum.MAGAZINE) {
                        return periodical;
                    }
            }
        }
        throw new XMLStreamException("Unknown element");
    }
    
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }   
}
