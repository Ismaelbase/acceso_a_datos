package ejercicio1;

import java.io.FileWriter;
import java.sql.ResultSetMetaData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
        Class.forName(driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base_datos,
                usuario, //usuario de la BD
                password); //contraseña
        // Se puede separar en dos ifs, uno para si esta vacio y otro para si no es admin.
        if(this.tipo_usuario.equals("") || !this.tipo_usuario.equals("admin")){
            throw new RuntimeException("No tienes permiso.");
        }
        
        // La primera consulta comprueba que el usuario no exista:
        
        String sql1 = "SELECT id "
                + "FROM usuarios "
                + "WHERE login =?";
        
        // Se prepara la sentencia, para usar el prepared.
        PreparedStatement sentencia1 = conexion.prepareStatement(sql1);
        // Se le indica que la primera (1) interrogacion es la variable login.
        sentencia1.setString(1, login);
        // Se ejecuta, sin poner el string dentro, se hace sin mas.
        ResultSet resultado1 = sentencia1.executeQuery();
        // Si devuelve algo, se lanza una excepcion, el usuario que se intenta crear, ya existe.
        if(resultado1.next()){
            throw new RuntimeException("Ya existe ese usuario.");
        }
        // Si no existe, se crea el string de la sentencia que añade el usuario nuevo a la BD.
        String sql2= "INSERT INTO usuarios VALUES(NULL,?,?,?,'normal')";

        // Para conseguir la fecha, se puede hacer asi: 
        Calendar fecha_hoy = Calendar.getInstance();
        
        String fecha = fecha_hoy.get(Calendar.YEAR)+"-"
                + (fecha_hoy.get(Calendar.MONTH)+1)+"-"
                + fecha_hoy.get(Calendar.DATE);
        
        
        PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
        
        sentencia2.setString(1, login);
        sentencia2.setString(2, fecha);
        sentencia2.setString(3, contra);
        
        int filas = sentencia2.executeUpdate();
        
        if(filas == 0){
            throw new RuntimeException("Error, el usuario no pudo crearse.");
        }
        
        
        resultado1.close();
        sentencia1.close();
        sentencia2.close();
        conexion.close();
    }

    public void borrarUsuario(String login) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base_datos,
            usuario, //usuario de la BD
            password); //contraseña
        
        
        //Comprobar si el usuario logeado que intenta borrar es admin se puede hacer con this.tipo
        
        String sql1 = "SELECT tipo FROM usuarios WHERE login =?";
        
        PreparedStatement sentencia1 = conexion.prepareStatement(sql1);
        sentencia1.setString(1, this.login);
        
        ResultSet resultado = sentencia1.executeQuery();
        
        if(resultado.next()){
            
            String tipo = resultado.getString(1);
            
            if(tipo.equalsIgnoreCase("admin")){
                String sql2 = "DELETE FROM usuarios WHERE login =?";
                PreparedStatement sentencia2 = conexion.prepareStatement(sql2);

                sentencia2.setString(1, login);

                int actualizadas = sentencia2.executeUpdate();
                
                if(actualizadas>0){
                    System.out.println("Usuario "+login+" borrado de la base de datos.");
                }else{
                    throw new RuntimeException("No se ha borrar el usuario.");
                }
                
                sentencia2.close();
            }
            
        }else{
            throw new RuntimeException("Error, el login no existe.");
        }
        
        resultado.close();
        sentencia1.close();
        conexion.close();
    }

    public void modificarPassword(String contra, String nueva_contra) throws ClassNotFoundException, SQLException {
            if(this.autenticar(login, contra)){
                
                Class.forName(driver);
                Connection conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base_datos,
                    usuario, //usuario de la BD
                    password); //contraseña
                
                String sql = "UPDATE usuarios SET pass=? WHERE login=?";
                
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, nueva_contra);
                sentencia.setString(2,this.login);
                
                sentencia.executeUpdate();
                
                sentencia.close();
                conexion.close();
            }else{
                throw new RuntimeException("La contraseña anterior no coincide");
            }
    }

    public void modificarLogin(String contra, String nuevo_login) throws ClassNotFoundException, SQLException {
            if(this.login.equals("")){
                
            }else{
                throw new RuntimeException("Tu usuario no puede estar vacio");
            }
            
            if(this.login.equals(nuevo_login)){
                
            }else{
                throw new RuntimeException("Login cambiado con exito :P");
            }
            
            if(this.autenticar(this.login, contra)){
                // Consulta para comprobar que el nuevo login existe o no, y si no existe se hace un update.
            }else{
                throw new RuntimeException("Autenticacion erronea.");
            }
            
    }
    
    public void importarUsuarios(String fichero) throws ClassNotFoundException, IOException, SQLException {
        //Este ejercicio no es de examen
        //Falta que el usuario este autenticado como admin para poder hacer esto: 
        Class.forName(driver);
        Connection conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base_datos,
            usuario, //usuario de la BD
            password); //contraseña
        
        String sql = "SELECT * FROM usuarios";
        Statement consulta = conexion.createStatement();
        
        ResultSet resultado = consulta.executeQuery(sql);
        
        FileWriter fw = new FileWriter(fichero);
        PrintWriter pw = new PrintWriter(fw);
        
        String[] cabecera = atributos_consulta(resultado.getMetaData());
        pw.println(String.join(":",cabecera));
        
        while(resultado.next()){

            // El metodo valores_fila esta arriba, devuelve la cabecera de la BD -> [id,login,fecha,pass,tipo]
            String[] fila = valores_fila(resultado);
            // Esto printea en el fichero los datos fila a fila unidos por ':'
            pw.println(String.join(":", fila));
        }
        fw.close();
        pw.close();
        conexion.close();
        resultado.close();
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
