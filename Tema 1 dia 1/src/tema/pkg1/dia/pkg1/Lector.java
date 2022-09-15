
package tema.pkg1.dia.pkg1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Lector {
    //psvm tab:
    public static void main(String[] args) {
        
        try{
            //Abrir en modo lectura.
            FileReader fr=new FileReader("numeros.txt");
            BufferedReader br=new BufferedReader(fr);
            
            String linea;
            while((linea=br.readLine())!=null){
                System.out.println(linea);
            }
            
            //Cerrar para liberar recursos.
            br.close();
            fr.close();
            
        }catch(FileNotFoundException fnf){
            System.out.println("No se encuentra el fichero.");
        }catch(IOException io){
            System.out.println("Error de lectura de datos.");
        }
    }
}
