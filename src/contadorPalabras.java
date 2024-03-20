
import java.io.*;
import java.text.Normalizer;
import java.util.*;

/**
 * Clase que proporciona métodos para contar palabras en archivos y guardar los resultados en un archivo de salida.
 */
public class contadorPalabras {


    /**
     * Cuenta palabras en los archivos especificados y guarda los resultados en un archivo de salida.
     *
     * @param lista La lista de archivos en los que se contarán las palabras.
     * @param palabras objeto de tipo ocurrencia usado para la busqueda
     * @throws IOException Si ocurre un error de entrada/salida al leer/escribir archivos.
     */
    public  void contador ( List<File> lista, TreeMap<String, Ocurrencia> palabras) throws IOException {
    for(File fichero : lista){
        if (!fichero.isDirectory()) {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            String linea;       //Tika

            while ((linea = br.readLine()) != null) {
                linea = quitarAcentos(linea);

                StringTokenizer st = new StringTokenizer(linea, ",.:;(){}!°?\t''%/|[]<=>&#+*$-¨^~ "); // Divide cada línea en palabras utilizando delimitadores
                while (st.hasMoreTokens()) {
                    String palabra = st.nextToken();

                    if (palabras.containsKey(palabra)) {
                        palabras.get(palabra).addFrequencies(fichero.getAbsolutePath());
                    } else {
                        palabras.put(palabra, new Ocurrencia(fichero.getAbsolutePath()));
                    }
                }
            }
            br.close();
        }
    }


        // Ordenar las claves del mapa alfabéticamente y escribirlas en el archivo de salida
        List <String> claves = new ArrayList <> (palabras.keySet ());
        Collections.sort (claves);

        //System.out.println(palabras);

        FileOutputStream fos = new FileOutputStream("diccionario.ser",true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Iterator <String> i = claves.iterator ();
        while (i.hasNext ()) {
            Object k = i.next ();

            objectUtilities ou = new objectUtilities();
            ou.salvar(oos,(String) k, palabras.get(k));
        }

        oos.close();
        fos.close();

    }

    /**
     * Elimina los acentos y normaliza la palabra que se pasa por parámetro
     *
     * @param texto Palabra (token) el cuál se normaliza y eliminan los acentos.
     *
     */
    public static String quitarAcentos(String texto) {
        String palabraNormalizada = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return palabraNormalizada.replaceAll("[áÁ]", "a")
                .replaceAll("[éÉ]", "e")
                .replaceAll("[íÍ]", "i")
                .replaceAll("[óÓ]", "o")
                .replaceAll("[úÚ]", "u");
    }



}


