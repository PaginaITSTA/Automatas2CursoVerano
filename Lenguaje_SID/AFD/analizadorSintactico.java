package Lenguaje_SID.AFD;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author erick
 */
public class analizadorSintactico {

    //Listas de errores
    private ArrayList<String> listaErrores;
    //Lista en la cual se guardan la lista de simbolos obtenidos en esta análisis
    private ArrayList<listaSimbolos> listaSimbolos;
    //Lista de tokens obtenido en el análisis léxico
    private ArrayList<token> listaTokens;
    //Contador, para avanzar en la lista de tokens
    private int contadorLista;

    public analizadorSintactico(ArrayList<token> lista) {
        this.listaTokens = lista;
        this.listaErrores = new ArrayList<>();
        listaSimbolos = new ArrayList<>();
        contadorLista = 0;

    }

    public void Programa() {
        //anexar a nodo raiz
        // -> PROGRAMA -> Inicio_programa -> cuerpo_codigo -> }

        inicio_Programa();
        cuerpo_Código();

    }

    private void inicio_Programa() {
        //anexa a nodo
        // -> INICIO_PROGRAMA -> public -> class -> identificador -> {

        comprobarInicio();
    }

    private void comprobarInicio() {
        /*
        diponibilidad class identificador {
        
        Aquí, quizá se podría separar el if, para poder detectar algun error
         */
        if (contadorLista < listaTokens.size()) {

            if (listaTokens.get(contadorLista).getValor().equals("public")
                    && listaTokens.get(contadorLista + 1).getValor().equals("class")
                    && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                    && listaTokens.get(contadorLista + 3).getToken().equals("llaveApertura")) {
                listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 2).getValor(), "", "", listaTokens.get(contadorLista).getValor()));
                contadorLista = contadorLista + 3;
                System.out.println("Inicio del Programa Correcto :) ");

            } else {

                listaErrores.add("Sintaxis incorrecta en la Linea " + listaTokens.get(contadorLista).getLinea() + " se esperaba -- > *public class identificador {*");
            }

        } else {

            System.out.println("No entro en el inicio del programa");
        }

    }

    private void cuerpo_Código() {
        /*
        CUERPO_CODIGO -> declaración método
         */
        if (declaración()) {
            System.out.println("Entro en la parte de declaración\n");
        } else if (metodo()) {
            System.out.println("Entro en la parte de método\n");
        }
    }

    private boolean declaración() {
        /*
        DECLARACION ->  declVE declaración | declVD declaración | declVB declaración | declVE | declVD | declVB
         */
        System.out.println("Entro en la linea 90");
        if (declVE()) {
            declaración();
        } else if (declVD()) {
            declaración();
        } else if (declVB()) {
            declaración();
        } else if (declVE()) {

        } else if (declVD()) {

        } else if (declVB()) {

        }

        return false;

    }

    private boolean declVE() {
        /*
        declVE -> Int identificador $ | Int identificador = num $ | Int identificador = Exp;
         */
        if ((contadorLista + 5) < listaTokens.size()) {
            if (listaTokens.get(contadorLista + 1).getValor().equals("Int")
                    && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                    && listaTokens.get(contadorLista + 3).getToken().equals("operadorDeAsignacion")
                    && listaTokens.get(contadorLista + 4).getToken().equals("NumeroEntero")
                    && listaTokens.get(contadorLista + 5).getToken().equals("Delimitador")) {
                listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 4).getValor(), ""));

                contadorLista = contadorLista + 5;
                return true;
            }
        }
        if ((contadorLista + 3) < listaTokens.size()) {
            if (listaTokens.get(contadorLista + 1).getValor().equals("Int")
                    && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                    && listaTokens.get(contadorLista + 3).getToken().equals("Delimitador")) {
                listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), "Int", "0", ""));
                contadorLista = contadorLista + 3;
                return true;
            }
        } else {
            listaErrores.add("Sintaxis de Declaracion incorrecta en la Linea " + listaTokens.get(contadorLista).getLinea() + "");
            return false;
        }
        return false;
    }

    private void Exp() {
        /*
        Exp -> Exp + Term | Exp – Term | Term
         */
    }

    private void Term() {
        /*
        Term -> Term * Factor | Term / Factor | Factor
         */
    }

    private void factor() {
        /*
        Factor -> Exp | digito | identificador
         */
    }

    private boolean declVD() {
        /*
        declVD -> float identificador $ | float identificador = num.num $
         */
        if ((contadorLista + 2) < listaTokens.size()) {
            if (listaTokens.get(contadorLista).getToken().equals("Float")
                    && listaTokens.get(contadorLista + 1).getToken().equals("Identificador")
                    && listaTokens.get(contadorLista + 2).getToken().equals("Delimitador")) {
                contadorLista = contadorLista + 2;
                return true;
            }
        } else if ((contadorLista + 4) < listaTokens.size()) {
            if (listaTokens.get(contadorLista).getToken().equals("Float")
                    && listaTokens.get(contadorLista + 1).getToken().equals("Identificador")
                    && listaTokens.get(contadorLista + 2).getToken().equals("operadorDeAsignacion")
                    && listaTokens.get(contadorLista + 3).getToken().equals("NumeroDecimal")
                    && listaTokens.get(contadorLista + 4).getToken().equals("Delimitador")) {
                contadorLista = contadorLista + 4;
                return true;
            }
        }
        return false;

    }

    private boolean declVB() {
        /*
        declVB -> bool identificador $ | bool identificador = valorbool $
         */
        if (listaTokens.get(contadorLista).getToken().equals("Float")
                && listaTokens.get(contadorLista + 1).getToken().equals("Identificador")
                && listaTokens.get(contadorLista + 2).getToken().equals("Delimitador")) {
            contadorLista = contadorLista + 2;
            return true;
        } else if (listaTokens.get(contadorLista).getToken().equals("Float")
                && listaTokens.get(contadorLista + 1).getToken().equals("Identificador")
                && listaTokens.get(contadorLista + 2).getToken().equals("operadorDeAsignacion")
                && listaTokens.get(contadorLista + 3).getToken().equals("NumeroDecimal")
                && listaTokens.get(contadorLista + 4).getToken().equals("Delimitador")) {
            contadorLista = contadorLista + 4;
            return true;
        } else {
            return false;
        }
    }

    private boolean metodo() {
        /*
        METODO -> disponibilidad  identificador () { función}
         */
        System.out.println("Entro en el método");
        if (comprobarMetodo()) {
            return true;
        }
        return false;
    }

    private boolean comprobarMetodo() {
        //METODO -> disponibilidad  identificador () { función }
        //String clase, String nombreValor, String tipoDeDato, String valor, String disponibilidad
        System.out.println("Entro en la parte de comprobar método");
        if ((contadorLista + 4) < listaTokens.size()) {
            if (listaTokens.get(contadorLista).getToken().equals("public")
                    && listaTokens.get(contadorLista + 1).getToken().equals("Identificador")
                    && listaTokens.get(contadorLista + 2).getToken().equals("ParentesisInicio")
                    && listaTokens.get(contadorLista + 3).getToken().equals("ParentesisFin")
                    && listaTokens.get(contadorLista + 4).getToken().equals("llaveApertura")) {
                listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), "Int", "0", ""));
                contadorLista = contadorLista + 4;

                //En éste punto se debe de comprobar si tiene alguna funcion a dentro o concluye con una llave
                if ((contadorLista + 1) < listaTokens.size()) {
                    if (listaTokens.get(contadorLista).getToken().equals("llaveFin")) {
                        System.out.println("Llego al final del método");
                    } else {
                        System.out.println("Lista de errores debe de adherir que faltaba una un token de cierre de llave");
                    }
                }
                return true;

            } else {
                return false;
            }
        }

        return false;
    }

    private boolean funcion() {

        return false;
    }

    /*
    ***********************************************************************************************************
    **************************Area de extraccion de infomación del programa************************************
    ***********************************************************************************************************
     */
    public ArrayList getListaSimbolos() {
        return listaSimbolos;
    }

    //método para obtener errores en el analisis sintactico
    public ArrayList<String> getListaErrores() {
        return this.listaErrores;
    }

}
