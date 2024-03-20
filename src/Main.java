import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Clase principal para ejecutar el programa
 */
public class Main {

    private static TreeMap<String, Ocurrencia> term_idx = new TreeMap<>(); //diccionario de terminos
    private static TreeMap<String, Object> thesaurus = new TreeMap<>(); //Thesauro
    static String direccion = "C:\\Users\\manur\\ribw";
    static objectUtilities ou = new objectUtilities();
    static contadorPalabras cp = new contadorPalabras();
    static listIterator li = new listIterator();
    static File diccionario;
    static File thesauro;


    /**
     * Método principal que se ejecuta al iniciar el programa.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este programa).
     * @throws IOException Si ocurre un error de entrada/salida al listar los archivos o contar palabras.
     */
    public static void main(String[] args) throws IOException {


        diccionario = new File("diccionario.ser");


        if (diccionario.exists()) {

            ou.cargarDic(term_idx, "diccionario.ser");
            System.out.println("diccionario cargado");


        } else {
            diccionario.createNewFile();
            System.out.println("diccionario.ser creado");
            // Lista para almacenar los archivos encontrados
            List<File> listaFicheros = new ArrayList<>();


            li.listIt(direccion, listaFicheros);

            cp.contador(listaFicheros, term_idx);

            ou.cargarDic( term_idx,"diccionario.ser");
        }

        thesauro = new File("thesauro.ser");

        if (thesauro.exists()) {

            ou.cargarThesauro(thesaurus, "thesauro.ser");


        } else {
            thesauro.createNewFile();
            ou.procesadorThesauro();
            System.out.println("thesauro.ser creado");
            ou.cargarThesauro(thesaurus, "thesauro.ser");
        }
        // Crear un objeto Scanner para leer desde la consola
        Scanner scanner = new Scanner(System.in);

        //Bucle de escritura
        while (true) {
            //  System.out.println(term_idx);
            System.out.print("Introduce el término a buscar (ESC para salir): ");

            String palabra = scanner.nextLine();

            if (!palabra.equalsIgnoreCase("ESC")) {   //equalsIgnoreCase es igual a .toLowerCase().equals()
                if (term_idx.containsKey(palabra) && thesaurus.containsKey(palabra)) {
                    System.out.println("Término: " + palabra + "\nVeces que aparece: " + term_idx.get(palabra).getTotalFreq());
                    //System.out.println(term_idx.get(palabra).getFTURL());


                    List<Map.Entry<String, Integer>> listaRanking = new ArrayList<>();

                    for (Map.Entry<String, Integer> entry : term_idx.get(palabra).getFTURL().entrySet()) {

                        listaRanking.add(entry);

                    }

                    //Funcion lambda para comprar el ranking
                    Collections.sort(listaRanking, (o1, o2) -> {
                        // Compara los valores de Integer en orden descendente
                        return o2.getValue().compareTo(o1.getValue());
                    });


                    for(Map.Entry<String, Integer> entry : listaRanking){
                        String path = entry.getKey();
                        Integer value = entry.getValue();
                        if (value == 1) {
                            System.out.println("Aparece en el fichero: " + path + " -> " + value + " vez.");
                        } else System.out.println("Aparece en el fichero: " + path + " -> " + value + " veces.");
                    }

                    System.out.println("\n");
                } else
                    System.out.println("El término \"" + palabra + "\" no ha lanzado resultados o no es una palabra clave");
            } else break;

        }

    }
}
