package Lenguaje_SID.AFD;

import java.util.ArrayList;

/**
 *
 * @author Clair
 */
public class GeneracionCodigoIntermedio {

    //Misma lista de simbolos de la clase de análisis sintactico
    private ArrayList<listaSimbolos> listaSimbolos;
    //Guarda el nombre de las variable que se guardan en la section.data
    ArrayList<String> listaDeImpresion;
    //Contador que lleva el apuntador de la lista de impresiones
    private int contadorListaImpresion;
    //Cadena de texto que guarda el código intermedio
    private String cadena = "";

    public GeneracionCodigoIntermedio(ArrayList<listaSimbolos> lista) {
        //guarda los valores de la lista de simbolos del análisis sintactico en una lista local del mismo tipo.
        this.listaSimbolos = lista;
        //inicializa la lista donde se guardan las posibles impresiones
        listaDeImpresion = new ArrayList<>();
        //Inicializa el contador de la lista de impresiones
        contadorListaImpresion = 0;
    }

    public void comienza() {
        impresiones();
        recorreListaSimbolos();
    }

    private void recorreListaSimbolos() {
        String variable;
        for (int i = 0; i < listaSimbolos.size(); i++) {
            variable = listaSimbolos.get(i).getClase();
            if (variable.equals("Operacion") || variable.equals("if") || variable.equals("while")) {
                String operacion = listaSimbolos.get(i).getNombreValor();
                switch (operacion) {
                    case "*":
                        Multiplicacion(i);
                        break;
                    case "/":
                        Division(i);
                        break;
                    case "+":
                        Suma(i);
                        break;
                    case "-":
                        Resta(i);
                        break;
                }
                cadena = cadena + "\n\n";
            } else if (variable.equals("Impresion")) {
                impresion();
                cadena = cadena + "\n\n";
            }
        }

        cadena = cadena + "\n\tcall \tquit";

    }

    private void Multiplicacion(int indice) {
        char LetraInicioTipoDato = listaSimbolos.get(indice).getTipoDeDato().charAt(0);
        char LetraInicioValor = listaSimbolos.get(indice).getValor().charAt(0);

        if ((LetraInicioTipoDato + "").equals("r")) {
            cadena = cadena + "\tmov \tecx, eax\n";
        } else {
            cadena = cadena + "\tmov \tecx, " + listaSimbolos.get(indice).getTipoDeDato() + "\n";
        }

        if ((LetraInicioValor + "").equals("r")) {
            cadena = cadena + "\tmov \tebx, eax\n";
        } else {
            cadena = cadena + "\tmov \tebx, " + listaSimbolos.get(indice).getValor() + "\n";
        }

        cadena = cadena + "\n\tmul \tebx\n"
                + "\tcall \tiprintLF";
    }

    private void Division(int indice) {
        char LetraInicioTipoDato = listaSimbolos.get(indice).getTipoDeDato().charAt(0);
        char LetraInicioValor = listaSimbolos.get(indice).getValor().charAt(0);

        if ((LetraInicioTipoDato + "").equals("r")) {
            cadena = cadena + "\tmov \tecx, eax\n"
                    + "\tmov \teax, ecx\n";
        } else {
            cadena = cadena + "\tmov \teax, " + listaSimbolos.get(indice).getTipoDeDato() + "\n";
        }

        if ((LetraInicioValor + "").equals("r")) {
            cadena = cadena + "\tmov \tecx, eax\n"
                    + "\tmov \tebx, ecx\n";
        } else {
            cadena = cadena + "\tmov \tebx, " + listaSimbolos.get(indice).getTipoDeDato() + "\n";
        }

        cadena = cadena + "\n\tdiv \tebx\n"
                + "\tcall \tiprintLF";
    }

    private void Suma(int indice) {
        char LetraInicioTipoDato = listaSimbolos.get(indice).getTipoDeDato().charAt(0);
        char LetraInicioValor = listaSimbolos.get(indice).getValor().charAt(0);

        if ((LetraInicioTipoDato + "").equals("r")) {
            cadena = cadena + "\tmov \tecx, eax\n"
                    + "\tmov \tebx, ecx\n";
        } else {
            cadena = cadena + "\tmov \tebx, " + listaSimbolos.get(indice).getTipoDeDato() + "\n";
        }

        if ((LetraInicioValor + "").equals("r")) {
            cadena = cadena + "\tmov \tecx, eax\n"
                    + "\tmov \teax, ecx\n";
        } else {
            cadena = cadena + "\tmov \teax, " + listaSimbolos.get(indice).getValor() + "\n";
        }

        cadena = cadena + "\n\tadd \teax, ebx\n"
                + "\tcall \tiprintLF";

        /*
        mov	ebx, 2
	mov 	eax, 2
	add  	eax, ebx
         */
    }

    private void Resta(int indice) {
        char LetraInicioTipoDato = listaSimbolos.get(indice).getTipoDeDato().charAt(0);
        char LetraInicioValor = listaSimbolos.get(indice).getValor().charAt(0);

        if ((LetraInicioTipoDato + "").equals("r")) {
            cadena = cadena + "\tmov \tecx, eax\n"
                    + "\tmov \teax, ecx\n";
        } else {
            cadena = cadena + "\tmov \teax, " + listaSimbolos.get(indice).getTipoDeDato() + "\n";
        }

        if ((LetraInicioValor + "").equals("r")) {
            cadena = cadena + "\tmov \tebx, eax\n"
                    + "\tmov \tebx, ecx\n";
        } else {
            cadena = cadena + "\tmov \tebx, " + listaSimbolos.get(indice).getValor() + "\n";
        }

        cadena = cadena + "\n\tsub \teax, ebx\n"
                + "\tcall \tiprintLF";

        /*
	mov 	eax, 90
	mov 	ebx, 9
	sub	eax, ebx
         */
    }

    private String DeterminaImpresion() {

        for (int i = 0; i < listaSimbolos.size(); i++) {
            if (listaSimbolos.get(i).getClase().equals("Impresion")) {
                return listaSimbolos.get(i).getValor();
            }
        }

        return "";
    }

    //Anexa la funcion de impresion al codigo objeto
    private void impresion() {
        cadena = cadena + "\tmov \teax, " + listaDeImpresion.get(contadorListaImpresion) + "\n"
                + "\tcall\t sprint\n";
        contadorListaImpresion++;
    }

    //
    private void impresiones() {

        cadena = "%include \'funciones.asm\'\n"
                + "SECTION .data\n\n";
        for (int i = 0; i < listaSimbolos.size(); i++) {
            if (listaSimbolos.get(i).getClase().equals("Impresion")) {
                cadena = cadena + ("impresion" + i + "      db      \'") + listaSimbolos.get(i).getValor() + " \', 0Ah, 0h\n";
                listaDeImpresion.add("impresion" + i);
            }
        }

        cadena = cadena + "\nSECTION .text\n"
                + "global  _start\n"
                + " \n"
                + "_start:\n"
                + " \n";
    }

    public String getImpresion() {
        return cadena;
    }
}
