import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Clase que proporciona métodos para listar archivos en un directorio y sus subdirectorios.
 */
public class ListIterator {

    /**
     * Lista archivos en el directorio especificado y sus subdirectorios de forma recursiva
     *
     * @param direccion La ruta del directorio que se va a listar.
     * @param lista La lista donde se almacenarán los archivos listados.
     */
    public void listItRec(String[] direccion, List<File> lista) {

        //Creacion del fichero a partir de la direccion escrita
        File fichero = new File(direccion[0]);

        //Verificar si el directorio existe y si es legible
        if (!fichero.exists() || !fichero.canRead()) {
            System.out.println("ERROR. No puedo leer " + fichero);
            return;
        }

        //En caso de ser directorio, llamada al metodo privado
        if (fichero.isDirectory()) {
            listarDirectorio(fichero, lista);
        } else {
            try {

                FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                while ((linea = br.readLine()) != null)
                    System.out.println(linea);
                br.close(); //bufer cerrado
            } catch (IOException fnfe) {
                System.out.println("ERROR. Fichero desaparecido en combate  ;-)");
            }
        }
    }

    /**
     * Método privado para listar los archivos en un directorio y sus subdirectorios de forma recursiva.
     *
     * @param directorio El directorio que se va a listar.
     * @param lista La lista donde se almacenarán los archivos listados.
     */
    private void listarDirectorio(File directorio, List<File> lista) {
        //Obtener la lista de archivos en el directorio
        File[] files = directorio.listFiles();
        if (files != null) {
            for (File file : files) {
                lista.add(file);    //Añadir a la lista de salida (solo para mostrar, si solo nos queremos quedar con los archivos no directorios, se debe meter en el else abajo)
                if (file.isDirectory()) {
                    listarDirectorio(file, lista); //Si el archivo es directorio, se rehace la llamada
                }
            }
        }
    }


    /**
     * Lista archivos en el directorio especificado y sus subdirectorios de forma iterativa
     *
     * @param direccion La ruta del directorio que se va a listar.
     * @param lista La lista donde se almacenarán los archivos listados.
     */
    public void listIt(String direccion, List<File> lista) {
        File fichero = new File(direccion);
        if (!fichero.exists() || !fichero.canRead()) {
            System.out.println("ERROR. No puedo leer " + fichero);
            return;
        }
        // Pila para almacenar los directorios
        Deque<File> stack = new ArrayDeque<>();
        stack.push(fichero); // Agregar el directorio raíz a la pila
        while (!stack.isEmpty()) {
            File file1 = stack.pop(); // Obtener el directorio actual de la pila
            File[] listaFicheros = file1.listFiles();
            if (listaFicheros != null) {
                for (File file : listaFicheros) {
                    lista.add(file); // Agregar el archivo a la lista
                    if (file.isDirectory()) {
                        stack.push(file); // Si es un directorio, agregarlo a la pila para procesarlo posteriormente
                    }
                }
            }
        }
    }
}


