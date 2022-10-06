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
        
        String[] cabecera = br.readLine().split(":");
        String linea = "";
        
        String nom,autor;
        double precio;
        int ejemplares;
        
        HashMap<String,String> info = new HashMap<>();
        
        while((linea = br.readLine()) != null){
            String[] datos = linea.split(":");
            
            for(int i=0;i<cabecera.length;i++){
                info.put(cabecera[i], datos[i]);
            }
            
            nom = info.get("titulo");
            autor = info.get("autor");
            precio = Double.parseDouble(info.get("precio"));
            ejemplares = Integer.parseInt(info.get("numero_ejemplares"));
            
            if(this.biblioteca.containsKey(nom)){
                throw new RuntimeException("Ya existe este libro.");
            }else{
//                String titulo, String autor, double precio, int numero_ejemplares
                this.añadirLibro(nom, autor, precio, ejemplares);
            }
            
        }
        
        br.close();
        fr.close();
        
    }

    public void guardarLibros(String nombre) throws IOException{
        FileWriter fw = new FileWriter(nombre);
        PrintWriter pw = new PrintWriter(fw);
        
        String[] cabecera=this.getColumnas();
        String linea = String.join(":", cabecera);
        pw.println(linea);
        
        for(Libro lib:this.biblioteca.values()){
            String[] datos = lib.valoresAtributos();
            pw.println(String.join(":", datos));
        }
        
        pw.close();
        fw.close();
        
    }

    public ArrayList<String[]> visualizarTodos() {
        return this.crearTabla(this.biblioteca.values());
    }
    
    public Libro encontrarLibro(String titulo){
        return this.biblioteca.get(titulo);
    }
    
    public void añadirLibro(String titulo, String autor, double precio, int copias) {
        
        if(this.biblioteca.containsKey(titulo.toLowerCase())){
            throw new RuntimeException("Ya existe un libro llamado así ;P");
        }else{
            Libro lib = new Libro(titulo,autor,precio,copias);
            this.biblioteca.put(titulo.toLowerCase(), lib);
        }
        
    }

    public String[] buscarTitulo(String titulo) {
        String [] res;
        Libro buscado = this.encontrarLibro(titulo);
        
        if(buscado != null){
            res = buscado.valoresAtributos();
        }else{
            throw new RuntimeException("Mete un libro que exista, porfa uwu");
        }
        // -1 por shulo.
        return res;
    }

    public ArrayList<String[]> buscarLibrosAutor(String autor) {
        
        ArrayList<Libro> libros_autor = new ArrayList<>();
        
        for(Libro lib:this.biblioteca.values()){
            if(lib.getAutor().equalsIgnoreCase(autor)){
                libros_autor.add(lib);
            }
        }
        if(libros_autor.size()>0){
            return this.crearTabla(libros_autor);
        }else{
            throw new RuntimeException("No existen libros de ese autor, uwun't");
        }
        
        
    }

    public ArrayList<String[]> ordenarLibros(int criterio, String orden) {
        
        ArrayList<Libro> ordenado = new ArrayList<>(this.biblioteca.values());
        
        if(criterio == 1 && orden.equalsIgnoreCase("DESC")){
            ordenado.sort((a,b)->Double.compare(a.getPrecio(),b.getPrecio()));
        }else if(criterio == 1 && orden.equalsIgnoreCase("ASC")){
            ordenado.sort((b,a)->Double.compare(a.getPrecio(),b.getPrecio()));
        }else if(criterio == 2 && orden.equalsIgnoreCase("DESC")){
            ordenado.sort((a,b)->Integer.compare(a.getNumero_ejemplares(), b.getNumero_ejemplares()));
        }else if(criterio == 2 && orden.equalsIgnoreCase("ASC")){
            ordenado.sort((b,a)->Integer.compare(a.getNumero_ejemplares(), b.getNumero_ejemplares()));
        }
        
        return crearTabla(ordenado);
    }

    public void modificarTitulo(String ant_titulo, String nuevo_titulo) {
        
        if(ant_titulo.equalsIgnoreCase(nuevo_titulo)){
            throw new RuntimeException("Has introducido el mismo nombre.");
        }else{
            Libro nuevo = this.biblioteca.get(ant_titulo);
            if(nuevo == null){
                throw new RuntimeException("No existe un libro que modificar con ese titulo.");
            }else{
                nuevo.setTitulo(nuevo_titulo);
                
                this.biblioteca.remove(ant_titulo);
                this.biblioteca.put(nuevo_titulo, nuevo);
            }
        }
        
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
