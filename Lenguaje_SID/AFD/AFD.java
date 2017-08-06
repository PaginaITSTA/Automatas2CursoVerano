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
    private final String[] palabrasReservadas
            = {"public", "private", "class", "String", "Int", "Boolean", "float", "double", "void", "false", "true",
                "if", "while", "do", "else", "for", "Print"};

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
            //System.out.println("linea =" + linea);
            if (contadorGeneral < cadenaGeneral.length) {
                //System.out.println("Caracter -> " + cadenaGeneral[contadorGeneral]);
                entro = false;
                //System.out.println("cadena[contador] = " + cadenaGeneral[contadorGeneral]);
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
//                    case ';':
//                        q11();
//                        entro = true;
//                        break;
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

                if (entro == false && isLetter(cadenaGeneral[contadorGeneral])) {
                    q23();
                } else if (entro == false && isNumeric(cadenaGeneral[contadorGeneral])) {
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
        listaTokens.add(new token("llaveApertura", "{", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q2() {
        listaTokens.add(new token("llaveFin", "}", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q3() {
        listaTokens.add(new token("corcheteInicio", "[", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q4() {
        listaTokens.add(new token("CorcheteFin", "]", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q5() {
        listaTokens.add(new token("ParentesisInicio", "(", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q6() {
        listaTokens.add(new token("ParentesisFin", ")", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q7() {
        listaTokens.add(new token("Punto", ".", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q8() {
        listaTokens.add(new token("Delimitador", "$", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q9() {
        listaTokens.add(new token("Porcentaje", "%", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q10() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[contadorGeneral + 1] == '#') {
                listaTokens.add(new token("ComentarioSimple", "##", linea, contadorGeneral));
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
//    private void q11() {
//        listaTokens.add(new token("PuntoyComa",";",contadorGeneral));                      
//        contadorGeneral++;
//    }
    //Aqui se hizo un cambio en el AFD, debido que se contemplan las cadenas de texto como un: "njsknksa"
    private void q12() {
        String cadenatemp = "";
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            boolean band = true;
            contadorGeneral++;
            while (band) {
                if (contadorGeneral >= cadenaGeneral.length) {
                    listaErrores.add("Esperado: \" en la linea: " + linea + ", en la columna" + (contadorGeneral - 1));
                    contadorGeneral++;
                    band = false;
                } else if (cadenaGeneral[contadorGeneral] == '"') {

                    listaTokens.add(new token("CadenaDeTexto", cadenatemp.substring(0, cadenatemp.length()), linea, contadorGeneral));
                    contadorGeneral++;
                    band = false;
                } else {
                    cadenatemp = cadenatemp + cadenaGeneral[contadorGeneral];
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
                listaTokens.add(new token("OperadorIncremento", "++", linea, contadorGeneral));
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add(new token("OperadorSuma", "+", linea, contadorGeneral));
                contadorGeneral++;
            }
        } else {
            listaTokens.add(new token("OperadorSuma", "+", linea, contadorGeneral));
            contadorGeneral++;
        }
    }

    private void q14() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '-') {
                listaTokens.add(new token("OperadorDecremento", "--", linea, contadorGeneral));
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add(new token("OperadorResta", "-", linea, contadorGeneral));
                contadorGeneral++;
            }
        } else {
            listaTokens.add(new token("OperadorResta", "-", linea, contadorGeneral));
            contadorGeneral++;
        }
    }

    private void q15() {
        listaTokens.add(new token("OperadorMultiplicacion", "*", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q16() {
        listaTokens.add(new token("OperadorDivision", "/", linea, contadorGeneral));
        contadorGeneral++;
    }

    private void q17() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '=') {
                listaTokens.add(new token("OperadorMenorOIgualQue", "<=", linea, contadorGeneral));
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add(new token("OperadorMenorQue", "<", linea, contadorGeneral));
                contadorGeneral++;
            }
        } else {
            listaTokens.add(new token("OperadorMenorQue", "<", linea, contadorGeneral));
            contadorGeneral++;
        }
    }

    private void q18() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '=') {
                listaTokens.add(new token("OperadorMayorOIgualQue", ">=", linea, contadorGeneral));
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add(new token("OperadorMayorQue", ">", linea, contadorGeneral));
                contadorGeneral++;
            }
        } else {
            listaTokens.add(new token("OperadorMayorQue", ">", linea, contadorGeneral));
            contadorGeneral++;
        }
    }

    //&
    private void q19() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '&') {
                listaTokens.add(new token("operadorAnd", "&", linea, contadorGeneral));
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
                listaTokens.add(new token("OperadorOr", "||", linea, contadorGeneral));
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
                listaTokens.add(new token("OperadorDesigual", "!=", linea, contadorGeneral));
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add(new token("operadorDeNegacion", "!", linea, contadorGeneral));
                contadorGeneral++;
            }
        } else {
            listaTokens.add(new token("operadorDeNegacion", "!", linea, contadorGeneral));
            contadorGeneral++;
        }
    }

    //=
    private void q22() {
        if (contadorGeneral < (cadenaGeneral.length - 1)) {
            if (cadenaGeneral[(contadorGeneral + 1)] == '=') {
                listaTokens.add(new token("operadorIgualIgual", "==", linea, contadorGeneral));
                contadorGeneral = contadorGeneral + 2;
            } else {
                listaTokens.add(new token("operadorDeAsignacion", "=", linea, contadorGeneral));
                contadorGeneral++;
            }
        } else {
            listaTokens.add(new token("operadorDeAsignacion", "=", linea, contadorGeneral));
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
//        boolean reservada = isReserved(temp);
//        
//
//        if (digit) {
//            listaTokens.add(new token("Identificador", temp, linea, valorTemporal));
//        } else if (reservada) {
//            listaTokens.add(new token("PalabraReservada", temp, linea, contadorGeneral));
//
//        } else {
//            listaTokens.add(new token("Identificador", temp, linea, valorTemporal));
//        }
        
        boolean reservada = true;
        for (int i = 0; i < palabrasReservadas.length; i++) {
            if (temp.equals(palabrasReservadas[i])) {
                listaTokens.add(new token("PalabraReservada", temp, linea, contadorGeneral));
                reservada = false;
                break;
            }            
        }
        if (reservada) {
            listaTokens.add(new token("Identificador", temp, linea, valorTemporal));
            
        }
        
    }
    

    //Este método es para identificar a los números 
    private void q24() {
        String numero = "";
        boolean decimal = false;

        while (contadorGeneral < cadenaGeneral.length) {
            if (cadenaGeneral[contadorGeneral] == ' ') {
                break;
            } else if (AFD.isNumeric(cadenaGeneral[contadorGeneral])) {
                numero = numero + cadenaGeneral[contadorGeneral];
                contadorGeneral++;
            } else if (cadenaGeneral[contadorGeneral] == '.') {
                numero = numero + cadenaGeneral[contadorGeneral];
                decimal = true;
                contadorGeneral++;
            } else {
                break;
            }
        }

        if (decimal) {
            listaTokens.add(new token("NumeroDecimal", numero, linea, contadorGeneral));
        } else {
            listaTokens.add(new token("NumeroEntero", numero, linea, contadorGeneral));
        }
    }

//    private String guaradaEnLista(String preservada) {
//        for (int i = 0; i <= palabrasReservadas.length; i++) {
//            if (preservada.equals(palabrasReservadas[0][i])) {
//                return palabrasReservadas[1][i];
//            }
//        }
//        return "";
//    }

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

//    private boolean isReserved(String valor) {
//        int contadorTemporal = 0;
//        boolean band = false;
//        while (contadorTemporal < palabrasReservadas.length) {
//
//            if (valor.equals(palabrasReservadas[0][contadorTemporal])) {
//                return band = true;
//            }
//            contadorTemporal++;
//        }
//        return band;
//    }

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

    public ArrayList getListaErrores() {
        return listaErrores;
    }
}
