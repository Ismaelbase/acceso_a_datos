package exament1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormularioExamen extends JFrame {

    private JButton busqueda, backup, guardar_texto, cargar_texto, guardar_binario, cargar_binario, resumen, masPopulares;
    private JLabel label_nombre;
    private JTextField text_nombre;
    private JFileChooser jf;
    private AccesoDatos ad;
    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioExamen() {
        super("Examen Tema 1");

        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        cargar_texto = new JButton("EJERCICIO 1");
        guardar_texto = new JButton("EJERCICIO 3");
        guardar_binario = new JButton("EJERCICIO 4");
        cargar_binario = new JButton("EJERCICIO 5");
        busqueda = new JButton("EJERCICIO 6");
        resumen = new JButton("EJERCICIO 7");
        backup = new JButton("EJERCICIO 8");
        masPopulares = new JButton("EJERCICIO 9");

        label_nombre = new JLabel("Nombre del curso a buscar");

        text_nombre = new JTextField(20);

        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(4, 2));

        panel_datos.add(label_nombre);
        panel_datos.add(text_nombre);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(2, 5));
        panel_botones.add(cargar_texto);
        panel_botones.add(guardar_texto);
        panel_botones.add(guardar_binario);
        panel_botones.add(cargar_binario);
        panel_botones.add(busqueda);
        panel_botones.add(resumen);
        panel_botones.add(backup);
        panel_botones.add(masPopulares);

        jf = new JFileChooser();
        jf.setCurrentDirectory(new File("."));
        ad = new AccesoDatos();

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

        cargar_texto.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showOpenDialog(FormularioExamen.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.cargarTexto(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioExamen.this, " Datos cargados con exito", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] curso : ad.visualizarCursos()) {
                        modelo.addRow(curso);
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "No se encuentra el fichero", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error al cargar fichero de texto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error inesperado", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioExamen.this, "No has seleccionado un archivo correcto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        guardar_texto.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioExamen.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.guardarTexto(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioExamen.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error al guardar fichero de texto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error inesperado", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioExamen.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        guardar_binario.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioExamen.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.guardarBinario(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioExamen.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error al guardar binario", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error inesperado", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioExamen.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cargar_binario.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showOpenDialog(FormularioExamen.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.cargarBinario(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioExamen.this, " Datos cargados con exito", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] curso : ad.visualizarCursos()) {
                        modelo.addRow(curso);
                    }

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "No se encuentra el fichero", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error al cargar binario", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error al cargar binario", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error inesperado", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(FormularioExamen.this, "No has seleccionado un archivo correcto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backup.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioExamen.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.backupXML(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioExamen.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error al guardar binario", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, "Error inesperado", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(FormularioExamen.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        busqueda.addActionListener((e) -> {

            if (text_nombre.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(FormularioExamen.this, "Faltan datos");
            } else {
                try {
                    modelo.setRowCount(0);
                    for (String[] libro : ad.busqueda(text_nombre.getText())) {
                        modelo.addRow(libro);
                    }
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    text_nombre.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioExamen.this, ex.getMessage());
                }
            }
        });

        resumen.addActionListener((e) -> {
            JOptionPane.showMessageDialog(FormularioExamen.this, ad.resumenAlumnos());
        });

        masPopulares.addActionListener((e) -> {
            modelo.setRowCount(0);
            modelo.setColumnIdentifiers(ad.getColumnas());
            for (String[] curso : ad.masPopulares()) {
                modelo.addRow(curso);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
