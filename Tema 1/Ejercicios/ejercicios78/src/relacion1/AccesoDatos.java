package relacion1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;


public class AccesoDatos {
    
    
    public AccesoDatos(){
        //Es tan simple que no es necesario atributos
    }
    
    public void crearBinario(String entrada,String salida) throws FileNotFoundException,IOException,ClassNotFoundException{
        
        // Para leer fichero linea a linea.
        FileReader fr = new FileReader(entrada);
        BufferedReader br = new BufferedReader(fr);
        
        // Para guardar el fichero en binario.
        FileOutputStream fos = new FileOutputStream(salida);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        String linea="";
        
        while((linea=br.readLine())!=null){
            String[] datos = linea.split("\\|");
            String nombre = datos[0];
            int edad = Integer.parseInt(datos[1]);
            
            Persona p = new Persona(nombre,edad);
            oos.writeObject(p);
        }
        
        oos.close();
        fos.close();
        br.close();
        fr.close();
    }
    
    public void crearTexto(String entrada,String salida) throws FileNotFoundException,IOException,ClassNotFoundException{
        FileInputStream fis = new FileInputStream(entrada);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        FileWriter fw = new FileWriter(salida);
        PrintWriter pw = new PrintWriter(fw);
        
        
        //Version 1, normal.
//        while(fis.available()>0){
//            Persona p = (Persona)ois.readObject();
//            pw.println(p.getNombre()+"/"+p.getEdad());
//        }
        
        //Version xml
        
        pw.println("<colegio>");
        
        while(fis.available()>0){
            
            Persona p = (Persona)ois.readObject();
            
            pw.println("    <alumno>");
            
            pw.println("        <nombre>"+p.getNombre()+"</nombre>");
            pw.println("        <edad>"+p.getEdad()+"</edad>");
            
            pw.println("    </alumno>");
        }
        
        pw.println("</colegio>");
        
        fis.close();
        ois.close();
        fw.close();
        pw.close();
        
    }
   
}
