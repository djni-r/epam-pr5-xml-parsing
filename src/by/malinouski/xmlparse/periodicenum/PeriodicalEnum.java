package by.malinouski.xmlparse.periodicenum;

public enum PeriodicalEnum {
    PERIODICALS("periodical"),
    ISSN("issn"),
    PERIOD("period"),
    TITLE("title"),
    VOLUME("volume"),
    NEWSPAPER("newspaper"),
    COLORED("colored"),
    MAGAZINE("magazine"),
    GLOSSY("glossy");
    
    private String value;
    
    private PeriodicalEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
}
