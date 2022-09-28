package ejemplobinario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EjemploBinario {

    public static void main(String[] args) {

        Persona p1 = new Persona("Fran", 24,1.85);
        Persona p2 = new Persona("Pepe", 20,2.20);
        Persona p3 = new Persona("Isabel", 25,1.86);

        try {
            FileOutputStream fos = new FileOutputStream("prueba.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //Aqui escribimos la información de los objetos en el fichero de "fos"
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.writeObject(p3);

            // Importante cerrar los flujos.
            fos.close();
            oos.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
