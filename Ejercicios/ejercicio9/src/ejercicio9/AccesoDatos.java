package ejercicio9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class AccesoDatos {
    private HashMap<String,Pais> paises;
    
    public AccesoDatos(){
        paises=new HashMap<>();
    }
    
    public String[] getColumnas() {
        return Pais.nombresAtributos();
    }
    
    public void cargarPaises(String nombre) throws FileNotFoundException,IOException{
        
    }
    
    public void guardarPaises(String nombre) throws IOException{
        
    }
    
    public ArrayList<String[]> mostrarTodo(){
        return null;
    }
    
    public void añadirPais(String nombre,String capital,int habitantes,int año){
        
    }
    
    public void backupTexto(String titulo) throws IOException{
        
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