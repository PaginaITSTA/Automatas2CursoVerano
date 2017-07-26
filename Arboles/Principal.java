/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Yadira
 */
public class Principal {

    public static void main(String[] args) {
        int opcion = 0, elemento;
        String listadatos;
        Arbol arbol = new Arbol();

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Agregar un Nodo\n"
                        + "2.- Recorrido Inorden\n"
                        + "3.- Salir\n "
                        + "Elige una opcion", "Arboles", JOptionPane.QUESTION_MESSAGE));

                switch (opcion) {
                    case 1:
                        elemento = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "Ingrese un Nodo ", "Agregando", JOptionPane.QUESTION_MESSAGE));
                        listadatos = JOptionPane.showInputDialog(null, "Ingresa Propiedades del Nodo", "Agregando", JOptionPane.QUESTION_MESSAGE);

                        arbol.AgregarNodo(elemento, listadatos);

                        break;
                    case 2:
                        if (!arbol.EstaVacio()) {
                            arbol.RecorridoInorden(arbol.raiz);
                        } else {
                            System.out.println("El Arbol esta Vacio");
                        }
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Cerrando", "Cerrando", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion incorrecta", "Cerrando", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException exp) {
                JOptionPane.showMessageDialog(null, "Error " + exp.getMessage());
            }
        } while (opcion != 2);
    }
}
