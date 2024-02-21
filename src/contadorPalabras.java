

    /*
     * FichContPalabras.java: Contabiliza palabras contenidas en un fichero
     * (i) Felix R. Rguez., EPCC, Universidad de Extremadura, 2009-23
     * http://madiba.unex.es/
     */

import java.io.*;
import java.util.*;

    public class contadorPalabras {

        public  void contador (List<File> listaFicheros, String fichSalida) throws IOException {

            Map <String, Integer> map = new TreeMap <String, Integer> ();

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

