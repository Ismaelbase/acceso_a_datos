package ejercicio9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class FormularioEjercicio9 extends JFrame {

    private JButton nuevo, guardar, cargar, backup, visualizar;
    private JLabel label_nombre, label_capital, label_habitantes, label_año;
    private JTextField text_nombre, text_capital, text_habitantes, text_año;

    private JTextArea area;
    private JFileChooser jf;
    private AccesoDatos ad;
    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioEjercicio9() {
        super("Paises");

        //Configuracion de la tabla
        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        nuevo = new JButton("Nuevo pais");
        guardar = new JButton("Guardar en fichero");
        cargar = new JButton("Cargar de fichero");
        backup = new JButton("Backup en texto");
        visualizar = new JButton("Ver todos los paises");

        label_nombre = new JLabel("Nombre");
        label_capital = new JLabel("Capital");
        label_habitantes = new JLabel("Nº habitantes");
        label_año = new JLabel("Año de fundacion");

        text_nombre = new JTextField(20);
        text_capital = new JTextField(20);
        text_habitantes = new JTextField(20);
        text_año = new JTextField(20);

        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(4, 2));

        panel_datos.add(label_nombre);
        panel_datos.add(text_nombre);

        panel_datos.add(label_capital);
        panel_datos.add(text_capital);

        panel_datos.add(label_habitantes);
        panel_datos.add(text_habitantes);

        panel_datos.add(label_año);
        panel_datos.add(text_año);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(2, 3));
        panel_botones.add(cargar);
        panel_botones.add(nuevo);
        panel_botones.add(guardar);
        panel_botones.add(backup);

        jf = new JFileChooser();
        ad = new AccesoDatos();

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

        cargar.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showOpenDialog(FormularioEjercicio9.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.cargarPaises(fichero.getPath());
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] pais : ad.mostrarTodo()) {
                        modelo.addRow(pais);
                    }
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, " Datos cargados con exito", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException fnf) {
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, "No has seleccionado un archivo correcto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, "Error al leer datos", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio9.this, "No has seleccionado un archivo correcto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backup.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioEjercicio9.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.backupTexto(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio9.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        nuevo.addActionListener((e) -> {
            int año, habitantes;
            double precio;

            if (text_nombre.getText().equals("") || text_capital.getText().equals("")
                    || text_habitantes.getText().equals("") || text_año.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioEjercicio9.this, "Faltan datos");
            } else {
                try {
                    año = Integer.parseInt(text_año.getText());
                    habitantes = Integer.parseInt(text_habitantes.getText());
                    ad.añadirPais(text_nombre.getText(), text_capital.getText(), habitantes, año);
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, "Datos introducidos con éxito");
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] pais : ad.mostrarTodo()) {
                        modelo.addRow(pais);
                    }
                    text_nombre.setText("");
                    text_capital.setText("");
                    text_habitantes.setText("");
                    text_año.setText("");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, "Se esperaban números correctos");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, ex.getMessage());
                }
            }
        });

        guardar.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioEjercicio9.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.guardarPaises(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(FormularioEjercicio9.this, io.getMessage(), "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio9.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
