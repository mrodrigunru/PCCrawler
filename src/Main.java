import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //String [] listaFicheros = {"C:\\Users\\manur"};
        String [] listaFicheros = {"C:\\Users\\manur\\ribw"};
        listIterator li = new listIterator();
        li.listIt(listaFicheros);

        System.out.println("-----------------");

        String ficheroAContar = "C:\\Users\\manur\\ribw\\uno.txt";
        //String salida = "C:\\Users\\manur\\ribw\\salida.txt";
        String salida = "C:\\Users\\manur\\salida.txt";
        String [] argumentos = {ficheroAContar,salida};

        contadorPalabras cp = new contadorPalabras();
        cp.contador(argumentos);

        System.out.println("Comprueba el archivo 'salida.txt' generado en tu carpeta usuario");

        System.out.println("-----------------");


    }
}