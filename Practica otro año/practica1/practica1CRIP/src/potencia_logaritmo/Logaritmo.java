package potencia_logaritmo;
import java.io.*;
import java.math.*;
import java.util.*;

public class Logaritmo {

	private BigInteger base,numero,modulo;
	
	//Constructor
	Logaritmo(BigInteger a,BigInteger b,BigInteger c){
		base=a;
		numero=b;
		modulo=c;
	}
	
	Logaritmo(String a, String b, String c){
		base = new BigInteger(a);
		numero = new BigInteger(b);
		modulo = new BigInteger(c);
	}
	
	//Metodo para calcular Log[a] b (mod p)
	public Vector<BigInteger> AShanks(){
		BigInteger n,tam,res,mod[],logaritmo,key1,value1,value2,ini;
		boolean sol=false;
		Map <BigInteger,BigInteger> S,T;
		S=new HashMap<BigInteger,BigInteger>();
		T=new HashMap<BigInteger,BigInteger>();
		Vector <BigInteger> soluciones = new Vector <BigInteger>();
		
		Potencia aux=new Potencia(BigInteger.valueOf(0),BigInteger.valueOf(0),BigInteger.valueOf(0));
		Double result = Math.sqrt(modulo.doubleValue());
		int r=(int) (result+1);
		n=BigInteger.valueOf(r);
		res=n;
		//Creamos la tabla S --> (numero*base^i) (mod p)
		tam=n.subtract(BigInteger.valueOf(1));
		for(int i=0;tam.compareTo(BigInteger.valueOf(i))>=0; i++){
			if(i==0){
				S.put(numero,BigInteger.valueOf(0));
				res=numero;
			}else{
				mod=res.multiply(base).divideAndRemainder(modulo);
				S.put(mod[1],BigInteger.valueOf(i));
				res=mod[1];
			}

		}
		ini=n;
		//Creamos la tabla T --> base^(i*tam) (mod p)
		tam=n;
		for(int i=1;tam.compareTo(BigInteger.valueOf(i))>=0; i++){
			if(i==1){
				aux.Cambiar(base,tam,modulo);
				res=aux.Apotenciacion();
				T.put(res,BigInteger.valueOf(1));
				ini=res;
			}else{
				mod=res.multiply(ini).divideAndRemainder(modulo);
				T.put(mod[1],BigInteger.valueOf(i));
				res=mod[1];
			}
		}
		
		//Recorremos S y buscamos una pareja con el mismo valor en T
		Set s=S.entrySet();
		Iterator it=s.iterator();
		logaritmo=BigInteger.valueOf(-1);
		while(it.hasNext()){
			Map.Entry m =(Map.Entry)it.next();
			key1=(BigInteger)m.getKey();
			value1=(BigInteger)m.getValue();
			if(T.containsKey(key1)){
				value2=T.get(key1);
				mod=value2.multiply(tam).subtract(value1).divideAndRemainder(modulo);
				logaritmo=mod[1];
			}
			if(logaritmo.compareTo(BigInteger.valueOf(-1))!=0){
				sol=true;
				if(!soluciones.contains(logaritmo))
					soluciones.add(logaritmo);
				logaritmo=BigInteger.valueOf(-1);
			}
		}
		return soluciones;	
	}
	
	
	public static void main(String[] args) throws IOException{
		Vector <BigInteger> logs = new Vector <BigInteger>();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		String cadena;

		System.out.println("Programa para calcular Log[a] b (mod p)");
		System.out.println("Introduce el valor deseado de a:");
		cadena = br.readLine();
		BigInteger a = new BigInteger(cadena);
		System.out.println("Introduce el valor deseado de b:");
		cadena = br.readLine();
		BigInteger b = new BigInteger(cadena);
		System.out.println("Introduce el valor deseado de p:");
		cadena = br.readLine();
		BigInteger p = new BigInteger(cadena);
		
		Logaritmo logaritmo=new Logaritmo(a,b,p);
		long ti = System.currentTimeMillis();
		logs=logaritmo.AShanks();
		long tf = System.currentTimeMillis() - ti;
		if(logs.size()>0){
			System.out.println("El Logaritmo tiene "+logs.size()+" soluciones");
			for(int i=0; i<logs.size();i++)
				System.out.println("El Logaritmo tiene como solucion: "+logs.elementAt(i));
		}else{
			System.out.println("El Logaritmo no tiene solucion.");
		}
		System.out.println("Tiempo transcurrido: "+tf+" ms.");
	}
}
