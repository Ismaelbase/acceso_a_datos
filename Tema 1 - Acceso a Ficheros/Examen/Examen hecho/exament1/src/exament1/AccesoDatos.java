package exament1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

public class AccesoDatos {

    private HashMap<String,Curso> lista;

    public AccesoDatos() {
        this.lista = new HashMap<>();
    }

    public String[] getColumnas(){
        return Curso.nombresAtributos();
    }
    
    public void cargarTexto(String nombre) throws IOException, FileNotFoundException {
       FileReader fr = new FileReader(nombre);
       BufferedReader br = new BufferedReader(fr);
       
       
       String[] cabecera = br.readLine().split(":");
       
       String siguiente = "";
       
       HashMap<String,String> info = new HashMap<>();
       
       while((siguiente=br.readLine()) != null){
           
           String[] valores = siguiente.split(":");
           
           for(int i=0;i<this.getColumnas().length;i++){
               info.put(this.getColumnas()[i], valores[i]);
               
           }
           if(this.lista.containsKey(info.get(this.getColumnas()[0]))){
               throw new RuntimeException("Ya existe un nombre igual");
           }else{
               Curso nuevo = new Curso(info.get("nombre"),info.get("modalidad"),Double.parseDouble(info.get("precio")),Integer.parseInt(info.get("matriculados")));
               this.lista.put(info.get(this.getColumnas()[0]).toLowerCase(), nuevo);
           }
       }
       
       br.close();
       fr.close();
    }

    public ArrayList<String[]> visualizarCursos() {
        ArrayList<Curso> res = new ArrayList(this.lista.values());
        return crearTabla(res);
    }

    public void guardarTexto(String nombre) throws IOException {
        FileWriter fw = new FileWriter(nombre);
        PrintWriter pw = new PrintWriter(fw);
        
        pw.println(String.join(":", this.getColumnas()));
        
        HashMap<String,String> datos = new HashMap();
        
        for(Curso c:this.lista.values()){
            
            String[] valores = c.valoresAtributos();
            
            for(int i=0;i<this.getColumnas().length;i++){
                datos.put(this.getColumnas()[i], valores[i]);
                
                if(i == this.getColumnas().length-1){
                    pw.print(datos.get(this.getColumnas()[i]));
                }else{
                    pw.print(datos.get(this.getColumnas()[i])+":");
                }
                
            }
            pw.println();
            
        }
        pw.close();
        fw.close();
        
    }

    public void guardarBinario(String nombre) throws IOException {
        FileOutputStream fos = new FileOutputStream(nombre);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        for(Curso c:this.lista.values()){
            oos.writeObject(c);
        }
        
        oos.close();
        fos.close();
    }

    public void cargarBinario(String nombre) throws IOException, FileNotFoundException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nombre);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        while(fis.available()>0){
            Curso nuevo = (Curso)ois.readObject();
            if(this.lista.containsKey(nuevo.getNombre())){
                throw new RuntimeException("Error al cargar: Datos repetidos");
            }else{
                this.lista.put(nuevo.getNombre().toLowerCase(), nuevo);
            }
        }
        
        ois.close();
        fis.close();
    }

    public String resumenAlumnos() {
        String resultado="";
        
        HashMap<String,Integer> resumen = new HashMap<>();
        
        for(Curso c:this.lista.values()){
            if(resumen.containsKey(c.getModalidad())){
                int acumulador = resumen.get(c.getModalidad());
                resumen.put(c.getModalidad(), c.getMatriculados()+acumulador);
            }else{
                resumen.put(c.getModalidad(), c.getMatriculados());
            }
        }
        
        for(Entry<String,Integer> lista:resumen.entrySet()){
            resultado += lista.getKey().toUpperCase()+": "+lista.getValue()+"\n";
        }
        
        return resultado;
    }

    public ArrayList<String[]> busqueda(String nombre) {
        ArrayList<Curso> encontrados = new ArrayList<>();
        
        for(Curso c:this.lista.values()){
            if(c.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                encontrados.add(c);
            }
        }
        return crearTabla(encontrados);
    }
    
    public void backupXML(String nombre) throws IOException, FileNotFoundException{
        FileWriter fw = new FileWriter(nombre);
        PrintWriter pw = new PrintWriter(fw);
        
        pw.println("<academia>");
        
        for(Curso c:this.lista.values()){
            String[] valores = c.valoresAtributos();
            
            pw.println("    <curso>");
            
            for(int i=0;i<this.getColumnas().length;i++){
                pw.println("        <"+this.getColumnas()[i]+">"+valores[i]+"</"+this.getColumnas()[i]+">");
            }
            pw.println("    </curso>");
        }
        pw.println("</academia>");
        
        pw.close();
        fw.close();
    }
    
    public ArrayList<String[]> masPopulares() {
        
        ArrayList<Curso> mas_alumnos = new ArrayList<>(this.lista.values());
        ArrayList<Curso> top_tres = new ArrayList<>();
        
        mas_alumnos.sort((b,a)->Integer.compare(a.getMatriculados(), b.getMatriculados()));
        
        int contador = 0;

        while(contador < 3 && contador != mas_alumnos.size()){
            top_tres.add(mas_alumnos.get(contador));
            contador++;
        }
        
        return crearTabla(top_tres);
    }

    
    private ArrayList<String[]> crearTabla(Collection<Curso> cursos) {
        ArrayList<String[]> resultado = new ArrayList<>();

        for (Curso obj_curso : cursos) {
            resultado.add(obj_curso.valoresAtributos());
        }

        return resultado;

    }
}

