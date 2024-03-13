import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Ocurrencia implements Serializable {

    private Integer totalFreq;
    private Map<String, Integer> FTURL;


    public Ocurrencia(Integer totalFreq, TreeMap<String, Integer> FTURL) {
        this.totalFreq = totalFreq;
        this.FTURL = FTURL;
    }

    public Integer getTotalFreq() {
        return totalFreq;
    }

    public void addTotalFreq() {
        this.totalFreq++;
    }

    public Map<String, Integer> getFTURL() {
        return FTURL;
    }

    public void setFTURL(Map<String, Integer> FTURL) {
        this.FTURL = FTURL;
    }
}
