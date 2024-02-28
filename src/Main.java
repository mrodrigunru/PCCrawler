import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Clase principal para ejecutar el programa
 *
 */
public class Main {

    TreeMap term_idx; //diccionario de terminos
    TreeMap thesaurus; //Thesauro
    static String direccion = "C:\\Users\\manur\\ribw";


    /**
     * Método principal que se ejecuta al iniciar el programa.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este programa).
     * @throws IOException Si ocurre un error de entrada/salida al listar los archivos o contar palabras.
     */
    public static void main(String[] args) throws IOException {


        File diccionario = new File("diccionario.ser");

        if (diccionario.exists()){
            System.out.println("existe");
            //TODO cargar al treemap

        }else {
            diccionario.createNewFile();


            // Lista para almacenar los archivos encontrados
            List<File> listaFicheros = new ArrayList<File>();

            listIterator li = new listIterator();
            li.listIt(direccion, listaFicheros);

            //Mostrar los ficheros para testear
            for (File file : listaFicheros) {
                System.out.println(file.getParentFile().getName() + "/" + file.getName());
            }

            String salida = "C:\\Users\\manur\\palabras.txt";

            contadorPalabras cp = new contadorPalabras();
            cp.contador(listaFicheros, salida);
        }



    }
}

//TODO si existe el diccionario.ser lo carga al treemap, else llamada al analisis. Al terminar ya no imprime, si no que salva el objeto en diccionario.ser

//TODO todo lo que se guarde en el treemap se guarda en minuscula y sin acento

//TODO si existe el Thesauro cargarlo en otro treemap

//TODO programar la consulta de palabras y devovler cuantas veces aparece