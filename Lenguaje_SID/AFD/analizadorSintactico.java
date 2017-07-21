package Lenguaje_SID.AFD;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class analizadorSintactico {

    //Listas con las que se va a trabajar en el proyecto de clase
    private ArrayList<String> listaTokens, listaErrores, arbol;

    public void analizadorSemantico(ArrayList<String> lista) {
        this.listaTokens = lista;
        this.listaErrores = new ArrayList<>();
    }

    public void exp(){
        
    }
    
    private void inicioPrograma(){
        
    }
    
    private void cuerpoCódigo(){
        
    }
    
    private void finPrograma(){
        
    }
    
    
    
    //método para obtener errores en el analisis sintactico
    public ArrayList<String> getListaErrores() {
        return this.listaErrores;
    }

    //método para optener el arbol creado por el análisis del código
    public ArrayList<String> getArbol() {
        return this.arbol;
    }
}
