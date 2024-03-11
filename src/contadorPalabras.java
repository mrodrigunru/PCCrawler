
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
         * @param listaFicheros La lista de archivos en los que se contarán las palabras.
         * @throws IOException Si ocurre un error de entrada/salida al leer/escribir archivos.
         */
        public  void contador (List<File> listaFicheros) throws IOException {

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
                            Object o = map.get(quitarAcentos(s.toLowerCase()));  //busca la cadena s en el mapa
                            if (o == null){
                                map.put (quitarAcentos(s.toLowerCase()), 1); //si no está, se guarda con un contador con valor 1
                            }
                            else {  //si está, se actualiza el valor del contador en 1
                                int cont =  (int) o;
                                map.put (quitarAcentos(s.toLowerCase()), cont + 1);
                            }
                        }
                    }
                    br.close ();


                }

            }


            // Ordenar las claves del mapa alfabéticamente y escribirlas en el archivo de salida
            List <String> claves = new ArrayList <String> (map.keySet ());
            Collections.sort (claves);

            FileOutputStream fos = new FileOutputStream("diccionario.ser",true);;
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Iterator <String> i = claves.iterator ();
            while (i.hasNext ()) {
                Object k = i.next ();

                objectUtilities ou = new objectUtilities();
                ou.salvar((String) k, map.get(k),oos);
            }

            oos.close();
            fos.close();



        }

        public static String quitarAcentos(String texto) {
            String palabraNormalizada = Normalizer.normalize(texto, Normalizer.Form.NFD);
            return texto.replaceAll("[áÁ]", "a")
                    .replaceAll("[éÉ]", "e")
                    .replaceAll("[íÍ]", "i")
                    .replaceAll("[óÓ]", "o")
                    .replaceAll("[úÚ]", "u");
        }
    }


