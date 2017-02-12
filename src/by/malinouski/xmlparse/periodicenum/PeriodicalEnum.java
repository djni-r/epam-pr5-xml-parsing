package by.malinouski.xmlparse.periodicenum;

public enum PeriodicalEnum {
    PERIODICALS("periodical"),
    INDEX("index"),
    PERIOD("period"),
    TITLE("title"),
    VOLUME("volume"),
    COLORED("colored"),
    GLOSSY("glossy");
    
    private String value;
    
    private PeriodicalEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
}
