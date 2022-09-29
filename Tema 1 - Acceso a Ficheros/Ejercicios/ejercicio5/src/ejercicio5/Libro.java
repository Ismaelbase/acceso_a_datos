package ejercicio5;

public class Libro {

    public final static int PRECIO = 1;
    public final static int EJEMPLARES = 2;

    private String titulo, autor;
    private double precio;
    private int numero_ejemplares;

    public Libro() {
        this.titulo = "Sin titulo";
        this.precio = 0.0;
        this.autor = "Sin autor";
        this.numero_ejemplares = 0;
    }

    public Libro(String titulo, String autor, double precio, int numero_ejemplares) {
        this.titulo = titulo;
        this.precio = precio;
        this.autor = autor;
        this.numero_ejemplares = numero_ejemplares;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumero_ejemplares() {
        return numero_ejemplares;
    }

    public void setNumero_ejemplares(int numero_ejemplares) {
        this.numero_ejemplares = numero_ejemplares;
    }

    @Override
    public String toString() {
        return "=======================\n"
                + "titulo=" + titulo + "\n"
                + " precio=" + precio + "\n"
                + " autor=" + autor + "\n"
                + " numero_ejemplares=" + numero_ejemplares + "\n";
    }

    public static String[] nombresAtributos() {
        String[] nombres = {"titulo", "autor", "precio", "numero_ejemplares"};
        return nombres;

        //ALTERNATIVA PROGRAMACION REFLEXIVA
//        ArrayList<String> nombres = new ArrayList<>();
//
//        Field[] atributos = Class.forName("ejercicio5.Libro").getDeclaredFields();
//        for (int i = 0; i < atributos.length; i++) {
//            if (atributos[i].getModifiers() == 2) {
//                nombres.add(atributos[i].getName());
//            }
//        }
    }

    public String[] valoresAtributos() {
        String[] valores = {this.titulo, this.autor, String.valueOf(this.precio), String.valueOf(this.numero_ejemplares)};
        return valores;

        //ALTERNATIVA PROGRAMACION REFLEXIVA
//        ArrayList<String> valores = new ArrayList<>();
//
//        Field[] atributos = this.getClass().getDeclaredFields();
//        for (int i = 0; i < atributos.length; i++) {
//            if (atributos[i].getModifiers() == 2) {
//                    valores.add(atributos[i].get(this).toString());
//            }
//        }
    }

}
