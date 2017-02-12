/**
 * Epam External Training
 * Task 5
 * XML SXD Parsing
 */
package by.malinouski.xmlparse.periodic;

/**
 * @author makarymalinouski
 *
 */
public abstract class Periodical {
    
    public enum Period {
        DAILY, WEEKLY, MONTHLY, QUARTERLY, UNSPECIFIED
    }
    
    private String issn;
    private Period period;
    private String title;
    private int volume;
    
    public Periodical() {
        period = Period.UNSPECIFIED;
    }
    
    public String getIssn() {
        return issn;
    }
    
    public void setIssn(String issn) {
        this.issn = issn;
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
    
    public boolean equalsPeriodical(Periodical other) {
        if (issn == other.issn && period == other.period && 
                title == other.title && volume == other.volume) {
            return true;
        } else {
            return false;
        }
    }

    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + issn.hashCode();
        result = 31 * result + period.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + volume;
        
        return result;
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\" %s %dp %s", 
                title, issn, volume, (period != Period.UNSPECIFIED ? period : ""));
    }

}
