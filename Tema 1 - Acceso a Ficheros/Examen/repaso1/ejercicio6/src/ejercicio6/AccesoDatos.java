package ejercicio6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AccesoDatos {

    private HashMap<String,Futbolista> liga;

    public AccesoDatos() {
        this.liga = new HashMap<>();
    }

    public String[] getColumnas(){
        return Futbolista.nombresAtributos();
    }
    
    
    public void cargarJugadores(String nombre) throws FileNotFoundException,IOException {

    }

    public ArrayList<String[]> visualizarJugadores() {
        return null;
    }

    public String[] buscarJugador(String nombre) {
        return null;
    }

    public ArrayList<String[]> defensasGoleadores() {
        return null;
    }

    public void añadirJugador(String nombre, String posicion, String club, int goles) {

    }

    public ArrayList<String[]> ordenarJugadores(int criterio, String orden) {
        return null;
    }

    public void modificarNombre(String nombre_ant, String nombre_nuevo) {

    }

    public void guardarJugadores(String nombre) throws IOException {

    }

    public void backupJugadores(String nombre) throws IOException {

    }
    
    public String resumenEquipos(){
        return "nada";
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
