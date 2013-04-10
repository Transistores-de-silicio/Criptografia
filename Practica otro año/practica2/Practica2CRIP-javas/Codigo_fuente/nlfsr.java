import java.io.*;
import java.math.*;
import java.util.*;



public class nlfsr {

	private int [] minterms;
	private int num_minterns;
	private int num_bytes;
	private String semilla;
	private String [] expresiones;
	
	nlfsr(){
		num_minterns=0;
	}
	
	nlfsr(int n, String sem){
		num_minterns=n;
		semilla = sem;
		minterms=new int[n];
	}
	
	
	void bytes(int n){
		num_bytes=n;
	}
	
	void calculo_binario(){
		
		String [] expresiones_auxiliares = new String [num_minterns];
		expresiones = new String[num_minterns];
		int auxiliar=0;
		for(int i=0; i < num_minterns; i++){
			auxiliar=minterms[i];
			expresiones[i] = "";
			expresiones_auxiliares[i]="";
			while(auxiliar >= 1){

				expresiones_auxiliares[i] += auxiliar%2 + "";
				auxiliar = auxiliar/2;
			}

			for(int j=0; j < 7-expresiones_auxiliares[i].length(); j++)
				expresiones[i] += "0";
			for(int j=expresiones_auxiliares[i].length()-1; j>=0 ; j--)
				expresiones[i] += expresiones_auxiliares[i].charAt(j);
		}
		
		
	}
	
	public String algoritmo(){
		String a = "";
		boolean salir=false;
		boolean encontrar=false;
		String cad = "";
		Integer h=1;
		String salida = "";
		int pasadas = 0;
		int x=0,y;
		while(pasadas < num_bytes){
		
			salir=false;
			
			for(int i=0; i < num_minterns && !salir ; i++){
				cad = "";
				encontrar=false;
				for(int j=0; j < expresiones[i].length() && !encontrar; j++){

					if(expresiones[i].charAt(j) == semilla.charAt(j)){
						x=i;
						cad = "1";
						salir=true;
					}
					else{
						salir=false;
						cad = "0";
						encontrar=true;
					}
				}
			}
			


			
			if(salir)
				a="1";
			else
				a="0";
			
			
			cad = "";
			for(int i=0; i < semilla.length(); i++){
				if(i==0)
					salida += semilla.charAt(i);
				else
					cad += semilla.charAt(i);
			}
			cad += a;
			semilla = cad;
			pasadas++;
		}
		
		return salida;
		
	}
	
	int numero_de_terminos(){
		return num_minterns;
	}
	
	void anadir_terminos(int term, int pos){
		minterms[pos]=term;
	}
	
	void imprimir(){
		for(int i=0; i < num_minterns; i++){
			System.out.println(minterms[i]);
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		String cadena;
		String nombre_archivo;
		String sem;
		String bytes;
		int num_bytes=0;
		System.out.println("Programa para calcular el NLFSR con 7 celdas");
		System.out.println("Introduce la semilla:");
		sem = br.readLine();
		System.out.println("Introduce el nº de bytes:");
		bytes = br.readLine();
		num_bytes = Integer.valueOf(bytes);/*
		System.out.println("Introduce el número de minterms usados:");
		cadena = br.readLine();
		nlfsr nuevo = new nlfsr(Integer.parseInt(cadena),sem);
		
		for(int i=0; i < nuevo.numero_de_terminos(); i++){
			System.out.println("Introduce el minterms numero " + i+1 + ":");
			cadena = br.readLine();
			nuevo.anadir_terminos(Integer.parseInt(cadena), i);
		}
*/
		System.out.println("Introduce la dirección del fichero:");
		cadena = br.readLine();
		nombre_archivo = cadena;
		File archivo = null;
		Scanner s;

		String linea = "";
		
		archivo = new File(cadena);

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
		
		int repe=0;
		
		for(int i=0; i < linea.length(); i++){
			if(linea.charAt(i) == ',')
				repe++;
		}
		repe++;
		
		nlfsr nuevo = new nlfsr(repe,sem);
		nuevo.bytes(num_bytes);
		cadena = "";
		int cad=0;
		for(int i=0; i < linea.length(); i++){
			if(linea.charAt(i) == ','){
				nuevo.anadir_terminos(Integer.parseInt(cadena),cad);
				cad++;
				cadena="";
			}
			else
				cadena += linea.charAt(i);
		}
		
		nuevo.anadir_terminos(Integer.parseInt(cadena), cad);
		
		nuevo.calculo_binario();
		//nuevo.imprimir();
		String contenido = nuevo.algoritmo();
		
		int numero = 0;
		numero = nombre_archivo.indexOf(".");
		numero = nombre_archivo.charAt(numero-1)-'0';
		
		String nombrefich = "nlfsr_";
		nombrefich += ""+numero;
		nombrefich += sem;
		nombrefich += ".txt";
		

		
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nombrefich);
            pw = new PrintWriter(fichero);
            pw.println(contenido);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		
	}
	
}
