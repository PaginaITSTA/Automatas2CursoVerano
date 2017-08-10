package Lenguaje_SID.AFD;

import java.util.ArrayList;

/**
 *
 * @author Erick, Rosa, Rodrigo
 */
public class analizadorSintactico {

    //Listas de errores en caso de que tenga en el analisis sintactico
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

        /*
            Se comprueba que el inicio sea correcto para que pueda continuar :)               
         */
        if (inicio_Programa()) {
            cuerpo_Código();
        }

    }

    private boolean inicio_Programa() {
        //anexa a nodo
        // -> INICIO_PROGRAMA -> public -> class -> identificador -> {
        if (comprobarInicio()) {
            return true;
        }
        System.out.println("Inicio Incorrecto");
        return false;

    }

    private boolean comprobarInicio() {
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
                return true;
            } else {
                listaErrores.add("Error la Linea " + listaTokens.get(contadorLista).getLinea() + " Se esperaba --> public class identifiador { <--");
                System.out.println("Error en la Linea: " + listaTokens.get(contadorLista).getLinea() + " se esperaba -- > * public class identificador { *");
                return false;
            }

        } else {
            System.out.println("No entro en el inicio del programa");
            return false;
        }

    }

    private void cuerpo_Código() {
        /*
        CUERPO_CODIGO -> declaración método
         */
        if (declaración()) {
            System.out.println("Entro en la parte de declaración\n");
        } else if (metodo()) {
            System.out.println("Salio del método");
        }
    }

    private boolean declaración() {
        /*
        DECLARACION ->  declVE declaración | declVD declaración | declVB declaración | declVE | declVD | declVB
         */

        String opcion = listaTokens.get(contadorLista + 1).getValor();
        switch (opcion) {
            case "Int":
                if (declVE()) {
                    declaración();
                }
                break;
            case "float":
                if (declVD()) {
                    declaración();
                }
                break;
            case "Boolean":
                if (declVB()) {
                    declaración();
                }
                break;
        }
        return false;

    }

    private boolean declVE() {
        /*
        declVE -> Int identificador $ | Int identificador = num $ | Int identificador = Exp;
         */

        String opcion = listaTokens.get(contadorLista + 3).getValor();
        System.out.println(opcion);

        switch (opcion) {
            case "=":
                if ((contadorLista + 5) < listaTokens.size()) {

                    if (listaTokens.get(contadorLista + 1).getValor().equals("Int")
                            && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                            && listaTokens.get(contadorLista + 3).getToken().equals("operadorDeAsignacion")
                            && listaTokens.get(contadorLista + 4).getToken().equals("NumeroEntero")
                            && listaTokens.get(contadorLista + 5).getToken().equals("Delimitador")) {
                        listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 4).getValor(), ""));
                        contadorLista = contadorLista + 5;
                        System.out.println("Declaracion de tipo 1 INT Correcto");
                        return true;
                    } else {
                        listaErrores.add("Error de Declaración INT en la Linea " + listaTokens.get(contadorLista + 1).getLinea() + " Se esperaba --> Tipo de Dato Identificador = Valor $");

                    }

                }
                break;
            case "$":
                if ((contadorLista + 3) < listaTokens.size()) {
                    if (listaTokens.get(contadorLista + 1).getValor().equals("Int")
                            && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                            && listaTokens.get(contadorLista + 3).getToken().equals("Delimitador")) {
                        listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), "Int", "0", ""));
                        contadorLista = contadorLista + 3;
                        System.out.println("Declaracion de tipo 2 INT Correcto");
                        return true;
                    } else {
                        listaErrores.add("Error de Declaración INT en la Linea " + listaTokens.get(contadorLista + 1).getLinea() + " Se esperaba --> Tipo de dato Identificador $");

                    }
                }
                break;
        }

        declVEexp();
        return false;

    }

    private boolean declVEexp() {
        if ((contadorLista + 5) < listaTokens.size()) {

            if (listaTokens.get(contadorLista + 1).getValor().equals("Int")) {
                if (listaTokens.get(contadorLista + 2).getToken().equals("Identificador")) {
                    if (listaTokens.get(contadorLista + 3).getToken().equals("operadorDeAsignacion")) {
                        String resultadoDeExp = Exp();
                        if (!resultadoDeExp.isEmpty()) {
                            if (listaTokens.get(contadorLista + 5).getToken().equals("Delimitador")) {
                                listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 4).getValor(), ""));
                                contadorLista = contadorLista + 5;
                                System.out.println("Declaracion de tipo 3 INT Correcto");
                                return true;
                            } else {
                                listaErrores.add("Error de Declaración INT 3 en la Linea " + listaTokens.get(contadorLista + 1).getLinea() + " Se esperaba --> Tipo de Dato Identificador = Valor $");

                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //Al parecer esta lista la condición, ya que se ejecuta en el método Term()
    private String Exp() {
        System.out.println("Entro a ver si es una Exp()");
        String resultadoDeTermino = Term(), resultadoDeExp = "", operadorSumatorio = "";
        if (!resultadoDeTermino.isEmpty()) {
            //contadorLista++;
            System.out.println("Exp: "+listaTokens.get(contadorLista).getToken()+"Linea: "+listaTokens.get(contadorLista).getLinea());// impresion por Rosa
            if (listaTokens.get(contadorLista).getToken().equals("OperadorSuma") || listaTokens.get(contadorLista).getToken().equals("OperadorResta")) {
                operadorSumatorio = listaTokens.get(contadorLista + 1).getValor();
                resultadoDeExp = Exp();
                if (!resultadoDeExp.isEmpty()) {
                    return resultadoDeExp;
                } else {
                    return "";
                }
            }
            return resultadoDeTermino;
        }
        return "";
    }

    private String Term() {
        String resultadoDeFactor = "", resultadoDeTerm = "", operadorMultiplicativo = "";
        //Term -> Factor * Term | Factor / Term | Factor
        System.out.println("Entro a ver si es un Term()");
        if ((contadorLista + 2) < listaTokens.size()) {
            resultadoDeFactor = factor();
            if (!resultadoDeFactor.isEmpty()) {
                System.out.println("Term: "+listaTokens.get(contadorLista).getToken()+"Linea: "+listaTokens.get(contadorLista).getLinea()); // impresion por Rosa
                if (listaTokens.get(contadorLista).getToken().equals("OperadorMultiplicacion")
                        || listaTokens.get(contadorLista).getToken().equals("OperadorDivision")) {
                    operadorMultiplicativo = listaTokens.get(contadorLista).getValor();
                    resultadoDeTerm = Term();
                    if (!resultadoDeTerm.isEmpty()) {
                        listaSimbolos.add(new listaSimbolos("", operadorMultiplicativo, resultadoDeFactor, resultadoDeTerm, "Debe una variable"));
                        return "RetornaUnavariable";
                    }
                }
                System.out.println("Solo encontro un factor");
                //contadorLista++;
                return resultadoDeFactor;
            }
        } else if ((contadorLista + 1) < listaTokens.size()) {
            resultadoDeFactor = factor();
            if (!resultadoDeFactor.isEmpty()) {
                System.out.println("Solo encontro un factor");
                return resultadoDeFactor;
            }
        }
        return "";
    }

    //Por el momento solo identifica a un identifiador o un dígito
    private String factor() {
        //Factor -> digito | identificador | (Exp)
        System.out.println("Entro a ver si es un factor()");
        System.out.println(listaTokens.get(contadorLista + 1).getValor() // Int
                + " " + listaTokens.get(contadorLista + 2).getValor()// identificador 
                + " " + listaTokens.get(contadorLista + 3).getValor()// =
                + " " + listaTokens.get(contadorLista + 4).getValor()// identificador o numero
                + " " + listaTokens.get(contadorLista + 5).getValor()// + - * /
                + " " + listaTokens.get(contadorLista + 6).getValor()// identificador o numero
                
                + " " + listaTokens.get(contadorLista+1).getLinea());// linea

        if ((contadorLista + 1) < listaTokens.size()) {
            String Identificador = "";
            System.out.println("Se espera un identificador o numero y se da: " + listaTokens.get(contadorLista+4).getToken() + " -> " + listaTokens.get(contadorLista+4).getValor());
            System.out.println("Factor: "+listaTokens.get(contadorLista).getToken()+"Linea: "+listaTokens.get(contadorLista).getLinea()); // impresion por rosa
            if (listaTokens.get(contadorLista+4).getToken().equals("Identificador")) {
                Identificador = listaTokens.get(contadorLista+4).getValor();
                contadorLista++;
                System.out.println("identificador encontrado");
                return Identificador;
            } else if (listaTokens.get(contadorLista+4).getToken().equals("NumeroDecimal") || listaTokens.get(contadorLista+4).getToken().equals("NumeroEntero")) {
                Identificador = listaTokens.get(contadorLista+4).getValor();
                contadorLista++;
                System.out.println("Numero encontrado");
                return Identificador;
            }
        }
        System.out.println("Salio de factor por falta de tokens");
        return "";
    }

    private boolean declVD() {
        /*
        declVD -> float identificador $ | float identificador = num.num $
        float a = 2.1 $
        float b $
         */

        String opcion = listaTokens.get(contadorLista + 3).getValor();
        System.out.println(opcion);

        switch (opcion) {
            case "=":
                if ((contadorLista + 5) < listaTokens.size()) {

                    if (listaTokens.get(contadorLista + 1).getValor().equals("float")
                            && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                            && listaTokens.get(contadorLista + 3).getToken().equals("operadorDeAsignacion")
                            && listaTokens.get(contadorLista + 4).getToken().equals("NumeroDecimal")
                            && listaTokens.get(contadorLista + 5).getToken().equals("Delimitador")) {
                        listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 4).getValor(), ""));
                        contadorLista = contadorLista + 5;
                        System.out.println("Declaracion de Tipo 1 FLOAT correcto");
                        return true;
                    } else {
                        listaErrores.add("Error de Declaración FLOAT en la Linea " + listaTokens.get(contadorLista + 1).getLinea() + " Se esperaba --> Tipo de Dato Identificador = Valor $");

                    }
                }
                break;
            case "$":

                if ((contadorLista + 3) < listaTokens.size()) {
                    if (listaTokens.get(contadorLista + 1).getValor().equals("float")
                            && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                            && listaTokens.get(contadorLista + 3).getToken().equals("Delimitador")) {
                        listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), listaTokens.get(contadorLista + 1).getValor(), "0.0", ""));
                        contadorLista = contadorLista + 3;
                        System.out.println("Declaracion de Tipo 2 FLOAT correcto");
                        return true;
                    } else {
                        listaErrores.add("Error de Declaración FLOAT en la Linea " + listaTokens.get(contadorLista + 1).getLinea() + " Se esperaba --> Tipo de dato Identificador $");

                    }
                }
                break;
        }

        return false;

    }

    private boolean declVB() {
        /*
        declVB -> bool identificador $ | bool identificador = valorbool $
        
        Boolean a = true$
        Boolean b =false$
         */

        String opcion = listaTokens.get(contadorLista + 3).getValor();
        System.out.println(opcion);

        switch (opcion) {
            case "=":
                if ((contadorLista + 5) < listaTokens.size()) {
                    if (listaTokens.get(contadorLista + 1).getValor().equals("Boolean")
                            && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                            && listaTokens.get(contadorLista + 3).getToken().equals("operadorDeAsignacion")
                            && (listaTokens.get(contadorLista + 4).getValor().equals("false") || listaTokens.get(contadorLista + 4).getValor().equals("true"))
                            && listaTokens.get(contadorLista + 5).getToken().equals("Delimitador")) {
                        listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 4).getValor(), ""));
                        contadorLista = contadorLista + 5;
                        System.out.println("Declaracion de tipo 1 BOOL Correcto");
                        return true;
                    } else {
                        listaErrores.add("Error de Declaración BOOL en la Linea " + listaTokens.get(contadorLista + 1).getLinea() + " Se esperaba --> Tipo de Dato Identificador = Valor $");
                    }

                }
                break;
            case "$":
                if ((contadorLista + 3) < listaTokens.size()) {
                    if (listaTokens.get(contadorLista + 1).getValor().equals("Boolean")
                            && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")
                            && listaTokens.get(contadorLista + 3).getToken().equals("Delimitador")) {
                        listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), listaTokens.get(contadorLista + 1).getValor(), "Sin valor", ""));
                        contadorLista = contadorLista + 3;
                        System.out.println("Declaracion de tipo 2 BOOL Correcto");
                        return true;
                    } else {

                        listaErrores.add("Error de Declaración BOOL en la Linea " + listaTokens.get(contadorLista + 1).getLinea() + " Se esperaba --> Tipo de dato Identificador $");

                    }

                }
                break;
        }

        return false;
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
        contadorLista++;
        if ((contadorLista + 4) < listaTokens.size()) {

            if ((listaTokens.get(contadorLista).getValor().equals("public") || listaTokens.get(contadorLista).getValor().equals("private"))
                    && listaTokens.get(contadorLista + 1).getToken().equals("Identificador")
                    && listaTokens.get(contadorLista + 2).getToken().equals("ParentesisInicio")
                    && listaTokens.get(contadorLista + 3).getToken().equals("ParentesisFin")
                    && listaTokens.get(contadorLista + 4).getToken().equals("llaveApertura")) {
                listaSimbolos.add(new listaSimbolos("Metodo", listaTokens.get(contadorLista + 1).getValor(), "", "", listaTokens.get(contadorLista).getValor()));
                contadorLista = contadorLista + 5;

                //En éste punto se debe de comprobar si tiene alguna funcion a dentro o concluye con una llave
                if ((contadorLista + 1) < listaTokens.size()) {
                    System.out.println("Entro a buscar la llave de cierre");
                    if (listaTokens.get(contadorLista).getToken().equals("llaveFin")) {
                        System.out.println("Llego al final del método");
                    } else if (funcion()) {
                        if (listaTokens.get(contadorLista).getToken().equals("llaveFin")) {
                            System.out.println("Se encontro la llave final del método y todo completo");
                            return true;
                        } else {
                            System.out.println("Aqui se produce un error ya que no está la llave final del método");
                        }
                        System.out.println("Tiene una funcion interna");
                    }
                } else {
                    System.err.println("Aqui se debe de adherir una un error por falta de una llave final");
                }
                return true;

            } else {
                return false;
            }
        }

        return false;
    }

    private boolean funcion() {
        System.out.println("Entro a buscar una función");
        //FUNCIÓN -> cond_if | cond_while | cod_for 

        if (cond_if()) {
            return true;
        }/* else if (cond_while()) {
            return true;
        }
        
        No esta declarado en la gramatica
        else if (cod_for()) {
            return true;
        }
         */
        return false;
    }

    private boolean cond_if() {
        //COND_IF -> if (condición) {impresión} else{ impresión}
        System.out.println("Entro en el metodo de if");
        if ((contadorLista + 5) < listaTokens.size()) {
            System.out.println("Se espera un if y se da: " + listaTokens.get(contadorLista).getValor());
            if (listaTokens.get(contadorLista).getValor().equals("if")) {
                contadorLista++;
                System.out.println("Encontró el if");
                if (listaTokens.get(contadorLista).getToken().equals("ParentesisInicio")) {
                    contadorLista++;
                    System.out.println("Encontró el parentesis de inicio");
                    //En esta parte se comprueba que tenga una condición en el método if.
                    if (condicion("if")) {
                        System.out.println("Paso de la función");
                        if (listaTokens.get(contadorLista).getToken().equals("ParentesisFin")) {
                            System.out.println("Encontro el parentesis de fin");
                            contadorLista++;
                            if (listaTokens.get(contadorLista).getToken().equals("llaveApertura")) {
                                System.out.println("Primera parte del if completada.");
                                contadorLista++;
                                if (listaTokens.get(contadorLista).getToken().equals("llaveFin")) {
                                    return true;
                                } else if (impresion()) {
                                    System.out.println("Despues de la impresión aún se tiene: " + listaTokens.get(contadorLista).getValor());
                                    return true;
                                } else {
                                    
                                    System.out.println("Termino la primera parte de if, pero no encontro la llave fin");
                                    return false;
                                }
                                
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
                
            } else {
                return false;
            }
        }

        return false;
    }

    private boolean condicion(String origenDeLafuncion) {
        System.out.println("Entro a verificar si existe alguna condicion");
        //CONDICION -> condición_lógica | condición_AND | condición_OR
        if (condicion_logica(origenDeLafuncion)) {
            return true;
        } else if (condicion_AND(origenDeLafuncion)) {
            return true;
        } else if (condicion_OR()) {
            return true;
        }
        return false;
    }

    private boolean condicion_logica(String origenDeLaFuncion) {
        System.out.println("Entro a ver si es una condición lógica");
        //CONDICIÓN_LÓGICA -> Exp > Exp | Exp < Exp | Exp >= Exp | Exp <= Exp | Exp == Exp | Exp !=  Exp
        String resultadoDeExp = Exp(), resultadoDeExp2 = "", operadorLogico = "";
        if (!resultadoDeExp.isEmpty()) {
            boolean band = false;
            System.out.println("Entro a ver si es >, < >=, etc, lo que se da es: " + listaTokens.get(contadorLista).getToken());
            switch (listaTokens.get(contadorLista).getToken()) {
                case "OperadorMayorQue":
                    band = true;
                    break;
                case "OperadorMenorQue":
                    band = true;
                    break;
                case "OperadorMayorOIgualQue":
                    band = true;
                    break;
                case "OperadorMenorOIgualQue":
                    band = true;
                    break;
                case "operadorIgualIgual":
                    band = true;
                    break;
                case "OperadorDesigual":
                    band = true;
                    break;
                default:
                    break;
            }

            if (band) {
                operadorLogico = listaTokens.get(contadorLista).getToken();
                contadorLista++;
                resultadoDeExp2 = Exp();
                if (!resultadoDeExp2.isEmpty()) {
                    listaSimbolos.add(new listaSimbolos(origenDeLaFuncion, operadorLogico, resultadoDeExp, resultadoDeExp2, "VariableDebeSerIncrementable"));
                    System.out.println("Encontro, Exp operacionLogica");
                    return true;
                }
                /*
                System.out.println("Encontro un simbolo de >=, <= o algo así");
                return true;
                 */
            } else {
                System.out.println("Solo encontró una expresión");
                return true;
            }
        }
        /*
        if (Exp() && listaTokens.get(contadorLista + 1).getToken().equals("OperadorMayorQue") && Exp()) {
            return true;
        } else if (Exp() && listaTokens.get(contadorLista+1).getToken().equals("OperadorMenorQue") && Exp()) {
            return true;
        } else if (Exp() && listaTokens.get(contadorLista+1).getToken().equals("OperadorMayorOIgualQue") && Exp()) {
            return true;
        } else if (Exp() && listaTokens.get(contadorLista+1).getToken().equals("OperadorMenorOIgualQue") && Exp()) {
            return true;
        } else if (Exp() && listaTokens.get(contadorLista).getToken().equals("operadorIgualIgual") && Exp()) {
            return true;
        } else if (Exp() && listaTokens.get(contadorLista).getToken().equals("OperadorDesigual") && Exp()) {
            return true;
        }
         */
        return false;
    }

    private boolean condicion_AND(String OrigenDeLaFuncion) {
        //CONDICION AND -> condición_lógica && condición_lógica
        if (condicion_logica(OrigenDeLaFuncion) && listaTokens.get(contadorLista).getToken().equals("operadorAnd") && condicion_logica(OrigenDeLaFuncion)) {
            return true;
        }
        return false;
    }

    private boolean condicion_OR() {
        //CONDICION OR -> condición_lógica | | condición_lógica
        return false;
    }

    private boolean cond_while() {
        //COND_WHLE -> while (condición) {impresión}
        if (listaTokens.get(contadorLista).getValor().equals("while")
                && listaTokens.get(contadorLista).getToken().equals("ParentesisInicio")
                && condicion("while")
                && listaTokens.get(contadorLista).getToken().equals("ParentesisFin")
                && listaTokens.get(contadorLista).equals("llaveApertura")
                && impresion()
                && listaTokens.get(contadorLista).equals("llaveFin")) {
            return true;
        }
        return false;
    }

    //No está declarada de lo que hace en la gramática
    private boolean cod_for() {

        return false;
    }

    private boolean impresion() {
        //IMPRESIÓN  -> Print ( CadenaDeTexto )$
        System.out.println("Entro a la impresión dato esperado Print dado: " + listaTokens.get(contadorLista).getValor());
        if (listaTokens.get(contadorLista).getValor().equals("Print")) {
            contadorLista++;
            if (listaTokens.get(contadorLista).getToken().equals("ParentesisInicio")) {
                contadorLista++;
                if (listaTokens.get(contadorLista).getToken().equals("CadenaDeTexto")) {
                    contadorLista++;
                    if (listaTokens.get(contadorLista).getToken().equals("ParentesisFin")) {
                        contadorLista++;
                        if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                            listaSimbolos.add(new listaSimbolos("Impresion", "", "", listaTokens.get(contadorLista - 2).getValor(), ""));
                            contadorLista++;
                            return true;
                        }
                    }
                }
            }
        }
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
