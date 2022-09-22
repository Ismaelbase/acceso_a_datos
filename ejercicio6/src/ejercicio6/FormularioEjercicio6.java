package ejercicio6;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormularioEjercicio6 extends JFrame {

    private JButton nuevo, guardar, cargar, modificar, backupJSON, buscarJugador, buscarDefensa, ordenar, backup;
    private JLabel label_goles, label_nombre, label_posicion, label_club;
    private JTextField text_goles, text_nombre, text_posicion, text_club;
    private JLabel label_orden, label_criterio, label_ant, label_nuevo;
    private JTextField text_ant, text_nuevo;
    private JFileChooser jf;
    private AccesoDatos ad;
    private JRadioButton por_goles, por_nombre;
    private JRadioButton ascendente, descendente;
    private ButtonGroup criterio, orden;
    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioEjercicio6() {
        super("Liga");

        //Configuracion de la tabla
        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        nuevo = new JButton("Nuevo jugador");
        guardar = new JButton("Guardar en fichero");
        cargar = new JButton("Cargar de fichero");
        modificar = new JButton("Modificar nombre");
        ordenar = new JButton("Ordenar jugadores");
        buscarJugador = new JButton("Buscar por nombre");
        buscarDefensa = new JButton("Buscar por defensas goleadores");
        backupJSON = new JButton("5 mejores en JSON");
        backup = new JButton("5 mejores en XML");

        label_goles = new JLabel("Goles");
        label_nombre = new JLabel("Nombre");
        label_posicion = new JLabel("Posicion");
        label_club = new JLabel("Club");

        text_goles = new JTextField(20);
        text_nombre = new JTextField(20);
        text_posicion = new JTextField(20);
        text_club = new JTextField(20);

        //Para los dialogos modales
        label_ant = new JLabel("Nombre anterior");
        label_nuevo = new JLabel("Nombre nuevo");
        label_criterio = new JLabel("Orden respecto");
        label_orden = new JLabel("Tipo de orden");

        text_ant = new JTextField(20);
        text_nuevo = new JTextField(20);

        criterio = new ButtonGroup();
        orden = new ButtonGroup();

        por_goles = new JRadioButton("goles");
        por_goles.setActionCommand(Futbolista.GOLES + "");
        por_nombre = new JRadioButton("nombre", true);
        por_nombre.setActionCommand(Futbolista.NOMBRE + "");
        criterio.add(por_goles);
        criterio.add(por_nombre);

        ascendente = new JRadioButton("ascendente", true);
        ascendente.setActionCommand("ASC");
        descendente = new JRadioButton("descendente");
        descendente.setActionCommand("DESC");

        orden.add(ascendente);
        orden.add(descendente);
        //=======================

       
        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(4, 2));

        panel_datos.add(label_nombre);
        panel_datos.add(text_nombre);

        panel_datos.add(label_posicion);
        panel_datos.add(text_posicion);

        panel_datos.add(label_goles);
        panel_datos.add(text_goles);

        panel_datos.add(label_club);
        panel_datos.add(text_club);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(2, 5));
        panel_botones.add(cargar);
        panel_botones.add(buscarJugador);
        panel_botones.add(buscarDefensa);
        panel_botones.add(nuevo);
        panel_botones.add(ordenar);
        panel_botones.add(modificar);
        panel_botones.add(guardar);
        panel_botones.add(backup);
        panel_botones.add(backupJSON);

        jf = new JFileChooser();
        ad = new AccesoDatos();

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

        cargar.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showOpenDialog(FormularioEjercicio6.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();

                try {
                    ad.cargarJugadores(fichero.getPath());
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] jugador : ad.visualizarJugadores()) {
                        modelo.addRow(jugador);
                    }
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, " Datos cargados con exito", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, ex.getMessage(), "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, "No has seleccionado un archivo correcto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buscarJugador.addActionListener((e) -> {
            if (text_nombre.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Faltan datos");
            } else {
                try {
                    modelo.setRowCount(0);
                    modelo.addRow(ad.buscarJugador(text_nombre.getText().trim()));
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    text_nombre.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, ex.getMessage());
                }
            }
        });

        buscarDefensa.addActionListener((e) -> {
            modelo.setRowCount(0);

            for (String[] libro : ad.defensasGoleadores()) {
                modelo.addRow(libro);
            }
            modelo.setColumnIdentifiers(ad.getColumnas());

        });

        nuevo.addActionListener((e) -> {
            int goles;

            if (text_goles.getText().equals("") || text_nombre.getText().equals("")
                    || text_posicion.getText().equals("") || text_club.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Faltan datos");
            } else {
                try {
                    goles = Integer.parseInt(text_goles.getText());
                    ad.añadirJugador(text_nombre.getText(), text_posicion.getText(), text_club.getText(), goles);
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Datos introducidos con éxito");
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] fut : ad.visualizarJugadores()) {
                        modelo.addRow(fut);
                    }
                    text_nombre.setText("");
                    text_goles.setText("");
                    text_posicion.setText("");
                    text_club.setText("");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Se esperaban números correctos");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, ex.getMessage());
                }
            }
        });

        ordenar.addActionListener((e) -> {

            Object[] options = new Object[]{label_criterio, por_nombre, por_goles, label_orden, ascendente, descendente, "Aceptar"};
            int opcion = JOptionPane.showOptionDialog(null, "Elija las opciones", "Ejercicio 6",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[1]);
            if (opcion != -1) {
                try {
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] fut : ad.ordenarJugadores(Integer.parseInt(criterio.getSelection().getActionCommand()), orden.getSelection().getActionCommand())) {
                        modelo.addRow(fut);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Pulse aceptar por favor");
            }

        });

        modificar.addActionListener((e) -> {

            Object[] options = new Object[]{label_ant, text_ant, label_nuevo, text_nuevo, "Aceptar"};
            int opcion = JOptionPane.showOptionDialog(null, "Rellene los datos necesarios", "Ejercicio 6",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[1]);
            if (opcion == -1 || text_ant.getText().equals("") || text_nuevo.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Rellene los datos");
            } else {
                try {
                    ad.modificarJugador(text_ant.getText(), text_nuevo.getText());
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Jugador modificado con éxito");
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] fut : ad.visualizarJugadores()) {
                        modelo.addRow(fut);
                    }

                    text_ant.setText("");
                    text_nuevo.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, ex.getMessage());
                }
            }
        });

        guardar.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioEjercicio6.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.guardarJugadores(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Error de acceso a datos", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backupJSON.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioEjercicio6.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.backupJugadoresJSON(fichero.getPath());
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Error al guardar", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backup.addActionListener((e) -> {
            int codigo;
            File fichero;
            codigo = jf.showSaveDialog(FormularioEjercicio6.this);
            if (codigo == 0) {
                fichero = jf.getSelectedFile();
                try {
                    ad.backupJugadoresXML(fichero.getPath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio6.this, "Error de acceso a datos", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio6.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);

        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
