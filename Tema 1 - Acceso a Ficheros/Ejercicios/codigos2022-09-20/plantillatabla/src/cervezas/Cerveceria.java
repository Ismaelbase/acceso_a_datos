package cervezas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Cerveceria {

    private String nombre_cerveceria;
    private HashMap<String, Cerveza> almacen;

    //CONSTRUCTOR
    public Cerveceria(String nombre_cerveceria) {
        this.nombre_cerveceria = nombre_cerveceria;
        this.almacen = new HashMap<>();
    }
    
    
    //METODO PARA EL JTABLE
    
    public ArrayList<String[]> todoElAlmacen(){
        ArrayList<String[]> res=new ArrayList<>();
        
        for(Cerveza cer:this.almacen.values()){
            res.add(cer.getValores());
        }
        
        return res;
    }
    
    
    

    //=====================================================================
    //OPCION 1
    //=====================================================================
    public String toString() {
        String res = "";

        res += "#####################\n";
        res += "NOMBRE DE LA EMPRESA:" + this.nombre_cerveceria + "\n";

        if (this.almacen.values().size() > 0) {
            for (Cerveza cerv : this.almacen.values()) {
                res += cerv.toString() + "\n";
            }
        } else {
            res += "NO HAY CERVEZAS DISPONIBLES TODAVIA\n";
        }

        res += "#####################\n";

        return res;
    }

    //=====================================================================
    //OPCION 2
    //=====================================================================
    public String filtrarCervezas(String nombre) {
        String res = "";

        for (Cerveza cerv : this.almacen.values()) {
            if (cerv.getNombre()
                    .toLowerCase()
                    .contains(nombre.toLowerCase())) {
                res += cerv.toString() + "\n";
            }
        }

        if (res.equals("")) {
            res = "NO HAY COINCIDENCIAS\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 3
    //=====================================================================
    public String verPorTipo(char tipo) {
        String res = "";
        boolean alguna = false;

        for (Cerveza cerv : this.almacen.values()) {
            if (cerv.getTipo() == tipo) {
                res += cerv.toString() + "\n";
                alguna = true;
            }
        }

        if (!alguna) {
            res = "NO HAY DE ESE TIPO\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 4
    //=====================================================================
    public String verPorDistribuidor(String dis) {
        String res = "";
        for (Cerveza cerv : this.almacen.values()) {
            if (cerv.getDistribuidor().equalsIgnoreCase(dis.trim())) {
                res += cerv.toString() + "\n";
            }
        }

        if (res.equals("")) {
            res = "NO HAY COINCIDENCIAS\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 5
    //=====================================================================
    public void rebajar(double porciento) {
        for (Cerveza cerv : this.almacen.values()) {
//            double precio_actual = cerv.getPrecio();
//            double rebaja = precio_actual * porciento / 100;
//            double precio_final = precio_actual - rebaja;

            //cerv.setPrecio(precio_final);
            cerv.setPrecio(cerv.getPrecio()
                    - cerv.getPrecio() * porciento / 100);

        }
    }

    //=====================================================================
    //OPCION 6
    //=====================================================================
    public String masCara() {
        String res = "";
        Cerveza mayor;

        if (this.almacen.size() > 0) {
            //OTRA VERSION
            ArrayList<Cerveza> cervezas
                    = new ArrayList<>(this.almacen.values());

            cervezas.sort((a, b) -> Double.
                    compare(b.getPrecio(), a.getPrecio()));

            mayor = cervezas.get(0);
            res = mayor.toString();

//            mayor=this.almacen.get(0);
//            for (Cerveza cerv : this.almacen) {
//                if(cerv.getPrecio()>mayor.getPrecio()){
//                    mayor=cerv;
//                }
//            }
//            res=mayor.toString();
        } else {
            res = "NO HAY CERVEZAS TODAVIA\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 7
    //=====================================================================
    public String mostrarOrdenadasPorNombre() {
        String res = "";
        //ORDENAMOS POR NOMBRE ALFABETICAMENTE (NATURAL) 
        ArrayList<Cerveza> cervezas
                = new ArrayList<>(this.almacen.values());

        cervezas.sort((a, b) -> a.getNombre()
                .compareToIgnoreCase(b.getNombre()));

        for (Cerveza cerv : cervezas) {
            res += cerv.toString() + "\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 8
    //=====================================================================
    private Cerveza buscarCerveza(String nombre) {
        return this.almacen.get(nombre);
    }

    public void añadirCerveza(String nombre, int stock, boolean artesanal, char tipo, double precio, String distribuidor) {
        Cerveza nueva, busqueda;

        busqueda = this.buscarCerveza(nombre);

        if (busqueda == null) {//NO EXISTE ESA CERVEZA
            nueva = new Cerveza(nombre, stock, artesanal, tipo, precio, distribuidor);
            this.almacen.put(nombre, nueva);
            System.out.println("Cerveza añadida");
        } else {
            System.out.println("ERROR:Ya existe esa cerveza");
        }

    }

    //=====================================================================
    //OPCION 9
    //=====================================================================
    public void borrarCerveza(String nombre) {
        Cerveza busqueda;

        busqueda = this.buscarCerveza(nombre);

        if (busqueda != null) {
            this.almacen.remove(nombre);
            System.out.println("Cerveza borrada de la lista");
        } else {
            System.out.println("ERROR: No existe");
        }

    }

    //=====================================================================
    //OPCION 10
    //=====================================================================
    public void venderCerveza(String nombre, int cantidad) {
        Cerveza busqueda;

        busqueda = this.buscarCerveza(nombre);

        if (busqueda != null) {
            busqueda.servir_cerveza(cantidad);
        } else {
            System.out.println("ERROR: No existe");
        }
    }

    //=====================================================================
    //OPCION 11
    //=====================================================================
    public void reponerCerveza(String nombre, int cantidad) {
        Cerveza busqueda;

        busqueda = this.buscarCerveza(nombre);

        if (busqueda != null) {
            busqueda.servir_cerveza(cantidad);
        } else {
            System.out.println("ERROR: No existe");
        }
    }

    //=====================================================================
    //OPCION 12
    //===================================================================== 
    private ArrayList<Cerveza> filtrarPorTipo(char tipo) {
        ArrayList<Cerveza> res = new ArrayList<>();

        for (Cerveza cerv : this.almacen.values()) {
            if (cerv.getTipo() == tipo) {
                res.add(cerv);
            }
        }

        return res;
    }

    public String masBarataTipo(char tipo) {
        String res = "";
        Cerveza menor_precio;
        //FILTRO POR TIPO EN ARRAYLIST A PARTE
        ArrayList<Cerveza> filtrada = this.filtrarPorTipo(tipo);
        if (filtrada.size() > 0) {
            //ORDENO DE MENOR A MAYOR POR PRECIO
            filtrada.sort((a, b) -> Double.compare(a.getPrecio(),
                    b.getPrecio()));
            //COGEMOS LA PRIMERA
            menor_precio = filtrada.get(0);

            res = menor_precio.toString();
        } else {
            res = "NO HAY CERVEZAS DE ESE TIPO";
        }
        return res;
    }

    //=====================================================================
    //OPCION 13
    //=====================================================================
    public void borrarSinStock() {
        Iterator<Cerveza> iter = this.almacen.values().iterator();

        while (iter.hasNext()) {
            Cerveza actual = iter.next();
            if (actual.getStock() == 0) {
                iter.remove();
            }
        }
    }

    private HashMap<Character, Integer> totalPorTipos() {
        //ESTADISTICAS DE STOCK DE CERVEZAS POR TIPOS
        HashMap<Character, Integer> sumaTipos
                = new HashMap<>();
        for (Cerveza cerv : this.almacen.values()) {
            if (sumaTipos.containsKey(cerv.getTipo())) {
                int actual = sumaTipos.get(cerv.getTipo());
                sumaTipos.put(cerv.getTipo(), actual + cerv.getStock());
            } else {
                sumaTipos.put(cerv.getTipo(), cerv.getStock());
            }
        }

        return sumaTipos;
    }

    //=====================================================================
    //OPCION 14
    //=====================================================================
    public String stockPorTipo() {
        String res = "";
        //r->350
        //n->97
        //R->271
        //t->0

        HashMap<Character, Integer> sumaTipos
                = this.totalPorTipos();
        //CONSTRUIR UN STRING QUE RESUMA LOS DATOS DEL HASHMAP

        for (Entry<Character, Integer> entrada : sumaTipos.entrySet()) {
            res += Cerveza.getTipoCompleto(entrada.getKey())
                    + "->"
                    + entrada.getValue() + "\n";
        }

        //SIN USAR HASHMAP
//        char[] tipos={'r','R','t','n'};
//        int[] sumas=new int[tipos.length];
//                    //[0,0,0,0]
//        for(Cerveza cerv:this.almacen.values()){  
//            int i=0,pos=-1;
//            while(i<tipos.length && pos==-1){
//                if(tipos[i]==cerv.getTipo()){
//                    pos=i;
//                    sumas[i]+=cerv.getStock();
//                }
//                i++;
//            }
//        }
//        
//        for(int i=0;i<tipos.length;i++){
//            res+=tipos[i]+"->"+sumas[i]+"\n";
//        }
        return res;
    }

    private HashMap<String,Integer> totalesDistribuidor(){
        HashMap<String,Integer> sumas=
                new HashMap<>();
        
        for (Cerveza cerv : this.almacen.values()) {
            if(sumas.containsKey(cerv.getDistribuidor())){
               sumas.put(cerv.getDistribuidor(),
                         sumas.get(cerv.getDistribuidor())
                         +cerv.getStock());  
            }else{
               sumas.put(cerv.getDistribuidor(),cerv.getStock()); 
            }
        }
        
        return sumas;
    }
    
    
    
    
    //=====================================================================
    //OPCION 15
    //=====================================================================
    public String stockPorDistribuidor() {
        String res="";
        
        for(Entry<String,Integer> entrada:this
                                           .totalesDistribuidor()
                                           .entrySet()){
            res+=entrada.getKey()+" suministra "+
                 entrada.getValue()+" unidades\n";
        }
        
        return res;
    }

    //=====================================================================
    //OPCION 16
    //=====================================================================
    public String tipoMasStock() {
        ArrayList<Entry<Character,Integer>> entradas=
                new ArrayList<>(this.totalPorTipos().entrySet());
        
        entradas.sort((a,b)->
                Integer.compare(b.getValue(), a.getValue()));
        char tipo_mayor=entradas.get(0).getKey();
        
        return Cerveza.getTipoCompleto(tipo_mayor);
        
    }

    //=====================================================================
    //OPCION 17
    //=====================================================================
    public String tiposSinStock() {
        String res = "";
        HashMap<Character, Integer> sumaTipos
                = this.totalPorTipos();

        for (Entry<Character, Integer> entrada : sumaTipos.entrySet()) {
            if (entrada.getValue() == 0) {
                res += Cerveza.getTipoCompleto(entrada.getKey())+"\n";
            }
        }
        return res;
    }

    //=====================================================================
    //OPCION 18
    //=====================================================================
    public String proveedorMasImportante() {
        ArrayList<Entry<String,Integer>> entradas=
                new ArrayList<>(this
                                .totalesDistribuidor()
                                .entrySet());
        entradas.sort((a,b)->Integer.compare(b.getValue(),a.getValue()));
        
        return entradas.get(0).getKey();
        
    }

}
