/**
 * Epam External Training
 * Task 5
 * XML SXD Parsing
 */
package by.malinouski.xmlparse.parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import by.malinouski.xmlparse.exception.PeriodicalElementNotPresentException;
import by.malinouski.xmlparse.periodic.Magazine;
import by.malinouski.xmlparse.periodic.Newspaper;
import by.malinouski.xmlparse.periodic.Periodical;

/*
 * Based on "Java Programming Methods" (Blinou, Ramanchyk) 2013
 */
public class PeriodicalsDOMBuilder {
    static final Logger logger = LogManager.getLogger(PeriodicalsDOMBuilder.class);
    private Set<Periodical> periodicals;
    private DocumentBuilder docBuilder;
    
    public PeriodicalsDOMBuilder() {
        periodicals = new HashSet<Periodical>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Parser configuration exception: " + e);
        }
    }
    
    public Set<Periodical> getPeriodicals() {
        return periodicals;
    }
    
    public void buildSetPeriodicals(String fileName) {
        try {
            // parsing XML-document and creation of tree model
            Document doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            buildSetByTagName("newspaper", root);
            buildSetByTagName("magazine", root);
            
        } catch (IOException e) {
            logger.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            logger.error("Parsing failure: " + e);
        } catch (IllegalArgumentException e) {
            logger.error("uri is null" + e);
        }
    }
    
    protected Periodical buildPeriodical(Element periodicalElement) { 
//                            throws PeriodicalElementNotPresentException {
        Periodical periodical = null;
        switch (periodicalElement.getTagName()) {
            case "newspaper":
                periodical = new Newspaper();
                ((Newspaper) periodical).setColored(Boolean.valueOf(
                        getElementTextContent(periodicalElement, "colored")));
                break;
            case "magazine":
                periodical = new Magazine();
                ((Magazine) periodical).setGlossy(Boolean.valueOf(
                        getElementTextContent(periodicalElement, "glossy")));
                break;
            default:
                // gotta think about this, probably not good
                return Periodical.getEmptyPeriodical();
//                throw new PeriodicalElementNotPresentException();
        }
        
        periodical.setIssn(periodicalElement.getAttribute("issn")); // проверка на null
        if (periodicalElement.hasAttribute("period")) {
            periodical.setPeriod(Periodical.Period.valueOf(
                    periodicalElement.getAttribute("period").toUpperCase()));
        }
        periodical.setTitle(getElementTextContent(periodicalElement, "title"));
        periodical.setVolume(Integer.parseInt(
                getElementTextContent(periodicalElement,"volume")));
        
        return periodical;
    }
    
    private void buildSetByTagName(String tagName, Element root) {
        logger.debug((root.getElementsByTagName(tagName)).getLength());
        NodeList periodicalsList = root.getElementsByTagName(tagName);
        for (int i = 0; i < periodicalsList.getLength(); i++) {
            Element periodicalElement = (Element) periodicalsList.item(i);
            Periodical periodical = buildPeriodical(periodicalElement);
            periodicals.add(periodical);
        }
    }
    
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
