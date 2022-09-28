package ejemplobinario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeerBinario {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("prueba.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            //Forma un poco cutre de hacerlo.
//            Persona p = (Persona) ois.readObject();
//            do{
//                System.out.println(p.getNombre()+" "+p.getEdad());
//            }while((p=(Persona) ois.readObject())!=null);
            

            //Forma correcta que comprueba los bytes sin leer restantes del documento.
            while(fis.available()>0){
                Persona p = (Persona) ois.readObject();
                System.out.println(p.getNombre()+" "+p.getEdad());
            }
            
            ois.close();
            fis.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("Fichero no encontrado.");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("La clase no coincide con el contenido.");
        }
    }
}
