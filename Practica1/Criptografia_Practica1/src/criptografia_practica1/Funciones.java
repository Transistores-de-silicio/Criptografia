/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia_practica1;

import Excepciones.AoBNoValidasException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Alex
 */
public class Funciones {

    /**
     *
     * @param a
     * @param b
     * @param m
     * @return
     */
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

    /**
     *
     * @param a
     * @param c
     * @param p
     * @return
     */
    public static ArrayList<BigInteger> logaritmoDiscreto(BigInteger a, BigInteger c, BigInteger p) {
        int aux = (int) Math.sqrt(p.doubleValue());
        BigInteger s = BigInteger.valueOf(aux).add(BigInteger.ONE);
        BigInteger rKey, tKey, solucion;
        ArrayList<BigInteger> rList, tList, soluciones = new ArrayList<BigInteger>(0);
        HashMap<BigInteger, ArrayList<BigInteger>> r = new HashMap<BigInteger, ArrayList<BigInteger>>();
        HashMap<BigInteger, ArrayList<BigInteger>> t = new HashMap<BigInteger, ArrayList<BigInteger>>();
        for (BigInteger i = BigInteger.ZERO; i.compareTo(s) == -1; i = i.add(BigInteger.ONE)) {
            rKey = potenciaModular(a, s.multiply(i.add(BigInteger.ONE)), p);
            if (r.containsKey(rKey)) { r.get(rKey).add(i); }
            else {
                rList = new ArrayList<BigInteger>();
                rList.add(i);
                r.put(rKey, rList);
            }
            tKey = c.multiply(potenciaModular(a, i, p)).mod(p);
            if (t.containsKey(tKey)) { t.get(tKey).add(i); }
            else {
                tList = new ArrayList<BigInteger>();
                tList.add(i);
                t.put(tKey, tList);
            }
        }
        for (Entry<BigInteger, ArrayList<BigInteger>> i : r.entrySet()) {
            if (t.containsKey(i.getKey())) {
                for (int iIndex = 0; iIndex < i.getValue().size(); iIndex++) {
                    for (int jIndex = 0; jIndex < t.get(i.getKey()).size(); jIndex++) {
                        solucion = s.multiply(i.getValue().get(iIndex).add(BigInteger.valueOf(1))).subtract(t.get(i.getKey()).get(jIndex));
                        if (solucion.compareTo(p) == -1) { soluciones.add(solucion); }
                    }
                }
            }
        }
        Collections.sort(soluciones);
        return soluciones;
    }
    
    public static ArrayList<BigInteger[]> puntosCurvasElipticas(BigInteger a, BigInteger b, BigInteger p) throws AoBNoValidasException {
        if ((a.pow(3).multiply(new BigInteger("4")).add(b.pow(2).multiply(new BigInteger("27")))).compareTo(BigInteger.ZERO)==0) {
            throw new AoBNoValidasException();
        }
        HashMap<BigInteger, BigInteger> x = new HashMap<BigInteger, BigInteger>();
        HashMap<BigInteger, BigInteger> y = new HashMap<BigInteger, BigInteger>();
        ArrayList<BigInteger[]> puntosFinales = new ArrayList<BigInteger[]>();
        BigInteger[] punto;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(p) == -1; i = i.add(BigInteger.ONE)) {
            x.put(i, i.pow(3).add(a.multiply(i)).add(b).mod(p));
            y.put(i, i.pow(2).mod(p));
        }
        for(Entry<BigInteger, BigInteger> puntoX : x.entrySet()) {
            if (y.containsValue(puntoX.getValue())) {
                for(Entry<BigInteger, BigInteger> puntoY : y.entrySet()) {
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

    public static int[] Encontrar_Punto(int x1, int x2, int y1, int y2,BigInteger a) {
        int[] re = null;

        if (x1 != x2 && y1 != y2) {
            int landa = (y2 - y1) / (x2 - x1);
            int x3 = landa ^ 2 - x1 - x2;
            int y3 = landa*(x1 - x3) - y1;
            re= new int[2];
            re[0] = x3;
            re[1] = y3;
            return re;
        }
        if (x2 == x1 && y2 == y1 && y1 != 0) {
            int landa = ((x1^2)*3+a.intValue())/(2*y1);
            int x3 = landa ^ 2 -(2*x1);
            int y3 = landa*(x1 - x3) - y1;
            re = new int[2];
            re[0] = x3;
            re[1] = y3;
        }
        return re;
    }
}
