package Lenguaje_SID.AFD;

import java.util.ArrayList;

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

    public void analizadorSemantico(ArrayList<token> lista) {
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
        // -> INICIO_PROGRAMA -> disponibilidad -> class -> identificador -> {

        comprobarInicio();
    }

    private void comprobarInicio() {
        /*
        diponibilidad clas identificador {
         */
        if (listaTokens.get(contadorLista).getToken().equals("public") && listaTokens.get(contadorLista + 1).getToken().equals("Class") && listaTokens.get(contadorLista + 2).getToken().equals("Identificador")) {
            listaSimbolos.add(new listaSimbolos(listaTokens.get(contadorLista + 1).getValor(), listaTokens.get(contadorLista + 2).getValor(), "", "", listaTokens.get(contadorLista).getValor()));
        }
    }

    private void cuerpo_Código() {
        /*
        CUERPO_CODIGO -> declaración método
         */
    }
    
    private void declaración(){
        /*
        DECLARACION ->  declVE declaración | declVD declaración | declVB declaración | declVE | declVD | declVB
        */
    }
    
    private void metodo(){
        /*
        METODO -> disponibilidad  identificador () { función}
        */
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
