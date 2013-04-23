/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p2;

import java.util.*;

/**
 *
 * @author Alexander Moreno y Carlos Basso
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

    public static Integer complejidadLineal(String sucesion) {
        final int longitudSucesion = sucesion.length();
        int[] b = new int[longitudSucesion], c = new int[longitudSucesion], t = new int[longitudSucesion];
        b[0] = c[0] = 1;
        int resultado = 0, m = -1, d, N_M;
        for (int n = 0; n < longitudSucesion; n++) {
            d = 0;
            for (int i = 0; i <= resultado; i++) {
                d ^= c[i] * Integer.parseInt(String.valueOf(sucesion.charAt(n - i)));
            }
            if (d == 1) {
                System.arraycopy(c, 0, t, 0, longitudSucesion);
                N_M = n - m;
                for (int j = 0; j < longitudSucesion - N_M; j++) {
                    c[N_M + j] ^= b[j];
                }
                if (resultado <= n / 2) {
                    resultado = n + 1 - resultado;
                    m = n;
                    System.arraycopy(t, 0, b, 0, longitudSucesion);
                }
            }
        }
        return resultado;
    }

    public static String funcionDeMezcla(Operaciones op, String[] sucesiones) {
        Funciones.arreglarFunciones(sucesiones);
        int longitudSucesion = sucesiones[0].length();
        char[] sucesion = sucesiones[0].toCharArray();
        if (op==Operaciones.SUMA) {
            for (int j = 1; j < sucesiones.length; j++) {
                for (int i = 0; i < longitudSucesion; i++) {
                    int num1 = ((int)sucesion[i])-48;
                    int num2 = ((int)sucesiones[j].charAt(i))-48;
                    sucesion[i] = (char)((num1 ^ num2)+48);
                }
            }
        } else {
            for (int j = 1; j < sucesiones.length; j++) {
                for (int i = 0; i < longitudSucesion; i++) {
                    int num1 = ((int)sucesion[i])-48;
                    int num2 = ((int)sucesiones[j].charAt(i))-48;
                    sucesion[i] = (char)((num1 & num2)+48);
                }
            }
        }
        return new String(sucesion);
    }
    
    private static void arreglarFunciones(String[] sucesiones) {
        int longMax = 0;
        for (String sucesion : sucesiones) {
            if (sucesion.length() > longMax) { longMax = sucesion.length(); }
        }
        
        for (int i = 0; i < sucesiones.length; i++) {
            if (sucesiones[i].length() < longMax) {
                int longPer = Funciones.comprobarPeriocidad(sucesiones[i]);
                String per = sucesiones[i].substring(0, longPer);
                for (int j = sucesiones[i].length() % longPer; sucesiones[i].length() < longMax; j = (j + 1) % longPer) {
                    sucesiones[i] = sucesiones[i] + per.charAt(j);
                }
            }
        }
    }

    private static boolean postulado1(String sucesion, int periodo) {
        int unos = 0, ceros = 0;
        boolean salida;
        /*
         * Primer Postulado
         */
        for (int i = 0; i < periodo; i++) {
            if (sucesion.charAt(i) == '1') {
                unos++;
            } else if (sucesion.charAt(i) == '0') {
                ceros++;
            }
        }
        if (unos == ceros) {
            salida = true;
        } else if ((unos - ceros) == 1 || (ceros - unos) == 1) {
            salida = true;
        } else {
            salida = false;
        }
        // System.out.print(unos);
        // System.out.print(ceros);
        return salida;
    }

    private static boolean postulado2(String sucesion, Integer periodo) {
        boolean salida;
        ArrayList<Integer> rachas = new ArrayList<Integer>();
        char simbolo;
        for (int j = 0, cont = 0; j < periodo; j++) {
            //problema en el principio de la cadena

            simbolo = sucesion.charAt(j);
            //cont++;
            while (simbolo == sucesion.charAt(j)) {
                cont++;
                j++;
            }
            rachas.add(cont);
            cont=1;
            //simbolo = sucesion.charAt(j);
        }

        System.out.print(rachas);
        Collections.sort(rachas);
        System.out.print(rachas);
        if (rachas.size() / 2 == contar(rachas, 1)) {
            salida = true;
        }
        if (rachas.size() / 4 == contar(rachas, 2)) {
            salida = true;
        } else {
            salida = false;
        }
        return salida;
    }

    private static int contar(ArrayList<Integer> a, int num) {
        int veces = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == num) {
                veces++;
            } else {
                break;
            }
        }

        return veces;
    }

    public static boolean[] golomb(String sucesion, Integer periodo) {
        boolean postulado[] = new boolean[3];
        postulado[0] = postulado1(sucesion, periodo);
        postulado[1] = postulado2(sucesion, periodo);
        postulado[2] = false;
        return postulado;
    }
}
