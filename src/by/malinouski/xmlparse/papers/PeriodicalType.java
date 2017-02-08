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
public abstract class PeriodicalType {
    
    public enum Period {
        DAILY, WEEKLY, MONTHLY, QUARTERLY
    }
    
    private int index;
    private Period period;
    private String title;
    private int volume;
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public Period getPeriod() {
        return period;
    }
    
    public void setPeriod(Period period) {
        this.period = period;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getVolume() {
        return volume;
    }
    
    public void setVolume(int volume) {
        this.volume = volume;
    }

}
