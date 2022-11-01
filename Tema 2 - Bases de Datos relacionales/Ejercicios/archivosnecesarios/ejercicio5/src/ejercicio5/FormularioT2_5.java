package ejercicio5;

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

public class FormularioT2_5 extends JFrame {

    private JButton masGanacias, nueva_venta, ultima, modificar, mayorVenta, masVendido, sinVentas, sinComprar, resumen, info;
    private JLabel label_producto, label_cliente, label_unidades, label_nuevo_nombre_producto;

    private JTextField text_producto, text_cliente, text_unidades, text_nuevo_nombre_producto;

    private JTable tabla;
    private DefaultTableModel modelo;
    private JFileChooser jf;
    private AccesoDatos ad;

    public FormularioT2_5() {
        super("Tienda virtual");

        //Configuracion de la tabla
        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        nueva_venta = new JButton("Nueva venta");
        ultima = new JButton("Venta más reciente");
        modificar = new JButton("Modificar nombre producto");
        mayorVenta = new JButton("Mayor venta individual");
        masVendido = new JButton("Producto que más vende");
        sinVentas = new JButton("Productos sin ventas");
        sinComprar = new JButton("Clientes que no compran");
        resumen = new JButton("Resumen ventas y recaudación");
        info = new JButton("Compras de clientes");
        masGanacias = new JButton("Año mas fructifero");

        label_producto = new JLabel("Nombre del producto");
        label_cliente = new JLabel("Nombre del cliente");
        label_unidades = new JLabel("Unidades vendidas");

        label_nuevo_nombre_producto = new JLabel("Nuevo nombre para el producto");

        text_producto = new JTextField(20);
        text_cliente = new JTextField(20);
        text_unidades = new JTextField(20);
        text_nuevo_nombre_producto = new JTextField(20);

       
        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(4, 2));

        panel_datos.add(label_producto);
        panel_datos.add(text_producto);
        panel_datos.add(label_cliente);
        panel_datos.add(text_cliente);
        panel_datos.add(label_unidades);
        panel_datos.add(text_unidades);

        
        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(3, 3));
        panel_botones.add(nueva_venta);
        panel_botones.add(ultima);
        panel_botones.add(masVendido);
        panel_botones.add(sinVentas);
        panel_botones.add(sinComprar);
        jf = new JFileChooser();
        ad = new AccesoDatos("localhost", "ejercicio5", "root", "", "com.mysql.jdbc.Driver");

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

        ultima.addActionListener((e)->{
                try {
                    JOptionPane.showMessageDialog(FormularioT2_5.this,"Venta mas reciente\n" + ad.ultimaVenta());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_5.this, ex.getMessage());
                }
            });

       
        masVendido.addActionListener((e)-> {
                try {
                    JOptionPane.showMessageDialog(FormularioT2_5.this,"Producto más vendido\n" + ad.masVendido());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_5.this, ex.getMessage());
                }
            });

        sinVentas.addActionListener((e)-> {
                try {
                    JOptionPane.showMessageDialog(FormularioT2_5.this,"Productos no vendido\n" + ad.sinVentas());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_5.this, ex.getMessage());
                }
            });

        sinComprar.addActionListener((e)->{
                try {
                    JOptionPane.showMessageDialog(FormularioT2_5.this,"Clientes que no compran\n" + ad.sinCompras());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioT2_5.this, ex.getMessage());
                }
            });

       
          
         

        nueva_venta.addActionListener((e)-> {
                int unidades;
                if (text_producto.getText().equals("") || text_cliente.getText().equals("")
                        || text_unidades.getText().equals("")) {
                    JOptionPane.showMessageDialog(FormularioT2_5.this, "Faltan datos");
                } else {
                    try {
                        unidades = Integer.parseInt(text_unidades.getText());
                        ad.añadirVenta(text_producto.getText(), text_cliente.getText(), unidades);
                        JOptionPane.showMessageDialog(FormularioT2_5.this, "Venta creada con éxito");
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(FormularioT2_5.this, "Se esperaban números");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormularioT2_5.this, ex.getMessage());
                    }
                }
            });

      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);

        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
