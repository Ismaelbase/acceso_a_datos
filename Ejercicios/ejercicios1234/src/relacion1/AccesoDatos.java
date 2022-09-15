package relacion1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
    
    }
    
    public void ejercicio4(String nombre_fichero) throws FileNotFoundException,IOException{
        
    }
}
