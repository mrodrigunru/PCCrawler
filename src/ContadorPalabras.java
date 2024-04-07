
import java.io.*;
import java.text.Normalizer;
import java.util.*;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 * Clase que proporciona métodos para contar palabras en archivos y guardar los resultados en un archivo de salida.
 */
public class ContadorPalabras {

    BufferedReader br;

    /**
     * Cuenta palabras en los archivos especificados y guarda los resultados en un archivo de salida.
     *
     * @param lista    La lista de archivos en los que se contarán las palabras.
     * @param palabras objeto de tipo ocurrencia usado para la busqueda
     * @throws IOException Si ocurre un error de entrada/salida al leer/escribir archivos.
     */
    public void contador(List<File> lista, TreeMap<String, Ocurrencia> palabras) throws IOException, TikaException, SAXException {
        for (File fichero : lista) {
            if (!fichero.isDirectory()) {
                BodyContentHandler handler = new BodyContentHandler();
                Metadata metadata = new Metadata();

                FileInputStream inputstream = new FileInputStream(fichero);

                String linea;       //Tika

                ParseContext pcontext = new ParseContext();
                ParserTika parser = new ParserTika();
                String extension = parser.getExtension(fichero);

                String texto = "";

                switch (extension) {
                    case "pdf":
                        texto = parser.parsePDF(fichero);
                        break;
                    case "htm":
                    case "html":
                        texto = parser.parseHTML(fichero);
                        break;
                    case "odt":
                        texto = parser.parseOpenDoc(fichero);
                        break;
                    case "txt":
                        texto = parser.parseTXT(fichero);
                        break;
                    case "docx":
                    case "xlsx":
                        texto = parser.parseMSDoc(fichero);
                    default:

                        break;
                }



                StringReader sr = new StringReader(texto);
                BufferedReader br = new BufferedReader(sr);

                while ( (linea = br.readLine () ) != null) {
                    StringTokenizer st = new StringTokenizer (linea, " ,.:;(){}!°?\t''%/|[]<=>&#+*$-¨^~");


                        while (st.hasMoreTokens()) {
                            String palabra = quitarAcentos(st.nextToken()).toLowerCase();

                            if (palabras.containsKey(palabra)) {
                                palabras.get(palabra).addFrequencies(fichero.getAbsolutePath());
                            } else {
                                palabras.put(palabra, new Ocurrencia(fichero.getAbsolutePath()));
                            }
                        }

                }
                br.close ();

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

            ObjectUtilities ou = new ObjectUtilities();
            ou.salvar(oos,(String) k, palabras.get(k));
        }


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




