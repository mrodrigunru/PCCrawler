import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ocurrencia implements Serializable {

    private int totalFreq;
    private Map<String, Integer> FTURL;

    // Constructor to initialize totalFreq to 0 and FTURL to an empty HashMap
    public Ocurrencia() {
        totalFreq = 0;
        FTURL = new TreeMap<>();
    }

    public int getTotalFreq() {
        return totalFreq;
    }

    public void incrementTotalFreq() {
        this.totalFreq++;
    }

    public void setTotalFreq(int nuevo) {
        this.totalFreq = nuevo;
    }

    public Map<String, Integer> getFTURL() {
        return FTURL;
    }

    public void setFTURL(Map<String, Integer> FTURL) {
        this.FTURL = FTURL;
    }
}