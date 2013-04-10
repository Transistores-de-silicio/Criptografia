import java.io.*;
import java.util.*;

public class lfsr {
	
	private String semilla;
	private String salida;
	private int bitReloj;
	private ArrayList<Integer> polconexion;
	
	lfsr(String s,ArrayList<Integer> p){
		semilla=s;
		polconexion=p;
		salida="";
		bitReloj=0;
	}
	
	public void desplazarSemilla(String nueva){
		String aux=nueva;
		for(int i=1;i<semilla.length();i++){
			aux+=semilla.charAt(i-1);
		}
		semilla=aux;
	}
	
	public void setPolinomio(ArrayList<Integer> p){
		polconexion=p;
	}
	
	public ArrayList<Integer> getPolinomio(){
		return polconexion;
	}
	
	public String getSalida(){
		return salida;
	}
	
	
	public char getBitReloj(){
		return semilla.charAt(bitReloj);
	}
	
	public String getSemilla(){
		return semilla;
	}

	public void setBitReloj(int c){
		bitReloj=c;
	}
	
	public void setSemilla(String s){
		semilla=s;
	}
	
	public int xor(ArrayList<Integer> lista){
		int res=0;

		for(int i=0; i<lista.size(); i++){
			res=res^lista.get(i);
		}
		return res;
	}
	
	public void EscribeFichero(String nombrefich, String contenido){
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
	
	public void algoritmo(){
		salida=semilla;
		int n=(int)Math.pow(2,semilla.length())-1;
		int x=0;
		ArrayList<Integer> C = new ArrayList<Integer>();
		
		for(int i=0;i<n-semilla.length();i++){
			C.clear();
			for(int j=0;j<polconexion.size();j++){
				x=semilla.length()-Integer.valueOf(polconexion.get(j));
				C.add(Integer.valueOf(salida.charAt(x+i)-'0'));
			}
			salida+=String.valueOf(xor(C));
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> Dx = new ArrayList<Integer>();
		int ncoef=0,aux=0;
		lfsr l;
		String fichero_salida="";
		
		System.out.print("Numero de elemntos del polinomio de conexion: ");
		String entrada = lectura.readLine();
		ncoef=Integer.valueOf(entrada);

		for(int i=ncoef;i>0;i--){
			System.out.print("Introduzca el termino "+i+": ");
			Dx.add(Integer.valueOf(lectura.readLine()));
		}
		System.out.print("Polinomio introducido: X^"+Dx.get(0));
		for(int i=1;i<Dx.size();i++){
			System.out.print("+X^"+Dx.get(i));
		}
		System.out.println("+1");
		
		System.out.print("Introduzca la semilla: ");
		String semilla = lectura.readLine();
		
		//Se crea un fichero de salida debido a que la secuencia mostrada puede ser demasiado larga.
		fichero_salida+="Salida obtenida para la semilla: "+semilla+"\n";

		l = new lfsr(semilla,Dx);
		l.algoritmo();
		fichero_salida+=l.getSalida()+"\n";
		
		l.EscribeFichero("salidaLFSR.txt", fichero_salida);
		System.out.print("Se ha generado el fichero de salida: salidaLFSR.txt");
	}
}
