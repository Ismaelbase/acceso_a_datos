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
    private Connection conexion;

    public Ejercicios(String host, String base_datos, String usuario, String password, String driver) {
        this.driver = driver;
        this.host = host;
        this.base_datos = base_datos;
        this.usuario = usuario;
        this.password = password;

    }

    public void ejercicio1() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        String localizacion = "Valencia";
        String nombre = "Dep 3";
        
        String sql = "INSERT INTO departamentos VALUES (NULL,'"+nombre+"','"+localizacion+"');";
        
        
        // executeQuery es para los SELECT y executeUpdate para el INSERT - DELETE - UPDATE
//        consulta.executeUpdate(sql);
        
        int actualizadas = consulta.executeUpdate(sql);
        
        if(actualizadas>0){
            System.out.println(actualizadas+" filas actualizadas.");
        }else{
            System.out.println("No han podido añadirse a la base de datos los valores indicados.");
        }
        
        consulta.close();
        conexion.close();
    }
    
    public void ejercicio2() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        String nombre = "Dep 1";
        
        String sql = "DELETE FROM departamentos WHERE nombre='"+nombre+"'";
        
        int actualizadas = consulta.executeUpdate(sql);
        
        if(actualizadas>0){
            System.out.println("Se han borrado "+actualizadas+" filas.");
        }else{
            System.out.println("Error, no se ha podido borrar la fila.");
        }
        
        consulta.close();
        conexion.close();
    }
    
    public void ejercicio3() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        String apellido = "SMITH";
        
        String sentencia = "DELETE FROM empleados WHERE apellido='"+apellido+"';";
        
        int actualizadas = consulta.executeUpdate(sentencia);
        
        if(actualizadas>0){
            System.out.println("Se han borrado "+actualizadas+" filas.");
        }else{
            System.out.println("No se ha podido borrar ninguna fila.");
        }
        
                
        consulta.close();
        conexion.close();
    }
    
    public void ejercicio4() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        double porcentaje = 10;
        
        porcentaje = porcentaje/100;
        
        String sentencia = "UPDATE empleados SET salario=salario*"+porcentaje+"+salario";
        
        int actualizadas = consulta.executeUpdate(sentencia);
        
        if(actualizadas>0){
            System.out.println("Se han actualizado "+actualizadas+" filas.");
        }else{
            System.out.println("No se ha podido actualizar ninguna fila.");
        }

        conexion.close();
    }
    
    public void ejercicio5() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        double cantidad = 49.5;
        String cargo = "COMERCIAL";
        
        String sentencia = "UPDATE empleados SET salario =salario+'"+cantidad+"' WHERE cargo='"+cargo+"'";
        
        int actualizadas = consulta.executeUpdate(sentencia);
        
        if(actualizadas>0){
            System.out.println("Se han actualizado "+actualizadas+" filas.");
        }else{
            System.out.println("No se ha actualizado ninguna fila.");
        }
        
        consulta.close();
        conexion.close();
    }
    
    public void ejercicio6() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        String apellido = "KING";
        int salario = 4000;
        
        String sentencia = "SELECT * FROM empleados WHERE apellido='"+apellido+"' AND salario>"+salario;
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        // Esta mal esto, solo mostrar apellido 
        while(resultado.next()){
            System.out.println("ID: "+resultado.getInt(1));
            System.out.println("Apellido: "+resultado.getString(2));
            System.out.println("Cargo: "+resultado.getString(3));
            
            if(resultado.getInt(4) == 0){
                System.out.println("Jefe: NULL");
            }else{
                System.out.println("Jefe: "+resultado.getInt(4));
            }
            
            System.out.println("Fecha de alta: "+resultado.getString(5));
            System.out.println("Salario: "+resultado.getDouble(6)+"€");
            System.out.println("Comision: "+resultado.getInt(7)+"€");
            System.out.println("ID Departamento: "+resultado.getInt(8));
        }
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    
    public void ejercicio7() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        double salario = 1000;
        String cargo = "COMERCIAL";
        
        String sentencia = "SELECT apellido FROM empleados WHERE salario>"+salario+" AND cargo='"+cargo+"';";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        while(resultado.next()){
            System.out.println(resultado.getString(1));
        }
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    
    public void ejercicio8() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        double suma=0;
        
        String sentencia = "SELECT salario FROM empleados";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        while(resultado.next()){
            suma += resultado.getDouble(1);
        }
        
        System.out.println("La suma de todos los sueldos es: "+suma+"€.");
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    public void ejercicio9() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        String cargo = "MANAGER";
        double suma = 0;
        
        String sentencia = "SELECT salario FROM empleados WHERE cargo='"+cargo+"'";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        while(resultado.next()){
            suma += resultado.getDouble(1);
        }
        
        System.out.println("La suma del sueldo de los empleados con cargo "+cargo+" es: "+suma+"€.");
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    public void ejercicio10() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        
        Statement consulta = conexion.createStatement();
        
