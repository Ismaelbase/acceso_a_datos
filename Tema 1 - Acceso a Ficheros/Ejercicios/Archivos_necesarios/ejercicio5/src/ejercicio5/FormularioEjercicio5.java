package ejercicio5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
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

public class FormularioEjercicio5 extends JFrame {

    private JButton nuevo, guardar, cargar, modificar, buscarTitulo, buscarAutor, ordenar;
    private JLabel label_precio, label_titulo, label_autor, label_ejemplares;
    private JTextField text_precio, text_titulo, text_autor, text_ejemplares;
    private JLabel label_orden, label_criterio, label_ant, label_nuevo;
    private JTextField text_ant, text_nuevo;
    private JTextArea area;
    private JFileChooser jf;
    private AccesoDatos ad;
    private JRadioButton por_precio, por_ejemplares;
    private JRadioButton ascendente, descendente;
    private ButtonGroup criterio, orden;
    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioEjercicio5() {
        super("Biblioteca");

        //Configuracion de la tabla
        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();

        ad = new AccesoDatos();

        nuevo = new JButton("Nuevo libro");
        guardar = new JButton("Guardar en fichero");
        cargar = new JButton("Cargar de fichero");
        modificar = new JButton("Modificar titulo");
        ordenar = new JButton("Ordenar libros");
        buscarTitulo = new JButton("Buscar por titulo");
        buscarAutor = new JButton("Buscar por autor");

        label_precio = new JLabel("Precio");
        label_titulo = new JLabel("Titulo");
        label_autor = new JLabel("Autor");
        label_ejemplares = new JLabel("Número de ejemplares");

        text_precio = new JTextField(20);
        text_titulo = new JTextField(20);
        text_autor = new JTextField(20);
        text_ejemplares = new JTextField(20);

        //Para los dialogos modales
        label_ant = new JLabel("Titulo anterior");
        label_nuevo = new JLabel("Titulo nuevo");
        label_criterio = new JLabel("Orden respecto");
        label_orden = new JLabel("Tipo de orden");

        text_ant = new JTextField(20);
        text_nuevo = new JTextField(20);

        criterio = new ButtonGroup();
        orden = new ButtonGroup();

        por_ejemplares = new JRadioButton("ejemplares");
        por_ejemplares.setActionCommand(Libro.EJEMPLARES + "");
        por_precio = new JRadioButton("precio", true);
        por_precio.setActionCommand(Libro.PRECIO + "");
        criterio.add(por_ejemplares);
        criterio.add(por_precio);

        ascendente = new JRadioButton("ascendente", true);
        ascendente.setActionCommand("ASC");
        descendente = new JRadioButton("descendente");
        descendente.setActionCommand("DESC");

        orden.add(ascendente);
        orden.add(descendente);
        //=======================

        area = new JTextArea(100, 20);
        area.setEditable(false);
        JScrollPane js = new JScrollPane(tabla);

        this.setLayout(new BorderLayout());

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(new GridLayout(6, 2));

        panel_datos.add(label_titulo);
        panel_datos.add(text_titulo);

        panel_datos.add(label_autor);
        panel_datos.add(text_autor);

        panel_datos.add(label_precio);
        panel_datos.add(text_precio);

        panel_datos.add(label_ejemplares);
        panel_datos.add(text_ejemplares);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(2, 4));
        panel_botones.add(cargar);

        panel_botones.add(buscarTitulo);
        panel_botones.add(buscarAutor);
        panel_botones.add(nuevo);
        panel_botones.add(ordenar);
        panel_botones.add(modificar);
        panel_botones.add(guardar);

        jf = new JFileChooser();
        jf.setCurrentDirectory(new File("."));

        this.add(panel_botones, BorderLayout.SOUTH);
        this.add(panel_datos, BorderLayout.NORTH);
        this.add(js, BorderLayout.CENTER);

