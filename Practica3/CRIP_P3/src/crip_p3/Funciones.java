/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.math.*;

/**
 * @author Carlos Jesus Fernandez Basso
 * @author Alex
 */
public class Funciones {

    public static int millerRabin(BigInteger impar) {
        int u = 0;
        BigInteger s, a;
        ArrayList<BigInteger> Lista = new ArrayList();
        boolean banderaverdad, salida;
        Random aleatorio = new Random();
        int primo = 0;
        int contador = 0;



        if (impar.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
            return -1;
        } else {
            /*
             * Calculamos u, s	(numero impar)-1=(2^u)*s
             */
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

            a = new BigInteger("0");
            int max = 0;
            if (impar.subtract(new BigInteger("3")).compareTo(new BigInteger("20")) == 1) {
                max = 20;
            } else {
                max = impar.intValue() - 4;
            }

            for (contador = 0; contador < max; contador++) {
                salida = true;
                while (!(a.compareTo(new BigInteger("1")) == 1 && a.compareTo(impar.subtract(new BigInteger("1"))) == -1)) {
                    a = new BigInteger(600, aleatorio);
                    a = a.mod(impar.subtract(new BigInteger("2")));

                    if (!Lista.isEmpty()) {
                        if (Lista.indexOf(a) != -1) {
                            a = new BigInteger("0");
                        }
                    }

                }
                Lista.add(a);
                /*
                 * Calculamos a^s (mod (numero impar))
                 */
                a = potenciaModular(a, s, impar);

                if (a.equals(new BigInteger("1")) || a.equals(new BigInteger("-1"))) {

                    primo++;
                    banderaverdad = true;

                } else if (!a.equals(impar.subtract(new BigInteger("1")))) {

                    banderaverdad = false;
                    for (int i = 1; i <= u - 1;) {

                        if (a.equals(impar.subtract(new BigInteger("1")))) {

                            salida = false;
                            i = u + 1;

                        }
                        a = potenciaModular(a, new BigInteger("2"), impar);

                        if (a.equals(impar.subtract(new BigInteger("1")))) {

                            salida = false;
                            i = u + 1;
                        }

                        if (a.equals(new BigInteger("1")) && salida == true) {

                            return 0;

                        } else if (a.equals(new BigInteger("-1")) && salida == true) {

                            primo++;
                            banderaverdad = true;

                        } else {

                            i++;

                        }

                    }
                    if (banderaverdad == false && salida == true) {

                        return 0;

                    }
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

    public static ArrayList<ArrayList> RSA() {
        ArrayList<ArrayList> Salida = new ArrayList();
        ArrayList<BigInteger> ClavePublica = new ArrayList();
        ArrayList<BigInteger> ClavePrivada = new ArrayList();
        BigInteger p, q, n, pq, e, d;
        boolean salida = false;
        Random Aleatorio = new Random();
        e = new BigInteger("2");
        d = new BigInteger("2");
        p = new BigInteger(40, Aleatorio);
        q = new BigInteger(40, Aleatorio);
        if (p.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
            p = p.subtract(new BigInteger("1"));
        }
        if (q.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
            q = q.subtract(new BigInteger("1"));
        }
        //System.out.println("Impares: " + q + " \n" + p);
        boolean sal = false, sal1 = false;
        while (!sal || !sal1) {
            if (millerRabin(p) == 0 && sal == false) {
                p = p.add(new BigInteger("2"));

            } else {
                sal = true;
            }
            if (millerRabin(q) == 0 && sal1 == false) {
                q = q.add(new BigInteger("2"));
            } else {
                sal1 = true;
            }
        }
        // System.out.print(q + " \n" + p);

        n = p.multiply(q);
        pq = p.subtract(new BigInteger("1")).multiply((q.subtract(new BigInteger("1"))));
        //System.out.println("n= " + n);
        //System.out.println("pq= " + pq);
        for (BigInteger i = new BigInteger("2"); salida != true; i = i.add(new BigInteger("1"))) {
            //System.out.println("\nEntramos" + i);
            BigInteger mcd = i.gcd(pq);
            if (mcd.equals(new BigInteger("1"))) {
                e = i;
                salida = true;
                //System.out.println("e= " + e);
            }

            // System.out.println(i.gcd(pq).equals(new BigInteger("1")));
        }
        ClavePublica.add(n);
        ClavePublica.add(e);
        System.out.println("pq: " + pq);
        System.out.println("inverso de " + e + "modulo " + pq + "es");
        d = inverso(pq, e);
        ClavePrivada.add(d);
        Salida.add(ClavePublica);
        Salida.add(ClavePrivada);
        return Salida;


    }

    public static BigInteger inverso(BigInteger n, BigInteger a) {
        BigInteger r, c, y, v, aux, nn, aa;
        y = new BigInteger("0");
        v = new BigInteger("1");
        nn = n;
        aa = a;
        r = nn.mod(a);
        //System.out.print("inverso de "+aa+"modulo "+nn+"es");
        while (!r.equals(new BigInteger("0"))) {
            c = nn.divide(aa);
            aux = v;
            v = y.subtract(v.multiply(c));
            y = aux;
            nn = aa;
            aa = r;
            r = nn.mod(aa);
        }
        if (!aa.equals(new BigInteger("1"))) {
            System.err.print("ERROR No existe Inverso");
        }

        if (v.compareTo(new BigInteger("0")) == -1) {
            v = v.add(n);
        }
        return v;

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

    public static String cifrar(String texto, BigInteger n, BigInteger e) {
        String salida;
        String Ascii = "";
        for (int x = 0; x < texto.length(); x++) {
            Ascii += texto.codePointAt(x)+"0";
            System.out.println(texto.charAt(x) + " = " + texto.codePointAt(x));
        }
        //texto=texto.getBytes().toString();
        System.out.println(Ascii);
        BigInteger cifrado, textoCifrar = new BigInteger(Ascii);
        cifrado = textoCifrar.pow(e.intValue()).mod(n);
        salida = cifrado.toString();
        return salida;
    }

    static String descifrar(String text, BigInteger publicaN, BigInteger publicaE, BigInteger privada) {
        String salida,sal="";
        String Ascii = "";

        /*
         * int i = 64;
         String aChar = new Character((char)i).toString();
         */
        BigInteger cifrado = new BigInteger(text);
        salida = potenciaModular(cifrado, privada, publicaN).toString();

        for (int x = 0; x < salida.length(); x++) {
            String aux="";
            while(salida.charAt(x)!='0'){
            aux+=salida.charAt(x);
            x++;
            }
            int numeroAscii=Integer.parseInt(aux);
            sal+=new Character((char)numeroAscii).toString();
        }
        /*if(cifrado.gcd(publicaN)==new BigInteger("1")){
           
        
         }else{
        
         }*/
        return sal;
    }
}
