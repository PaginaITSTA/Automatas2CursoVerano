package Lenguaje_SID.AFD;

/**
 *
 * @author erick
 */
public class listaSimbolos {

    private String clase, nombreValor, tipoDeDato, valor, disponibilidad;
    private int linea;

    public listaSimbolos() {

    }

    public listaSimbolos(String clase, String nombreValor, String tipoDeDato, String valor, String disponibilidad, int linea) {
        this.clase = clase;
        this.nombreValor = nombreValor;
        this.tipoDeDato = tipoDeDato;
        this.valor = valor;
        this.disponibilidad = disponibilidad;
        this.linea = linea;
    }

    public String getClase() {
        return clase;
    }

    public String getNombreValor() {
        return nombreValor;
    }

    public String getTipoDeDato() {
        return tipoDeDato;
    }

    public int getLinea() {
        return linea;
    }

    public String getValor() {
        return valor;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

}
