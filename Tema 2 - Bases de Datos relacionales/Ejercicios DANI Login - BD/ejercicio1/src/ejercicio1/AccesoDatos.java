package ejercicio1;

import java.sql.ResultSetMetaData;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AccesoDatos {

    //Datos de MySQL para entrar en el sistema
    private String base_datos, usuario, password, driver, host;

    //Datos del usuario que se ha autenticado
    private String login, tipo_usuario;

    public AccesoDatos(String host, String base_datos, String usuario, String password, String driver) {
        this.driver = driver;
        this.host = host;
        this.base_datos = base_datos;
        this.usuario = usuario;
        this.password = password;

        //Al iniciar el programa nadie está autenticado en el sistema
        this.login = "";
        this.tipo_usuario = "";
    }

    public boolean autenticar(String login, String contraseña) throws ClassNotFoundException, SQLException {
        boolean autenticado;
        Class.forName(driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base_datos,
                usuario, //usuario de la BD
                password); //contraseña
        String sql="SELECT tipo FROM usuarios "
                + "WHERE login=? AND "
                + "pass=?";
        PreparedStatement sentencia=conexion.prepareStatement(sql);
        sentencia.setString(1, login);
        sentencia.setString(2, contraseña);
        
        ResultSet resultado=sentencia.executeQuery();
        if(resultado.next()){
            this.login=login;
            this.tipo_usuario=resultado.getString(1);
            autenticado=true;
        }else{
            autenticado=false;
        }
        
        resultado.close();
        sentencia.close();
        conexion.close();
        
        return autenticado;
    }

    public ArrayList<String[]> mostrarDatos() throws ClassNotFoundException, SQLException {
        ArrayList<String[]> datos=new ArrayList<>();
        Class.forName(driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base_datos,
                usuario, //usuario de la BD
                password); //contraseña
        Statement sentencia=conexion.createStatement();
        String sql;
        if(this.login.equals("")){
            throw new RuntimeException("No hay usuario autenticado");
        }
        
        if(this.tipo_usuario.equals("admin")){
            sql="SELECT * FROM usuarios";
        }else{
            sql="SELECT * FROM usuarios WHERE login='"+this.login+"'";
        }
        ResultSet resultado=sentencia.executeQuery(sql);
        String[] atributos=atributos_consulta(resultado.getMetaData());
        datos.add(atributos);
        while(resultado.next()){
            String[] fila=valores_fila(resultado);
            datos.add(fila);
        }
        
        resultado.close();
        sentencia.close();
        conexion.close();
        
        return datos;
    }

    public void nuevoUsuario(String login, String contra) throws ClassNotFoundException, SQLException {
        
    }

    public void borrarUsuario(String login) throws ClassNotFoundException, SQLException {

    }

    public void modificarPassword(String contra, String nueva_contra) throws ClassNotFoundException, SQLException {

    }

    public void modificarLogin(String contra, String nuevo_login) throws ClassNotFoundException, SQLException {

    }

    public void importarUsuarios(String fichero) throws ClassNotFoundException, IOException, SQLException {

    }

    public String[] atributos_consulta(ResultSetMetaData metadatos) throws SQLException {
        String[] nombres_atributos = new String[metadatos.getColumnCount()];

        for (int i = 0; i < metadatos.getColumnCount(); i++) {
            nombres_atributos[i] = metadatos.getColumnName(i + 1);
        }

        return nombres_atributos;

    }

    public String[] valores_fila(ResultSet fila) throws SQLException {

        String[] valores = new String[fila.getMetaData().getColumnCount()];
        for (int i = 0; i < fila.getMetaData().getColumnCount(); i++) {
            valores[i] = fila.getString(i + 1);
        }
        return valores;

    }

    public void metodoPlantillaSQL() {
        String nombre = "", localizacion = "", ocupacion = "", fecha = "";
        int filas;
        try {

            Class.forName(driver);
            Connection conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base_datos,
                    usuario, //usuario de la BD
                    password); //contraseña

            //INSERT UPDATE DELETE
            //SIN PREPARAR
            String sql_insert = "INSERT INTO departamentos VALUES ('" + nombre + "', '" + localizacion + "')";
            System.out.println(sql_insert);
            Statement sentencia_insert = conexion.createStatement();
            filas = sentencia_insert.executeUpdate(sql_insert);
            System.out.println("Filas afectadas: " + filas);
            sentencia_insert.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexion

            //PREPARADA
            String sql_prepa_insert = "INSERT INTO departamentos VALUES (?, ?)";
            PreparedStatement sentencia_prepa_insert = conexion.prepareStatement(sql_prepa_insert);
            filas = sentencia_prepa_insert.executeUpdate();
            System.out.println("Filas afectadas: " + filas);
            sentencia_prepa_insert.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexion

            //SELECT
            //SIN PREPARAR
            String sql_select = "SELECT * FROM empleados "
                    + "WHERE ocupacion ='" + ocupacion + "'AND fecha_alta='" + fecha + "'";
            Statement sentencia_select = conexion.createStatement();

            ResultSet resul = sentencia_select.executeQuery(sql_select);

            while (resul.next()) {

            }
            resul.close(); //Cerrar ResultSet
            sentencia_select.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión    

            //PREPARADA
            String sql_prepa_select = "SELECT * FROM empleados WHERE ocupacion =? AND fecha_alta=?";
            PreparedStatement sentencia_prepa_select = conexion.prepareStatement(sql_prepa_select);
            sentencia_prepa_select.setString(1, "ocupacion");
            sentencia_prepa_select.setString(2, "fecha de alta");
            ResultSet resul_prepa = sentencia_prepa_select.executeQuery();
            while (resul.next()) {

            }
            resul.close(); //Cerrar ResultSet
            sentencia_prepa_select.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión    
        } catch (ClassNotFoundException cfe) {
            JOptionPane.showMessageDialog(null, cfe.getMessage());
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
        }
    }
}
