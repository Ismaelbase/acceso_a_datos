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
    private JTextField campo1;
    private JLabel label1;

    public Ventana(String titulo, AccesoDatos ad) {
        this.ad = ad;
        label1 = new JLabel("Nombre del fichero");

        campo1 = new JTextField(15);

        principal = new JFrame(titulo);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.setLocation(400, 10);
        principal.setSize(600, 700);
        principal.setLayout(new FlowLayout());
        principal.setResizable(false);

        botones = new JButton[4];
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton("Ejercicio " + (i + 1));
            principal.add(botones[i]);
            botones[i].addActionListener((e) -> {
                int ok;
                try {
                    switch (e.getActionCommand()) {
                        case "Ejercicio 1":
                            ok = entradaDatos();
                            if (ok != -1 && !campo1.getText().equals("")) {
                                area.setText("Suma de numeros: " + ad.ejercicio1(campo1.getText()));
                            } else {
                                JOptionPane.showMessageDialog(principal, "Escribe el fichero con los datos y pulsa aceptar");
                            }
                            campo1.setText("");
                            
                            break;
                        case "Ejercicio 2":
                            ok = entradaDatos();
                            if (ok != -1 && !campo1.getText().equals("")) {
                                area.setText("Empleados que han vendido 1000 o más:\n" + ad.ejercicio2(campo1.getText()));
                            } else {
                                JOptionPane.showMessageDialog(principal, "Escribe el fichero con los datos y pulsa aceptar");
                            }
                            campo1.setText("");
                            break;

                        case "Ejercicio 3":
                            ok = entradaDatos();
                            if (ok != -1 && !campo1.getText().equals("")) {
                                ad.ejercicio3(campo1.getText());
                            } else {
                                JOptionPane.showMessageDialog(principal, "Escribe el fichero con los datos y pulsa aceptar");
                            }
                            campo1.setText("");
                            break;
                        case "Ejercicio 4":
                            ok = entradaDatos();
                            if (ok != -1 && !campo1.getText().equals("")) {
                                ad.ejercicio4(campo1.getText());
                                area.setText("Fichero de provincias guardado con exito\n");
                            } else {
                                JOptionPane.showMessageDialog(principal, "Escribe el fichero con los datos y pulsa aceptar");
                            }
                            campo1.setText("");
                            break;
                    }
                } catch (FileNotFoundException fnf) {
                    JOptionPane.showMessageDialog(principal, "El fichero no se encuentra");
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(principal, "Error de lecturqa");
                }
            });
        }

        area = new JTextArea("");
        area.setColumns(50);
        area.setRows(40);
        area.setEditable(false);

        principal.add(area);
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);

    }

    public int entradaDatos() {

        Object[] options = new Object[]{label1, campo1, "Aceptar"};
        int opcion = JOptionPane.showOptionDialog(null, "Rellene los datos necesarios", "Formulario",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[1]);

        return opcion;
    }
}
