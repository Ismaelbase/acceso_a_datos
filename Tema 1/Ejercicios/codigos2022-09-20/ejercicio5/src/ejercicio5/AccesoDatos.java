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
        FileReader fr = new FileReader(nombre);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        linea = br.readLine();
        String[] cabecera = linea.split(":");

        HashMap<String, String> datos = new HashMap<>();
        String titulo, autor;
        double precio;
        int num_ejemplares;

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(":");
            for (int i = 0; i < partes.length; i++) {
                datos.put(cabecera[i], partes[i]);
            }
            titulo = datos.get("titulo");
            autor = datos.get("autor");
            precio = Double.parseDouble(datos.get("precio"));
            num_ejemplares = Integer.parseInt(datos.get("numero_ejemplares"));

//            Libro nuevo = new Libro(titulo, autor, precio, num_ejemplares);
//            this.biblioteca.put(titulo, nuevo);
            this.añadirLibro(titulo, autor, precio, num_ejemplares);
        }
        System.out.println(this.biblioteca);

        br.close();
        fr.close();
    }

    public void guardarLibros(String nombre) throws IOException {
        
    }

    public ArrayList<String[]> visualizarTodos() {
        return crearTabla(this.biblioteca.values());
    }

    private Libro encontrarLibro(String titulo){
        return this.biblioteca.get(titulo);
    }
    
    public void añadirLibro(String titulo, String autor, double precio, int copias) {
//        if (this.biblioteca.containsKey(titulo.toLowerCase())) {
//            throw new RuntimeException("Ya existe un libro con ese titulo en la biblioteca");
//        } else {
//            Libro nuevo = new Libro(titulo, autor, precio, copias);
//            this.biblioteca.put(titulo.toLowerCase(), nuevo);
//        }
        //COMO EN EL AÑO PASADO
        if (encontrarLibro(titulo.toLowerCase()) == null) {
            Libro nuevo = new Libro(titulo, autor, precio, copias);
            this.biblioteca.put(titulo.toLowerCase(), nuevo);
        } else {
            throw new RuntimeException("Ya existe un libro con ese titulo en la biblioteca");
        }
    }

    public String[] buscarTitulo(String titulo) {
        String[] res;
        Libro buscado=encontrarLibro(titulo.toLowerCase());
        if(buscado!=null){
            res=buscado.valoresAtributos();
        }else{
            throw new RuntimeException("No existe ese libro");
        }
        return res;
    }

    public ArrayList<String[]> buscarLibrosAutor(String autor) {
        ArrayList<Libro> res=new ArrayList<>();
        for(Libro lib:this.biblioteca.values()){
            if(lib.getAutor().equalsIgnoreCase(autor)){
                res.add(lib);
            }
        }
        
        if(res.size()>0){
            return crearTabla(res);
        }else{
            throw new RuntimeException("No hay libros de ese autor");
        }
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
