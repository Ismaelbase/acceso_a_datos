package ejercicio10;

import java.io.IOException;
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
    
    public void cargarMatriculas(String nombre) throws IOException{
        
    }
    
    public void guardarMatriculas(String nombre) throws IOException{
        
    }
    
    public double sumarPagadas(){
        return 0.0;
    }
    
    public ArrayList<String[]> visualizarMatriculas(){
        return null;
    }
    
    public void nuevaMatricula(String nombre,int año,double precio,boolean pagada){
        
    }
    
    public void matriculasResumen(String nombre) throws IOException{
        
    }
    
    public ArrayList<String[]> rankingMorosos(){
            return null;
    }
    
    private ArrayList<String[]> crearTabla(Collection<Matricula> matriculas) {
        ArrayList<String[]> resultado = new ArrayList<>();

        for (Matricula obj_mat : matriculas) {
            resultado.add(obj_mat.valoresAtributos());
        }

        return resultado;

    }
}
