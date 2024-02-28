import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Clase principal para ejecutar el programa
 *
 */
public class Main {

    static Map term_idx = new TreeMap(); //diccionario de terminos
    static TreeMap thesaurus = new TreeMap(); //Thesauro
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

            ou.cargar(term_idx, "diccionario.ser");

          //  System.out.println("------------Diccionario.ser-----------");
          //  System.out.println(term_idx.toString());

        }else {



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

            ou.cargar(thesaurus,"thesauro.ser");

            //System.out.println("------------Thesauro.ser-----------");
            //System.out.println(thesaurus.toString());

        }else {

            ou.procesadorThesauro();

        }
        // Crear un objeto Scanner para leer desde la consola
        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.print("Palabra (ESC para salir): ");

            String palabra = scanner.nextLine();

            if(!palabra.toUpperCase().equals("ESC")){
                if(term_idx.containsKey(palabra) && thesaurus.containsKey(palabra)){
                    System.out.println("Término: " + palabra + "\nVeces que aparece: "+ term_idx.get(palabra));
                } else System.out.println("El término "+ palabra + " no ha lanzado resultados o no es una palabra clave");
            } else break;

        }

    }
}

// si existe el diccionario.ser lo carga al treemap, else llamada al analisis. Al terminar ya no imprime, si no que salva el objeto en diccionario.ser

// lo que se guarde en el treemap se guarda en minuscula y sin acento

//TODO si existe el Thesauro cargarlo en otro treemap

//TODO programar la consulta de palabras y devovler cuantas veces aparece