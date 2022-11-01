package ejercicio4;

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

public class FormularioT2_4 extends JFrame {

    private JButton crearEvento, apuntarEvento, cancelarEvento, eventosSocio;
    private JButton mesAjetreado, sociosEvento, eventosRestantes, sinSocios, eventomulti, ultimoEvento;
    private JLabel label_socio, label_evento, label_fecha;
    private JTextField text_socio, text_evento, text_fecha;

    private JFileChooser jf;
    private AccesoDatos ad;
    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioT2_4() {
        super("Club");
        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        crearEvento = new JButton("Crear evento");
        apuntarEvento = new JButton("Apuntar socio a evento");
        cancelarEvento = new JButton("Cancelar evento");
        eventosSocio = new JButton("Eventos de un socio");
        sociosEvento = new JButton("Socios apuntados a un evento");
        eventosRestantes = new JButton("Eventos hasta fin de año");
        eventomulti = new JButton("Evento popular");
        sinSocios = new JButton("Eventos vacios");
        ultimoEvento = new JButton("Ultimo evento programado");
        mesAjetreado = new JButton("Mes con más eventos del año");
        label_socio = new JLabel("Nombre socio");
        label_evento = new JLabel("Nombre del evento");
        label_fecha = new JLabel("Fecha evento");

        text_socio = new JTextField(20);
        text_evento = new JTextField(20);
        text_fecha = new JTextField(20);

        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(4, 2));

        panel_datos.add(label_socio);
        panel_datos.add(text_socio);

        panel_datos.add(label_evento);
        panel_datos.add(text_evento);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(3, 3));
        panel_botones.add(apuntarEvento);
        panel_botones.add(eventosSocio);
        panel_botones.add(sociosEvento);
        panel_botones.add(eventomulti);
        panel_botones.add(sinSocios);
        
        jf = new JFileChooser();
        ad = new AccesoDatos("localhost", "ejercicio4", "root", "", "com.mysql.cj.jdbc.Driver");

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

       
        apuntarEvento.addActionListener((e) -> {

            if (text_evento.getText().equals("") || text_socio.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_4.this, "Faltan datos");
            } else {
                try {
                    ad.apuntarseEvento(text_socio.getText(), text_evento.getText());
                    JOptionPane.showMessageDialog(FormularioT2_4.this, "Inscripcion realizada con éxito");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_4.this, ex.getMessage());
                }
            }
        });

        

        eventosSocio.addActionListener((e) -> {
            if (text_socio.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_4.this, "Faltan datos");
            } else {
                try {
                    ArrayList<String[]> datos = ad.eventoSocio(text_socio.getText());
                    Iterator<String[]> it = datos.iterator();
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(it.next());
                    while (it.hasNext()) {
                        modelo.addRow(it.next());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_4.this, ex.getMessage());
                }
            }
        });

        sociosEvento.addActionListener((e) -> {
            if (text_evento.getText().equals("")) {
                JOptionPane.showMessageDialog(FormularioT2_4.this, "Faltan datos");
            } else {
                try {
                    ArrayList<String[]> datos = ad.eventoSocio(text_evento.getText());
                    Iterator<String[]> it = datos.iterator();
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(it.next());
                    while (it.hasNext()) {
                        modelo.addRow(it.next());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_4.this, ex.getMessage());
                }
            }
        });

        eventomulti.addActionListener((e) -> {
            try {
                JOptionPane.showMessageDialog(FormularioT2_4.this, "Evento con mas socios:\n" + ad.eventoMultitudinario());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FormularioT2_4.this, ex.getMessage());
            }
        });

      
       
       

        sinSocios.addActionListener((e)->{
                try {
                    JOptionPane.showMessageDialog(FormularioT2_4.this,"Evento sin socios:\n" + ad.sinSocios());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_4.this, ex.getMessage());
                }
            });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(600, 500);

        this.setResizable(false);

        this.setLocationRelativeTo(null);

        this.setVisible(true);

    }

}
