
import java.io.*;
import java.util.*;

public class objectUtilities {
    public  void salvar (String key, Object value) {
        /*
        Hashtable <String, Object> h = new Hashtable <String, Object> ();
        h.put("String","Luis Rodriguez Duran");
        h.put("Integer",new Integer(23));
        h.put("Double",new Double(0.96));

         * en el caso de nuestro PC-Crawler ha de utilizarse la estructura Heap
         * Map <String, Integer> map
         */

         Map <String,Object> h = new TreeMap<String, Object>();
         h.put(key,value);

        try {
            FileOutputStream fos = new FileOutputStream("diccionario.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(h);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public  void cargar (Map h) {
        try {
            FileInputStream fis = new FileInputStream("diccionario.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            /*
            Hashtable h = (Hashtable) ois.readObject();
             en el caso de nuestro PC-Crawler ha de utilizarse la estructura Heap:
             * Map <String, Integer> map = TreeMap <String, Integer> ois.readObject();
             */
            h = (TreeMap)  ois.readObject();
            System.out.println(h.toString());
        }
        catch (Exception e) { System.out.println(e); }
    }
}