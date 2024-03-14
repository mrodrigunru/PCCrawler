
import java.io.*;
import java.text.Normalizer;
import java.util.*;

    /**
     * Clase que proporciona métodos para contar palabras en archivos y guardar los resultados en un archivo de salida.
     */
    public class contadorPalabras {

        private Map<String, Ocurrencia> wordOccurrences = new TreeMap<>();

        /**
         * Cuenta palabras en los archivos especificados y guarda los resultados en un archivo de salida.
         *
         * @param listaFicheros La lista de archivos en los que se contarán las palabras.
         * @throws IOException Si ocurre un error de entrada/salida al leer/escribir archivos.
         */
        public  void contador (List<File> listaFicheros) throws IOException {

            // Mapa para almacenar las palabras y su frecuencia
            Map <String, Ocurrencia> map = new TreeMap <> ();

            Ocurrencia occ ;

            Map<String, Integer> FTURL = new TreeMap<>();

            // Iterar sobre cada archivo en la lista para parsear las palabras
            for(File ficheroActual : listaFicheros){

                //FTURL.put(ficheroActual.getAbsolutePath(),0);
               // FTURL.put(ficheroActual.getAbsolutePath(),1);
              //  System.out.println(FTURL.toString());

                if(!ficheroActual.isDirectory()){
                    BufferedReader br = new BufferedReader (new FileReader (ficheroActual.getAbsolutePath()));
                    String linea;


                    while ( (linea = br.readLine () ) != null) {
                        StringTokenizer st = new StringTokenizer (linea, " ,.:;(){}!°?\t'%/|[]<=>&#+*$-¨^~");
                        while (st.hasMoreTokens () ) {

                            String s = st.nextToken();
                            Ocurrencia o = map.get(quitarAcentos(s.toLowerCase()));  //busca la cadena s en el mapa
                            if (o == null){
                               // FTURL.put(ficheroActual.getAbsolutePath(),-1);
                                //occ.setFTURL(FTURL);
                                //occ.addTotalFreq();
                                occ = new Ocurrencia();
                                occ.setTotalFreq(1);
                                map.put (quitarAcentos(s.toLowerCase()), occ);
                               //si no está, se guarda con un contador con valor 1

                            }
                            else {  //si está, se actualiza el valor del contador en 1
                                int cont =  o.getTotalFreq() + 1;
                                occ = new Ocurrencia();
                                occ.setTotalFreq(cont);
                                map.put (quitarAcentos(s.toLowerCase()), occ);
                            }
                        }
                    }
                    br.close ();


                }
            System.out.println(map);
            }


            // Ordenar las claves del mapa alfabéticamente y escribirlas en el archivo de salida
            List <String> claves = new ArrayList <> (map.keySet ());
            Collections.sort (claves);

            FileOutputStream fos = new FileOutputStream("diccionario.ser",true);
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


