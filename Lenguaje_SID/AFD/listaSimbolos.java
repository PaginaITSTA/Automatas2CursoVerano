package Lenguaje_SID.AFD;

/**
 *
 * @author erick
 */
public class listaSimbolos {

    private String clase, nombreValor, tipoDeDato, valor, disponibilidad;

    public listaSimbolos() {

    }

    public listaSimbolos(String clase, String nombreValor, String tipoDeDato, String valor, String disponibilidad) {
        this.clase = clase;
        this.nombreValor = nombreValor;
        this.tipoDeDato = tipoDeDato;
        this.valor = valor;
        this.disponibilidad = disponibilidad;
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

    public String getValor() {
        return valor;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

}
