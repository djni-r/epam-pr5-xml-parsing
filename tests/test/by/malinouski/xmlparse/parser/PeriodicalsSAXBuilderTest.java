package test.by.malinouski.xmlparse.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import by.malinouski.xmlparse.parser.PeriodicalsSAXBuilder;
import by.malinouski.xmlparse.periodic.Magazine;
import by.malinouski.xmlparse.periodic.Newspaper;
import by.malinouski.xmlparse.periodic.Periodical;

public class PeriodicalsSAXBuilderTest {
    
    private static PeriodicalsSAXBuilder builder;

    @BeforeClass
    public static void initBuilder() {
        builder = new PeriodicalsSAXBuilder();
        builder.buildSetPeriodicals("files/periodicals/periodicals.xml");
    }

    @Test
    public void buildSetPeriodicalsTestSize() {
        assertEquals(16, builder.getPeriodicals().size());
    }
    
    @Test
    public void buildSetPeriodicalsTestFullElementNewspaper() {
        Newspaper news = new Newspaper();
        news.setIssn("2343-342X");
        news.setPeriod(Periodical.Period.WEEKLY);
        news.setTitle("The Daily Mash");
        news.setVolume(36);
        news.setColored(true);
        
        System.out.println(builder.getPeriodicals());
        assertTrue(builder.getPeriodicals().contains(news));
    }
    
    @Test
    public void buildSetPeriodicalsTestFullElementMagazine() {
        Magazine mag = new Magazine();
        mag.setIssn("5436-4430");
        mag.setTitle("Cosmopolitan");
        mag.setVolume(94);
        mag.setGlossy(true);
        
        System.out.println(builder.getPeriodicals());
        assertTrue(builder.getPeriodicals().contains(mag));
    }

}
