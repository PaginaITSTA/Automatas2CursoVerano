package A03_Analisis_lexico_sintactico_Semantico;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class analisisLexico {

    private String rows[];
    private ArrayList<String> listatokens, listaErrores;
    private int contadorGeneral, linea;
    private char[] cadenaGeneral;

    //Palabras reservadas del sistema
    private final String[][] palabraReservada = {{"int", "float"},
    {"palabraReservadaInt", "palabraReservadaFloat"}};

    public analisisLexico(String cadena) {
        this.listatokens = new ArrayList<>();
        //this.cadenaGeneral = cadena.toCharArray();
        this.contadorGeneral = 0;
        this.linea = 0;
        this.rows = cadena.split("\n");
    }

    //estado inicial del identificador
    public void estadoInicial() {
        cambiaLinea();
        q0();
    }

    private void q0() {
        boolean entro;
        while (linea <= rows.length) {
            if (contadorGeneral < cadenaGeneral.length) {
                entro = false;
                switch (cadenaGeneral[contadorGeneral]) {

                    //para saltar espacios en blanco
                    case ' ':
                        entro = true;
                        contadorGeneral++;
                        break;

                    //Para detectar las comas entre las variables
                    case ',':
                        entro = true;
                        q1();
                        break;
                }

                if (entro == false && analisisLexico.isNumeric(cadenaGeneral[contadorGeneral])) {
                    q3();
                } else if ((entro == false) && (analisisLexico.isLetter(cadenaGeneral[contadorGeneral]))) {
                    q2();
                }
            } else {
                cambiaLinea();
            }
        }
    }

    private void q1() {
        listatokens.add("separador");
        contadorGeneral++;
    }

    private void q2() {
        String temp = "";
        boolean diferent = false, digit = false;

        while (contadorGeneral < cadenaGeneral.length && diferent == false) {
            //System.out.println(cadena[contador]);
            if (cadenaGeneral[contadorGeneral] == ' ') {
                diferent = true;
            } else if (analisisLexico.isNumeric(cadenaGeneral[contadorGeneral])) {
                digit = true;
                temp = temp + cadenaGeneral[contadorGeneral];
                contadorGeneral++;
            } else if (analisisLexico.isLetter(cadenaGeneral[contadorGeneral])) {
                temp = temp + cadenaGeneral[contadorGeneral];
                contadorGeneral++;
            } else {
                diferent = true;
            }

        }

        System.out.println("temporal = " + temp);
        boolean reservada = isReserved(temp);

        if (digit) {
            listatokens.add("identificador");
        } else if (reservada) {
            listatokens.add(guaradaEnLista(temp));
        } else {
            listatokens.add("identificador");
        }
    }

    private void q3() {
        boolean decimal = false;

        while (contadorGeneral < cadenaGeneral.length) {
            if (cadenaGeneral[contadorGeneral] == ' ') {
                break;
            } else if (analisisLexico.isNumeric(cadenaGeneral[contadorGeneral])) {
                contadorGeneral++;
            } else if (cadenaGeneral[contadorGeneral] == '.') {
                decimal = true;
                contadorGeneral++;
            } else {
                break;
            }
        }

        if (decimal) {
            listaErrores.add("Numero decimal encontrado");
        } else {
            listaErrores.add("Numero entero encontrado");
        }
    }

    private String guaradaEnLista(String preservada) {
        for (int i = 0; i <= palabraReservada.length; i++) {
            //System.err.println("Es igual: " + pReservada + " a " + lista[0][i]);
            if (preservada.equals(palabraReservada[0][i])) {
                return palabraReservada[1][i];
                //System.out.println("lista[0][i] = " + lista[1][i]);
            }
        }
        return "";
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
        while (contadorTemporal < palabraReservada.length) {
            if (valor.equals(palabraReservada[0][contadorTemporal])) {
                System.out.println("es igual: " + valor + " a " + palabraReservada[contadorTemporal]);
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

    public ArrayList<String> getListaTokens() {
        return this.listatokens;
    }

    public ArrayList<String> getListaErroresLexicos() {
        return this.listaErrores;
    }
}
