package ejercicio5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AccesoDatos {

    private HashMap<String, Libro> biblioteca;

    public AccesoDatos() {
        this.biblioteca = new HashMap<>();
    }

    public String[] getColumnas() {
        return Libro.nombresAtributos();
    }

    public void cargarLibros(String nombre) throws FileNotFoundException, IOException {
        
    }

    public void guardarLibros(String nombre) throws IOException{
        
    }

    public ArrayList<String[]> visualizarTodos() {
        return new ArrayList<>();
    }

    public void añadirLibro(String titulo, String autor, double precio, int copias) {

    }

    public String[] buscarTitulo(String titulo) {
        return null;
    }

    public ArrayList<String[]> buscarLibrosAutor(String autor) {
        return null;
    }

    public ArrayList<String[]> ordenarLibros(int criterio, String orden) {
        return null;
    }

    public void modificarTitulo(String ant_titulo, String nuevo_titulo) {

    }

    //METODO AUXILIAR 
     private ArrayList<String[]> crearTabla(Collection<Libro> libros) {
        ArrayList<String[]> resultado = new ArrayList<>();

        for (Libro obj_libro : libros) {
            resultado.add(obj_libro.valoresAtributos());
        }

        return resultado;

    }

}
