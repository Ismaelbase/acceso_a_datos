package basesdatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Ejercicios {

    private String driver;
    private String host;
    private String base_datos;
    private String usuario;
    private String password;

    public Ejercicios(String host, String base_datos, String usuario, String password, String driver) {
        this.driver = driver;
        this.host = host;
        this.base_datos = base_datos;
        this.usuario = usuario;
        this.password = password;

    }

    public void ejercicio1() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña

        conexion.close();
    }

    public void ejercicio2() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio3() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio4() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        Statement sentencia = conexion.createStatement();
        double porcentaje = 2.5;

        String sql = "UPDATE empleados "
                + "SET salario=salario+"
                + "salario*" + porcentaje + "/100";

        int filas = sentencia.executeUpdate(sql);

        System.out.println("Empleados afectados:" + filas);

        sentencia.close();
        conexion.close();
    }

    public void ejercicio5() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        Statement sentencia = conexion.createStatement();
        double cantidad = 300;
        String cargo = "COMERCIAL";
        String sql = "UPDATE empleados "
                + "SET salario=salario+" + cantidad + " "
                + "WHERE cargo='" + cargo + "'";
        System.out.println(sql);

        int filas = sentencia.executeUpdate(sql);

        System.out.println("Empleados afectados:" + filas);

        sentencia.close();
        conexion.close();
    }

    public void ejercicio6() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio7() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio8() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        Statement sentencia = conexion.createStatement();

//        String sql="SELECT sum(salario) "
//                 + "FROM empleados";
//        ResultSet resultado=sentencia.executeQuery(sql);
//        
//        resultado.next();
//        System.out.println("La suma:"+resultado.getDouble(1));
//        
        String sql = "select salario from empleados";
        ResultSet resultado = sentencia.executeQuery(sql);
        double suma_total = 0;

        while (resultado.next()) {
            suma_total += resultado.getDouble("salario");
        }

        System.out.println("La suma:" + suma_total);

        resultado.close();
        sentencia.close();
        conexion.close();
    }

    public void ejercicio9() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio10() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        Statement sentencia = conexion.createStatement();
        int año_limite = 1982;
        String sql = "SELECT apellido,YEAR(fecha_alta) "
                + "FROM empleados";

        ResultSet resultado = sentencia.executeQuery(sql);
        String salida = "";
        while (resultado.next()) {
            int año = resultado.getInt(2);
            String apellido = resultado.getString(1);
            if (año < año_limite) {
                salida += apellido + "\n";
            }
        }

//        String sql = "SELECT apellido "
//                   + "FROM empleados "
//                   + "WHERE YEAR(fecha_alta)<"+año;
//
//        String limite="1982-01-01";
//        String sql = "SELECT apellido "
//                   + "FROM empleados "
//                   + "WHERE fecha_alta<'"+limite+"'";
//        
//        ResultSet resultado = sentencia.executeQuery(sql);
//        String salida="";
//        while(resultado.next()){
//            salida+=resultado.getString(1)+"\n";
//        }
//        
        System.out.println("EMPLEADOS ANTES DEL 1982:\n"
                + salida);

        resultado.close();
        sentencia.close();
        conexion.close();
    }

    public void ejercicio11() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio12() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio13() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio14() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        Statement sentencia = conexion.createStatement();
        String sql = "SELECT apellido,salario"
                  + " FROM empleados "
                  + " ORDER BY salario DESC";
        ResultSet resultado = sentencia.executeQuery(sql);
        
        if(resultado.next()){
            //HAY UNO AL MENOS
            System.out.println(resultado.getString(1));
            System.out.println(resultado.getDouble(2));
        }else{
            System.out.println("No hay empleados");
        }
        
        resultado.close();
        sentencia.close();
        conexion.close();
    }

    public void ejercicio15() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio16() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        Statement sentencia = conexion.createStatement();
        String apellido="SMITH";
        String sql = "SELECT nombre"
                  + " FROM departamentos "
                  + " WHERE id="
                + "(SELECT departamento "
                + "FROM empleados "
                + "WHERE apellido='"+apellido+"')";
        
        ResultSet resultado = sentencia.executeQuery(sql);
        
        resultado.next();
        System.out.println(resultado.getString(1));
        
        resultado.close();
        sentencia.close();
        conexion.close();
    }

    public void ejercicio17() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio18() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio19() throws ClassNotFoundException, SQLException {

    }

    public void ejercicio20() throws ClassNotFoundException, SQLException {

    }

    public void metodoPlantilla() {
//        try {
//            Class.forName(driver);
//            Connection conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base_datos,
//                    usuario, //usuario de la BD
//                    password); //contraseña
//
//            String sql_select = "SELECT * FROM empleados "
//                    + "WHERE ocupacion ='" + cargo + "'AND fecha_alta='" + fecha + "'";
//            Statement sentencia_select = conexion.createStatement();
//
//            ResultSet resul = sentencia_select.executeQuery(sql_select);
//
//            while (resul.next()) {
//
//            }
//            resul.close(); //Cerrar ResultSet
//            sentencia_select.close(); // Cerrar Statement
//            conexion.close(); // Cerrar conexión    
//
//            String sql_insert = "INSERT INTO departamentos VALUES ('" + nombre + "', '" + localizacion + "')";
//            System.out.println(sql_insert);
//            Statement sentencia_insert = conexion.createStatement();
//            filas = sentencia_insert.executeUpdate(sql_insert);
//            System.out.println("Filas afectadas: " + filas);
//            sentencia_insert.close(); // Cerrar Statement
//            conexion.close(); // Cerrar conexion
//
//            resul.close(); //Cerrar ResultSet
//            sentencia_prepa_select.close(); // Cerrar Statement
//            conexion.close(); // Cerrar conexión    
//        } catch (ClassNotFoundException cfe) {
//            JOptionPane.showMessageDialog(null, cfe.getMessage());
//        } catch (SQLException sqle) {
//            JOptionPane.showMessageDialog(null, sqle.getMessage());
//        }
    }
}
