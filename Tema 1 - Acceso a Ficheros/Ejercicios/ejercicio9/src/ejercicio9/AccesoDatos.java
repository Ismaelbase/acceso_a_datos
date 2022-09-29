package ejercicio9;

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
import java.util.Iterator;

public class AccesoDatos {

    private HashMap<String, Pais> paises;

    public AccesoDatos() {
        paises = new HashMap<>();
    }

    public String[] getColumnas() {
        return Pais.nombresAtributos();
    }

    // Ejercicio 1
    public void cargarPaises(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nombre);
        ObjectInputStream ois = new ObjectInputStream(fis);

        while (fis.available() > 0) {
            Pais p = (Pais) ois.readObject();
            paises.put(p.getNombre(), p);
        }

        ois.close();
        fis.close();

    }

    // Ejercicio 6
    public void guardarPaises(String nombre) throws IOException {
        FileOutputStream fos = new FileOutputStream(nombre);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Pais p : this.paises.values()) {
            oos.writeObject(p);
        }

        oos.close();
        fos.close();

    }

    // Ejercicio 2
    public ArrayList<String[]> mostrarTodo() {
        ArrayList<Pais> todo = new ArrayList<>(this.paises.values());

        return crearTabla(todo);
    }

    // Ejercicio 3
    public void añadirPais(String nombre, String capital, int habitantes, int año) {

        Pais buscado = this.paises.get(nombre);

        if (buscado == null) {
            Pais nuevo = new Pais(nombre, capital, habitantes, año);
            this.paises.put(nombre, nuevo);
        } else {
            throw new RuntimeException("Ya existe el pais que intentas insertar");
        }
    }

    // Ejercicio 4
    public void backupTexto(String titulo) throws IOException {
        FileWriter fw = new FileWriter(titulo);
        PrintWriter pw = new PrintWriter(fw);

        String[] cabecera = {"nombre", "año", "habitantes", "capital"};

        // Version normal Ej 4
//        
//        pw.println("Nombre,año,habitantes,capital");
//        
//        for(Pais p:this.paises.values()){
//            pw.println(p.getNombre()+","+p.getFundacion()+","+p.getHabitantes()+","+p.getCapital());
//        }
        // Version XML Ej 5
//        
//        pw.println("<mundo>");
//        
//        for(Pais p:this.paises.values()){
//            pw.println("    <pais>");
//            
//            pw.println("        <nombre>"+p.getNombre()+"</nombre>");
//            pw.println("        <año>"+p.getFundacion()+"</año>");
//            pw.println("        <habitantes>"+p.getHabitantes()+"</habitantes>");
//            pw.println("        <capital>"+p.getCapital()+"</capital>");
//            
//            pw.println("    </pais>");
//        }
//        pw.println(("</mundo>"));
//    
        // Version JSON ej5 1
//    pw.println("{ \n\"paises\": {");
//    
//    for(Pais p:this.paises.values()){
//        
//        for(int i=0;i<cabecera.length;i++){
//            
//            if(i!=cabecera.length-1){
//                pw.println("\""+cabecera[i]+"\": \""+p.getNombre()+"\",");
//            }else{
//                pw.println("\""+cabecera[i]+"\": \""+p.getNombre()+"\"");
//            }   
//        }   
//    }
//    pw.println("}\n}");
// Version JSON ej5 2
        Iterator<Pais> iter = this.paises.values().iterator();

        pw.println("{");

        while (iter.hasNext()) {

            Pais p = iter.next();
            String[] valores = {p.getNombre(), String.valueOf(p.getFundacion()), String.valueOf(p.getHabitantes()), p.getCapital()};
            pw.println("\n  \"" + p.getNombre() + "\": {");
            if (iter.hasNext()) {
                for (int i = 0; i < cabecera.length; i++) {
                    if (i != cabecera.length - 1) {
                        pw.println("        \"" + cabecera[i] + "\": \"" + valores[i] + "\",");
                    } else {
                        pw.println("        \"" + cabecera[i] + "\": \"" + valores[i] + "\"");
                    }

                }
                pw.println("   },");
            } else {
                for (int i = 0; i < cabecera.length; i++) {
                    if (i != cabecera.length - 1) {
                        pw.println("        \"" + cabecera[i] + "\": \"" + valores[i] + "\",");
                    } else {
                        pw.println("        \"" + cabecera[i] + "\": \"" + valores[i] + "\"");
                    }
                }
                pw.println("   }");
            }

        }
        pw.println("}");
        pw.close();
        fw.close();
    }

    private ArrayList<String[]> crearTabla(Collection<Pais> paises) {
        ArrayList<String[]> resultado = new ArrayList<>();

        for (Pais obj_pais : paises) {
            resultado.add(obj_pais.valoresAtributos());
        }

        return resultado;

    }
}
/*
PLANTILLAS
	   FileReader fr = new FileReader("FichTexto.txt");
       BufferedReader fichero = new BufferedReader(fr); 

       while((linea = fichero.readLine())!=null) 	 
       {
         resultado+=linea+"\n";     
       }
       System.out.println(resultado);
       fichero.close();
        fr.close();
  
       FileWriter fw = new FileWriter("FichTexto.txt"); //Borra el fichero si existe
      PrintWriter fichero = new PrintWriter(fw);
      
      for (int i=1; i<=10; i++){
	      fichero.println("Fila numero: "+i); //escribe en la posicion actual
      }
      fichero.close(); 
    fr.close();
    -----------------------------------------------------------------------------------
    Persona persona;//para contener los datos de la persona
    FileOutputStream fileout = new FileOutputStream("FichPersona.dat");
    ObjectOutputStream dataOS = new ObjectOutputStream(fileout);  
   
    String nombres[] = {"Ana","Luis Miguel","Alicia","Pedro",
            "Manuel","Andrés","Julio","Antonio","María Jesús"};
    int edades[] = {14,15,13,15,16,12,16,14,13};

    System.out.println("GRABANDO DATOS...");  
    for (int i=0;i<edades.length; i++){ //recorro los arrays    
        persona= new Persona(nombres[i],edades[i]);
        dataOS.writeObject(persona);
    }  
    System.out.println("FIN DE GRABACION...");  

    dataOS.close();
 

------------

    Persona persona; // defino la variable persona
    File fichero = new File("FichPersona.dat");
    FileInputStream filein= new FileInputStream(fichero);
    ObjectInputStream dataIS = new ObjectInputStream(fileout);

    while (filein.available()>0){ 
            // lectura del fichero
            persona = (Persona)dataIS.readObject()
            resultado+="Nombre: "+persona.getNombre()+"\n"+
                    "Edad: "+persona.getEdad()+"\n"+
                   "------------------------------\n";

    }
System.out.println(resultado);
filein.close();
dataIS.close();


 */
