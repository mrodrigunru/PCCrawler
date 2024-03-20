
import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

/**
 * La clase objectUtilities proporciona métodos para realizar operaciones
 * de manipulación de objetos, esta tiene los métodos agregados para salvar y cargar los objetos.
 * De igual manera también se encuentra el método que procesa el thesaurus.
 */
public class objectUtilities {

    TreeMap<String, Ocurrencia> mapa;

    /**
     * Guarda un objeto de tipo Ocurrencia en el fichero especificado.
     * En este caso el salvar es utilizado para almacenar
     *
     * @param oos Stream de escritura para escribir el objeto
     * @param key clave (palabra) a guardar
     * @param ocurrencia ocurrencia asociada a la key
     * @return true si la operación de guardado es exitosa, false en caso contrario
     */
    public boolean salvar(ObjectOutputStream oos, String key,  Object ocurrencia){
        try {

            Map<String, Object> h = new TreeMap<>();
            h.put(key, ocurrencia);

            oos.writeObject(h);

        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Carga un mapa desde un archivo especificado, en este caso sería el diccionario que queremos utilizar para almacenar términos.
     *
     * @param m mapa que guarda el diccionario
     * @param nombreFich nombre del fichero que contiene el diccionario
     * @return un TreeMap que contiene el objeto cargado, o null si ocurre un error
     */
    public void cargarDic(Map<String, Ocurrencia> m, String nombreFich) {
        try {
            FileInputStream fis = new FileInputStream(nombreFich);
            ObjectInputStream ois = new ObjectInputStream(fis);
            /*
            Hashtable h = (Hashtable) ois.readObject();
             en el caso de nuestro PC-Crawler ha de utilizarse la estructura Heap:
             * Map <String, Integer> map = TreeMap <String, Integer> ois.readObject();
             */

            if(nombreFich.equals("diccionario.ser")){
                while (true) {
                    try {

                        Map<String, Ocurrencia> h = (TreeMap<String, Ocurrencia>) ois.readObject();
                        m.putAll(h);
                        //System.out.println(h.toString());

                    } catch (EOFException eofe) {
                        break;
                    }

                }
            }

            fis.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga datos de un archivo de thesaurus en un mapa dado por parámetro.
     *
     * @param m el mapa en el que se cargarán los datos del thesaurus
     * @param nombreFich el nombre del archivo de thesaurus
     */
    public void cargarThesauro(Map<String, Object> m, String nombreFich) throws IOException {

        if(nombreFich.equals("thesauro.ser")){
            FileInputStream fis = new FileInputStream(nombreFich);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {

                    String palabra = (String) ois.readObject();
                    m.put(palabra,palabra);
                    //System.out.println(h.toString());

                } catch (EOFException | ClassNotFoundException eofe) {
                    break;
                }

            }
        }
    }

    /**
     * Procesa el archivo de thesaurus de un búfer de lectura y escribe los datos en un archivo serializado a través de un búfer de escritura,
     * esto guarda el thesaurus serializado en el archivo que está especificado en el método.
     */
    public void procesadorThesauro() {
        try (BufferedReader br = new BufferedReader(new FileReader("Thesaurus_es_ES.txt"));
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("thesauro.ser"))) {

            String linea;
            while (true) {
                try{
                    linea = br.readLine();
                    if(linea!=null){
                        // Si la línea comienza con "#" la saltamos
                        if (!linea.startsWith("#")) {
                            // Dividir la línea en tokens usando ";" o ","
                            String[] tokens = linea.split("[;,]");

                            // Guardar los tokens en una lista
                            List<String> datos = new ArrayList<>();
                            for (String token : tokens) {

                                datos.add(token.trim());
                            }
                            // Escribir los datos en el archivo de salida utilizando ObjectOutputStream
                            for (String dato : datos) {

                                oos.writeObject(dato);
                            }
                        }

                    }else break;

                }catch (EOFException eofe) {
                    break;
                }

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
