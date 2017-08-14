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
    //private 
    private int valorIncremVariableTemp;

    public analizadorSintactico(ArrayList<token> lista) {
        this.listaTokens = lista;
        this.listaErrores = new ArrayList<>();
        listaSimbolos = new ArrayList<>();
        contadorLista = 0;

    }

    public void Programa() {
        valorIncremVariableTemp = 1;
        //anexar a nodo raiz
        // -> PROGRAMA -> Inicio_programa -> cuerpo_codigo -> }

        /*
            Se comprueba que el inicio sea correcto para que pueda continuar :)               
         */
        if (inicio_Programa()) {
            //Busca código interno
            contadorLista++;
            if (cuerpo_Código()) {

                if (listaTokens.get(contadorLista).getToken().equals("llaveFin")) {
                    System.out.println("Llave final de la clase");
                } else {
                    System.out.println("Aquí se produce un error debido a que no esta la  llave final");
                }
            }

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

            if (listaTokens.get(contadorLista).getValor().equals("public")) {
                if (listaTokens.get(contadorLista + 1).getValor().equals("class")) {
                    if (listaTokens.get(contadorLista + 2).getToken().equals("Identificador")) {
                        if (listaTokens.get(contadorLista + 3).getToken().equals("llaveApertura")) {
                            listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 2).getValor(), "", "", listaTokens.get(contadorLista).getValor(), listaTokens.get(contadorLista).getLinea()));
                            contadorLista = contadorLista + 3;
                            System.out.println("Inicio del Programa Correcto :) ");
                            return true;
                        } else {
                            listaErrores.add("Error la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> llave de apertura");
                            return false;
                        }
                    } else {
                        listaErrores.add("Error la Linea: " + listaTokens.get(contadorLista + 2).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 2).getColumna() + " Se esperaba --> identificador");
                        return false;
                    }
                } else {
                    listaErrores.add("Error la Linea: " + listaTokens.get(contadorLista + 1).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 1).getColumna() + " Se esperaba --> class");
                    return false;
                }
            } else {
                listaErrores.add("Error la Linea: " + listaTokens.get(contadorLista).getLinea() + " en la Columna: " + listaTokens.get(contadorLista).getColumna() + " Se esperaba --> public");
                return false;
            }

        } else {
            System.out.println("No entro en el inicio del programa");
            return false;
        }

    }

    private boolean cuerpo_Código() {
        /*
        CUERPO_CODIGO -> declaración método
         */
        if (declaración()) {
            System.out.println("Entro en la parte de declaración\n");
        } else if (metodo()) {
            System.out.println("Salio del método");
            if (impresion()) {
                return true;
            }
            return true;
        } else if (impresion()) {
            return true;
        }
        return false;
    }

    private boolean declaración() {
        /*
        DECLARACION ->  declVE declaración | declVD declaración | declVB declaración | declVE | declVD | declVB
         */
        String opcion = listaTokens.get(contadorLista).getValor();
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

        if ((contadorLista + 5) < listaTokens.size()) {

            if (listaTokens.get(contadorLista).getValor().equals("Int")) {
                contadorLista++;
                if (listaTokens.get(contadorLista).getToken().equals("Identificador")) {
                    contadorLista++;
                    String opcion = listaTokens.get(contadorLista).getValor();

                    if (opcion.equals("=")) {
                        if (listaTokens.get(contadorLista).getToken().equals("operadorDeAsignacion")) {
                            contadorLista++;
                            if (listaTokens.get(contadorLista).getToken().equals("NumeroEntero")) {
                                contadorLista++;
                                if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                                    listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista - 3).getToken(), listaTokens.get(contadorLista - 3).getValor(), listaTokens.get(contadorLista - 4).getValor(), listaTokens.get(contadorLista - 1).getValor(), "", listaTokens.get(contadorLista).getLinea()));
                                    contadorLista++;
                                    //System.out.println("Declaracion de tipo 1 INT Correcto");
                                    return true;
                                } else {
                                    listaErrores.add("Error de Declaración INT en la Linea: " + listaTokens.get(contadorLista + 5).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 5).getColumna() + " Se esperaba --> $");
                                }
                            } else {
                                listaErrores.add("Error de Declaración INT en la Linea: " + listaTokens.get(contadorLista + 4).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 4).getColumna() + " Se esperaba --> Numero Entero");
                            }
                        } else {
                            listaErrores.add("Error de Declaración INT en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> =");
                        }
                    } else if (opcion.equals("$")) {
                        if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                            listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista - 1).getToken(), listaTokens.get(contadorLista - 1).getValor(), "Int", "0", "", listaTokens.get(contadorLista).getLinea()));
                            contadorLista++;
                            //System.out.println("Declaracion de tipo 2 INT Correcto");
                            return true;
                        } else {
                            listaErrores.add("Error de Declaración INT en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> $");
                        }
                    } else {
                        listaErrores.add("Error de Declaración INT en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> = | $");
                    }

                } else {
                    listaErrores.add("Error de Declaración INT en la Linea: " + listaTokens.get(contadorLista + 2).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 2).getColumna() + " Se esperaba --> identificador");
                }
            } else {
                listaErrores.add("Error de Declaración INT en la Linea: " + listaTokens.get(contadorLista + 1).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 1).getColumna() + " Se esperaba --> int");
            }
        }

        return false;

    }

    private boolean declVD() {
        /*
        declVD -> float identificador $ | float identificador = num.num $
        float a = 2.1 $
        float b $
         */

        if ((contadorLista + 5) < listaTokens.size()) {
            //contadorLista++;
            System.out.println("Se espera float y se da: " + listaTokens.get(contadorLista).getValor());
            if (listaTokens.get(contadorLista).getValor().equals("float")) {
                contadorLista++;
                if (listaTokens.get(contadorLista).getToken().equals("Identificador")) {
                    String variable = listaTokens.get(contadorLista).getValor();
                    contadorLista++;
                    String opcion = listaTokens.get(contadorLista).getValor();
                    if (opcion.equals("=")) {
                        if (listaTokens.get(contadorLista).getToken().equals("operadorDeAsignacion")) {
                            contadorLista++;
                            String resultadoExp = Exp();
                            if (!resultadoExp.isEmpty()) {
                                //contadorLista++;
                                System.out.println("Se espera un delimitador y se da: " + listaTokens.get(contadorLista).getToken());
                                if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                                    listaSimbolos.add(new listaSimbolos("Identificador", variable, "float", resultadoExp, "", listaTokens.get(contadorLista).getLinea()));
                                    contadorLista++;
                                    return true;
                                } else {
                                    listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 5).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 5).getColumna() + " Se esperaba --> $");
                                }
                                //listaSimbolos.add(new listaSimbolos("Identificador", "", "float", resultadoExp, "", listaTokens.get(contadorLista).getLinea()));
                                //return true;
                            } else if (listaTokens.get(contadorLista).getToken().equals("NumeroDecimal")) {
                                contadorLista++;
                                System.out.println("Se espera un delimitador y se da: " + listaTokens.get(contadorLista).getToken());
                                if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                                    listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista - 3).getToken(), listaTokens.get(contadorLista - 3).getValor(), listaTokens.get(contadorLista - 4).getValor(), listaTokens.get(contadorLista - 1).getValor(), "", listaTokens.get(contadorLista + 1).getLinea()));
                                    contadorLista++;
                                    return true;
                                } else {
                                    listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 5).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 5).getColumna() + " Se esperaba --> $");
                                }
                            }
                            /*
                            if (listaTokens.get(contadorLista).getToken().equals("NumeroDecimal")) {
                                contadorLista++;
                                System.out.println("Se espera un delimitador y se da: " + listaTokens.get(contadorLista).getToken());
                                if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                                    listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista - 3).getToken(), listaTokens.get(contadorLista - 3).getValor(), listaTokens.get(contadorLista - 4).getValor(), listaTokens.get(contadorLista - 1).getValor(), "", listaTokens.get(contadorLista + 1).getLinea()));
                                    contadorLista++;
                                    return true;
                                } else {
                                    listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 5).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 5).getColumna() + " Se esperaba --> $");
                                }
                            } else {
                                String resultadoExp = Exp();
                                if (!resultadoExp.isEmpty()) {
                                    listaSimbolos.add(new listaSimbolos("Identificador", "", "float", resultadoExp, "", listaTokens.get(contadorLista).getLinea()));
                                    return true;
                                } else {
                                    listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 4).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 4).getColumna() + " Se esperaba --> Numero Decimal");
                                }
                            }
                             */
                        } else {
                            listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> =");
                        }
                    } else if (opcion.equals("$")) {
                        if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                            listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista - 1).getToken(), listaTokens.get(contadorLista - 1).getValor(), listaTokens.get(contadorLista - 2).getValor(), "0", "", listaTokens.get(contadorLista).getLinea()));
                            contadorLista++;
                            return true;
                        } else {
                            listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> $");
                        }
                    } else {
                        contadorLista--;
                        listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> = | $");
                    }

                } else {
                    contadorLista--;
                    listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 2).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 2).getColumna() + " Se esperaba --> identificador");
                }
            } else {
                contadorLista--;
                listaErrores.add("Error de Declaración FLOAT en la Linea: " + listaTokens.get(contadorLista + 1).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 1).getColumna() + " Se esperaba --> float");
            }
        }

        return false;

    }

    private boolean declVB() {
        /*
        declVB -> bool identificador $ | bool identificador = valorbool $
         */
        if ((contadorLista + 3) < listaTokens.size()) {
            if (listaTokens.get(contadorLista).getValor().equals("Boolean")) {
                contadorLista++;
                if (listaTokens.get(contadorLista).getToken().equals("Identificador")) {
                    contadorLista++;
                    String opcion = listaTokens.get(contadorLista).getValor();
                    if (opcion.equals("=")) {
                        if (listaTokens.get(contadorLista).getToken().equals("operadorDeAsignacion")) {
                            contadorLista++;
                            if (listaTokens.get(contadorLista).getValor().equals("true") || listaTokens.get(contadorLista).getValor().equals("false")) {
                                contadorLista++;
                                if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                                    listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista - 3).getToken(), listaTokens.get(contadorLista - 3).getValor(), listaTokens.get(contadorLista - 4).getValor(), listaTokens.get(contadorLista - 1).getValor(), "", listaTokens.get(contadorLista).getLinea()));
                                    contadorLista++;
                                    //System.out.println("Declaracion de tipo 1 BOOL Correcto");
                                    return true;
                                } else {
                                    listaErrores.add("Error de Declaración BOOL en la Linea: " + listaTokens.get(contadorLista + 5).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 5).getColumna() + " Se esperaba --> $");
                                }
                            } else {
                                listaErrores.add("Error de Declaración BOOL en la Linea: " + listaTokens.get(contadorLista + 4).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 4).getColumna() + " Se esperaba --> true | false");
                            }
                        } else {
                            listaErrores.add("Error de Declaración BOOL en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> =");
                        }
                    } else if (opcion.equals("$")) {
                        if (listaTokens.get(contadorLista).getToken().equals("Delimitador")) {
                            listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista - 1).getToken(), listaTokens.get(contadorLista - 1).getValor(), listaTokens.get(contadorLista - 2).getValor(), "0", "", listaTokens.get(contadorLista).getLinea()));
                            contadorLista++;
                            //System.out.println("Declaracion de tipo 2 BOOL Correcto");
                            return true;
                        } else {
                            listaErrores.add("Error de Declaración BOOL en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> $");
                        }
                    } else {
                        listaErrores.add("Error de Declaración BOOL en la Linea: " + listaTokens.get(contadorLista + 3).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 3).getColumna() + " Se esperaba --> = | $");
                    }

                } else {
                    listaErrores.add("Error de Declaración BOOL en la Linea: " + listaTokens.get(contadorLista + 2).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 2).getColumna() + " Se esperaba --> identificador");
                }
            } else {
                listaErrores.add("Error de Declaración BOOL en la Linea: " + listaTokens.get(contadorLista + 1).getLinea() + " en la Columna: " + listaTokens.get(contadorLista + 1).getColumna() + " Se esperaba --> Boolean");
            }
        }

        return false;

    }

    private boolean declVEexp() {
//        if ((contadorLista + 5) < listaTokens.size()) {
        contadorLista = contadorLista + 4;
        System.out.println(listaTokens.get(contadorLista).getValor());
        System.out.println(contadorLista);
        if (listaTokens.get(contadorLista).getToken().equals("Identificador")) {
            if (listaTokens.get(contadorLista + 1).getToken().equals("OperadorSuma")
                    || listaTokens.get(contadorLista + 1).getToken().equals("OperadorResta")
                    || listaTokens.get(contadorLista + 1).getToken().equals("OperadorMultiplicacion")
                    || listaTokens.get(contadorLista + 1).getToken().equals("OperadorDivision")) {
                if (listaTokens.get(contadorLista + 2).getToken().equals("Identificador")) {
                    if (listaTokens.get(contadorLista + 3).getToken().equals("Delimitador")) {
                        //listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 2).getToken(), listaTokens.get(contadorLista + 2).getValor(), listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 4).getValor(), "", listaTokens.get(contadorLista).getLinea()));
                        contadorLista = contadorLista + 7;
                        System.out.println("Declaracion de tipo 3 INT Correcto");
                        return true;
                    } else {
                        System.out.println("Hasta aqui errror");

                    }

                }
            }
        }
//        }
        return false;
    }

    //Al parecer esta lista la condición, ya que se ejecuta en el método Term()
    private String Exp() {
        System.out.println("Entro a ver si es una Exp()");
        String resultadoDeTermino = Term(), resultadoDeExp = "", operadorSumatorio = "";
        if (!resultadoDeTermino.isEmpty()) {
            System.out.println("Se espera un valor de suma o resta o regresa el valor y da: " + listaTokens.get(contadorLista).getToken()
                    + " -> " + listaTokens.get(contadorLista).getValor());
            if (listaTokens.get(contadorLista).getToken().equals("OperadorSuma") || listaTokens.get(contadorLista).getToken().equals("OperadorResta")) {
                operadorSumatorio = listaTokens.get(contadorLista).getValor();
                contadorLista++;
                resultadoDeExp = Exp();
                if (!resultadoDeExp.isEmpty()) {
                    listaSimbolos.add(new listaSimbolos("Operacion", operadorSumatorio, resultadoDeTermino, resultadoDeExp, "resultado" + valorIncremVariableTemp, listaTokens.get(contadorLista).getLinea()));
                    valorIncremVariableTemp++;
                    return "resultado" + (valorIncremVariableTemp - 1);
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
                System.out.println("Se espera una mul o div y se da: " + listaTokens.get(contadorLista).getToken() + " -> "
                        + listaTokens.get(contadorLista).getValor());
                if (listaTokens.get(contadorLista).getToken().equals("OperadorMultiplicacion")
                        || listaTokens.get(contadorLista).getToken().equals("OperadorDivision")) {
                    operadorMultiplicativo = listaTokens.get(contadorLista).getValor();
                    contadorLista++;
                    resultadoDeTerm = Term();
                    if (!resultadoDeTerm.isEmpty()) {
                        listaSimbolos.add(new listaSimbolos("operacion", operadorMultiplicativo, resultadoDeFactor, resultadoDeTerm, "resultado" + valorIncremVariableTemp, listaTokens.get(contadorLista).getLinea()));
                        valorIncremVariableTemp++;
                        return "resultado" + (valorIncremVariableTemp - 1);
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
        System.out.println(listaTokens.get(contadorLista + 1).getValor()
                + " " + listaTokens.get(contadorLista + 2).getValor()
                + " " + listaTokens.get(contadorLista + 3).getValor()
                + " " + listaTokens.get(contadorLista + 4).getValor()
                + " " + listaTokens.get(contadorLista + 5).getValor() + " " + listaTokens.get(contadorLista).getLinea());

        if ((contadorLista + 1) < listaTokens.size()) {
            String Identificador = "";
            System.out.println("Se espera un identificador o numero y se da: " + listaTokens.get(contadorLista).getToken() + " -> " + listaTokens.get(contadorLista).getValor());
            if (listaTokens.get(contadorLista).getToken().equals("Identificador")) {
                Identificador = listaTokens.get(contadorLista).getValor();
                contadorLista++;
                System.out.println("identificador encontrado");
                return Identificador;
            } else if (listaTokens.get(contadorLista).getToken().equals("NumeroDecimal") || listaTokens.get(contadorLista).getToken().equals("NumeroEntero")) {
                Identificador = listaTokens.get(contadorLista).getValor();
                contadorLista++;
                System.out.println("Numero encontrado");
                return Identificador;
            }
        }
        System.out.println("Salio de factor por falta de tokens");
        return "";
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
        //contadorLista++;
        if ((contadorLista + 4) < listaTokens.size()) {

            if ((listaTokens.get(contadorLista).getValor().equals("public") || listaTokens.get(contadorLista).getValor().equals("private"))
                    && listaTokens.get(contadorLista + 1).getToken().equals("Identificador")
                    && listaTokens.get(contadorLista + 2).getToken().equals("ParentesisInicio")
                    && listaTokens.get(contadorLista + 3).getToken().equals("ParentesisFin")
                    && listaTokens.get(contadorLista + 4).getToken().equals("llaveApertura")) {
                listaSimbolos.add(new listaSimbolos("Metodo", listaTokens.get(contadorLista + 1).getValor(), "", "", listaTokens.get(contadorLista).getValor(), listaTokens.get(contadorLista).getLinea()));
                contadorLista = contadorLista + 5;

                //En éste punto se debe de comprobar si tiene alguna funcion a dentro o concluye con una llave
                if ((contadorLista + 1) < listaTokens.size()) {
                    System.out.println("Entro a buscar la llave de cierre");
                    if (listaTokens.get(contadorLista).getToken().equals("llaveFin")) {
                        System.out.println("Llego al final del método");
                    } else if (funcion()) {
                        //Aquí se debe de probar la distancia de la lista para evitar que produsca un OutIndexException
                        if (listaTokens.get(contadorLista).getToken().equals("llaveFin")) {
                            System.out.println("Se encontro la llave final del método y todo completo");
                            contadorLista++;
                            return true;
                        } else {
                            listaErrores.add("Falta una llave de cierre");
                            System.out.println("Aqui se produce un error ya que no está la llave final del método");
                        }
                        System.out.println("Tiene una funcion interna");
                    }
                } else {
                    System.err.println("Aqui se debe de adherir una un error por falta de una llave final");
                }
                return true;

            }
            return false;

        }

        return false;
    }

    private boolean funcion() {
        System.out.println("Entro a buscar una función");
        //FUNCIÓN -> cond_if | cond_while | cod_for 

        if (cond_if()) {
            return true;
        } else if (cond_while()) {
            return true;
        }
        /*
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
                                    if (listaTokens.get(contadorLista).getToken().equals("llaveFin")) {
                                        System.out.println("Encontro la llave final del if");
                                        contadorLista++;
                                        return true;
                                    } else {
                                        System.out.println("Aquí debe de ir un error en caso de que no esté la llave de fin");
                                    }
                                    return true;
                                } else {

                                    System.out.println("Termino la primera parte de if, pero no encontro la llave fin");
                                    return false;
                                }

                            } else {
                                listaErrores.add("Error en la Linea: " + listaTokens.get(contadorLista).getLinea() + " en la Columna: " + listaTokens.get(contadorLista).getColumna() + " se esperaba --> { ");
                                return false;
                            }
                        } else {
                            listaErrores.add("Error en la Linea: " + listaTokens.get(contadorLista).getLinea() + " en la Columna: " + listaTokens.get(contadorLista).getColumna() + " se esperaba --> ) ");
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    listaErrores.add("Error en la Linea: " + listaTokens.get(contadorLista).getLinea() + " en la Columna: " + listaTokens.get(contadorLista).getColumna() + " se esperaba --> ( ");
                    return false;
                }

            } else {
                listaErrores.add("Error en la Linea: " + listaTokens.get(contadorLista).getLinea() + " en la Columna: " + listaTokens.get(contadorLista).getColumna() + " se esperaba --> if ");
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
                    listaSimbolos.add(new listaSimbolos(origenDeLaFuncion, operadorLogico, resultadoDeExp, resultadoDeExp2, "resultado" + valorIncremVariableTemp, listaTokens.get(contadorLista).getLinea()));
                    valorIncremVariableTemp++;
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
        System.out.println("Entro en el metodo de while");
        if ((contadorLista + 5) < listaTokens.size()) {
            System.out.println("Se espera un while y se da: " + listaTokens.get(contadorLista).getValor());
            if (listaTokens.get(contadorLista).getValor().equals("while")) {
                contadorLista++;
                System.out.println("Encontró el while");
                if (listaTokens.get(contadorLista).getToken().equals("ParentesisInicio")) {
                    contadorLista++;
                    System.out.println("Encontró el parentesis de inicio");
                    //En esta parte se comprueba que tenga una condición en el método if.
                    if (condicion("while")) {
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
                            listaSimbolos.add(new listaSimbolos("Impresion", "", "", listaTokens.get(contadorLista - 2).getValor(), "", listaTokens.get(contadorLista).getLinea()));
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
