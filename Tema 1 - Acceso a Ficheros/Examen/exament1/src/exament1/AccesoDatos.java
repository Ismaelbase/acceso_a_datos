package exament1;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AccesoDatos {

    private HashMap<String,Curso> lista;

    public AccesoDatos() {
        this.lista = new HashMap<>();
    }

    public String[] getColumnas(){
        return Curso.nombresAtributos();
    }
    
    public void cargarTexto(String nombre) throws IOException, FileNotFoundException {
       
    }

    public ArrayList<String[]> visualizarCursos() {
        return new ArrayList<>();
    }

    public void guardarTexto(String nombre) throws IOException {

    }

    public void guardarBinario(String nombre) throws IOException {
                
    }

    public void cargarBinario(String nombre) throws IOException, FileNotFoundException, ClassNotFoundException {
        
    }

    public String resumenAlumnos() {

        return "VACIO";
    }

    public ArrayList<String[]> busqueda(String nombre) {
        return new ArrayList<>();
    }
    
    public void backupXML(String nombre) throws IOException, FileNotFoundException{
        
    }
    
    public ArrayList<String[]> masPopulares() {
        return new ArrayList<>();
    }

    
    private ArrayList<String[]> crearTabla(Collection<Curso> cursos) {
        ArrayList<String[]> resultado = new ArrayList<>();

        for (Curso obj_curso : cursos) {
            resultado.add(obj_curso.valoresAtributos());
        }

        return resultado;

    }
}

