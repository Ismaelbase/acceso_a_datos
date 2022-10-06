package relacion1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class AccesoDatos {
    
    public int ejercicio1(String nombre_fichero) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(nombre_fichero);
        BufferedReader br = new BufferedReader(fr);
        
        String linea;
        int suma=0;
        
        while((linea = br.readLine()) != null){
            int numero=0;
            if((numero = Integer.parseInt(linea)) > 10){
                suma += numero;
            }
        }

        br.close();
        fr.close();
        
        return suma;
    }
    
    public String ejercicio2(String nombre_fichero) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(nombre_fichero);
        BufferedReader br = new BufferedReader(fr);
        
        String linea="";
        String vendedores = "";
        double suma=0;
        int contador = 0;
        
        while((linea = br.readLine()) != null){
            String[] datos = linea.split(",");
            
            double dineros = Double.parseDouble(datos[1]);
            suma += dineros;
            contador++;
            
            if(dineros>1000){
                vendedores += "El empleado "+datos[0]+" ha vendido "+dineros+"\n";
            }
            
        }
        
        vendedores += "La media de todas las ventas de la empresa es: "+suma/contador;
        
        fr.close();
        br.close();
        
        return vendedores;
    }
    
    public void ejercicio3(String nombre_fichero) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(nombre_fichero);
        BufferedReader br = new BufferedReader(fr);
        
        FileWriter fw = new FileWriter("copia.txt");
        PrintWriter pw = new PrintWriter(fw);
        
        
        String linea = "";
        
//        while((linea = br.readLine()) != null){
//            pw.println(linea.replace(",", "="));
//        }
        String [] cabecera = br.readLine().split(",");
                
        pw.println("{\"empleados\": {");
        
        while((linea = br.readLine()) != null){
            
            String[] datos = linea.split(",");
            
            pw.println("    {");
            
            for(int i = 0; i<cabecera.length;i++){
                
                if(i == cabecera.length-1){
                    pw.println("    \""+cabecera[i]+"\": "+"\""+datos[i]+"\"");
                }else{
                    pw.println("    \""+cabecera[i]+"\": "+"\""+datos[i]+"\",");
                }
            }
            
            if(br.readLine() != null){
                pw.println("    },");
            }else{
                pw.println("}");
            }
        }
        
        pw.println("}}");
        
        fw.close();
        pw.close();
        
        br.close();
        fr.close();
        
        
        
    }
    
    public void ejercicio4(String nombre_fichero) throws FileNotFoundException,IOException{
        FileReader fr = new FileReader("empleados.txt");
        BufferedReader br = new BufferedReader(fr);
        
        FileWriter fw = new FileWriter(nombre_fichero);
        PrintWriter pw = new PrintWriter(fw);
        
        String linea = "";
        
        HashMap <String,Double> suma_provincias = new HashMap<>();
        
        while((linea = br.readLine()) != null){
            
            String[] datos = linea.split(":");
            
            String provincia = datos[2];
            double ventas = Double.parseDouble(datos[1]);
            
            if(suma_provincias.containsKey(provincia)){
                double sumatoria = suma_provincias.get(provincia);
                sumatoria += ventas;
                suma_provincias.put(provincia, sumatoria);
            }else{
                suma_provincias.put(provincia, ventas);
            }
            
        }
        
        for(Entry<String,Double> info:suma_provincias.entrySet()){
            pw.println("La pronvicia "+info.getKey()+" -> "+info.getValue()+"€");
        }
        
        fw.close();
        pw.close();
        
        br.close();
        fr.close();
        
    }
}
