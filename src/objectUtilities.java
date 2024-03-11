
import java.io.*;
import java.util.*;

public class objectUtilities {


    public void salvar(String key, Object value, ObjectOutputStream oos) {
        /*
        Hashtable <String, Object> h = new Hashtable <String, Object> ();
        h.put("String","Luis Rodriguez Duran");
        h.put("Integer",new Integer(23));
        h.put("Double",new Double(0.96));

         * en el caso de nuestro PC-Crawler ha de utilizarse la estructura Heap
         * Map <String, Integer> map
         */

        Map<String, Object> h = new TreeMap<String, Object>();
        h.put(key, value);

        try {

            oos.writeObject(h);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarDic(Map<String, Ocurrencia> m, String nombreFich) {
        try {
            FileInputStream fis = new FileInputStream(nombreFich);
            ObjectInputStream ois = new ObjectInputStream(fis);
            /*
            Hashtable h = (Hashtable) ois.readObject();
             en el caso de nuestro PC-Crawler ha de utilizarse la estructura Heap:
             * Map <String, Integer> map = TreeMap <String, Integer> ois.readObject();
             */

            if(nombreFich.equals("diccionario.ser")){
                while (true) {
                    try {

                        Map<String, Ocurrencia> h = (TreeMap<String, Ocurrencia>) ois.readObject();
                        m.putAll(h);
                        //System.out.println(h.toString());

                    } catch (EOFException eofe) {
                        break;
                    }

                }
            }



            fis.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarThe(Map<String, Object> m, String nombreFich) throws IOException {

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