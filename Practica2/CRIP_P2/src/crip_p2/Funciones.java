/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p2;

import java.util.ArrayList;

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
        
        
        return salida;
    }
}
