package base_de_datos;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Base_de_datos {
    
    public static void main(String[] args){
        
        
        // Importante, iniciar MySQL y Apache en XAMPP ! ! !
        try {
            // Primero cargamos el driver de la base de datos: 
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Ahora conectamos con la base de datos: 
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa","root","");
            
            
            // DELETE
//            String localizacion = "NEW YORK";
//            
//            String sql = "DELETE FROM deparatamentos "
//                    + "WHERE localizacion='"+localizacion+"';";
//            
//            
//            
            // UPDATE
//            
//            String nombre = "Ventas";
//            String localizacion = "Málaga";
//            
//            String sql = "UPDATE departamentos "
//                    + "SET localizacion='"+localizacion+"' "
//                    + "WHERE nombre ='"+nombre+"'";
            
            
            
            // INSERT 
//            
//            Statement sentencia = conexion.createStatement();
//            String nombre = "Marketing";
//            String localizacion = "Chicago";
//            // Esto es una sentencia para hacer un insert, es necesario poner NULL para los valores que se autoincrementan.
//            String sql = "INSERT INTO departamentos VALUES (NULL,'"+nombre+"','"+localizacion+"');";
//            
//            //Esto comprueba si se ha actualizado la tabla, devuelve 0 si no y 1 o más dependiendo de las filas cambiadas.
//            int filas = sentencia.executeUpdate(sql);
//            
//            // Haciendo esto comprobamos si la tabla se ha actualizado o no.
//            if(filas>0){
//                System.out.println("Datos insertados con éxito.");
//            }else{
//                System.out.println("Datos no insertados.");
//            }
            
            
            
            // SELECT
            
//            // Con statement nos comunicamos con la base de datos, debemos usar UN STATEMENT POR CADA CONSULTA ! 
//            Statement consulta = conexion.createStatement();
//            
//            String sql = "SELECT * "
//                    + "   FROM departamentos";
//            
//            ResultSet resultado = consulta.executeQuery(sql);
//            
//            // Esto es parecido a un cursor, si hacemos .next() avanzamos.
//            
//            // Se puede acceder a los datos por la posición 
//            while(resultado.next()){
//                System.out.println(resultado.getInt(1));
//                System.out.println(resultado.getString(2));
//                System.out.println(resultado.getString(3));
//                
//            }
//            
//            // Es posible comprobar si una consulta da o no resultado con if(resultado.next())
//            
//            if(resultado.next()){
//                System.out.println(resultado.getInt(1));
//            }else{
//                System.out.println("No hay resultados.");
//            }
//            
//            
//            // Tambien se puede acceder por el nombre del dato
////            resultado.next();
////            System.out.println(resultado.getInt("id"));
////            System.out.println(resultado.getString("nombre"));
////            System.out.println(resultado.getString("localizacion"));
//            
//            // Recuerda cerrar todo lo creado para liberar recursos! 
//            resultado.close();
//            consulta.close();
//            conexion.close();
//            
            
            
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver MySQL no cargado.");
        } catch(SQLException sql){
            System.out.println("Error de SQL: ");
            sql.printStackTrace();
        }
        
        
        
    }
    
}
