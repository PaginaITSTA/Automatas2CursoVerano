    package A01_repaso_automata;

/**
 *
 * @author erick
 */
public class AFD {

    private int contador;
    private char[] cadena;
    public boolean aceptado;

    public AFD(String cad) {
        this.cadena = cad.toCharArray();
    }

    public void estadoInicial() {
        contador = 0;
        aceptado = false;
        q0();
    }

    private void q0() {
        if (contador < cadena.length) {
            if (AFD.isLetter(cadena[contador])) {
                contador++;
                aceptado = true;
                q1();
            }
        }
    }

    //Estado final
    private void q1() {
        if (contador < cadena.length) {
            if ((AFD.isLetter(cadena[contador])) || (AFD.isNumeric(cadena[contador]))) {
                contador++;
                q1();
            } else {
                aceptado = false;
            }
        }
    }

    private static boolean isNumeric(char numero) {
        String cadena = numero + "";
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static boolean isLetter(char letra) {
        if ((letra >= 'a' && letra <= 'z') || (letra >= 'A' && letra <= 'Z')) {
            return true;
        }
        return false;
    }
}
