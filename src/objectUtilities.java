/*
 * SalvarObjeto.java: Guarda un objeto serializable en un fichero
 * (i) Felix R. Rguez., EPCC, Universidad de Extremadura, 2009-23
 * http://madiba.unex.es/
 */

import java.io.*;
import java.util.*;

public class objetoUtilities {
    public  void salvar (String args[]) {
        Hashtable <String, Object> h = new Hashtable <String, Object> ();
        h.put("String","Luis Rodriguez Duran");
        h.put("Integer",new Integer(23));
        h.put("Double",new Double(0.96));
        /*
         * en el caso de nuestro PC-Crawler ha de utilizarse la estructura Heap
         * Map <String, Integer> map
         */
        try {
            FileOutputStream fos = new FileOutputStream("diccionario.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(h);
        }
        catch (Exception e) { System.out.println(e); }
    }

    public  void cargar (String args[]) {
        try {
            FileInputStream fis = new FileInputStream("diccionario.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Hashtable h = (Hashtable) ois.readObject();
            /* en el caso de nuestro PC-Crawler ha de utilizarse la estructura Heap:
             * Map <String, Integer> map = TreeMap <String, Integer> ois.readObject();
             */
            System.out.println(h.toString());
        }
        catch (Exception e) { System.out.println(e); }
    }
}