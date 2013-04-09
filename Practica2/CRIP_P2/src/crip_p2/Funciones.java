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

    public static void periodo(String secuencia) {
        int longitud = secuencia.length();
        int minimo = 1;
        int periodo = 0;
        int i = 0;
        int j = minimo;
        boolean repetir = false;
        boolean salir = false;
        int h = 0;

        while (minimo < longitud / 2 && !salir) {
            while (j < longitud && !repetir) {
                if (secuencia.charAt(i) != secuencia.charAt(j)) {
                    repetir = true;
                }
                if (i == minimo) {
                    periodo = i;
                }
                i++;
                j++;

            }
            if (!repetir) {
                salir = true;
            }
            h++;
            minimo += 1;
            i = 0;
            j = minimo;
            repetir = false;
        }

        if (salir) {
            System.out.println("La secuencia tiene un periodo de tamaño :" + periodo);
        } else {
            System.out.println("La secuencia no tiene periodo");
        }

    }
}
