import java.io.*;

public class listIterator {

    public void listIt(String[] args) {
        /* if (args.length < 1) {
            System.out.println("ERROR. Ejecutar: >java ListIt nombre_archivo");
            return;
        }*/

        File fichero = new File(args[0]);

        if (!fichero.exists() || !fichero.canRead()) {
            System.out.println("ERROR. No puedo leer " + fichero);
            return;
        }

        if (fichero.isDirectory()) {
            listarDirectorio(fichero);
        } else {
            try {
                /* Interesante filtrar previamente archivos solo textuales, como los
                 * .txt, .java, .c, .cpp, etc., empleando metodos de la clase String:
                 * lastIndexOf('.'), substring (posic) y equals(".txt")...
                 */
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

    private void listarDirectorio(File directorio) {
        File[] files = directorio.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getParentFile().getName() + "/" + file.getName());     //.getAbsolutePath() para mostrar la ruta absoluta del archivo
                if (file.isDirectory()) {
                    listarDirectorio(file);
                }
            }
        }
    }

}
