/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p2;

import java.util.*;

/**
 *
 * @author Carlos Basso
 */
public class Funciones {

    public static Integer comprobarPeriocidad(String secuencia) {
        Integer longitudPeriodo = null;
        boolean iguales = true, salir = false;

        for (int grupo = 1; grupo <= secuencia.length() / 2 && !salir; grupo++) {
            for (int indParPer = 0; indParPer < grupo && iguales; indParPer++) {
                for (int indParCom = indParPer + grupo; indParCom < secuencia.length() && iguales; indParCom += grupo) {
                    if (secuencia.charAt(indParPer) != secuencia.charAt(indParCom)) {
                        iguales = false;
                    }
                }
            }
            if (!iguales) {
                iguales = true;
            } else {
                salir = true;
                longitudPeriodo = grupo;
            }
        }

        return longitudPeriodo;
    }

    public static String lfsr(String polinomio, String sucesion) {
        String salida = sucesion;
        String[] pol = polinomio.trim().split(",");
        int[] polComp = new int[sucesion.length()];
        boolean primero = true;
        for (String in : pol) {
            if (!primero) {
                polComp[Integer.parseInt(in)] = 1;
            } else {
                primero = false;
                polComp[0] = 1;
            }
        }
        Integer suma = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < sucesion.length(); j++) {
                suma += polComp[j] * Integer.parseInt(String.valueOf(salida.charAt(i + j)));
            }
            salida = salida.concat(String.valueOf(suma % 2));
            suma = 0;
        }

        return salida;
    }

    private static boolean postulado1(String sucesion) {
        int unos = 0, ceros = 0;
        boolean salida;
        /*
         * Primer Postulado
         */
        for (int i = 0; i < sucesion.length(); i++) {
            if (sucesion.charAt(i) == '1') {
                unos++;
            } else if (sucesion.charAt(i) == '0') {
                ceros++;
            }
        }
        if (unos == ceros) {
            salida = true;
        } else if (unos - ceros == -1 || ceros - unos == -1) {
            salida = true;
        } else {
            salida = false;
        }
        return salida;
    }

    private static boolean postulado2(String sucesion) {
        boolean salida = false;
        ArrayList<Integer> rachas = new ArrayList<Integer>();
        int periodo;
        periodo = comprobarPeriocidad(sucesion);
        char simbolo = sucesion.charAt(0);
        for (int j = 0, cont = 0; j < periodo; j++) {
            if (j == 0) {
                simbolo = sucesion.charAt(j);
                cont++;
            } else if (sucesion.charAt(j) == simbolo) {
                simbolo = sucesion.charAt(j);
                rachas.add(cont);
                cont = 0;
            }
            if (sucesion.charAt(j) != simbolo) {
                cont++;
            }
        }
        Collections.sort(rachas);
        if (rachas.size() / 2 == contar(rachas, 1)) {
            salida = true;
        }
        return salida;
    }

    private static int contar(ArrayList<Integer> a, int num) {
        int veces = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == num) {
                veces++;
            }else{
                break;
            }
        }

        return veces;
    }

    public static void golomb(String sucesion) {
    }
}
