
import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class objectUtilities {

    TreeMap<String, Ocurrencia> mapa;

    public boolean salvar(String fichero, Object ocurrencia){
        try {
            FileOutputStream fos = new FileOutputStream(fichero);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(ocurrencia);

            oos.close();
            fos.close();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public TreeMap<String, Ocurrencia> cargar(String fichero){
        try {
            FileInputStream fis = new FileInputStream(fichero);
            ObjectInputStream ois = new ObjectInputStream(fis);

            mapa = (TreeMap<String, Ocurrencia>) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (Exception e) {
            return null;
        }
        return mapa;
    }
    public void cargarThesauro(Map<String, Object> m, String nombreFich) throws IOException {

        if(nombreFich.equals("thesauro.ser")){
            FileInputStream fis = new FileInputStream(nombreFich);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {

                    String palabra = (String) ois.readObject();
                    m.put(palabra,palabra);
                    //System.out.println(h.toString());

                } catch (EOFException | ClassNotFoundException eofe) {
                    break;
                }

            }
        }
    }

    public void procesadorThesauro() {
        try (BufferedReader br = new BufferedReader(new FileReader("Thesaurus_es_ES.txt"));
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("thesauro.ser"))) {

            String linea;
            while (true) {
                try{
                    linea = br.readLine();
                    if(linea!=null){
                        // Si la línea comienza con "#" la saltamos
                        if (linea.startsWith("#")) {
                            continue;
                        }

                        // Dividir la línea en tokens usando ";" o ","
                        String[] tokens = linea.split("[;,]");

                        // Guardar los tokens en una lista
                        List<String> datos = new ArrayList<>();
                        for (String token : tokens) {

                            datos.add(token.trim());
                        }

                        // Escribir los datos en el archivo de salida utilizando ObjectOutputStream
                        for (String dato : datos) {

                            oos.writeObject(dato);
                        }
                    }else break;

                }catch (EOFException eofe) {
                    break;
                }

            }
            br.close();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
