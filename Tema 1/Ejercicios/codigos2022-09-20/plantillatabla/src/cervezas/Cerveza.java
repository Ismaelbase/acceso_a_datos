package cervezas;

import java.util.HashMap;

public class Cerveza {

    private String nombre;
    private int orden, stock;
    private boolean artesanal;
    private char tipo;
    private double precio;
    private String distribuidor;

    public static String[] getCabecera(){
        String[] atributos={"Nombre","Stock","Artesanal","Tipo","Precio","Distribuidor"};
        return atributos;
    }
    
    public String[] getValores(){
        String[] datos={this.nombre,
                        String.valueOf(this.stock),
                        String.valueOf(this.artesanal),
                        String.valueOf(this.tipo),
                        String.valueOf(this.precio),
                        this.distribuidor
        };
        
        return datos;
        
    }
    
    
    
    private static HashMap<Character, String> tipos = new HashMap<>();

    public static String getTipoCompleto(char tipo) {
        return tipos.get(tipo);
    }

    private static int contador = 1;

    public Cerveza(String nombre, int stock, boolean artesanal, char tipo, double precio, String distribuidor) {
        this.nombre = nombre;
        this.orden = this.contador;
        this.contador++;
        this.stock = stock;
        this.artesanal = artesanal;
        this.tipo = tipo;
        this.precio = precio;
        this.distribuidor = distribuidor;
        tipos.put('r', "Rubia");
        tipos.put('n', "Negra");
        tipos.put('t', "Tostada");
        tipos.put('R', "Roja");
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            System.out.println("ERROR:Precio incorrecto");
        }
    }

    public void servir_cerveza(int unidades) {
        if (unidades > this.stock) {
            System.out.println("NO HAY SUFICIENTES SE HAN SERVIDO " + this.stock);
            this.stock = 0;
        } else {
            this.stock -= unidades;
            System.out.println("HAY SUFICIENTES UNIDADES");
        }
    }

    public void reponer_cerveza(int unidades) {
        this.stock += unidades;
        System.out.println("Cervezas almacenadas con éxito");
    }

    public void setTipo(char tipo) {
        if (this.tipos.containsKey(tipo)) {
            this.tipo = tipo;
            System.out.println("Tipo cambiado con éxito");
        } else {
            System.out.println("Tipo incorrecto");
        }
    }

    public String toString() {

        String res = "";
        res += "-----------------\n"
                + "ORDEN DE LLEGADA:" + this.orden + "\n"
                + "NOMBRE:" + this.nombre + "\n"
                + "STOCK:" + this.stock + "\n"
                + "PRECIO:" + this.precio + "\n"
                + "DISTRIBUIDOR:" + this.distribuidor + "\n";
        if (this.artesanal) {
            res += "FABRICACION ARTESANAL\n";
        } else {
            res += "FABRICACION INDUSTRIAL\n";
        }
        res += "TIPO:";

        res += this.tipos.get(this.tipo) + "\n";

        res += "-----------------\n";
        return res;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getOrden() {
        return this.orden;
    }

    public int getStock() {
        return this.stock;
    }

    public boolean isArtesanal() {
        return this.artesanal;
    }

    public char getTipo() {
        return this.tipo;
    }

    public double getPrecio() {
        return this.precio;
    }

    public String getDistribuidor() {
        return this.distribuidor;
    }

    public boolean equals(Cerveza c) {
        return this.nombre.equals(c.nombre);
    }
}
