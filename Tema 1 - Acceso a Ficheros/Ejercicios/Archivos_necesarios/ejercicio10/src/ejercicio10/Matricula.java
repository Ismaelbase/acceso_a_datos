package ejercicio10;

import java.io.Serializable;


public class Matricula implements Serializable {
    private static final long serialVersionUID = 8799656478674716638L;
    private String nombre;
    private double precio;
    private int año;
    private boolean pagado;

    public Matricula(String nombre, double precio, int año, boolean pagado) {
        this.nombre = nombre;
        this.precio = precio;
        this.año = año;
        this.pagado = pagado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
    
    
    public static String[] nombresAtributos() {
        String[] nombres = {"nombre", "precio", "año", "pagado"};
        return nombres;
    }
    
    public String[] valoresAtributos() {
        String[] valores = {this.nombre, String.valueOf(this.precio), String.valueOf(this.año),String.valueOf(this.pagado)};
        return valores;
    }
}
