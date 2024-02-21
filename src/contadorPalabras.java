
import java.io.*;
import java.util.*;

    /**
     * Clase que proporciona métodos para contar palabras en archivos y guardar los resultados en un archivo de salida.
     */
    public class contadorPalabras {

        /**
         * Cuenta palabras en los archivos especificados y guarda los resultados en un archivo de salida.
         *
         * @param listaFicheros La lista de archivos en los que se contarán las palabras.
         * @param fichSalida La ruta del archivo donde se guardarán los resultados del conteo de palabras.
         * @throws IOException Si ocurre un error de entrada/salida al leer/escribir archivos.
         */
        public  void contador (List<File> listaFicheros, String fichSalida) throws IOException {

            // Mapa para almacenar las palabras y su frecuencia
            Map <String, Integer> map = new TreeMap <String, Integer> ();

            // Iterar sobre cada archivo en la lista para parsear las palabras
            for(File ficheroActual : listaFicheros){
                if(!ficheroActual.isDirectory()){
                    BufferedReader br = new BufferedReader (new FileReader (ficheroActual.getAbsolutePath()));
                    String linea;

                    while ( (linea = br.readLine () ) != null) {
                        StringTokenizer st = new StringTokenizer (linea, " ,.:;(){}!°?\t'%/|[]<=>&#+*$-¨^~");
                        while (st.hasMoreTokens () ) {
                            String s = st.nextToken();
                            Object o = map.get(s);  //busca la cadena s en el mapa
                            if (o == null){
                                map.put (s, 1); //si no está, se guarda con un contador con valor 1
                            }
                            else {  //si está, se actualiza el valor del contador en 1
                                int cont =  (int) o;
                                map.put (s, cont + 1);
                            }
                        }
                    }
                    br.close ();


                }

            }
            System.out.println("----------------Contenido del map-------------");
            System.out.println(map.keySet().toString());

            // Ordenar las claves del mapa alfabéticamente y escribirlas en el archivo de salida
            List <String> claves = new ArrayList <String> (map.keySet ());
            Collections.sort (claves);

            PrintWriter pr = new PrintWriter (new FileWriter (fichSalida));
            Iterator <String> i = claves.iterator ();
            while (i.hasNext ()) {
                Object k = i.next ();
                pr.println (k + " : " + map.get (k));
            }
            pr.close ();

        }
    }

