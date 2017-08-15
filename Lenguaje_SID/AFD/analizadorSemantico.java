package Lenguaje_SID.AFD;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class analizadorSemantico {

    private ArrayList<listaSimbolos> Simbolos;
    private ArrayList<token> Token;
    private ArrayList<String> ErroresSemanticos;
    private int contadorLista;

    public analizadorSemantico(ArrayList<listaSimbolos> Simbolos, ArrayList<token> Token) {
        this.Simbolos = Simbolos;
        this.Token = Token;
        Simbolos = new ArrayList<>();
        ErroresSemanticos = new ArrayList<>();
        contadorLista = 0;
    }

    public void comprobar() {
        BuscarTS_Identificadores_iguales();
        Imprimir();
        BuscarTS_Comprobacion_de_Tipos();
    }

    private void BuscarTS_Identificadores_iguales() {

        for (int i = 0; i < Simbolos.size(); i++) {
            String identificador = Simbolos.get(contadorLista + i).getNombreValor();

            for (int j = i - 1; j >= 0; j--) {
                boolean valorar = Simbolos.get(j).getNombreValor().equals(identificador);
                if (valorar) {
                    if (isIdentificador(j)) {
                        ErroresSemanticos.add("Error, se encontraron variables con el mismo nombre dado: ( " + Simbolos.get(i).getNombreValor() + " ) en la linea: "
                                + Simbolos.get(i).getLinea());
                    }
                }
            }
        }
    }

    private void Imprimir() {
        System.out.println("\nImprimiendo Lista de Simbolos desde An. Semantico");
        for (int i = 0; i < Simbolos.size(); i++) {
            System.out.println("( " + Simbolos.get(contadorLista + i).getClase()
                    + " ) ( " + Simbolos.get(contadorLista + i).getNombreValor()
                    + " ) ( " + Simbolos.get(contadorLista + i).getTipoDeDato()
                    + " ) ( " + Simbolos.get(contadorLista + i).getValor()
                    + " ) ( " + Simbolos.get(contadorLista + i).getDisponibilidad()
                    + " ) ( " + Simbolos.get(contadorLista + i).getLinea() + " )");
        }
    }

    private void BuscarTS_Comprobacion_de_Tipos() {
        Boolean buscar = false, ident = false;
        String var, var2;
        for (int i = 0; i < Simbolos.size(); i++) {
            buscar = Simbolos.get(contadorLista + i).getClase().equals("Operacion") || Simbolos.get(contadorLista + i).getClase().equals("operacion");
            if (buscar == true) {
                var = Simbolos.get(contadorLista + i).getTipoDeDato();
                var2 = Simbolos.get(contadorLista + i).getValor();
                /*
                if (isIdentificadorTS(var) == false) {
                    if (isNumeroDecimal(var) || isNumeroEntero(var) || isNumeroDecimal(var2) || isNumeroEntero(var2)) {

                    } else {
                        ErroresSemanticos.add("El Identificador: ( " + var + " ) no se encuentra declarado. ");
                    }

                } else if (isIdentificadorTS(var2) == false) {
                    if (isNumeroDecimal(var) || isNumeroEntero(var) || isNumeroDecimal(var2) || isNumeroEntero(var2)) {

                    } else {
                        ErroresSemanticos.add("El Identificador: ( " + var2 + " ) no se encuentra declarado. ");
                    }
                } else {
                 */
                System.out.println("\nSe encontro: " + Simbolos.get(contadorLista + i).getClase() + " de: " + var + " | " + var2 + " en la Linea: " + Simbolos.get(contadorLista + i).getLinea());

                if (isIdentificador_Int(var) == true && isIdentificador_Int(var2) == true) {
                    System.out.println("\nSi, se puede realizar la Operación de tipo Int");
                } else if (isIdentificador_float(var) == true && isIdentificador_float(var2) == true) {
                    System.out.println("\nSi, se puede realizar la Operación de tipo float");
                } else if (isNumeroEntero(var) && isNumeroEntero(var2)) {
                    System.out.println("\nSi, se puede realizar la Operación de valores enteros");
                } else if ((isIdentificador_Int(var) || isIdentificador_Int(var2)) && (isNumeroEntero(var) || isNumeroEntero(var2))) {
                    System.out.println("\nSi, se puede realizar la Operación de valores enteros con identificadores del mismo tipo");
                } else if ((isIdentificador_float(var) || isIdentificador_float(var2)) && (isNumeroDecimal(var) || isNumeroDecimal(var2))) {
                    System.out.println("\nSi, se puede realizar la Operación de valores enteros con identificadores del mismo tipo");
                } else {
                    ErroresSemanticos.add("Error, en la linea: " + Simbolos.get(contadorLista + i).getLinea() + " los identificadores y los valores de: ( " + var + " ) y ( " + var2 + ") No son del mismo tipo");
                }

                //}
            }
        }
    }

    private boolean isIdentificador_Int(String valor) {
        String valorar;
        for (int i = 0; i < Simbolos.size(); i++) {
            valorar = Simbolos.get(contadorLista + i).getNombreValor();
            if (valorar.equals(valor)) {
                if (Simbolos.get(contadorLista + i).getTipoDeDato().equals("Int")) {
                    System.out.println("( " + valor + " ) Es identificador de tipo Int");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIdentificadorTS(String valor) {
        String valorar;
        for (int i = 0; i < Simbolos.size(); i++) {
            valorar = Simbolos.get(contadorLista + i).getNombreValor();
            if (valorar.equals(valor)) {
                if (Simbolos.get(contadorLista + i).getClase().equals("Identificador")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIdentificador_float(String valor) {
        String valorar;
        for (int i = 0; i < Simbolos.size(); i++) {
            valorar = Simbolos.get(contadorLista + i).getNombreValor();
            if (valorar.equals(valor)) {
                if (Simbolos.get(contadorLista + i).getTipoDeDato().equals("float")) {
                    System.out.println("( " + valor + " ) Es identificador de tipo float");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNumeroEntero(String valor) {
        String valorar;
        for (int i = 0; i < Token.size(); i++) {
            valorar = Token.get(contadorLista + i).getValor();

            if (valorar.equals(valor)) {
                if (Token.get(contadorLista + i).getToken().equals("NumeroEntero")) {
                    System.out.println(valor + " es un: " + Token.get(contadorLista + i).getToken());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNumeroDecimal(String valor) {
        String valorar;
        for (int i = 0; i < Token.size(); i++) {
            valorar = Token.get(contadorLista + i).getValor();

            if (valorar.equals(valor)) {
                if (Token.get(contadorLista + i).getToken().equals("NumeroDecimal")) {
                    System.out.println(valor + " es un: " + Token.get(contadorLista + i).getToken());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIdentificador(int valor) {
        boolean valorar = Simbolos.get(valor).getClase().equals("Identificador");
        if (valorar) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getErroresSemanticos() {
        return ErroresSemanticos;
    }

}
