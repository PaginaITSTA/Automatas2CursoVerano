package A03_Analisis_lexico_sintactico_Semantico;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class analisisSintactico {

    private int contadorGeneral;
    private ArrayList<String> listaTokens, listaErrores, arbol;
    private final String[][] palabraReservada = {{"int", "float"},
    {"palabraReservadaInt", "palabraReservadaFloat"}};

    //Solo el contructor que resive la lista de tokens que produce el analizador lexico
    public analisisSintactico(ArrayList listaTokens) {
        this.listaTokens = listaTokens;
        this.arbol = new ArrayList<>();
    }

    //Si no es llamado este método, no se iniciara el analisis
    public void iniciaProceso() {
        contadorGeneral = 0;
        declaracion();
    }

    private void declaracion() {
        arbol.add("Declaracion -> Tipo | Lista\n");
        arbol.add("-> Tipo ");
        tipo();
        arbol.add("-> Lista ");
        lista();
    }

    private void tipo() {
        if (compruebaTipo()) {
            arbol.add("-> " + tipoDato() + "\n");
        } else {
            arbol.add("-> Error ");
            listaErrores.add("Error, esperado un tipo de dato \"int, bool\" dado = " + listaTokens.get(contadorGeneral));
        }
        contadorGeneral++;
    }

    private void lista() {
        if (listaTokens.size() >= 2) {
            listaIdentificador();
        } else {
            listaErrores.add("Error, esperado un identificado.");
        }
    }

    private void listaIdentificador() {
        if (contadorGeneral < listaTokens.size()) {
            if (identificador(contadorGeneral) && separador(contadorGeneral + 1)) {
                arbol.add("identificador -> \n");
                arbol.add("Separador -> ");
                arbol.add(",\n");
                contadorGeneral++;
                contadorGeneral++;
                listaIdentificador();
            } else if (identificador(contadorGeneral) && (separador(contadorGeneral) == false)) {
                arbol.add("identificador -> \n");
            } else if ((identificador(contadorGeneral) == false) && separador(contadorGeneral)) {
                listaErrores.add("Error, esperado un identificador, dado un delimitador \",\"");
            }
        }
    }

    private boolean identificador(int numero) {
        return false;
    }

    private boolean separador(int numero) {
        return false;
    }

    private boolean compruebaTipo() {
        int contadorTemporal = 0;

        while (contadorTemporal < palabraReservada.length) {
            if (listaTokens.get(contadorGeneral).equals(palabraReservada[1][contadorTemporal])) {
                return true;
            }
            contadorTemporal++;
        }

        return false;
    }

    private String tipoDato() {

        int contadorTemporal = 0;

        while (contadorTemporal < palabraReservada.length) {
            if (listaTokens.get(contadorGeneral).equals(palabraReservada[1][contadorTemporal])) {
                return palabraReservada[0][contadorTemporal];
            }
            contadorTemporal++;
        }

        return "";
    }
}
