import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class cifrar {
	
	public cifrar(){
		
	}
	
	public String xor(String a, String b){
		String res="";
		int x1=0,x2=0;

		for(int i=0; i<a.length(); i++){
			x1=Integer.valueOf(a.charAt(i)-'0');
			x2=Integer.valueOf(b.charAt(i)-'0');
			
			res+=String.valueOf(x1^x2);
		}
		return res;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		cifrar c=new cifrar();
		
		a51 b=new a51(0,"","","");
		ArrayList<String> semillas = new ArrayList<String>();
		System.out.print("Introduzca el nombre del fichero con las semillas: ");
		String fentrada = lectura.readLine();
		semillas=b.LeeFichero(fentrada);
		System.out.print("Introduzca el nombre del fichero a cifrar: ");
		fentrada = lectura.readLine();
		ArrayList<String> entrada = b.LeeFichero(fentrada);
		int longitud=entrada.get(0).length();
		a51 a=new a51(longitud,semillas.get(0),semillas.get(1),semillas.get(2));
		String aux = a.algoritmo();
		
		aux=c.xor(aux, entrada.get(0));
		System.out.print("Introduzca el nombre del fichero de salida: ");
		String fsalida = lectura.readLine();
		a.EscribeFichero(fsalida,aux);
		System.out.print("Se ha generado el fichero: "+fsalida);
	}
}
