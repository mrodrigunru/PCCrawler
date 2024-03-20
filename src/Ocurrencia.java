import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ocurrencia implements Serializable {

    // Frecuencia total de ocurrencia
    private int totalFreq;

    // Mapa que almacena la frecuencia del término por cada URL/Fichero
    private Map<String, Integer> FTURL = new TreeMap<>();

    /**
     * Constructor para inicializar la frecuencia total a 1 y la frecuencia
     * de la URL especificada a 1.
     *
     * @param url la URL para la cual se inicializa la frecuencia de ocurrencia
     */
    public Ocurrencia(String url) {
        totalFreq = 1;
        this.FTURL.put(url,1);
    }

    /**
     * Obtiene la frecuencia total de ocurrencia.
     *
     * @return la frecuencia total de ocurrencia
     */
    public int getTotalFreq() {
        return totalFreq;
    }

    /**
     * Obtiene el mapa que almacena las frecuencias de los términos por cada URL.
     *
     * @return el mapa de frecuencias de términos por cada URL
     */
    public Map<String, Integer> getFTURL() {
        return FTURL;
    }

    /**
     * Establece la frecuencia total de ocurrencia.
     *
     * @param nuevo la frecuencia total de ocurrencia
     */
    public void setTotalFreq(int nuevo) {
        this.totalFreq = nuevo;
    }

    /**
     * Establece el mapa que almacena las frecuencias de los términos por cada URL.
     *
     * @param nuevo el nuevo mapa de frecuencias de los términos por cada URL
     */
    public void setFTURL(Map<String, Integer> nuevo) {
        this.FTURL = nuevo;
    }

    /**
     * Incrementa la frecuencia total de ocurrencia en uno.
     * Busca dentro del mapa de FTURL si ya existe la URL pasada por parámetro y la actualiza
     * Si la URL no existe en el mapa de FTURL, crea una nueva y  la agrega con una frecuencia de 1.
     *
     * @param url la URL del fichero sobre el cuál se va a actualizar la ocurrencia del término
     */
    public void addFrequencies(String url){
        this.totalFreq++;
        if(this.FTURL.containsKey(url)){
            int nuevaFreq;
            nuevaFreq = this.FTURL.get(url) + 1;
            this.FTURL.put(url, nuevaFreq);
        }else this.FTURL.put(url,1);
    }

    /**
     * Devuelve una representación en cadena de la frecuencia total y el mapa
     * de frecuencias de los términos por cada URL.
     *
     * @return una cadena que representa la frecuencia total y el mapa de
     *         frecuencias de los términos por cada URL.
     */
    @Override
    public String toString(){

        return totalFreq + " ::::: " + FTURL;
    }
}