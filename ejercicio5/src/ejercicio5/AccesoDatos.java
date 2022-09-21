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
        
        FileWriter fw = new FileWriter(nombre);
        PrintWriter pw = new PrintWriter(fw);
        
//        String[] cabecera=getColumnas();
//        String separador=":";
//        String linea="";
        
        pw.println(String.join(":", getColumnas()));
        
//        for(int i=0;i<cabecera.length;i++){
//            linea+=separador+cabecera[i];
//            separador=":";
//        }
//        
//        pw.println(linea);
        
        for(Libro lib:this.biblioteca.values()){
            
            String[] valores=lib.valoresAtributos();
            pw.println(String.join(":", valores));
            
            //O alternativamente y mas compacto: 
//            pw.println(String.join(":", lib.valoresAtributos()));
        }
        
        pw.close();
        fw.close();
        
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
        //Crear tabla admite cualquier colección y devuelve una colección de 
        //cualquier tipo, por lo que ordenamos el Array con la información 
        // de la biblioteca y luego lo devolvemos.
    public ArrayList<String[]> ordenarLibros(int criterio, String orden) {
        
        ArrayList<Libro> aux = new ArrayList<>(this.biblioteca.values());
        
        //Aquí se puede hace run solo sort y dentro, añadiendo unas llaves, 
        // podemos hacer las operaciones necesarias, en este caso nos aprovechamos
        // de que el sort devuelve un número para ordenar en cada iteración
        // y al final de cada iteración preguntamos si el ORDEN que se ha pedido
        // es descente, si lo es, multiplicamos el resultado por -1 para modificar
        // el resultado de la ordenación. Bastante loco la verdad si me preguntas.
        
        
        aux.sort((a,b)->{
        int res=0;
        if(criterio==Libro.PRECIO){
            res=Double.compare(a.getPrecio(), b.getPrecio());
        }else if(criterio==Libro.EJEMPLARES){
            res=Integer.compare(a.getNumero_ejemplares(), b.getNumero_ejemplares());
        }
        
        if(orden.equals("DESC")){
            res*=-1;
        }
        
            return res;
    });
        
        
        
        // Esto cubre todas las posibilidades de ordenación posibles actualmente, 
        // por precio ascedente o descendente y por numero de ejemplares 
        // ascedente o decendente, aunque está hecho a lo bruto.
//        
//        if(criterio==Libro.PRECIO && orden.equals("DES")){
//            aux.sort((a,b)->Double.compare(b.getPrecio(), a.getPrecio()));
//        }else if(criterio==Libro.PRECIO && orden.equals("ASC")){
//            aux.sort((a,b)->Double.compare(a.getPrecio(), b.getPrecio()));
//        }else if(criterio==Libro.EJEMPLARES && orden.equals("ASC")){
//            aux.sort((a,b)->Integer.compare(a.getNumero_ejemplares(), b.getNumero_ejemplares()));
//        }else if(criterio==Libro.EJEMPLARES && orden.equals("DESC")){
//            aux.sort((a,b)->Integer.compare(b.getNumero_ejemplares(), a.getNumero_ejemplares()));
//        }

        return crearTabla(aux);
    }

    public void modificarTitulo(String ant_titulo, String nuevo_titulo) {
        if(ant_titulo.equalsIgnoreCase(nuevo_titulo)){
            throw new RuntimeException("El titulo nuevo introducido es igual al que ya existe.");
        }else{
            Libro buscado=encontrarLibro(ant_titulo.toLowerCase());
            
            if(buscado==null){
                throw new RuntimeException("No existe el libro.");
            }else{
                if(encontrarLibro(nuevo_titulo.toLowerCase())!=null){
                    throw new RuntimeException("Ya existe un libro llamado asi.");
                }else{
                    //Ahora que hemos comprobado que los títulos antiguo y nuevo no son iguales, que el libro antiguo existe y que no hay un libro llamado igual que el nuevo titulo podemos continuar.
                    buscado.setTitulo(nuevo_titulo);
                    
                    //Es necesario tener en cuenta que las claves se guardan en minúscula para facilitar las comparaciones.
                    this.biblioteca.remove(ant_titulo.toLowerCase());
                    this.biblioteca.put(nuevo_titulo.toLowerCase(), buscado);
                }
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
