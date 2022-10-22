package basededatos;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Basededatos {

    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        try {
            //CARGAR EL DRIVER DE LA BASE DE DATOS
            Class.forName("com.mysql.cj.jdbc.Driver");
            //CONECTAR CON LA BASE DE DATOS
            Connection conexion = DriverManager.
                    getConnection("jdbc:mysql://localhost/empresa",
                            "root",
                            "");
            
            Statement sentencia=conexion.createStatement();
            String nombre="Ventas";
            String localizacion="New York";
            
//            String sql="INSERT INTO departamentos "+
//                       "VALUES (NULL,'"+nombre+"',NULL,'"+localizacion+"');";
//                       //"VALUES ('Marketing','Chicago')"
//            sql="INSERT INTO departamentos "+
//                "(nombre,localizacion) "+
//                "VALUES ('"+nombre+"','"+localizacion+"');";

            
//            String sql="UPDATE departamentos "+
//                       "SET localizacion='"+localizacion+"' "+
//                       "WHERE nombre='"+nombre+"'"; 
            String sql="DELETE FROM departamentos "
                     + "WHERE localizacion='"+localizacion+"'";           
            
            int filas=sentencia.executeUpdate(sql);
            
            if (filas>0){
                System.out.println("Datos actualizados con éxito");
                System.out.println("Filas:"+filas);
                
            }else{
                System.out.println("Datos no insertados");
            }
             
            sentencia.close();
            conexion.close();
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
//            Statement consulta = conexion.createStatement();
//            
//            
//            //System.out.print("Dime localizacion:");
////            String localizacion="Chicago";
////            String sql = "SELECT *"
////                       + "FROM departamentos "
////                       + "WHERE localizacion='"+localizacion+"'";
//            String nombre="Marketing";
//            String sql = "SELECT localizacion "
//                       + "FROM departamentos "
//                       + "WHERE nombre='"+nombre+"'";
//            ResultSet resultado = consulta.executeQuery(sql);
//            
//            if(resultado.next()){
//                System.out.println("Localizacion:"+
//                                    resultado.getString(1));
//            }else{
//                System.out.println("No existe el departamento");
//            }
//
////            while (resultado.next()) {
//                System.out.println(resultado.getInt(1));
//                System.out.println(resultado.getString(2));
//                System.out.println(resultado.getString(3));
//
//                System.out.println(resultado.getInt("id"));
//                System.out.println(resultado.getString("nombre"));
//                System.out.println(resultado.getString("localizacion"));
//            }
//            resultado.close();
//            consulta.close();
//            conexion.close();

        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver MySQL no cargado");
        } catch (SQLException sql) {
            sql.printStackTrace();
            System.out.println("Error de SQL");
        }
    }

}
