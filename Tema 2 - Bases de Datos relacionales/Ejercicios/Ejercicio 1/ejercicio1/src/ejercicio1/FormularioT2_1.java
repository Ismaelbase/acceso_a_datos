package ejercicio1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormularioT2_1 extends JFrame {

    private JButton autenticar, crear, borrar, modPass, modLogin, importar;
    private JLabel label_login, label_pass, label_repite_pass, label_nuevo_login, label_nuevo_pass;
    private JTextField text_login, text_pass, text_repite_pass, text_nuevo_login, text_nuevo_pass;

    private JTextArea area;
    private JFileChooser jf;
    private AccesoDatos ad;
    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioT2_1() {
        super("Sistema login");

        //Configuracion de la tabla
        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        ad = new AccesoDatos("localhost", "ejercicio1", "root", "", "com.mysql.cj.jdbc.Driver");

        autenticar = new JButton("Autenticar");

        crear = new JButton("Crear usuario");
        borrar = new JButton("Borrar usuario");
        modPass = new JButton("Modificar password");
        modLogin = new JButton("Modificar login");
        importar = new JButton("Importar desde fichero");

        label_login = new JLabel("Nombre");
        label_pass = new JLabel("Contraseña");
        label_repite_pass = new JLabel("Repite constraseña");
        label_nuevo_pass = new JLabel("Cambio de contraseña");
        label_nuevo_login = new JLabel("Cambio de login");

        text_login = new JTextField(20);
        text_pass = new JTextField(20);
        text_repite_pass = new JTextField(20);
        text_nuevo_pass = new JTextField(20);
        text_nuevo_login = new JTextField(20);

        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(5, 2));

        panel_datos.add(label_login);
        panel_datos.add(text_login);

        panel_datos.add(label_pass);
        panel_datos.add(text_pass);

        panel_datos.add(label_repite_pass);
        panel_datos.add(text_repite_pass);

        panel_datos.add(label_nuevo_login);
        panel_datos.add(text_nuevo_login);

        panel_datos.add(label_nuevo_pass);
        panel_datos.add(text_nuevo_pass);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(2, 3));
        panel_botones.add(autenticar);
        panel_botones.add(crear);
        panel_botones.add(borrar);
        panel_botones.add(modPass);
        panel_botones.add(modLogin);
        panel_botones.add(importar);

        jf = new JFileChooser();

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

        importar.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioT2_1.this);
            if (codigo == 0) {
                try {
                    fichero = jf.getSelectedFile();
                    ad.importarUsuarios(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioT2_1.this, " Datos importados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                    ArrayList<String[]> datos = ad.mostrarDatos();
                    Iterator<String[]> it = datos.iterator();
                    modelo.setColumnIdentifiers(it.next());
                    while (it.hasNext()) {
                        modelo.addRow(it.next());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_1.this, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(FormularioT2_1.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        autenticar.addActionListener((e) -> {
            if (text_login.getText().trim().equals("") || text_pass.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_1.this, "Faltan datos");
            } else {
                try {
                    if (ad.autenticar(text_login.getText().trim(), text_pass.getText().trim())) {
                        JOptionPane.showMessageDialog(FormularioT2_1.this, "Autenticado con exito");
                        text_login.setText("");
                        text_pass.setText("");
                        ArrayList<String[]> datos = ad.mostrarDatos();
                       
                        Iterator<String[]> it = datos.iterator();
                        modelo.setColumnIdentifiers(it.next());
                        while (it.hasNext()) {
                            modelo.addRow(it.next());
                        }
                    } else {
                        JOptionPane.showMessageDialog(FormularioT2_1.this, "Error de autenticacion");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_1.this, ex.getMessage());
                }
            }

        });

        crear.addActionListener((e) -> {
            if (text_login.getText().trim().equals("") || text_pass.getText().trim().equals("")
                    || text_repite_pass.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_1.this, "Faltan datos");
            } else {
                if (!text_pass.getText().trim().equals(text_repite_pass.getText().trim())) {
                    JOptionPane.showMessageDialog(FormularioT2_1.this, "No coinciden los passwords");
                } else {
                    try {
                        ad.nuevoUsuario(text_login.getText().trim(), text_pass.getText().trim());
                        JOptionPane.showMessageDialog(FormularioT2_1.this, "Datos introducidos con éxito");
                        text_login.setText("");
                        text_pass.setText("");
                        text_repite_pass.setText("");
                        ArrayList<String[]> datos = ad.mostrarDatos();
                        Iterator<String[]> it = datos.iterator();
                        modelo.setRowCount(0);
                        modelo.setColumnIdentifiers(it.next());
                        while (it.hasNext()) {
                            modelo.addRow(it.next());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormularioT2_1.this, ex.getMessage());
                    }
                }
            }
        });

        borrar.addActionListener((e) -> {
            if (text_login.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_1.this, "Faltan datos");
            } else {
                try {
                    ad.borrarUsuario(text_login.getText());
                    JOptionPane.showMessageDialog(FormularioT2_1.this, "Borrado con éxito");
                    text_login.setText("");
                    ArrayList<String[]> datos = ad.mostrarDatos();
                    Iterator<String[]> it = datos.iterator();
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(it.next());
                    while (it.hasNext()) {
                        modelo.addRow(it.next());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_1.this, ex.getMessage());
                }
            }
        });

        modPass.addActionListener((e) -> {
            if (text_pass.getText().equals("")
                    || text_nuevo_pass.equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_1.this, "Faltan datos");
            } else {
                try {
                    ad.modificarPassword(text_pass.getText(), text_nuevo_pass.getText());
                    JOptionPane.showMessageDialog(FormularioT2_1.this, "Contraseña modificada con exito.");
                    text_pass.setText("");
                    text_nuevo_pass.setText("");
                    ArrayList<String[]> datos = ad.mostrarDatos();
                    Iterator<String[]> it = datos.iterator();
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(it.next());
                    while (it.hasNext()) {
                        modelo.addRow(it.next());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_1.this, ex.getMessage());
                }
            }
        });

        modLogin.addActionListener((e) -> {
            if (text_pass.getText().equals("")
                    || text_nuevo_login.equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_1.this, "Faltan datos");
            } else {
                try {
                    ad.modificarLogin(text_pass.getText(), text_nuevo_login.getText());
                    JOptionPane.showMessageDialog(FormularioT2_1.this, "Nombre cambiado con con éxito");
                    text_pass.setText("");
                    text_nuevo_login.setText("");
                    ArrayList<String[]> datos = ad.mostrarDatos();
                    Iterator<String[]> it = datos.iterator();
                    modelo.setColumnIdentifiers(it.next());
                    while (it.hasNext()) {
                        modelo.addRow(it.next());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_1.this, ex.getMessage());
                }
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
