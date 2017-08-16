package Lenguaje_SID.AFD;

import java.util.ArrayList;

/**
 *
 * @author Clair
 */
public class GeneracionCodigoIntermedio {

    private ArrayList<listaSimbolos> listaSimbolos;

    private String cadena = "";

    public GeneracionCodigoIntermedio(ArrayList<listaSimbolos> lista) {
        this.listaSimbolos = lista;
    }

    public void comienza() {
        /*
        String Impresion = DeterminaImpresion();
        if (!Impresion.isEmpty()) {
            cadena = impresion(Impresion);
        } else {
            System.out.println("No había ninguna impresion :(");
        }
         */

        cadena = impresiones();
    }

    private String DeterminaImpresion() {

        for (int i = 0; i < listaSimbolos.size(); i++) {
            if (listaSimbolos.get(i).getClase().equals("Impresion")) {
                return listaSimbolos.get(i).getValor();
            }
        }

        return "";
    }

    private String impresion(String cadena) {
        String codigo = "%include \'funciones.asm\'\n"
                + "SECTION .data\n"
                + "\n"
                + " msgSal      db      \'" + cadena + " \', 0Ah, 0h\n"
                + " \n"
                + "SECTION .text\n"
                + "global  _start\n"
                + " \n"
                + "_start:\n"
                + " \n"
                + "    mov     eax, msgSal\n"
                + "    call    sprint\n"
                + "\n"
                + "    call    salir";
        return codigo;
    }

    private String impresiones() {
        ArrayList<String> listaDeImpresion = new ArrayList<>();
        String cadena = "%include \'funciones.asm\'\n"
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

        for (int i = 0; i < listaDeImpresion.size(); i++) {
            cadena = cadena + "\n"
                    + "    mov     eax, " + listaDeImpresion.get(i) + "\n"
                    + "    call    sprint\n";
        }

        cadena = cadena + "\ncall    salir";
        return cadena;
    }

    public String getImpresion() {
        return cadena;
    }
}
