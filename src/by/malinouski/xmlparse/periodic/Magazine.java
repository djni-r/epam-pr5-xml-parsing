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
public class Magazine extends Periodical {

    private boolean glossy;

    public boolean isGlossy() {
        return glossy;
    }
    
    public void setGlossy(boolean glossy) {
        this.glossy = glossy;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        
        Magazine other = (Magazine) obj;
        if (this.equalsPeriodical(other) && glossy == other.glossy) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (glossy ? 1 : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s magazine", super.toString(), glossy ? "glossy" : "");
    }
}
