package Lenguaje_SID.AFD;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class analizadorSemantico {

    private ArrayList<listaSimbolos> Simbolos;
    private ArrayList<String> ErroresSemanticos;
    private int contadorLista;

    public analizadorSemantico(ArrayList<listaSimbolos> Simbolos) {
        this.Simbolos = Simbolos;
        Simbolos = new ArrayList<>();
        ErroresSemanticos = new ArrayList<>();
        contadorLista = 0;
    }

    public void imprimirSimbolos() {
        for (int i = 0; i < Simbolos.size(); i++) {
            System.out.println(" Clase: " + Simbolos.get(contadorLista + i).getClase()
                    + " Nombre: " + Simbolos.get(contadorLista + i).getNombreValor()
                    + " TipoDato: " + Simbolos.get(contadorLista + i).getTipoDeDato()
                    + " Valor: " + Simbolos.get(contadorLista + i).getValor()
                    + " Disponibilidad: " + Simbolos.get(contadorLista + i).getDisponibilidad());
        }

    }

}
