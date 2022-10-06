package relacion1;

import java.io.Serializable;

public class Persona implements Serializable {

    private static final long serialVersionUID = 8799656478674716638L;
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }


    public void setNombre(String nom) {
        nombre = nom;
    }

    public void setEdad(int ed) {
        edad = ed;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}
