/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p3;

import java.util.Random;

/**
 *
 * @author Alex
 */
public class Funciones {

    public static int millerRabin(int impar) {
        int u = 0, s, a;
        Random aleatorio = new Random(123456789);
        if (impar % 2 == 0) {
            return -1;
        } else {
            //Calculamos u, s	(numero impar)-1=2^u*s
            int Par = impar - 1;
            boolean bandera = false;
            s = Par;
            for (int i = 0; bandera == true;) {
                if (Par % 2 == 0) {
                    Par = Par / 2;
                    i++;
                } else {
                    s = Par;
                }
                u = i;
            }

            a = 0;
            System.err.println(impar);
            for (int contador = 0; contador < 70; contador++) {
                while (1 > a && a < 2) {
                    System.err.println("dentro:" + a);
                    a = aleatorio.nextInt((impar - 2));
                }
                System.err.println(a);
                //Calculamos a^s (mod (numero impar))
                a = (a ^ s) % impar;
                if (a == 1 || a == -1) {
                    //return 1;
                } else {
                    for (int i = 1; i < u - 1;) {
                        a = (a ^ 2) % impar;
                        if (a == 1) {
                            return 0;
                        } else if (a == -1) {
                            
                        } else {
                            i++;
                        }

                    }
                    return 0;
                }

            }
            return 1;

        }

    }

    public static boolean isInteger(String cadena) {
        boolean esEntero = true;
        try {
            Integer.parseInt(cadena);
        } catch (Exception e) {
            esEntero = false;
        }
        return esEntero;
    }
}
