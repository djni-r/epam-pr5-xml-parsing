/**
 * Epam External Training
 * Task 5
 * XML SXD Parsing
 */
package by.malinouski.xmlparse.papers;

/**
 * @author makarymalinouski
 *
 */
public class Newspaper extends PeriodicalType {

    private boolean colored;
    
    public boolean isColored() {
        return colored;
    }
    
    public void setColored(boolean colored) {
        this.colored = colored;
    }
}
