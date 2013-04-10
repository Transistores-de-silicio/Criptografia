import java.io.*;
import java.util.Scanner;

public class golomb {
	String secuencia;
	int rachas;
	
	golomb(){
		secuencia = "";
	}
	
	golomb(String sec){
		secuencia = sec;
	}
	
	void mayor_racha(){

		int i=1,j=secuencia.length()-1;
		int racha=1;
		int aux=1;
		boolean salir=false;
		char comparar = secuencia.charAt(0);

		while(i <= j && !salir){
			if(comparar != secuencia.charAt(i))
				salir=true;
			else{
				racha++;
				i++;
			}
		}
		
		
		salir=false;
		
		while(j >= 0 && !salir){
			if(comparar != secuencia.charAt(j))
				salir=true;
			else{
				racha++;
				j--;
			}
		}
		
		while(i <= j){
			comparar = secuencia.charAt(i);
			salir=false;
			aux=1;
			i++;
			while(i <= j && !salir){
				if(comparar != secuencia.charAt(i))
					salir=true;
				else{
					aux++;
					i++;

				}
			}
			if(aux > racha)
				racha = aux;
		}
		rachas=racha;	
	}
	
	
	int [][] rachas(){
		
		mayor_racha();
		
		int [][] salida = new int[rachas][];
		for(int i=0; i < rachas; i++)
			salida[i] = new int[2];
		
		for(int i=0; i < rachas; i++){
			for(int j=0; j < 2; j++){
				salida[i][j] = 0;
			}
		}
		
		int i=1,j=secuencia.length()-1;
		int racha=1;
		boolean salir=false;
		char comparar = secuencia.charAt(0);

		while(i <= j && !salir){
			if(comparar != secuencia.charAt(i))
				salir=true;
			else{
				racha++;
				i++;
			}
		}
		
		salir=false;
		
		while(j >= 0 && !salir){
			if(comparar != secuencia.charAt(j))
				salir=true;
			else{
				racha++;
				j--;
			}
		}
		
		
		salida[racha-1][comparar-'0']++;
		
		while(i <= j){
			comparar = secuencia.charAt(i);
			i++;
			salir=false;
			racha=1;
			while(i <= j && !salir){
				if(comparar != secuencia.charAt(i))
					salir=true;
				else{
					racha++;
					i++;
				}
			}
			salida[racha-1][comparar-'0']++;

		}
		return salida;
	}
	
	void primer_postulado(){
		int unos,ceros;
		unos=ceros=0;
		
		for(int i=0; i < secuencia.length(); i++){
			if(secuencia.charAt(i) == '1')
				unos++;
			else if(secuencia.charAt(i) == '0')
				ceros++;
		}
		
		if(unos == ceros){
			System.out.println("La secuencia cumple el primer postulado");
		}
		else if(unos == ceros+1 || unos+1 == ceros){
			System.out.println("La secuencia cumple el primer postulado");
		}
		else{
			System.out.println("La secuencia no cumple el primer postulado");
		}
	}
	
	int dist_hamming(String cad1, String cad2){
		int distancia=0;
		for(int i=0; i < cad1.length(); i++){
			if(cad1.charAt(i) != cad2.charAt(i))
				distancia++;
		}
		return distancia;
	}
	
	void tercer_postulado(){
		int num_despl=secuencia.length();
		String cadena = secuencia;
		int distancia=0;
		int auxiliar=0;
		int pasadas = secuencia.length()-2;
		boolean salir = false;
		
		String nueva;
		
		nueva = "";
		nueva += cadena.charAt(num_despl-1);
		for(int i=0; i < cadena.length()-1; i++)
			nueva += cadena.charAt(i);
		distancia = dist_hamming(nueva,cadena);
		cadena = nueva;
		
		while(pasadas > 0 && !salir){
			nueva = "";
			nueva += cadena.charAt(num_despl-1);

			for(int i=0; i < cadena.length()-1; i++)
				nueva += cadena.charAt(i);
			auxiliar = dist_hamming(nueva,secuencia);
			if(auxiliar != distancia)
				salir = true;
			else{
				cadena = nueva;
				pasadas--;
			}
			


		}
		if(!salir){
			System.out.println("La secuencia cumple el tercer postulado de golomb");
		}
		else{
			System.out.println("La secuencia no cumple el tercer postulado de golomb");		
		}
	}
	
	
	void segundo_postulado(){
		if(secuencia.length()%2 == 0){
			System.out.println("La secuencia no cumple el segundo postulado");
		}
		else{
			int [][] matriz = new int[rachas][];
			for(int i=0; i < rachas; i++)
				matriz[i] = new int[2];
			
			matriz = rachas();
			
			boolean salir=true;
			for(int i=rachas-1; i >= 0 && salir; i--){
				if( i == rachas-1 || i == rachas-2){
					if(matriz[i][0] != matriz[i][1] && matriz[i][0]+1 != matriz[i][1] && matriz[i][0] != matriz[i][1]+1)
						salir=false;
				}
				else{
					if(matriz[i][0] != matriz[i][1])
						salir=false;
				}
					
			}
			
			if(salir)
				System.out.println("La secuencia cumple el segundo postulado");
			else
				System.out.println("La secuencia no cumple el segundo postulado");
		}
	}
	
	void periodo(){
		int longitud = secuencia.length();
		int minimo= 1;
		int periodo=0;
		int i = 0;
		int j = minimo;
		boolean repetir = false;
		boolean salir=false;
		int h=0;
		
		while(minimo < longitud/2 && !salir){
			while(j < longitud && !repetir){
				if(secuencia.charAt(i) != secuencia.charAt(j))
					repetir=true;
				if(i == minimo)
					periodo = i;
				i++;
				j++;

			}
			if(!repetir){
				salir=true;
			}
			h++;
			minimo += 1;
			i=0;
			j=minimo;
			repetir=false;
		}
		
		if(salir)
			System.out.println("La secuencia tiene un periodo de tamaño :" + periodo);
		else
			System.out.println("La secuencia no tiene periodo");

	}
	
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		String sec;
		
		File archivo = null;
		Scanner s;
		
		System.out.println("Programa para calcular si una secuencia cumple los postulados de golomb");
		System.out.println("Introduce la dirección del fichero de texto:");
		sec = br.readLine();
		
		

		String linea = "";
		
		archivo = new File(sec);

		try {
			s = new Scanner(archivo);
			while (s.hasNextLine()) {
				linea += s.nextLine();
			}
			s.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		
		golomb gol = new golomb(linea);
		gol.periodo();
		gol.primer_postulado();
		gol.segundo_postulado();
		gol.tercer_postulado();
	}

}
