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

    public void analizadorSemantico(ArrayList<token> lista) {
        this.listaTokens = lista;
        this.listaErrores = new ArrayList<>();
        listaSimbolos = new ArrayList<>();
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
        if (listaTokens.get(0).getToken().equals("public")) {

        }
    }

    private void cuerpo_Código() {

    }

    //método para obtener errores en el analisis sintactico
    public ArrayList<String> getListaErrores() {
        return this.listaErrores;
    }

}
