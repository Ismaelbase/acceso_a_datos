package ejemplobinario;

import java.io.Serializable;

// Necesario que la clase implemente "Serializable".
public class Persona implements Serializable {
    
    //El serialVersionUID cambia el tipo cifrado, si no está lo cifrará genericamente.
    private static final long serialVersionUID = 8799656478674716638L;
    private String nombre;
    private int edad;
    private double altura;

    public Persona(String nombre, int edad, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.altura = altura;
    }

    public void setNombre(String nom) {
        nombre = nom;
    }

    public void setEdad(int ed) {
        edad = ed;
    }
    
    public void setAltura(double altura) {
        this.altura = altura;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
    
        public double getAltura() {
        return altura;
    }

    
}
