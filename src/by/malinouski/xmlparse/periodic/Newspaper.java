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
public class Newspaper extends Periodical {

    private boolean colored;
    
    public boolean isColored() {
        return colored;
    }
    
    public void setColored(boolean colored) {
        this.colored = colored;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        
        Newspaper other = (Newspaper) obj;
        if (equalsPeriodical(other) && colored == other.colored) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (colored ? 1 : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s newspaper", super.toString(), 
                             colored ? "colored" : "black-and-white");
    }
}