//        String fecha = "1981-12-09";
          int fecha = 1982;
        // Mejor forma de hacerlo ! poniendo YEAR(fecha) solo tiene en cuenta el año ! 
        String sentencia = "SELECT apellido FROM empleados WHERE YEAR(fecha_alta)<'"+fecha+"'";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        while(resultado.next()){
            System.out.println(resultado.getString(1));
        }
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    public void ejercicio11() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        String fecha = "1981-12-09";
        
        String sentencia = "SELECT apellido FROM empleados WHERE fecha_alta>'"+fecha+"'";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        while(resultado.next()){
            System.out.println(resultado.getString(1));
        }
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    public void ejercicio12() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        
        Statement consulta = conexion.createStatement();
        
        String fecha_uno = "1981-0-0";
        String fecha_dos = "1982-0-0";
        
        String sentencia = "SELECT apellido FROM empleados WHERE fecha_alta<'"+fecha_dos+"' AND fecha_alta>'"+fecha_uno+"'";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        while(resultado.next()){
            System.out.println(resultado.getString(1));
        }
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    public void ejercicio13() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        String sentencia = "SELECT apellido FROM empleados WHERE jefe IS NULL";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        while(resultado.next()){
            System.out.println(resultado.getString(1));
        }
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    public void ejercicio14() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        Statement consulta = conexion.createStatement();
        
        String sentencia = "SELECT salario FROM empleados";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        double sueldo = 0;
        
        while(resultado.next()){
            if(resultado.getDouble(1)>sueldo){
                sueldo = resultado.getDouble(1);
//                System.out.println(resultado.getDouble(1));
            }
        }
        
        String sentencia2 = "SELECT apellido FROM empleados WHERE salario='"+sueldo+"'";
        
        resultado = consulta.executeQuery(sentencia2);
        
        resultado.next();
        System.out.println(resultado.getString(1));
        
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    public void ejercicio15() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        
        Statement consulta = conexion.createStatement();
        
        String sentencia = "SELECT fecha_alta FROM empleados";
        
        ResultSet resultado = consulta.executeQuery(sentencia);
        
        resultado.next();
        String fecha_inicial = resultado.getString(1);
        String[] primero_partido = fecha_inicial.split("-");
        
        
        int año_menor = Integer.parseInt(primero_partido[0]);
        int mes_menor = Integer.parseInt(primero_partido[1]);
        int dia_menor = Integer.parseInt(primero_partido[2]);
        
        while(resultado.next()){
            String fecha = resultado.getString(1);
            String[] partido = fecha.split("-");
            
            int año = Integer.parseInt(partido[0]);
            int mes = Integer.parseInt(partido[1]);
            int dia = Integer.parseInt(partido[2]);
             
            if(año<año_menor){
                if(mes<mes_menor){
                    if(dia<dia_menor){
                        año_menor = año;
                        mes_menor = mes;
                        dia_menor = dia;
                    }
                }
            }
        }
        
        String fecha_menor = año_menor+"-"+mes_menor+"-"+dia_menor;
        
        String sentencia2 = "SELECT apellido FROM empleados WHERE fecha_alta='"+fecha_menor+"'";
        
        resultado = consulta.executeQuery(sentencia2);
        
        resultado.next();
        
        System.out.println(resultado.getString(1));
        
        resultado.close();
        consulta.close();
        conexion.close();
    }
    public void ejercicio16() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        
        
        conexion.close();
    }
    
    public void ejercicio17() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        
        
        conexion.close();
    }
    
    public void ejercicio18() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        
        
        conexion.close();
    }
    
    public void ejercicio19() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        
        
        conexion.close();
    }
    
    public void ejercicio20() throws ClassNotFoundException, SQLException {
     Class.forName(this.driver);
        conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.base_datos,
                this.usuario, //usuario de la BD
                this.password); //contraseña
        
        
        
        conexion.close();
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
