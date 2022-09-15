package relacion1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;


public class AccesoDatos {
    
    public int ejercicio1(String nombre_fichero) throws FileNotFoundException, IOException{
        int resultado=0;
        
        FileReader fr=new FileReader(nombre_fichero);
        BufferedReader br=new BufferedReader(fr);
        String numero;
        
        while((numero=br.readLine())!=null){
            int valor=Integer.parseInt(numero);
            
            if(valor>10){
                resultado+=valor;
            }
            
        }
        
        br.close();
        fr.close();
        
        return resultado;
    }
    
    public String ejercicio2(String nombre_fichero)throws FileNotFoundException, IOException{
        String res="";
        
        FileReader fr=new FileReader(nombre_fichero);
        BufferedReader br = new BufferedReader(fr);
        String empleado;
        int contador=0;
        double media=0;
        
        while((empleado=br.readLine())!=null){
            String[] partes=empleado.split(",");
            String nombre=partes[0];
            double ventas=Double.parseDouble(partes[1]);
            String provincia=partes[2];
            
            
            if(ventas>1000){
                res+=nombre+"\n";
            }
            media+=ventas;
            contador++;
            
        }
        media=media/contador;
        res+="La media de ventas de la empresa es "+media+"€";
        
        br.close();
        fr.close();
        
        return res;
    }
    
    public void ejercicio3(String nombre_fichero) throws FileNotFoundException, IOException{
    
//        FileReader fr = new FileReader(nombre_fichero);
//        BufferedReader br = new BufferedReader(fr);
//        
//        FileWriter fw = new FileWriter("fichero_modificado.txt");
//        PrintWriter nuevo_txt= new PrintWriter(fw);
//        
//        String linea;
//        
//        while((linea=br.readLine())!=null){
//            String [] partes=linea.split(",");
//            
//            String linea_mod=partes[0]+"="+partes[1]+"="+partes[2];
//            nuevo_txt.println(linea_mod);
//            Se puede usar pw.println(String.join("=",partes); una vez spliteado para juntar partes del array con "=". easy peasy.
//            
//        }
//
//        nuevo_txt.close();
//        fw.close();
//        
//        br.close();
//        fr.close();

          FileReader fr = new FileReader(nombre_fichero);
          BufferedReader br = new BufferedReader(fr);
          
          FileWriter fw = new FileWriter("fichero_xml.txt");
          PrintWriter pw = new PrintWriter(fw);
          
          File archivo = new File(nombre_fichero);
          String nombre_archivo = archivo.getName();
          String linea;
          
          //Lo idoneo es crear un array que contenga las etiquetas y crear un bucle que recorra el array poniendo etiquetas y contenidos.
          
          pw.println("<"+nombre_archivo+">");
          
          while((linea=br.readLine())!=null){
              pw.println("  <vendedor>");
              
              String[] partes=linea.split(",");
              
              pw.println("      <nombre>"+partes[0]+"</nombre>");
              pw.println("      <ventas>"+partes[1]+"</ventas>");
              pw.println("      <provincia>"+partes[2]+"</provincia>");
              
              pw.println("  </vendedor>");
          }
          
          pw.println("</"+nombre_archivo+">");
          
          
          
          br.close();
          fr.close();
          
          pw.close();
          fw.close();
    }
    
    public void ejercicio4(String nombre_fichero) throws FileNotFoundException,IOException{
        
        FileReader fr = new FileReader(nombre_fichero);
        BufferedReader br= new BufferedReader(fr);
        
        FileWriter fw = new FileWriter("provincias.txt");
        PrintWriter pw = new PrintWriter(fw);
        
        String linea;
        
        HashMap<String,Double> ventas_prov = new HashMap<>();
        
        while((linea=br.readLine())!=null){
            
            String[] datos=linea.split(",");
            
            if(ventas_prov.containsKey(datos[2])){
                double suma=ventas_prov.get(datos[2]);
                suma+=Double.parseDouble(datos[1]);
                ventas_prov.put(datos[2], suma);
            }else{
                ventas_prov.put(datos[2], Double.parseDouble(datos[1]));
            }
        }
        
        if(ventas_prov.size()>0){
            for(Entry<String,Double> prov:ventas_prov.entrySet()){
                pw.println("En "+prov.getKey()+" se ha vendido un total de "+prov.getValue()+".");
            }
        }
        
        br.close();
        fr.close();
        
        fw.close();
        pw.close();

    }
}
