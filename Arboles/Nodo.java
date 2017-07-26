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
public class Nodo {

    //private ArrayList<String> listdatos;
    private String listdatos;
    Nodo izq;
    Nodo der;
    //private String dato;
    int dato;

    public Nodo() {
    }

    public Nodo(int dato, String listdatos) {
        this.dato = dato;
        this.listdatos = listdatos;
        this.der = null;
        this.izq = null;
    }

    /*
    public Nodo(ArrayList listdatos, Nodo izq, Nodo der) {
        this.listdatos = listdatos;
        this.izq = izq;
        this.der = der;
    }

    public Nodo(String datos, Nodo izq, Nodo der) {
        this.datos = datos;
        this.izq = izq;
        this.der = der;
    }*/
    public String getListdatos() {
        return listdatos;
    }

    public void setListdatos(String listdatos) {
        this.listdatos = listdatos;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public String toString() {
        return dato + "Sus propiedades son: " + listdatos;
    }

}
