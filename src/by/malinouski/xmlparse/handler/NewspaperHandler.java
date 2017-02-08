/**
 * Epam External Training
 * Task 5
 * XML SXD Parsing
 */
package by.malinouski.xmlparse.handler;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import by.malinouski.xmlparse.papers.Newspaper;
import by.malinouski.xmlparse.papers.PeriodicalType;
import by.malinouski.xmlparse.papersenum.NewspaperEnum;

/**
 * @author makarymalinouski
 *
 */
public class NewspaperHandler extends DefaultHandler {

    private Set<Newspaper> newspapers;
    private Newspaper current;
    private NewspaperEnum currentEnum;
    private EnumSet<NewspaperEnum> withText;

    public NewspaperHandler() {
        newspapers = new HashSet<>();
    }

    public Set<Newspaper> getNewspapers() {
        return Collections.unmodifiableSet(newspapers);
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("newspaper".equals(localName)) {
            current = new Newspaper();
            current.setIndex(Integer.parseInt(attrs.getValue(0)));
            
            if (attrs.getLength() == 2) {
                current.setPeriod(PeriodicalType.Period.valueOf(attrs.getValue(1).toUpperCase()));
            }
        } else {
            NewspaperEnum temp = NewspaperEnum.valueOf(localName.toUpperCase());
            if (withText != null && withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }
    
    public void endElement(String uri, String localName, String qName) {
        if ("newspaper".equals(localName)) {
        newspapers.add(current);
        }
    }
    
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                
            }
            currentEnum = null;
        }
    }
}   
