import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ocurrencia implements Serializable {

    private int totalFreq;
    private Map<String, Integer> FTURL = new TreeMap<>();

    // Constructor to initialize totalFreq to 0 and FTURL to an empty HashMap
    public Ocurrencia(String url) {
        totalFreq = 1;
        this.FTURL.put(url,1);
    }

    public int getTotalFreq() {
        return totalFreq;
    }

    public Map<String, Integer> getFTURL() {
        return FTURL;
    }


    public void setTotalFreq(int nuevo) {
        this.totalFreq = nuevo;
    }


    public void setFTURL(Map<String, Integer> nuevo) {
        this.FTURL = nuevo;
    }

    public void addFrequencies(String url){
        this.totalFreq++;
        if(this.FTURL.containsKey(url)){
            int nuevaFreq;
            nuevaFreq = this.FTURL.get(url) + 1;
            this.FTURL.put(url, nuevaFreq);
        }else this.FTURL.put(url,1);
    }

    @Override
    public String toString(){

        return totalFreq + " ::::: " + FTURL;
    }
}