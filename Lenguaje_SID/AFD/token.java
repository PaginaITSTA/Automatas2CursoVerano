package Lenguaje_SID.AFD;

/**
 *
 * @author erick
 */
public class token {

    private String token, valor;
    private int linea, columna;

    public token() {

    }

    public token(String token, String valor, int linea, int columna) {
        this.token = token;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    public String getToken() {
        return token;
    }

    public String getValor() {
        return valor;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

   
    
    

}
