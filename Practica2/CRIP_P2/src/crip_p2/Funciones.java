/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p2;

/**
 *
 * @author Carlos Basso
 */
public class Funciones {

    public static Integer comprobarPeriocidad(String secuencia) {
        Integer longitudPeriodo = null;
        boolean iguales = true, salir = false;
        
        for (int grupo = 1; grupo <= secuencia.length()/2 && !salir; grupo++) {
            for (int indParPer = 0; indParPer < grupo && iguales; indParPer++) {
                for (int indParCom = indParPer + grupo; indParCom < secuencia.length() && iguales; indParCom+=grupo) {
                    if (secuencia.charAt(indParPer) != secuencia.charAt(indParCom)) {
                        iguales = false;
                    }
                }
            }
            if (!iguales) { iguales = true; }
            else { 
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
                suma += polComp[j]*Integer.parseInt(String.valueOf(salida.charAt(i+j)));
            }
            salida = salida.concat(String.valueOf(suma%2));
            suma = 0;
        }
        
        return salida;
    }
}
