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

import by.malinouski.xmlparse.periodic.Magazine;
import by.malinouski.xmlparse.periodic.Newspaper;
import by.malinouski.xmlparse.periodic.Periodical;
import by.malinouski.xmlparse.periodicenum.PeriodicalEnum;

/**
 * @author makarymalinouski
 *
 */
public class PeriodicalHandler extends DefaultHandler {

    static final int ISSN_ATTR = 0;
    static final int PERIOD_ATTR = 1;
    private Set<Periodical> periodicals;
    private Periodical current;
    private PeriodicalEnum currentEnum;
    private EnumSet<PeriodicalEnum> withText;

    public PeriodicalHandler() {
        periodicals = new HashSet<>();
        withText = EnumSet.range(PeriodicalEnum.TITLE, PeriodicalEnum.GLOSSY);
    }

    public Set<Periodical> getPeriodicals() {
        return Collections.unmodifiableSet(periodicals);
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("newspaper".equals(localName)) {
            current = new Newspaper();
            current.setIssn(attrs.getValue(ISSN_ATTR).intern());
            
            if (attrs.getLength() == 2) {
                current.setPeriod(Periodical.Period.valueOf(attrs.getValue(PERIOD_ATTR).toUpperCase()));
            }
            
        } else if ("magazine".equals(localName)) {
            current = new Magazine();
            current.setIssn(attrs.getValue(ISSN_ATTR).intern());
            
            if (attrs.getLength() == 2) {
                current.setPeriod(Periodical.Period.valueOf(attrs.getValue(PERIOD_ATTR).toUpperCase()));
            }
        } else {
            PeriodicalEnum temp = PeriodicalEnum.valueOf(localName.toUpperCase());
            if (withText != null && withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }
    
    public void endElement(String uri, String localName, String qName) {
        if ("newspaper".equals(localName)  || "magazine".equals(localName)) {
            periodicals.add(current);
        }
    }
    
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim().intern();
        if (currentEnum != null) {
            switch (currentEnum) {
                case TITLE: 
                    current.setTitle(s);
                    break;
                case VOLUME:
                    current.setVolume(Integer.valueOf(s));
                    break;
                case COLORED:
                    ((Newspaper) current).setColored(Boolean.valueOf(s));
                    break;
                case GLOSSY:
                    ((Magazine) current).setGlossy(Boolean.valueOf(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}   
