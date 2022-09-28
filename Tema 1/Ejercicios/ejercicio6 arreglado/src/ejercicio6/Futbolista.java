package ejercicio6;


public class Futbolista {
    public final static int GOLES=1;
    public final static int NOMBRE=2; 
    
    private String nombre,club,posicion;
    private int goles;

    
    public Futbolista() {
        this.nombre = "";
        this.club = "";
        this.posicion = "";
        this.goles = 0;
    }
    
    public Futbolista(String nombre, String club, String posicion, int goles) {
        this.nombre = nombre;
        this.club = club;
        this.posicion = posicion;
        this.goles = goles;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClub() {
        return this.club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosicion() {
        return this.posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    
    
    public String toString() {
        return "=======================\n"
             + "Nombre=" + this.nombre + "\n"
             + " Posicion=" + this.posicion + "\n"
             + " Goles=" + this.goles + "\n"
             + " Club=" + this.club + "\n";
    }
    
    
    public static String[] nombresAtributos(){
        String[] nombres={"nombre","posicion","goles","club"};
        return nombres;
    }
    
    public String[] valoresAtributos() {
        String[] valores = {this.nombre, this.posicion, String.valueOf(this.goles), this.club};
        return valores;
    }
    
}
    

