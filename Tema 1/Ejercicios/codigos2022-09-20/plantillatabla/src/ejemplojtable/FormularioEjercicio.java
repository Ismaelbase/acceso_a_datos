package ejemplojtable;

import cervezas.Cerveceria;
import cervezas.Cerveza;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FormularioEjercicio extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public FormularioEjercicio() {
        super("Ejemplo tabla");
        
        Cerveceria la_cerveceria = new Cerveceria("Cervezas EAG");

        //CERVEZAS DE PRUEBA
        la_cerveceria.añadirCerveza("Alhambra 1925", 200, true, 'r', 2, "Cervezas Alhambra S.A");
        la_cerveceria.añadirCerveza("Guinnes West Indies Porter", 50, true, 'n', 5, "Diageo S.A");
        la_cerveceria.añadirCerveza("Alhambra Especial", 150, false, 'r', 2.5, "Cervezas Alhambra S.A");
        la_cerveceria.añadirCerveza("Heineken", 0, false, 'r', 4, "Heineken International S.L.");
        la_cerveceria.añadirCerveza("Guinnes Original", 47, true, 'n', 5.2, "Diageo S.A");
        la_cerveceria.añadirCerveza("Alhambra Red Ale", 200, true, 'R', 3.5, "Cervezas Alhambra S.A");
        la_cerveceria.añadirCerveza("La Cibeles Castana", 0,true, 't', 6, "Cibeles S.A");
        la_cerveceria.añadirCerveza("Ayinger Urweisse", 71, true, 'R', 5.3, "Ayinger S.A");
        la_cerveceria.añadirCerveza("Taste of 1 DAM EAG",10, true, 't', 5.8, "EAG S.L.");
        
        //Configuracion de la tabla
        tabla = new JTable();
        tabla.setEnabled(false);
        modelo = (DefaultTableModel) tabla.getModel();
       
//        String[] cabecera={"nombre","edad","ciudad"};
//        modelo.setColumnIdentifiers(cabecera);
//        String[] persona={"Jorge","24","Granada"};
//        for(int i=1;i<=100;i++){
//            modelo.addRow(persona);
//        }
        
//        Cerveza prueba=new Cerveza("La garrapata roja",200,true,'n',5.5,"Perro & Garrapata S.L");
        modelo.setColumnIdentifiers(Cerveza.getCabecera());
//        modelo.addRow(prueba.getValores());
        
        for(String[] fila:la_cerveceria.todoElAlmacen()){
            modelo.addRow(fila);
        }


        JScrollPane js = new JScrollPane(tabla);
        this.add(js, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
