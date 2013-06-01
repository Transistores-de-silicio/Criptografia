/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Carlos Jesus Fernandez Basso
 * @author Alex
 */
public class Funciones {

    public static int millerRabin(BigInteger impar) {
        int u = 0;
        BigInteger s, a;
        ArrayList<BigInteger> Lista = new ArrayList();
        boolean banderaverdad, salida = true;
        Random aleatorio = new Random();
        int primo = 0;

        //System.out.println(impar.mod(new BigInteger("2")));
        if (impar.mod(new BigInteger("2")).equals(new BigInteger("0")) || impar.compareTo(new BigInteger("5")) == -1) {
            return -1;
        } else {
            //Calculamos u, s	(numero impar)-1=(2^u)*s
            BigInteger Par = impar.subtract(new BigInteger("1"));
            boolean bandera = false;
            s = Par;
            for (int i = 0; bandera == false;) {
                if (Par.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
                    Par = Par.divide(new BigInteger("2"));
                    i++;
                } else {
                    s = Par;
                    bandera = true;
                }
                u = i;
            }
            System.out.println("u:" + u + "\ns:" + s + "\nPrimo:" + impar);

            a = new BigInteger("0");
            // System.err.println(impar);
            for (int contador = 0; contador < 50; contador++) {
                System.out.println("Lista: " + Lista);
                banderaverdad = false;
                salida = true;
                while (!(a.compareTo(new BigInteger("1")) == 1 && a.compareTo(impar.subtract(new BigInteger("2"))) == -1)) {
                    //System.err.println("dentro:" + a);
                    a = new BigInteger(impar.intValue(), aleatorio);
                    a = a.mod(impar.subtract(new BigInteger("1")));
                    if (!Lista.isEmpty()) {
                        if (Lista.indexOf(a) != -1) {
                            a = new BigInteger("0");
                        }
                    }
                    //a=impar.subtract(new BigInteger("1"));

                }
                Lista.add(a);
                // System.out.println("\na:" + a);
                /*
                 * Calculamos a^s (mod (numero impar))
                 */
                System.out.println("\na:" + a);
                a = potenciaModular(a, s, impar);
                System.out.println("a elevada:" + a);
                if (a.equals(new BigInteger("1")) || a.equals(new BigInteger("-1"))) {
                    //return 1;
                    primo++;
                    banderaverdad = true;
                } else {
                    banderaverdad = false;//|| banderaverdad != true 
                    for (int i = 1; i <= u - 1;) {
                        //a = (a.modPow(new BigInteger("2"), impar));
                        if (a.equals(impar.subtract(new BigInteger("1")))) {
                            // System.out.print("saleeee");
                            salida = false;
                            i = u + 1;
                        }
                        a = potenciaModular(a, new BigInteger("2"), impar);
                        if (a.equals(impar.subtract(new BigInteger("1")))) {
                            // System.out.print("saleeee");
                            salida = false;
                            i = u + 1;
                        }
                        System.out.println("a elevada2:" + a);
                        //System.out.print(a+" --- "+impar.subtract(new BigInteger("1")));

                        if (a.equals(new BigInteger("1")) && salida == true) {
                            return 0;
                        } else if (a.equals(new BigInteger("-1")) && salida == true) {
                            // return 1;
                            primo++;
                            banderaverdad = true;
                        } else {
                            i++;
                        }

                    }
                    if (banderaverdad == false && salida == true) {
                        System.out.println("\nSale al final");
                        return 0;
                    }
                }
            }
            System.out.println("---" + Lista + "---" + "\nPrimo: " + primo);
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

    public static BigInteger potenciaModular(BigInteger a, BigInteger b, BigInteger m) {
        BigInteger result = BigInteger.ONE;
        while (b.compareTo(BigInteger.ZERO) == 1) {
            if (b.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ONE) == 0) {
                result = result.multiply(a).mod(m);
            }
            a = a.multiply(a).mod(m);
            b = b.divide(BigInteger.valueOf(2));
        }
        return result;
    }

    /* public BigInteger aleatorio(BigInteger a) {
     while(){
     a.divide(new BigInteger("2")).equals(a)
     }
     Random ran = new Random();
     BigInteger resultado = new BigInteger(, ran);
     return resultado;
     }*/
    public static ArrayList<BigInteger[]> puntosCurvasElipticas(BigInteger a, BigInteger b, BigInteger p) {
        if ((a.pow(3).multiply(new BigInteger("4")).add(b.pow(2).multiply(new BigInteger("27")))).compareTo(BigInteger.ZERO) == 0) {
        }
        HashMap<BigInteger, BigInteger> x = new HashMap<BigInteger, BigInteger>();
        HashMap<BigInteger, BigInteger> y = new HashMap<BigInteger, BigInteger>();
        ArrayList<BigInteger[]> puntosFinales = new ArrayList<BigInteger[]>();
        BigInteger[] punto;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(p) == -1; i = i.add(BigInteger.ONE)) {
            x.put(i, i.pow(3).add(a.multiply(i)).add(b).mod(p));
            y.put(i, i.pow(2).mod(p));
        }
        for (Map.Entry<BigInteger, BigInteger> puntoX : x.entrySet()) {
            if (y.containsValue(puntoX.getValue())) {
                for (Map.Entry<BigInteger, BigInteger> puntoY : y.entrySet()) {
                    if (puntoX.getValue().equals(puntoY.getValue())) {
                        punto = new BigInteger[2];
                        punto[0] = puntoX.getKey();
                        punto[1] = puntoY.getKey();
                        puntosFinales.add(punto);
                    }
                }
            }
        }
        return puntosFinales;
    }

    public static BigInteger[] encontrarPuntoCurvasElipticas(BigInteger x1, BigInteger x2, BigInteger y1, BigInteger y2, BigInteger a, BigInteger p) {
        BigInteger[] re = null;

        if (x1.compareTo(x2) != 0 && y1.compareTo(y2) != 0) {
            BigInteger landa = y2.subtract(y1).multiply(moduloPotenciaNegativa(x2.subtract(x1), p)).mod(p);
            re = new BigInteger[2];
            re[0] = landa.pow(2).subtract(x1).subtract(x2).mod(p);
            re[1] = landa.multiply(x1.subtract(re[0])).subtract(y1).mod(p);
            return re;
        } else if (x1.compareTo(x2) == 0 && y1.compareTo(y2) == 0 && y1.compareTo(BigInteger.ZERO) != 0) {
            BigInteger landa = x1.pow(2).multiply(new BigInteger("3")).add(a).multiply(moduloPotenciaNegativa(y1.multiply(new BigInteger("2")), p)).mod(p);
            re = new BigInteger[2];
            re[0] = landa.pow(2).subtract(x1.multiply(new BigInteger("2"))).mod(p);
            re[1] = landa.multiply(x1.subtract(re[0])).subtract(y1).mod(p);
        }
        return re;
    }

    private static BigInteger moduloPotenciaNegativa(BigInteger divisor, BigInteger p) {
        boolean encontrado = false;
        BigInteger resultado = BigInteger.ZERO;
        for (BigInteger n = BigInteger.ZERO.subtract(BigInteger.ONE); !encontrado; n = n.subtract(BigInteger.ONE)) {
            if (p.multiply(n).add(BigInteger.ONE).mod(divisor).compareTo(BigInteger.ZERO) == 0) {
                encontrado = true;
                resultado = p.multiply(n).add(BigInteger.ONE).divide(divisor);
            }
        }
        return resultado;
    }
}
