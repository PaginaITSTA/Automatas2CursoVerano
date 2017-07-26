package Lenguaje_SID.AFD;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class AFD {

    private int contadorGeneral, linea;
    private char cadenaGeneral[];
    private String rows[];
    private final String[][] palabrasReservadas
            = {{"class", "String", "Int", "Boolean", "Float", "char", "double", "void", "protected", "private",
                "public", "false", "true", "if", "Do", "While", "For", "Print"},
            {"Class", "String", "Int", "Boolean", "Float", "Char", "Double", "Void", "protected", "Private",
                "Public", "False", "True"}};
    private ArrayList<token> listaTokens;
    private ArrayList<String> listaErrores;

    public AFD(String cadena) {
        this.rows = cadena.split("\n");
        //this.cadenaGeneral = cadena.toCharArray();
        listaTokens = new ArrayList<>();
        listaErrores = new ArrayList<>();
        this.contadorGeneral = 0;
        this.linea = 0;
    }

    public void estadoInicial() {
        cambiaLinea();
        q0();
    }

    private void q0() {
        boolean entro;
        while (linea <= rows.length) {
            System.out.println("linea =" + linea);
            if (contadorGeneral < cadenaGeneral.length) {
                System.out.println("Caracter -> " + cadenaGeneral[contadorGeneral]);
                entro = false;
                //System.out.println("cadena[contador] = " + cadena[contador]);
                switch (cadenaGeneral[contadorGeneral]) {
                    case ' ':
                        entro = true;
                        contadorGeneral++;
                        break;
                    case '{':
                        q1();
                        entro = true;
                        break;
                    case '}':
                        q2();
                        entro = true;
                        break;
                    case '[':
                        q3();
                        entro = true;
                        break;
                    case ']':
                        q4();
                        entro = true;
                        break;
                    case '(':
                        q5();
                        entro = true;
                        break;
                    case ')':
                        q6();
                        entro = true;
                        break;
                    case '.':
                        q7();
                        entro = true;
                        break;
                    case '$':
                        q8();
                        entro = true;
                        break;
                    case '%':
                        q9();
                        entro = true;
                        break;
                    case '#':
                        q10();
                        entro = true;
                        break;
                    case ';':
                        q11();
                        entro = true;
                        break;
                    case '"':
                        q12();
                        entro = true;
                        break;
                    case '+':
                        q13();
                        entro = true;
                        break;
                    case '-':
                        q14();
                        entro = true;
                        break;
                    case '*':
                        q15();
                        entro = true;
                        break;
                    case '/':
                        q16();
                        entro = true;
                        break;
                    case '<':
                        q17();
                        entro = true;
                        break;
                    case '>':
                        q18();
                        entro = true;
                        break;
                    case '&':
                        q19();
                        entro = true;
                        break;
                    case '|':
                        q20();
                        entro = true;
                        break;
                    case '!':
                        q21();
                        entro = true;
                        break;
                    case '=':
                        q22();
                        entro = true;
                        break;

                }

                if (entro == false && AFD.isLetter(cadenaGeneral[contadorGeneral])) {
                    q23();
                } else if (entro == false && AFD.isNumeric(cadenaGeneral[contadorGeneral])) {
                    q24();
                } else if (entro == false) {
                    contadorGeneral++;
                }
            } else {
                cambiaLinea();
            }
        }
    }

    private void q1() {
        listaTokens.add(new token("llaveApertura", "{", contadorGeneral));
        contadorGeneral++;
    }

    private void q2() {
        listaTokens.add(new token("llaveFin", "}", contadorGeneral));
        contadorGeneral++;
    }

    private void q3() {
        listaTokens.add(new token("corcheteInicio", "[", contadorGeneral));
        contadorGeneral++;
    }

    private void q4() {
        listaTokens.add("corcheteFin");
        contadorGeneral++;
    }

    private void q5() {
        listaTokens.add("parentesisInicio");
        contadorGeneral++;
    }

    private void q6() {
        listaTokens.add("parentesisFin");
        contadorGeneral++;
    }

    private void q7() {
        listaTokens.add("punto");
        contadorGeneral++;
    }

    private void q8() {
        listaTokens.add("simboloDollar");
        contadorGeneral++;
    }

    private void q9() {
        listaTokens.add("porcentaje");
        contadorGeneral++;
    }

    private void q10() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[contadorGeneral + 1] == '#') {
                listaTokens.add("comentarioSimple");
                cambiaLinea();
            } else {
                listaErrores.add("Esperado: # en la linea: " + linea + ", en la columna: " + contadorGeneral + 1);
                contadorGeneral++;
            }
        } else {
            listaErrores.add("Esperado: # en la linea: " + linea + ", en la columna: " + contadorGeneral);
            contadorGeneral++;
        }
    }

    //queda para validación ------------------------------------------------------------------------------
    private void q11() {
        listaTokens.add("delimitador");
        contadorGeneral++;
    }

    //Aqui se hizo un cambio en el AFD, debido que se contemplan las cadenas de texto como un: "njsknksa"
    private void q12() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            boolean band = true;
            contadorGeneral++;
            while (band) {
                if (contadorGeneral >= cadenaGeneral.length) {
                    listaErrores.add("Esperado: \" en la linea: " + linea + ", en la columna" + (contadorGeneral - 1));
                    contadorGeneral++;
                    band = false;
                } else if (cadenaGeneral[contadorGeneral] == '"') {
                    listaTokens.add("cadenaDeTexto");
                    contadorGeneral++;
                    band = false;
                } else {
                    contadorGeneral++;
                }
            }
        } else {
            contadorGeneral++;
        }
    }

    private void q13() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '+') {
                listaTokens.add("operadorIncremento");
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add("operadorSuma");
                contadorGeneral++;
            }
        } else {
            listaTokens.add("operadorSuma");
            contadorGeneral++;
        }
    }

    private void q14() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '-') {
                listaTokens.add("operadorDecremento");
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add("operadorResta");
                contadorGeneral++;
            }
        } else {
            listaTokens.add("operadorResta");
            contadorGeneral++;
        }
    }

    private void q15() {
        listaTokens.add("operadorMultiplicacion");
        contadorGeneral++;
    }

    private void q16() {
        listaTokens.add("operadorDivision");
        contadorGeneral++;
    }

    private void q17() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '=') {
                listaTokens.add("operadorMenorOIgualQue");
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add("operadorMenorQue");
                contadorGeneral++;
            }
        } else {
            listaTokens.add("operadorMenorQue");
            contadorGeneral++;
        }
    }

    private void q18() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '=') {
                listaTokens.add("operadorMayorOIgualQue");
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add("operadorMayorQue");
                contadorGeneral++;
            }
        } else {
            listaTokens.add("operadorMayorQue");
            contadorGeneral++;
        }
    }

    //&
    private void q19() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '&') {
                listaTokens.add("operadorAnd");
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaErrores.add("Esperado: & en la linea: " + linea + ", en la columna" + contadorGeneral);
                contadorGeneral++;
            }
        } else {
            listaErrores.add("Esperado: & en la linea: " + linea + ", en la columna" + contadorGeneral);
            contadorGeneral++;
        }
    }

    //||
    private void q20() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '|') {
                listaTokens.add("operadorOr");
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaErrores.add("Esperado: | en la linea: " + linea + ", en la columna" + contadorGeneral);
                contadorGeneral++;
            }
        } else {
            listaErrores.add("Esperado: | en la linea: " + linea + ", en la columna" + contadorGeneral);
            contadorGeneral++;
        }
    }

    //!
    private void q21() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '=') {
                listaTokens.add("operadorDesigual");
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add("operadorDeNegacion");
                contadorGeneral++;
            }
        } else {
            listaTokens.add("operadorDeNegacion");
            contadorGeneral++;
        }
    }

    //=
    private void q22() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '=') {
                listaTokens.add("operadorIgualIgual");
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add("operadorDeAsignacion");
                contadorGeneral++;
            }
        } else {
            listaTokens.add("operadorDeAsignacion");
            contadorGeneral++;
        }
    }

    private void q23() {
        int valorTemporal = contadorGeneral;
        String temp = "";
        boolean diferent = false, digit = false;

        while (contadorGeneral < cadenaGeneral.length && diferent == false) {
            if (cadenaGeneral[contadorGeneral] == ' ') {
                diferent = true;
            } else if (AFD.isNumeric(cadenaGeneral[contadorGeneral])) {
                digit = true;
                temp = temp + cadenaGeneral[contadorGeneral];
                contadorGeneral++;
            } else if (AFD.isLetter(cadenaGeneral[contadorGeneral])) {
                temp = temp + cadenaGeneral[contadorGeneral];
                contadorGeneral++;
            } else {
                diferent = true;
            }
        }

        boolean reservada = isReserved(temp);

        if (digit) {
            listaTokens.add(new token("identificador", temp, valorTemporal));
        } else if (reservada) {
            listaTokens.add("palabraReservada");
        } else {
            listaTokens.add("identificador");
        }
    }

    private void q24() {
        boolean decimal = false;

        while (contadorGeneral < cadenaGeneral.length) {
            if (cadenaGeneral[contadorGeneral] == ' ') {
                break;
            } else if (AFD.isNumeric(cadenaGeneral[contadorGeneral])) {
                contadorGeneral++;
            } else if (cadenaGeneral[contadorGeneral] == '.') {
                decimal = true;
                contadorGeneral++;
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
        while (contadorTemporal < palabrasReservadas.length) {
            //System.out.println("Es igual: " + valor + " a " + P_Reser[contadorTemporal]);
            if (valor.equals(palabrasReservadas[contadorTemporal])) {
                return true;
            }
            contadorTemporal++;
        }

        return false;
    }

    private void cambiaLinea() {
        if (linea < rows.length) {
            cadenaGeneral = rows[linea].toCharArray();
            contadorGeneral = 0;
            linea++;
        } else {
            linea++;
        }
    }

    public ArrayList getListaTokens() {
        return listaTokens;
    }
}
