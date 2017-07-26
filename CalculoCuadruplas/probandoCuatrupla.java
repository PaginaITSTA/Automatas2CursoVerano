package CalculoCuadruplas;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class probandoCuatrupla {

    private static ArrayList<cuadrupla> lista;

    public static void main(String[] args) {
        lista = new ArrayList<>();

        //a := b*-c+b*-c
        lista.add(new cuadrupla('-', "c", "", "t1"));
        lista.add(new cuadrupla('*', "b", "t1", "t2"));
        lista.add(new cuadrupla('-', "c", "", "t3"));
        lista.add(new cuadrupla('*', "b", "t3", "t4"));
        lista.add(new cuadrupla('+', "t2", "t4", "t5"));
        lista.add(new cuadrupla('=', "t5", "", "a"));

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("op -> " + lista.get(i).getOp() + "\t\targ1-> " + lista.get(i).getArg1() + "\targ2-> " + lista.get(i).getArg2() + "\t\tresultado-> " + lista.get(i).getResultado());
        }

    }
}
