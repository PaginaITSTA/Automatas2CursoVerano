package A04_ProgramaClase;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class analizadorSintactico {

    /*
    ************************************ Reglas definidas: ***********************************
    
    Declaracion := DeclVE | DeclVD | DeclVB
    DeclVE := int identificador ; | int identificador = num ;
    
    DeclVD := float identificador ; | float identificador = Dec ;
    Dec := num.num
    
    DeVB := bool identificador ; | bool identificador = ExpLog;
    ExpLog := verdadero | falso
    
    ******************************************************************************************
     */
    private ArrayList<String> listaTokens, listaErrores, arbol;
    private int posicionListaTokens;
    private String valorTemporalFinal = "";
    private boolean aceptado;

    public analizadorSintactico(ArrayList<String> listaTokens) {
        this.arbol = new ArrayList<>();
        this.listaErrores = new ArrayList<>();
        this.listaTokens = listaTokens;
        this.posicionListaTokens = 0;
        aceptado = false;
    }

    public void iniciaAnalisisLexico() {
        Declaracion();
    }

    private void Declaracion() {
        arbol.add("Declaracion := DeclVE | DeclVD | DeclVB ");
        if (DeclVE()) {
            aceptado = true;
        } else if (DeclVD()) {
            aceptado = true;
        } else if (DeclVB()) {
            aceptado = true;
        } else {
            listaErrores.add("Error encontrado, de token en in orden");
        }
    }

    private boolean DeclVE() {
        if (CompruebaVE1() || CompruebaVE2()) {
            return true;
        }
        return false;
    }

    private boolean CompruebaVE1() {
        if ((listaTokens.get(posicionListaTokens).equals("palabraReservadaInt"))
                && (listaTokens.get(posicionListaTokens + 1).equals("identificador"))
                && (listaTokens.get(posicionListaTokens + 2).equals("delimitador"))) {
            arbol.add("DeclVE := int identificador ; | int identificador = num ; ");
            arbol.add("-> DeclVE ");
            arbol.add("-> Palabra reservada (int) ");
            arbol.add("-> Identificador ");
            arbol.add("-> Delimitador (;) ");
            return true;
        }
        return false;
    }

    private boolean CompruebaVE2() {

        if ((posicionListaTokens + 4 < listaTokens.size())) {
            if ((listaTokens.get(posicionListaTokens).equals("palabraReservadaInt"))
                    && (listaTokens.get(posicionListaTokens + 1).equals("identificador"))
                    && (listaTokens.get(posicionListaTokens + 2).equals("operadorAsignacion"))
                    && (listaTokens.get(posicionListaTokens + 3).equals("numeroEntero"))
                    && (listaTokens.get(posicionListaTokens + 4).equals("delimitador"))) {
                arbol.add("DeclVE := int identificador ; | int identificador = num ; ");
                arbol.add("-> DeclVE ");
                arbol.add("-> Palabra reservada (int) ");
                arbol.add("-> Identificador ");
                arbol.add("-> Operador de asignacion (=) ");
                arbol.add("-> Numero entero ");
                arbol.add("-> Delimitador (;) ");
                return true;
            }
        }

        return false;
    }

    private boolean DeclVD() {
        if (CompruebaVD1() || CompruebaVD2()) {
            return true;
        }
        return false;
    }

    private boolean CompruebaVD1() {
        if ((listaTokens.get(posicionListaTokens).equals("palabraReservadaFloat"))
                && (listaTokens.get(posicionListaTokens + 1).equals("identificador"))
                && (listaTokens.get(posicionListaTokens + 2).equals("delimitador"))) {
            arbol.add("DeclVD := float identificador ; | float identificador = Dec ;");
            arbol.add("-> DeclVD ");
            arbol.add("-> Palabra reservada (float) ");
            arbol.add("-> Identificador ");
            arbol.add("-> Delimitador (;) ");
            return true;
        }
        return false;
    }

    private boolean CompruebaVD2() {

        if ((posicionListaTokens + 4 < listaTokens.size())) {
            if ((listaTokens.get(posicionListaTokens).equals("palabraReservadaFloat"))
                    && (listaTokens.get(posicionListaTokens + 1).equals("identificador"))
                    && (listaTokens.get(posicionListaTokens + 3).equals("operadorAsignacion"))
                    && (listaTokens.get(posicionListaTokens + 4).equals("numeroDecimal"))
                    && (listaTokens.get(posicionListaTokens + 2).equals("delimitador"))) {
                arbol.add("DeclVD := float identificador ; | float identificador = Dec ;");
                arbol.add("-> DeclVD ");
                arbol.add("-> Palabra reservada (float) ");
                arbol.add("-> Identificador ");
                arbol.add("-> Operador de asignacion (=) ");
                arbol.add("-> Numero Decimal ");
                arbol.add("-> Delimitador (;) ");
                return true;
            }
        }

        return false;
    }

    private boolean DeclVB() {
        if (CompruebaVB1() || CompruebaVB2()) {
            return true;
        }
        return false;
    }

    private boolean CompruebaVB1() {
        if ((listaTokens.get(posicionListaTokens).equals("palabraReservadaBool"))
                && (listaTokens.get(posicionListaTokens + 1).equals("identificador"))
                && (listaTokens.get(posicionListaTokens + 2).equals("delimitador"))) {
            arbol.add("DeVB := bool identificador ; | bool identificador = ExpLog; ");
            arbol.add("-> DeclVB ");
            arbol.add("-> Palabra reservada (Bool) ");
            arbol.add("-> Identificador ");
            arbol.add("-> Delimitador (;) ");
            return true;
        }
        return false;
    }

    private boolean CompruebaVB2() {

        if ((posicionListaTokens + 4 < listaTokens.size())) {
            if ((listaTokens.get(posicionListaTokens).equals("palabraReservadaBool"))
                    && (listaTokens.get(posicionListaTokens + 1).equals("identificador"))
                    && (listaTokens.get(posicionListaTokens + 3).equals("operadorAsignacion"))
                    && ExpLog(listaTokens.get(posicionListaTokens + 4))
                    && (listaTokens.get(posicionListaTokens + 2).equals("delimitador"))) {

                arbol.add("DeVB := bool identificador ; | bool identificador = ExpLog; ");
                arbol.add("-> DeclVB ");
                arbol.add("-> Palabra reservada (Bool) ");
                arbol.add("-> Identificador ");
                arbol.add("-> Operador de asignacion (=) ");
                arbol.add(valorTemporalFinal);
                arbol.add("-> Delimitador (;) ");

                return true;
            }
        }

        return false;
    }

    private boolean ExpLog(String valorLogico) {
        if (valorLogico.equals("palabraReservadaTrue")) {
            this.valorTemporalFinal = "palabraReservadaTrue (true) ";
            return true;
        } else if (valorLogico.equals("palabraReservadaFalse")) {
            this.valorTemporalFinal = "palabraReservadaFalse (false) ";
            return true;
        }
        return false;
    }

    public boolean getEsAceptado() {
        return aceptado;
    }

    public ArrayList getArbol() {
        return arbol;
    }
}
