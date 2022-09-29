package ejercicio10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class AccesoDatos {
    private HashMap<String,Matricula> matriculas;
    
    public AccesoDatos(){
        matriculas=new HashMap<>();
    }
    
    public String[] getColumnas() {
        return Matricula.nombresAtributos();
    }
    
    // Ejercicio 1
    public void cargarMatriculas(String nombre) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(nombre);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        while(fis.available()>0){
            
            Matricula m = (Matricula)ois.readObject();
            if(this.matriculas.containsKey(m.getNombre())){
                throw new RuntimeException("El nombre "+m.getNombre()+" esta duplicado");
            }else{
                this.matriculas.put(m.getNombre(), m);
            }
            
        }
        ois.close();
        fis.close();
    }
    
    // Ejercicio 5
    public void guardarMatriculas(String nombre) throws IOException{
        FileOutputStream fos = new FileOutputStream(nombre);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        for(Matricula m:this.matriculas.values()){
            oos.writeObject(m);
        }
        
        oos.close();
        fos.close();
    }
    // Ejercicio 3
    public double sumarPagadas(){
        double suma=0;
        
        for(Matricula p:this.matriculas.values()){
            if(p.isPagado()){
                suma+=p.getPrecio();
            }
        }
        return suma;
    }
    
    // Ejercicio 2
    public ArrayList<String[]> visualizarMatriculas(){
        ArrayList<Matricula> todo = new ArrayList<>(this.matriculas.values());
        return crearTabla(todo);
    }
    
    // Ejercicio 4
    public void nuevaMatricula(String nombre,int año,double precio,boolean pagada){
        Matricula m = this.matriculas.get(nombre);
        
        if(m != null){
            throw new RuntimeException("Un alumno con ese nombre ya está matriculado.");
        }else{
            Matricula nueva = new Matricula(nombre,precio,año,pagada);
            this.matriculas.put(nombre, nueva);
        }
       
    }
    
    // Ejercicio 6
    public void matriculasResumen(String nombre) throws IOException{
        FileWriter fw = new FileWriter(nombre);
        PrintWriter pw = new PrintWriter(fw);
        
        pw.println("nombre:precio:año:pagado");
        for(Matricula m:this.matriculas.values()){
            if(!m.isPagado()){
                pw.println(m.getNombre()+":"+m.getPrecio()+":"+m.getAño()+":"+m.isPagado());
            }
            
        }
        pw.close();
        fw.close();
    }
    
    // Ejercicio 7
    public ArrayList<String[]> rankingMorosos(){
        
        ArrayList<Matricula> orden = new ArrayList();
        int contador=0;
        
        for(Matricula m:this.matriculas.values()){
            if(!m.isPagado() && contador<3){
                orden.add(m);
                contador++;
            }
        }
        
        if(orden.size()>0){
            orden.sort((b,a)->Double.compare(a.getPrecio(), b.getPrecio()));
            return crearTabla(orden);
        }else{
            throw new RuntimeException("No hay morosos 😊");
        }
        
        
    }
    
    private ArrayList<String[]> crearTabla(Collection<Matricula> matriculas) {
        ArrayList<String[]> resultado = new ArrayList<>();

        for (Matricula obj_mat : matriculas) {
            resultado.add(obj_mat.valoresAtributos());
        }

        return resultado;

    }
}
