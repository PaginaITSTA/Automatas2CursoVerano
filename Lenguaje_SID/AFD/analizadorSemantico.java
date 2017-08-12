package Lenguaje_SID.AFD;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class analizadorSemantico {

    private ArrayList<listaSimbolos> Simbolos;
    private ArrayList<String> ErroresSemanticos;
    private int contadorLista;

    public analizadorSemantico(ArrayList<listaSimbolos> Simbolos) {
        this.Simbolos = Simbolos;
        Simbolos = new ArrayList<>();
        ErroresSemanticos = new ArrayList<>();
        contadorLista = 0;
    }

    public void comprobar() {
        BuscarTSIdentificadoresiguales();
    }

    private void BuscarTSIdentificadoresiguales() {

        for (int i = 0; i < Simbolos.size(); i++) {
            String identificador = Simbolos.get(contadorLista + i).getNombreValor();

            for (int j = i - 1; j >= 0; j--) {
                boolean valorar = Simbolos.get(j).getNombreValor().equals(identificador);
                if (valorar) {
                    if (isIdentificador(j)) {
                        ErroresSemanticos.add("Error, se encontraron variables con el mismo nombre dado: ( " + Simbolos.get(i).getNombreValor() + " ) en la linea: "
                                + Simbolos.get(i).getLinea());
                    }
                }
            }

        }

    }

    private boolean isIdentificador(int valor) {
        boolean valorar = Simbolos.get(valor).getClase().equals("Identificador");
        if (valorar) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getErroresSemanticos() {
        return ErroresSemanticos;
    }

}
