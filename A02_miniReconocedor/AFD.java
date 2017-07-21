/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A02_miniReconocedor;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class AFD {

    private final String[] P_Reser = {"int", "bool", "float", "true", "false"};
    public ArrayList<String> listaTokens;
    private int contador;
    private char[] cadena;
    public boolean aceptado, entro;

    public AFD(String codigo) {
        this.cadena = codigo.toCharArray();
        listaTokens = new ArrayList<>();
        entro = false;
    }

    public void estadoInicial() {
        contador = 0;
        aceptado = false;
        q0();
    }

    private void q0() {
        while (contador < cadena.length) {
            entro = false;
            //System.out.println("cadena[contador] = " + cadena[contador]);
            switch (cadena[contador]) {
                case ' ':
                    entro = true;
                    contador++;
                    break;
                case ';':
                    q1();
                    entro = true;
                    break;
                case '=':
                    q2();
                    entro = true;
                    break;
                case '+':
                    q3();
                    entro = true;
                    break;
                case '-':
                    q4();
                    entro = true;
                    break;
                case '*':
                    q5();
                    entro = true;
                    break;
                case '/':
                    q6();
                    entro = true;
                    break;
            }

            if (entro == false && AFD.isLetter(cadena[contador])) {
                q7();
            } else if (entro == false && AFD.isNumeric(cadena[contador])) {
                q8();
            } else if (entro == false) {
                contador++;
            }
        }
    }

    private void q1() {
        //System.out.println("delimitador -> ");
        listaTokens.add("delimitador");
        contador++;
    }

    private void q2() {
        //System.out.println("operadorAsignacion -> ");
        listaTokens.add("operadorAsignacion");
        contador++;
    }

    private void q3() {
        //System.out.println("operadorSuma -> ");
        listaTokens.add("operadorSuma");
        contador++;
    }

    private void q4() {
        //System.out.println("operadorResta -> ");
        listaTokens.add("operadorResta");
        contador++;
    }

    private void q5() {
        //System.out.println("operadorMultiplicacion -> ");
        listaTokens.add("operadorMultiplicacion");
        contador++;
    }

    private void q6() {
        //System.out.println("operadorDivisión");
        listaTokens.add("operadorDivision");
        contador++;
    }

    private void q7() {
        String temp = "";
        boolean diferent = false, digit = false;

        while (contador < cadena.length && diferent == false) {
            //System.out.println(cadena[contador]);
            if (cadena[contador] == ' ') {
                diferent = true;
            } else if (AFD.isNumeric(cadena[contador])) {
                digit = true;
                temp = temp + cadena[contador];
                contador++;
            } else if (AFD.isLetter(cadena[contador])) {
                temp = temp + cadena[contador];
                contador++;
            } else {
                diferent = true;
            }

        }

        boolean reservada = isReserved(temp);

        if (digit) {
            listaTokens.add("identificador");
        } else if (reservada) {
            listaTokens.add("palabraReservada");
        } else {
            listaTokens.add("identificador");
        }
    }

    private void q8() {
        boolean decimal = false;

        while (contador < cadena.length) {
            if (cadena[contador] == ' ') {
                break;
            } else if (AFD.isNumeric(cadena[contador])) {
                contador++;
            } else if (cadena[contador] == '.') {
                decimal = true;
                contador++;
            } else {
                break;
            }
        }

        if (decimal) {
            listaTokens.add("numeroDecimal");
        } else {
            listaTokens.add("numeroEntero");
        }
    }

    private static boolean isNumeric(char numero) {
        String cadena = numero + "";
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static boolean isLetter(char letra) {
        if ((letra >= 'a' && letra <= 'z') || (letra >= 'A' && letra <= 'Z')) {
            return true;
        }
        return false;
    }

    private boolean isReserved(String valor) {
        int contadorTemporal = 0;
        boolean band = false;
        while (contadorTemporal < P_Reser.length) {
            //System.out.println("Es igual: " + valor + " a " + P_Reser[contadorTemporal]);
            if (valor.equals(P_Reser[contadorTemporal])) {
                return true;
            }
            contadorTemporal++;
        }

        return false;
    }

    public ArrayList<String> getListTokens() {
        return this.listaTokens;
    }
}
