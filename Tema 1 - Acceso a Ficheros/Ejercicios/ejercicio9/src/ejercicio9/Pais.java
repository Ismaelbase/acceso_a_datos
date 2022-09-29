
package ejercicio9;

import java.io.Serializable;

public class Pais implements Serializable{
    private static final long serialVersionUID = 8799656478674716638L;
    private String nombre,capital;
    private int habitantes,fundacion;

    public Pais(String nombre, String capital, int habitantes, int fundacion) {
        this.nombre = nombre;
        this.capital = capital;
        this.habitantes = habitantes;
        this.fundacion = fundacion;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }
    
    @Override
    public String toString() {
        return "=======================\n"
                + "nombre=" + this.nombre + "\n"
                + " capital=" + this.capital + "\n"
                + " habitantes=" + this.habitantes + "\n"
                + " fundacion=" + this.fundacion + "\n";
    }
    
    public static String[] nombresAtributos() {
        String[] nombres = {"nombre", "capital", "habitantes", "fundacion"};
        return nombres;
    }
    
    public String[] valoresAtributos() {
        String[] valores = {this.nombre, this.capital, String.valueOf(this.habitantes), String.valueOf(this.fundacion)};
        return valores;
    }
}
