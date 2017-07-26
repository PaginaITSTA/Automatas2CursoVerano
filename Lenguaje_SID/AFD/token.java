package Lenguaje_SID.AFD;

/**
 *
 * @author erick
 */
public class token {

    private String token, valor;
    private int linea;

    public token() {

    }

    public token(String token, String valor, int linea) {
        this.token = token;
        this.valor = valor;
        this.linea = linea;
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

}
