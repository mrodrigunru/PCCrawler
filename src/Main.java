import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal para ejecutar el programa
 */
public class Main {

    /**
     * Método principal que se ejecuta al iniciar el programa.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este programa).
     * @throws IOException Si ocurre un error de entrada/salida al listar los archivos o contar palabras.
     */
    public static void main(String[] args) throws IOException {

        // Dirección de la carpeta que se va a explorar
        String[] direccion = {"C:\\Users\\manur\\ribw"};

        // Lista para almacenar los archivos encontrados
        List<File> listaFicheros = new ArrayList<File>();

        //Llamada a listIt
        listIterator li = new listIterator();
        li.listIt(direccion, listaFicheros);

        //Mostrar los ficheros
        for (File file : listaFicheros) {
            System.out.println(file.getParentFile().getName() + "/" + file.getName());
        }

        //Direccion del fichero de salida
        String salida = "C:\\Users\\manur\\palabras.txt";

        contadorPalabras cp = new contadorPalabras();
        cp.contador(listaFicheros, salida);


    }
}