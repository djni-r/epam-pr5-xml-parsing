package test.by.malinouski.xmlparse.parser;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import by.malinouski.xmlparse.parser.PeriodicalsDOMBuilder;

public class PeriodicalsDOMBuilderTest {

    private static PeriodicalsDOMBuilder builder;
    
    @BeforeClass
    public static void initBuilder() {
        builder = new PeriodicalsDOMBuilder();
        builder.buildSetPeriodicals("files/periodicals/periodicals.xml");
    }
    @Test
    public void buildSetPeriodicalsTest() {
        System.out.println(builder.getPeriodicals());
        assertEquals(16, builder.getPeriodicals().size());
    }

}
