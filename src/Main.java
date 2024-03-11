import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Clase principal para ejecutar el programa
 *
 */
public class Main {

    private static Map<String,Ocurrencia> term_idx = new TreeMap<String, Ocurrencia>(); //diccionario de terminos
    private static Map<String, Object> thesaurus = new TreeMap<String, Object>(); //Thesauro
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
    public static void main(String[] args) throws IOException, InterruptedException {



        diccionario = new File("diccionario.ser");

        if (diccionario.exists()){

            ou.cargarDic(term_idx, "diccionario.ser");

          //  System.out.println("------------Diccionario.ser-----------");
          //  System.out.println(term_idx.toString());

        }else {
            System.out.println("diccionario.ser creado");
            // Lista para almacenar los archivos encontrados
            List<File> listaFicheros = new ArrayList<File>();


            li.listIt(direccion, listaFicheros);

            //Mostrar los ficheros para testear
           // for (File file : listaFicheros) {
            //    System.out.println(file.getParentFile().getName() + "/" + file.getName());
            //}


            cp.contador(listaFicheros);
        }
        thesauro = new File("thesauro.ser");

        if (thesauro.exists()){

            ou.cargarThe(thesaurus,"thesauro.ser");

            //System.out.println("------------Thesauro.ser-----------");
            //System.out.println(thesaurus.toString());

        }else {

            ou.procesadorThesauro();
            System.out.println("thesauro.ser creado");
        }
        // Crear un objeto Scanner para leer desde la consola
        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.print("Palabra (ESC para salir): ");

            String palabra = scanner.nextLine();

            if(!palabra.equalsIgnoreCase("ESC")){   //equalsIgnoreCase es igual a .toLowerCase().equals()
                if(term_idx.containsKey(palabra) && thesaurus.containsKey(palabra)){
                    System.out.println("Término: " + palabra + "\nVeces que aparece: "+ term_idx.get(palabra));
                } else System.out.println("El término \""+ palabra + "\" no ha lanzado resultados o no es una palabra clave");
            } else break;

        }

    }
}

    //TODO en vez de guardar ahora la frecuencia del termino global en el diccionario, guardar en su lugar un mapa cuya clave es la frecuencia global y el valor un mapa con
    // los documentos donde aparece y la frecuencia en la que aparece por cada documento (ordenado por frecuencia) la estructura es un objeto de tipo  "ocurrencias" cuyos
    // valores serán variables de la clase