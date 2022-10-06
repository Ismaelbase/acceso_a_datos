package exament1;

import java.io.Serializable;

public class Curso implements Serializable{
    private static final long serialVersionUID = 8799656478674716658L;
    private String nombre,modalidad;
    private double precio;
    private int matriculados;

    public Curso(String nombre, String modalidad, double precio, int matriculados) {
        this.nombre = nombre;
        this.modalidad = modalidad;
        this.precio = precio;
        this.matriculados = matriculados;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModalidad() {
        return this.modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getMatriculados() {
        return this.matriculados;
    }

    public void setMatriculados(int matriculados) {
        this.matriculados = matriculados;
    }
    
    public String toString(){
        String res="";
        
        res="Nombre:"+this.nombre+"\n"+
            "modalidad:"+this.modalidad+"\n"+
            "matriculados:"+this.matriculados+" alumnos\n"+
            "precio:"+this.precio+" euros\n";        
        
        return res;
    }
    public static String[] nombresAtributos() {
        String[] nombres = {"nombre", "modalidad", "matriculados", "precio"};
        return nombres;
    }
    
    public String[] valoresAtributos() {
        String[] valores = {this.nombre, this.modalidad, String.valueOf(this.matriculados), String.valueOf(this.precio)};
        return valores;
    }
}
