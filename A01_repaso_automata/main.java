/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A01_repaso_automata;

import java.util.Scanner;

/**
 *
 * @author erick
 */
public class main {

    public static void main(String[] args) {
        AFD automata;
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("cadena");
        String cadena = entrada.nextLine();
        
        automata = new AFD(cadena);
        
        automata.estadoInicial();
        
        if(automata.aceptado){
            System.out.println("El automata es aceptado");
        }else{
            System.out.println("El automata no es aceptado");
        }
    }
    
}
