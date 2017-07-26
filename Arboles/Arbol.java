/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import java.util.ArrayList;

/**
 *
 * @author Yadira
 */
public class Arbol {

    Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    public void AgregarNodo(int dato, String datoslist) {
        Nodo nuevo = new Nodo(dato, datoslist);
        if (raiz == null) {
            raiz = nuevo;
            System.out.println("Insertando en Raiz --> " + raiz.dato);
        } else {
            Nodo auxiliar = raiz;
            Nodo padre;           

            while (true) {
                padre = auxiliar;
                if (dato < auxiliar.dato) {
                    auxiliar = auxiliar.izq;
                     System.out.println("Insertando en Nodo Izquierdo --> " + dato);
                    if (auxiliar == null) {
                        padre.izq = nuevo;
                        return;
                    }
                } else {
                    auxiliar = auxiliar.der;
                    System.out.println("Insertando en Nodo Derecha --> " + dato);
                    if (auxiliar == null) {
                        padre.der = nuevo;
                        return;
                    }
                }
            }
        }
    }

    public boolean EstaVacio() {
        return raiz == null;
    }
    
    public void RecorridoInorden(Nodo r){
        if (r!=null) {
            RecorridoInorden(r.izq);
            System.out.println(r.dato);        
            RecorridoInorden(r.der);
        }
    }
    
}
