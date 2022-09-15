
package tema.pkg1.dia.pkg1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Tema1Dia1 {

    
    public static void main(String[] args) {
        try {
            // Crear el fichero en modo escritura.
            FileWriter fw = new FileWriter("numeros.txt");
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println("Inicio del fichero.");
            for (int i=1; i<=10 ; i++){
                pw.println("Linea "+i);
            }
            pw.println("Fin del fichero.");
            
            //Cerrar el fichero para volcar datos.
            pw.close();
            fw.close();
            
        } catch (IOException ex) {
            System.out.println("Fallo al escribir el fichero.");
        }
        
    }
    
}
