package potencia_logaritmo;
import java.io.*;
import java.math.*;
import java.util.Random;

public class Primo {
	
	
	private int pruebas;
	private BigInteger primo;
	private BigDecimal porcentaje;
	private boolean porc;
	
	//Constructor
	Primo(BigInteger p,int prueb){
    //Efecto: inicializa los valores para el algoritmo
		primo=p;
		pruebas = prueb;
		porc=false;
    };//End Primo
    
	Primo(String p, int prueb){
		primo = new BigInteger(p);
		pruebas = prueb;
		porc=false;
	}
	
	Primo(String p, String dec){
		primo = new BigInteger(p);
		porcentaje = new BigDecimal(dec);
		porc=true;
	}
	
	public String porcentaje(){
		return porcentaje.toString();
	}
	
	public String pasadas(){
		return pruebas+"";
	}
    
	/**
	 * @param num
	 */
	public static boolean comprobar_impar(BigInteger num){
		BigInteger aux[] = num.subtract(BigInteger.valueOf(1)).divideAndRemainder(BigInteger.valueOf(2));
	
		BigInteger numero = aux[1];
		
		if(numero.compareTo(BigInteger.valueOf(0))==0 && num.compareTo(BigInteger.valueOf(5))==1)
			return true;
		else
			return false;
	}

	/**
	 * @param num
	 */
	public boolean algoritmo(){
		BigInteger a;
		BigInteger s = primo.subtract(BigInteger.valueOf(1));
		int u=0;
		
		BigInteger modulo[] = s.divideAndRemainder(BigInteger.valueOf(2));
		
		BigInteger aux = modulo[1];
		
		while(aux.compareTo(BigInteger.valueOf(0))==0){
			u++;
			s = modulo[0];
			modulo = s.divideAndRemainder(BigInteger.valueOf(2));
			aux = modulo[1];
		}
		boolean salida=true;
		boolean es;
		boolean noes;
		
		Potencia pot;
		BigInteger resul;
		if(!porc){
			for(int i=0; i < pruebas && salida; i++){
				es=true;
				noes=false;
				a= aleatorio(primo.subtract(BigInteger.valueOf(2)),BigInteger.valueOf(2));
				pot=new Potencia(a, s, primo);
				resul=pot.Apotenciacion();
				if(resul.compareTo(BigInteger.valueOf(1)) == 0
						|| resul.compareTo(primo.subtract(BigInteger.valueOf(1))) == 0){
					salida= true;
				}
				else{
					for(int j=1; j < u && salida && es  && !noes; j++){
						a=resul;
						pot = new Potencia(a, BigInteger.valueOf(2), primo);
						resul=pot.Apotenciacion();	
						if(resul.compareTo(primo.subtract(BigInteger.valueOf(1))) == 0){
							salida=true;
							es=false;
						}
						else if(resul.compareTo(BigInteger.valueOf(1)) == 0){
							salida=false;
							noes=true;
						}
					}
					if(!noes && es){
						salida=false;
					}
				}
				porcentaje = new BigDecimal((Math.pow(4.0,i+1)-1)/Math.pow(4.0, i+1));
			}
		}
		else{
			int i=0;
			BigDecimal auxiliar=new BigDecimal(0);
			while(auxiliar.compareTo(porcentaje) < 0 &&salida){
				es=true;
				noes=false;
				a= aleatorio(primo.subtract(BigInteger.valueOf(2)),BigInteger.valueOf(2));
				pot=new Potencia(a, s, primo);
				resul=pot.Apotenciacion();
				if(resul.compareTo(BigInteger.valueOf(1)) == 0
						|| resul.compareTo(primo.subtract(BigInteger.valueOf(1))) == 0){
					salida= true;
				}
				else{
					for(int j=1; j < u && salida && es  && !noes; j++){
						a=resul;
						pot = new Potencia(a, BigInteger.valueOf(2), primo);
						resul=pot.Apotenciacion();	
						if(resul.compareTo(primo.subtract(BigInteger.valueOf(1))) == 0){
							salida=true;
							es=false;
						}
						else if(resul.compareTo(BigInteger.valueOf(1)) == 0){
							salida=false;
							noes=true;
						}
					}
					if(!noes && es){
						salida=false;
					}
				}
				auxiliar = new BigDecimal((Math.pow(4.0,i+1)-1)/Math.pow(4.0, i+1));
				i++;
			}
			pruebas = i;
			
		}
		
		return salida;
	}

	/**
	 * @param max, min
	 */
	public BigInteger aleatorio(BigInteger max,BigInteger min){
		Random ran = new Random();
		BigInteger resultado = new BigInteger(100,ran);
		return resultado;		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		StreamTokenizer numInput= new StreamTokenizer(System.in); 

		System.out.println("Programa para calcular si un número es primo  o no");
		System.out.println("Elige la opción deseada:");
		System.out.println("1.Introduce el número impar p y el número natural n");
		System.out.println("2.Introduce el número natural n");
		numInput.nextToken();
		int menu = (int) numInput.nval;
		int n;
		BigInteger p = null;
		if(menu == 1){
			System.out.println("Introduzca p");
			numInput.nextToken();
			p = new BigInteger(numInput.toString());
			System.out.println("Introduzca n");
			numInput.nextToken();
			n = (int) numInput.nval;
			if(comprobar_impar(p)){
				Primo primo = new Primo(p,n);
				if(primo.algoritmo()){
					System.out.println("Es probable que sea primo");
				}
				else{
					System.out.println("No es primo");
				}
			}
			else{
				System.out.println("El número p introducido es par o menor que 5");
			}
		}
		else if(menu == 2){
			System.out.println("Introduzca n");
			
			Random ran = new Random();
			BigInteger resultado = new BigInteger(100,ran);
			
			numInput.nextToken();
			n = (int) numInput.nval;
			p = resultado; 
			System.out.println("El número introducido es: "+p);
			if(comprobar_impar(p)){
				Primo primo = new Primo(p,n);
				if(primo.algoritmo()){
					System.out.println("Es probable primo");
				}
				else{
					System.out.println("No es primo");
				}
					
			}
			else{
				System.out.println("El número p introducido es par o menor que 5");
			}	
		}
		else{
			System.out.println("No ha seleccionado una opción correcta");
		}
	}

}
