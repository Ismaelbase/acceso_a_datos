package basesdatos;

import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Basesdatos {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion=0;
        Ejercicios objeto = new Ejercicios("localhost", "empresa", "root", "", "com.mysql.cj.jdbc.Driver");
        do {
            try {

                System.out.println("Elija el número de ejercicios que quiere ejecutar(de 1 a 20)");
                System.out.println("0 para salir");
                opcion=teclado.nextInt();
                switch(opcion){
                    case 0:
                        System.out.println("Adios");
                        break;    
                    
                    case 1:
                        objeto.ejercicio1();
                        break;
                    case 2:
                        objeto.ejercicio2();
                        break;
                    case 3:
                        objeto.ejercicio3();
                        break;
                    case 4:
                        objeto.ejercicio4();
                        break;
                    case 5:
                        objeto.ejercicio5();
                        break;
                    case 6:
                        objeto.ejercicio6();
                        break;
                    case 7:
                        objeto.ejercicio7();
                        break;
                    case 8:
                        objeto.ejercicio8();
                        break;
                    case 9:
                        objeto.ejercicio9();
                        break;
                    case 10:
                        objeto.ejercicio10();
                        break;
                    case 11:
                        objeto.ejercicio11();
                        break;
                    case 12:
                        objeto.ejercicio12();
                        break;
                    case 13:
                        objeto.ejercicio13();
                        break;
                    case 14:
                        objeto.ejercicio14();
                        break;
                    case 15:
                        objeto.ejercicio15();
                        break;
                    case 16:
                        objeto.ejercicio16();
                        break;
                    case 17:
                        objeto.ejercicio17();
                        break;
                    case 18:
                        objeto.ejercicio18();
                        break;
                    case 19:
                        objeto.ejercicio19();
                        break;
                    case 20:
                        objeto.ejercicio20();
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                }
                

            } catch (ClassNotFoundException cfe) {
                JOptionPane.showMessageDialog(null, cfe.getMessage() + " problema al cargar el driver");
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, sqle.getMessage() + " problema de sintaxis");
            }
        }while(opcion!=0);

    }

}
