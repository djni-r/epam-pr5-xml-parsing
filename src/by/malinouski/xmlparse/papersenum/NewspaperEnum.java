package by.malinouski.xmlparse.papersenum;

public enum NewspaperEnum {
    PAPERS("papers"),
    INDEX("index"),
    PERIOD("period"),
    NEWSPAPER("newspaper"),
    TITLE("title"),
    VOLUME("volume"),
    COLORED("colored");
    
    private String value;
    
    private NewspaperEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
