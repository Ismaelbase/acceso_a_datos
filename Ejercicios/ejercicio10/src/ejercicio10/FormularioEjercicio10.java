package ejercicio10;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class FormularioEjercicio10 extends JFrame {

    private JButton nuevo, guardar, cargar, backup, verPagadas, morosos;
    private JLabel label_nombre, label_precio, label_pagada, label_año;
    private JTextField text_nombre, text_precio, text_año;
    private JCheckBox check_pagada;

    private JFileChooser jf;
    private AccesoDatos ad;
    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioEjercicio10() {
        super("Academia");

        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        nuevo = new JButton("Nueva matricula");
        guardar = new JButton("Guardar en fichero");
        cargar = new JButton("Cargar de fichero");
        backup = new JButton("Backup en texto");
        verPagadas = new JButton("Ver suma pagadas");
        
        morosos = new JButton("Top 3 morosos");

        label_nombre = new JLabel("Nombre");
        label_precio = new JLabel("Capital");
        label_pagada = new JLabel("Pagado");
        label_año = new JLabel("Año de matricula");

        text_nombre = new JTextField(20);
        text_precio = new JTextField(20);
        check_pagada = new JCheckBox();
        text_año = new JTextField(20);

        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(4, 2));

        panel_datos.add(label_nombre);
        panel_datos.add(text_nombre);

        panel_datos.add(label_año);
        panel_datos.add(text_año);

        panel_datos.add(label_precio);
        panel_datos.add(text_precio);

        panel_datos.add(label_pagada);
        panel_datos.add(check_pagada);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(2, 3));
        panel_botones.add(cargar);
        panel_botones.add(verPagadas);
        panel_botones.add(nuevo);
        panel_botones.add(guardar);
        panel_botones.add(backup);
        panel_botones.add(morosos);

        jf = new JFileChooser();
        ad = new AccesoDatos();

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

        cargar.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showOpenDialog(FormularioEjercicio10.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.cargarMatriculas(fichero.getPath());
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(FormularioEjercicio10.this, " Falla al acceder a ficheros", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(FormularioEjercicio10.this, " Datos cargados con exito", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio10.this, "No has seleccionado un archivo correcto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        morosos.addActionListener((e) -> {
            try {
                modelo.setRowCount(0);
                
                for (String[] mat : ad.rankingMorosos()) {
                    modelo.addRow(mat);
                }
                modelo.setColumnIdentifiers(ad.getColumnas());
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FormularioEjercicio10.this, ex.getMessage());
            }
        });

        verPagadas.addActionListener((e) -> {
            JOptionPane.showMessageDialog(FormularioEjercicio10.this, "Suma de las matriculas pagadas:" + ad.sumarPagadas() + " €", "Mostrar datos", JOptionPane.INFORMATION_MESSAGE);
        });

        backup.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioEjercicio10.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.matriculasResumen(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioEjercicio10.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(FormularioEjercicio10.this, " Falla al acceder a ficheros", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio10.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        nuevo.addActionListener((e) -> {
            int año;
            boolean pagada;
            double precio;

            if (text_nombre.getText().equals("") || text_año.getText().equals("")
                    || text_precio.getText().equals("") || text_año.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioEjercicio10.this, "Faltan datos");
            } else {
                try {
                    año = Integer.parseInt(text_año.getText());
                    precio = Double.parseDouble(text_precio.getText());
                    pagada = check_pagada.isSelected();
                    ad.nuevaMatricula(text_nombre.getText(), año, precio, pagada);
                    JOptionPane.showMessageDialog(FormularioEjercicio10.this, "Datos introducidos con éxito");
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] mat : ad.visualizarMatriculas()) {
                        modelo.addRow(mat);
                    }
                    text_nombre.setText("");
                    text_precio.setText("");
                    text_año.setText("");
                    check_pagada.setSelected(false);

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(FormularioEjercicio10.this, "Se esperaban números correctos");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio10.this, ex.getMessage());
                }
            }
        });

        guardar.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioEjercicio10.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.guardarMatriculas(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioEjercicio10.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(FormularioEjercicio10.this, " Falla al acceder a ficheros", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio10.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
