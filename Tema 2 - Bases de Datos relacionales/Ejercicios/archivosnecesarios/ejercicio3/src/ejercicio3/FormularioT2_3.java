package ejercicio3;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormularioT2_3 extends JFrame {

    private JButton verEquipos, nuevo_equipo, verJugadores, nuevo_jugador, pichichi, resumen, maximo;
    private JLabel label_club, label_fundacion, label_titulos;
    private JLabel label_nombre, label_posicion, label_goles, label_equipo;
    private JTextField text_club, text_fundacion, text_titulos;
    private JTextField text_nombre, text_posicion, text_goles, text_equipo;

    private JFileChooser jf;
    private AccesoDatos ad;

    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioT2_3() {
        super("Liga de futbol");
        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        verEquipos = new JButton("Ver equipos");
        nuevo_equipo = new JButton("Crear equipo");
        verJugadores = new JButton("Ver jugadores equipo");
        nuevo_jugador = new JButton("Crear jugador");
        pichichi = new JButton("Pichichi");
        resumen = new JButton("Resumen equipos");
        maximo = new JButton("Pichichi equipos");

        label_club = new JLabel("Nombre club");
        label_fundacion = new JLabel("Fecha fundacion");
        label_titulos = new JLabel("Titulos ganados");

        label_nombre = new JLabel("Nombre jugador");
        label_posicion = new JLabel("Posicion en la que juega");
        label_goles = new JLabel("Goles marcados");
        label_equipo = new JLabel("Club por el que ficha");

        text_club = new JTextField(20);
        text_fundacion = new JTextField(20);
        text_titulos = new JTextField(20);

        text_nombre = new JTextField(20);
        text_posicion = new JTextField(20);
        text_goles = new JTextField(20);
        text_equipo = new JTextField(20);

        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(7, 2));

        panel_datos.add(label_club);
        panel_datos.add(text_club);
        panel_datos.add(label_fundacion);
        panel_datos.add(text_fundacion);
        panel_datos.add(label_titulos);
        panel_datos.add(text_titulos);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(3, 3));
        panel_botones.add(verEquipos);
        panel_botones.add(nuevo_equipo);
        panel_botones.add(verJugadores);
        panel_botones.add(pichichi);
        panel_botones.add(resumen);

        jf = new JFileChooser();
        ad = new AccesoDatos("localhost", "ejercicio3", "root", "", "com.mysql.cj.jdbc.Driver");

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

        verEquipos.addActionListener((e) -> {
            try {
                ArrayList<String[]> datos = ad.verEquipos();
                Iterator<String[]> it = datos.iterator();
                modelo.setRowCount(0);
                modelo.setColumnIdentifiers(it.next());
                while (it.hasNext()) {
                    modelo.addRow(it.next());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FormularioT2_3.this, ex.getMessage());
            }
        });

        verJugadores.addActionListener((e) -> {
            if (text_club.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_3.this, "Faltan datos");
            } else {
                try {
                    ArrayList<String[]> datos = ad.verJugadores(text_club.getText());
                    Iterator<String[]> it = datos.iterator();
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(it.next());
                    while (it.hasNext()) {
                        modelo.addRow(it.next());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_3.this, ex.getMessage());
                }
            }
        });

        nuevo_equipo.addActionListener((e) -> {
            int titulos;
            if (text_club.getText().equals("") || text_fundacion.getText().equals("")
                    || text_titulos.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_3.this, "Faltan datos");
            } else {
                try {
                    titulos = Integer.parseInt(text_titulos.getText());
                    ad.crearEquipo(text_club.getText(), text_fundacion.getText(), titulos);
                    JOptionPane.showMessageDialog(FormularioT2_3.this, "Club creado con éxito");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(FormularioT2_3.this, "Se esperaban números");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_3.this, ex.getMessage());
                }
            }
        });
//        
//
        resumen.addActionListener((e) -> {
            try {
                String datos = ad.verResumen();
                JOptionPane.showMessageDialog(FormularioT2_3.this, "RESUMEN:\n" + datos);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FormularioT2_3.this, ex.getMessage());
            }
        });

        pichichi.addActionListener((e) -> {
            try {
                String datos = ad.verPichichi();
                JOptionPane.showMessageDialog(FormularioT2_3.this, "RESUMEN:\n" + datos);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FormularioT2_3.this, ex.getMessage());
            }
        });
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(600, 500);

        this.setResizable(false);

        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

}
