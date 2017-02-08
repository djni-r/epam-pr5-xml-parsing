package test.by.malinouski.xmlparse.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import by.malinouski.xmlparse.parser.NewspapersSAXBuilder;

public class NewspaperSAXBuilderTest {

    @Test
    public void buildSetNewspapersTest() {
        NewspapersSAXBuilder builder = new NewspapersSAXBuilder();
        builder.buildSetNewspapers("files/papers/papers.xml");
        System.out.println(builder.getNewspapers());
    }

}