        cargar.addActionListener((e) -> {
            int codigo;
            File fichero;
            jf.setSelectedFile(new File(""));
            codigo = jf.showOpenDialog(FormularioEjercicio5.this);
            if (codigo == JFileChooser.APPROVE_OPTION) {
                fichero = jf.getSelectedFile();
                try {
                    ad.cargarLibros(fichero.getPath());
                    modelo.setRowCount(0);
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    for (String[] libro : ad.visualizarTodos()) {
                        modelo.addRow(libro);
                    }
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Datos introducidos con éxito");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, ex.getMessage(), "Cargar datos", JOptionPane.INFORMATION_MESSAGE);;
                }
            } else {
                JOptionPane.showMessageDialog(FormularioEjercicio5.this, "No has seleccionado un archivo correcto", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buscarTitulo.addActionListener((e) -> {

            if (text_titulo.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Faltan datos");
            } else {
                try {
                    modelo.setRowCount(0);
                    modelo.addRow(ad.buscarTitulo(text_titulo.getText().trim()));
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    text_titulo.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, ex.getMessage());
                }
            }
        });

        buscarAutor.addActionListener((e) -> {
            if (text_autor.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Faltan datos");
            } else {
                try {
                    modelo.setRowCount(0);

                    for (String[] libro : ad.buscarLibrosAutor(text_autor.getText().trim())) {
                        modelo.addRow(libro);
                    }
                    modelo.setColumnIdentifiers(ad.getColumnas());
                    text_autor.setText("");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, ex.getMessage());
                }
                
            }
        });

        nuevo.addActionListener((e) -> {
                int ejemplares;
                double precio;

                if (text_precio.getText().trim().equals("") || text_titulo.getText().trim().equals("")
                        || text_autor.getText().trim().equals("") || text_ejemplares.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Faltan datos");
                } else {
                    try {
                        ejemplares = Integer.parseInt(text_ejemplares.getText().trim());
                        precio = Double.parseDouble(text_precio.getText().trim());
                        ad.añadirLibro(text_titulo.getText().trim(), text_autor.getText().trim(), precio, ejemplares);
                        JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Datos introducidos con éxito");

                        modelo.setRowCount(0);
                        modelo.setColumnIdentifiers(ad.getColumnas());
                        for (String[] libro : ad.visualizarTodos()) {
                            modelo.addRow(libro);
                        }
                        text_titulo.setText("");
                        text_autor.setText("");
                        text_precio.setText("");
                        text_ejemplares.setText("");

                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Se esperaban números correctos");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormularioEjercicio5.this, ex.getMessage());
                    }
                }
            });

            ordenar.addActionListener((e) -> {

                Object[] options = new Object[]{label_criterio, por_precio, por_ejemplares, label_orden, ascendente, descendente, "Aceptar"};
                int opcion = JOptionPane.showOptionDialog(null, "Elija las opciones", "Ejercicio 5",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null,
                        options, options[1]);
                if (opcion != -1) {
                    try {
                        modelo.setRowCount(0);
                        modelo.setColumnIdentifiers(ad.getColumnas());
                        for (String[] libro : ad.ordenarLibros(Integer.parseInt(criterio.getSelection().getActionCommand()), orden.getSelection().getActionCommand())) {
                            modelo.addRow(libro);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormularioEjercicio5.this, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Pulse aceptar por favor");
                }

            });

            modificar.addActionListener((e) -> {

                Object[] options = new Object[]{label_ant, text_ant, label_nuevo, text_nuevo, "Aceptar"};
                int opcion = JOptionPane.showOptionDialog(null, "Rellene los datos necesarios", "Ejercicio 5",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null,
                        options, options[1]);
                if (opcion == -1 || text_ant.getText().trim().equals("") || text_nuevo.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Rellene los datos");
                } else {
                    try {
                        ad.modificarTitulo(text_ant.getText().trim(), text_nuevo.getText().trim());
                        JOptionPane.showMessageDialog(FormularioEjercicio5.this, "Título módificado con éxito");
                        modelo.setRowCount(0);
                        modelo.setColumnIdentifiers(ad.getColumnas());
                        for (String[] libro : ad.visualizarTodos()) {
                            modelo.addRow(libro);
                        }

                        text_ant.setText("");
                        text_nuevo.setText("");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormularioEjercicio5.this, ex.getMessage());
                    }
                }
            });

            guardar.addActionListener((e) -> {
                int codigo;
                File fichero;
                jf.setSelectedFile(new File(""));
                codigo = jf.showSaveDialog(FormularioEjercicio5.this);
                if (codigo == JFileChooser.APPROVE_OPTION) {
                    fichero = jf.getSelectedFile();
                    try {
                        ad.guardarLibros(fichero.getPath());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormularioEjercicio5.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, " Datos guardados con exito", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(FormularioEjercicio5.this, "No has seleccionado un archivo correcto", "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
                }

            });

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(600, 400);

            this.setResizable(false);

            this.setLocationRelativeTo(null);
            this.setVisible(true);

        }

    }
