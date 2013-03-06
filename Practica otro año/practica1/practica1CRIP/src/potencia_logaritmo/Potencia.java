package potencia_logaritmo;
import java.io.*;
import java.math.*;
import java.util.Random;

public class Potencia {

	private BigInteger base,exponente,modulo;
	
	//Constructor
	Potencia(BigInteger a0, BigInteger b0, BigInteger p0){
    //Efecto: inicializa los valores para el algoritmo
		base=a0;
		exponente=b0;
		modulo=p0;
    };//End Potencia
    
    Potencia(String a0, String b0, String p0){
    	base = new BigInteger(a0);
    	exponente = new BigInteger(b0);
    	modulo = new BigInteger(p0);
    }
    
    //Cambiar los valores de la potencia
    public void Cambiar(BigInteger a, BigInteger b, BigInteger p){
    	base=a;
		exponente=b;
		modulo=p;
    }
    
    
    /*	Algoritmo de Potenciacion.
    	Metodo para calcular a^b(mod p).
    	Devuelve el Resultado del algoritmo.
    */
	public BigInteger Apotenciacion(){
		BigInteger a0,b0,m0,a1,b1,m1;
		a0=base;
		b0= BigInteger.valueOf(1);
		m0=exponente;
		
		BigInteger mod[] = m0.divideAndRemainder(BigInteger.valueOf(2));
		
		BigInteger aux = mod[1];
		
		while(m0.compareTo(BigInteger.valueOf(0)) == 1){
			if(aux.compareTo(BigInteger.valueOf(1))==0){
				
				mod = b0.multiply(a0).divideAndRemainder(modulo);
				
				b1=mod[1];
				
				mod = a0.multiply(a0).divideAndRemainder(modulo);
				
				a1=mod[1];
				
				mod = m0.subtract(BigInteger.valueOf(1)).divideAndRemainder(BigInteger.valueOf(2));
				
				mod = mod[0].divideAndRemainder(modulo);
				
				m1= mod[1];
				
			}else{
				
				mod = b0.divideAndRemainder(modulo);
				
				b1=mod[1];
				
				mod = a0.multiply(a0).divideAndRemainder(modulo);
				
				a1=mod[1];
				
				mod = m0.divideAndRemainder(BigInteger.valueOf(2));
				
				mod = mod[0].divideAndRemainder(modulo);
				
				m1=mod[1];
				
				
			}
			b0=b1;
			a0=a1;
			m0=m1;
			
			mod = m0.divideAndRemainder(BigInteger.valueOf(2));
			
			aux = mod[1];
			
		}
		return b0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		StreamTokenizer numInput= new StreamTokenizer(System.in); 
		
		System.out.println("Programa para calcular a^b(mod p)");
		System.out.println("Introduce el valor deseado de a:");
		numInput.nextToken();
		BigInteger a = BigInteger.valueOf((int) numInput.nval);
		System.out.println("Introduce el valor deseado de b:");
		numInput.nextToken();
		BigInteger b = BigInteger.valueOf((int) numInput.nval);
		System.out.println("Introduce el valor deseado de p:");
		numInput.nextToken();
		BigInteger p = BigInteger.valueOf((int) numInput.nval);
		
		Potencia potencia= new Potencia(a,b,p);
		BigInteger resultado=potencia.Apotenciacion();
		System.out.println("El Resultado del Algoritmo es: "+resultado);
	}

}
