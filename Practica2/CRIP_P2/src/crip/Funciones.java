/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip;

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
        if (op == Operaciones.SUMA) {
            for (int j = 1; j < sucesiones.length; j++) {
                for (int i = 0; i < longitudSucesion; i++) {
                    int num1 = ((int) sucesion[i]) - 48;
                    int num2 = ((int) sucesiones[j].charAt(i)) - 48;
                    sucesion[i] = (char) ((num1 ^ num2) + 48);
                }
            }
        } else {
            for (int j = 1; j < sucesiones.length; j++) {
                for (int i = 0; i < longitudSucesion; i++) {
                    int num1 = ((int) sucesion[i]) - 48;
                    int num2 = ((int) sucesiones[j].charAt(i)) - 48;
                    sucesion[i] = (char) ((num1 & num2) + 48);
                }
            }
        }
        return new String(sucesion);
    }

    private static void arreglarFunciones(String[] sucesiones) {
        int longMax = 0;
        for (String sucesion : sucesiones) {
            if (sucesion.length() > longMax) {
                longMax = sucesion.length();
            }
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
        ArrayList<Integer> bloques = new ArrayList<Integer>();
        ArrayList<Integer> huecos = new ArrayList<Integer>();
        char simbolo;

        for (int i = 0; i < periodo;) {
            //System.out.println(periodo);
            if (sucesion.charAt(i) == '1') {
                int cont = 0;
                int j = i;
                while ('1' == sucesion.charAt(j) && j < periodo) {
                    //System.out.println(sucesion.charAt(j)+"==1");
                    cont++;
                    j++;
                }

                rachas.add(cont);
                bloques.add(cont);
                // System.out.println("metemos racha unos: " + rachas);
                i = j;
            } else if (sucesion.charAt(i) == '0') {
                int cont = 0;
                int j = i;
                while ('0' == sucesion.charAt(j) && j < periodo) {
                    //System.out.println(sucesion.charAt(j)+"==0");
                    cont++;
                    j++;
                }

                rachas.add(cont);
                huecos.add(cont);
                // System.out.println("metemos racha ceros: " + rachas);
                i = j;
            }

        }
        if (bloques.size() != huecos.size() || sucesion.length() % 2 != 0) {
            /*int i = 0, contador = 1;
             while (i < bloques.size()) {
             int blo=contar(bloques, contador);
             int hue=contar(huecos, contador);
             if (blo != hue) {*/
            //System.out.println("fallo bloques: " + sucesion.length() % 2);
            return false;
            //}
            //i=blo+i;
            //contador++;
            //}
        }
        /*for (int j = 0, cont = 0; j <= periodo; j++) {
         //problema en el principio de la cadena

         simbolo = sucesion.charAt(j);
         //cont++;
         while (simbolo == sucesion.charAt(j) || j == periodo) {
         cont++;
         j++;
         }
         rachas.add(cont);
         cont = 1;
         //simbolo = sucesion.charAt(j);
         }*/

        //System.out.print(rachas);
        Collections.sort(rachas);
        // System.out.println("porcentajes: " + rachas);

        if (2 / rachas.size() == contar(rachas, 1)) {
            salida = true;
        }
        if (4 / rachas.size() == contar(rachas, 2)) {
            salida = true;
        } else {
            salida = false;
        }
        return salida;
    }

    private static boolean postulado3(String sucesion, Integer periodo) {
        boolean salida = true;
        char[] cadena = (sucesion.substring(0, periodo)).toCharArray();
        double[] diferencias = new double[periodo-1];

        System.out.println(periodo);
        // System.out.println(cadena.length);
        System.out.println(cadena);

        char[] cadenarotada = cadena.clone();
        for (int i = 0; i < periodo-1; i++) {
            cadenarotada = rotar(cadenarotada);
            int[] parametros = diferencia(cadena, cadenarotada);
            System.out.println("parametros:" + parametros[0] + parametros[1] + parametros[2]);
            diferencias[i] = (parametros[0] - parametros[1]) / (double)parametros[2];
            System.out.println("diferencia:" +diferencias[i] );
        }
        
        for (int s = 0; s < diferencias.length; s++) {
            int i = 0;
            while (i < diferencias.length ) {
                if (diferencias[s] != diferencias[i] ) {
                    System.out.print("sale");
                    return false; 
                }
                System.out.print("entra");
                i++;
            }
        }

        System.out.println(cadenarotada);
        return salida;
    }

    public static char[] rotar(char[] sucesion) {
        char primero = sucesion[0];
        char[] sucesionRotada;
        int x;  
        sucesionRotada = new char[sucesion.length];

        for (x = 0; x < sucesion.length - 1; x++) {
            sucesionRotada[x] = sucesion[x + 1];
        }

        sucesionRotada[x] = primero;

        return sucesionRotada;
    }

    public static int[] diferencia(char[] sucesion, char[] sucrotada) {
        int[] salida = new int[3];
        int diferencias = 0;
        int similitudes = 0;
        for (int i = 0; i < sucesion.length; i++) {
            if (sucesion[i] == sucrotada[i]) {
                similitudes++;

            } else {
                diferencias++;
            }
        }

        salida[0] = similitudes;
        salida[1] = diferencias;
        salida[2] = sucesion.length;

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
        postulado[1] = true;//postulado2(sucesion, periodo);
        postulado[2] = postulado3(sucesion, periodo);
        return postulado;
    }
}
