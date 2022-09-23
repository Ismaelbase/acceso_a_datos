package ejercicio6;

import static ejercicio6.Futbolista.nombresAtributos;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccesoDatos {

    private HashMap<String,Futbolista> liga;

    public AccesoDatos() {
        this.liga = new HashMap<>();
    }

    public String[] getColumnas(){
        return Futbolista.nombresAtributos();
    }
    
    
    public void cargarJugadores(String nombre) throws FileNotFoundException,IOException {
        FileReader fw = new FileReader(nombre);
        BufferedReader br = new BufferedReader(fw);
        
        String[] cabecera = br.readLine().split(":");
        String linea;
        HashMap<String,String> datos = new HashMap();
        
        String nom="",pos="",equipo="";
        int goles=0;
        
        while((linea=br.readLine())!=null){
            
            String[] partes = linea.split(":");
            
            for(int i=0;i<cabecera.length;i++){
                datos.put(cabecera[i], partes[i]);
            }
            
            nom=datos.get("nombre");
            pos=datos.get("posicion");
            goles=Integer.parseInt(datos.get("goles"));
            equipo=datos.get("equipo");
            
            // Usamos añadiJugador para crear el objeto jugador y para añadirlo
            // al HashMap.
            
            añadirJugador(nom,equipo,pos,goles);
        }
        br.close();
        fw.close();
    }
    

    public ArrayList<String[]> visualizarJugadores() {
        return crearTabla(this.liga.values());
    }

    public String[] buscarJugador(String nombre) {
        Futbolista encontrado = this.liga.get(nombre.toLowerCase());
        String[] res;
        
        if(encontrado == null){
            throw new RuntimeException("El jugador no existe.");
        }else{
            res = encontrado.valoresAtributos();
        }
        
        return res;
    }

    public ArrayList<String[]> defensasGoleadores() {
        ArrayList<Futbolista> lista = new ArrayList<>();
        
        for(Futbolista fut:this.liga.values()){
            if(fut.getGoles()>5 && fut.getPosicion().equalsIgnoreCase("defensa")){
                lista.add(fut);
            }
        }
        if(!lista.isEmpty()){
            return crearTabla(lista);
        }else{
            throw new RuntimeException("No hay jugadores que cumplan con los parámetros pedidos.");
        }
    }

    public void añadirJugador(String nombre, String posicion, String club, int goles) {
        // Se puede usar buscarJugador simplemente para ver si devuelve o no algo
        // como condicion del if.
        
        if(this.liga.containsKey(nombre.toLowerCase())){
            throw new RuntimeException("Ya existe este jugador.");
        }else{
            Futbolista fut = new Futbolista(nombre,posicion,club,goles);
            this.liga.put(nombre.toLowerCase(), fut);
        }
    }

    public ArrayList<String[]> ordenarJugadores(int criterio, String orden) {
        ArrayList<Futbolista> ordenado = new ArrayList<>(this.liga.values());
        
        ordenado.sort((a,b)->{
            int resultado=0;
            
            if(criterio==Futbolista.GOLES){
                resultado=Integer.compare(a.getGoles(), b.getGoles());
            }else if(criterio==Futbolista.NOMBRE){
                resultado=a.getNombre().compareToIgnoreCase(b.getNombre());
            }
            
            if(orden.equalsIgnoreCase("DESC")){
                resultado*=-1;
            }
            
            return resultado;
        
    });
        return crearTabla(ordenado);
    }

    public void modificarNombre(String nombre_ant, String nombre_nuevo) {
        if(nombre_ant.equalsIgnoreCase(nombre_nuevo)){
            throw new RuntimeException("El titulo nuevo introducido es igual al que ya existe.");
        }else{
            Futbolista buscado = this.liga.get(nombre_ant.toLowerCase());
            if(buscado==null){
                throw new RuntimeException("No existe ningún jugador llamado así archivado.");
            }else{
                if(buscarJugador(nombre_nuevo.toLowerCase())!=null){
                    throw new RuntimeException("Ya existe un jugador llamado como el nuevo nombre introducido.");
                }else{
                    buscado.setNombre(nombre_nuevo);
                    
                    // TO LOWER CASE SIEMPRE QUE SE INTERACTUA CON LAS KEYS !!!
                    this.liga.remove(nombre_ant.toLowerCase());
                    this.liga.put(nombre_nuevo.toLowerCase(), buscado);
                }
            }
        }
    }

    public void guardarJugadores(String nombre) throws IOException {
        FileWriter fw = new FileWriter(nombre);
        PrintWriter pw = new PrintWriter(fw);
        
        
        // Esto introduce la cabecera separada por dos puntos gracias al método
        // nombresAtributis() en la clase futbolista.
        
        pw.println(String.join(":", nombresAtributos()));
        
        for(Futbolista fut:this.liga.values()){
//            String[] linea = fut.valoresAtributos();
//            pw.println(String.join(":", linea));
            
            pw.println(String.join(":", fut.valoresAtributos()));
        }
        
        pw.close();
        fw.close();
    }

    public void backupJugadores(String nombre) throws IOException {
        FileWriter fw = new FileWriter(nombre);
        PrintWriter pw = new PrintWriter(fw);
        
        // Este print solo se usa en formato normal, no es necesario XML ni JSON
//        pw.println(String.join(":", this.getColumnas()));
        
        ArrayList<Futbolista> orden = new ArrayList<>(this.liga.values());
        
        orden.sort((b,a)->Integer.compare(a.getGoles(), b.getGoles()));
        
        int contador=0;
        
        pw.println("{\"futbolistas\":");
        
        // '{"name":"John", "age":30, "car":null}'
        do{
            pw.println("    {");
            for(int i=0;i<this.getColumnas().length;i++){
                pw.println("      \""+this.getColumnas()[i]+"\": "+"\""+orden.get(contador).valoresAtributos()[i]+"\",");
            }
            
            contador++;
            
            if(contador<5){
                pw.println("    },");
            }else{
                pw.println("    }");
            }
            
        }while(contador<5 && contador<orden.size());
        
        pw.println("}");
        
        
        
        
        
        //Versión XML
//        ----------------------------------------------------------------
//        int contador=0;
//
//        pw.println("<futbolistas>");
//
//        do {
//            pw.println("    <jugador>");
//
//            for (int i = 0; i < this.getColumnas().length; i++) {
//                pw.println("        <" + this.getColumnas()[i] + ">" + orden.get(contador).valoresAtributos()[i] + "</" + this.getColumnas()[i] + ">");
//            }
//
//            pw.println("    </jugador>");
//            contador++;
//
//        } while (contador < 5 && contador < orden.size());
//
//        pw.println("</futbolistas>");
        
        
        //-----------------------------------------------------------------------
        
        // Version Backup normal separada por ":"
//        int contador=0;
//        
//        do{
//            String linea = String.join(":", orden.get(contador).valoresAtributos());
//            pw.println(linea);
//            contador++;
//        }while(contador<5 && contador<orden.size());
        
        
        
        
//        for(int i=0;i<5;i++){
//            String linea = String.join(":", orden.get(i).valoresAtributos());
//            pw.println(linea);
//        }
        
        pw.close();
        fw.close();
    }
    
    public String resumenEquipos(){
        String res="";
        
        HashMap<String,Integer> goles = new HashMap<>();
        
        for(Futbolista fut:this.liga.values()){
            
            if(goles.containsKey(fut.getClub())){
                int suma_goles=goles.get(fut.getClub());
                
                goles.put(fut.getClub(), fut.getGoles()+suma_goles);
            }else{
                goles.put(fut.getClub(), fut.getGoles());
            }
        }
        
        if(!goles.isEmpty()){
            
            ArrayList<String> goles_equipo = new ArrayList<>();
            
            for(Map.Entry<String,Integer> listado:goles.entrySet()){
                res+="El equipo "+listado.getKey()+" ha anotado un total de "+listado.getValue()+"\n";
            }
            
        }else{
            throw new RuntimeException("No hay datos que procesar");
        }
        return res;
    }
    
    //METODO AUXILIAR 
     private ArrayList<String[]> crearTabla(Collection<Futbolista> fut) {
        ArrayList<String[]> resultado = new ArrayList<>();

        for (Futbolista obj_fut : fut) {
            resultado.add(obj_fut.valoresAtributos());
        }

        return resultado;

    }
}
