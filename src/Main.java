import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        String [] direccion = {"C:\\Users\\manur\\ribw"};
        listIterator li = new listIterator();

        List<File> listaFicheros = new ArrayList<File>();
        li.listIt(direccion, listaFicheros);
        /* for(File file : listaFicheros){
            System.out.println(file.getParentFile().getName() + "/" + file.getName());
        }
        */

        String ficheroAContar;
        String salida = "C:\\Users\\manur\\salida.txt";
        for (File file : listaFicheros){
            //String [] argumentos = {file,salida};

            contadorPalabras cp = new contadorPalabras();
            cp.contador(file, salida);
        }





    }
}