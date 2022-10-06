package relacion1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ventana {

    private JFrame principal;
    private JButton[] botones;
    private JTextArea area;
    private AccesoDatos ad;
    private JTextField campo1, campo2;
    private JLabel label1, label2;

    public Ventana(String titulo, AccesoDatos ad) {
        this.ad = ad;
        label1 = new JLabel("Nombre del fichero de entrada");
        campo1 = new JTextField(15);

        label2 = new JLabel("Nombre del fichero de salida");
        campo2 = new JTextField(15);

        principal = new JFrame(titulo);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.setLocation(400, 10);
        principal.setSize(600, 700);
        principal.setLayout(new FlowLayout());
        principal.setResizable(false);

        botones = new JButton[2];
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton("Ejercicio " + (i + 7));
            principal.add(botones[i]);
            botones[i].addActionListener((e) -> {
                int ok;
                try {
                    switch (e.getActionCommand()) {
                        case "Ejercicio 7":
                            ok = entradaDatos();
                            if (ok != -1 && ok != -1 && !campo1.getText().equals("") && !campo2.getText().equals("")) {
                                ad.crearBinario(campo1.getText(), campo2.getText());
                                area.setText("Proceso completado");
                            } else {
                                JOptionPane.showMessageDialog(principal, "Faltan datos");
                            }
                            area.setText("Proceso completado");
                            break;
                        case "Ejercicio 8":
                            ok = entradaDatos();
                            if (ok != -1 && ok != -1 && !campo1.getText().equals("") && !campo2.getText().equals("")) {
                                ad.crearTexto(campo1.getText(), campo2.getText());
                                area.setText("Proceso completado");
                            } else {
                                JOptionPane.showMessageDialog(principal, "Faltan datos");
                            }
                            break;
                        
                    }
                } catch (ClassNotFoundException cnf) {
                    JOptionPane.showMessageDialog(principal, "Fallo al leer datos no corresponden con lo esperado", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException fnf) {
                    JOptionPane.showMessageDialog(principal, "No has seleccionado un archivo correcto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(principal, "Error de lectura escritura", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
                }

            });
        }

        area = new JTextArea("");
        area.setColumns(50);
        area.setRows(40);
        area.setEditable(false);

        principal.add(area);
        principal.setVisible(true);
    }

    public int entradaDatos() {

        Object[] options = new Object[]{label1, campo1, label2, campo2, "Aceptar"};
        int opcion = JOptionPane.showOptionDialog(null, "Rellene los datos necesarios", "Formulario",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[1]);

        return opcion;
    }
}
