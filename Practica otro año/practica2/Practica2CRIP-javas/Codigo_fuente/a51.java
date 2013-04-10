import java.io.*;
import java.util.*;


public class a51 {
	private lfsr lfsr1;
	private lfsr lfsr2;
	private lfsr lfsr3;
	private String salida;
	private int nbits;
	
	public a51(int n, String s1, String s2, String s3){
		ArrayList<Integer> D = new ArrayList<Integer>(),C=new ArrayList<Integer>(),E=new ArrayList<Integer>();
		nbits=n;
		salida="";
		D.add(18); D.add(17); D.add(16); D.add(13);
		lfsr1 = new lfsr(s1,D);
		
		E.clear();
		E.add(21); E.add(20);
		lfsr2 = new lfsr(s2,E);
		
		C.clear();
		C.add(22); C.add(21); C.add(20); C.add(7);
		lfsr3 = new lfsr(s3,C);

		lfsr1.setBitReloj(8);
		lfsr2.setBitReloj(10);
		lfsr3.setBitReloj(10);
	}
	
	public int xor(ArrayList<Integer> lista){
		int res=0;

		for(int i=0; i<lista.size(); i++){
			res=res^lista.get(i);
		}
		return res;
	}
	
	public String lfsrNext(String semilla, ArrayList<Integer> D){
		ArrayList<Integer> C = new ArrayList<Integer>();
		C.clear();
		for(int i=0;i<D.size();i++){
			C.add(Integer.valueOf(semilla.charAt(D.get(i))));
		}
		
		return String.valueOf(xor(C));
	}
	
	public String algoritmo(){
		String r1="",r2="",r3="";
		ArrayList<Integer> C = new ArrayList<Integer>();
		int c1=0,c2=0,c3=0;
		
		for(int i=0;i<nbits;i++){
			
			if((lfsr1.getBitReloj()=='0' && lfsr2.getBitReloj()=='0' && lfsr3.getBitReloj()=='0') 
					|| (lfsr1.getBitReloj()=='1' && lfsr2.getBitReloj()=='1' && lfsr3.getBitReloj()=='1')){
				r1=lfsrNext(lfsr1.getSemilla(), lfsr1.getPolinomio());
				r2=lfsrNext(lfsr2.getSemilla(), lfsr2.getPolinomio());
				r3=lfsrNext(lfsr3.getSemilla(), lfsr3.getPolinomio());
				
				c1=Integer.valueOf(lfsr1.getSemilla().charAt(lfsr1.getSemilla().length()-1)-'0');
				c2=Integer.valueOf(lfsr2.getSemilla().charAt(lfsr2.getSemilla().length()-1)-'0');
				c3=Integer.valueOf(lfsr3.getSemilla().charAt(lfsr3.getSemilla().length()-1)-'0');
				C=new ArrayList<Integer>();
				C.add(c1); C.add(c2); C.add(c3); 
				
				lfsr1.desplazarSemilla(r1);
				lfsr2.desplazarSemilla(r2);
				lfsr3.desplazarSemilla(r3);
				
			}else

			if((lfsr1.getBitReloj()=='0' && lfsr2.getBitReloj()=='0' && lfsr3.getBitReloj()=='1') 
					|| (lfsr1.getBitReloj()=='1' && lfsr2.getBitReloj()=='1' && lfsr3.getBitReloj()=='0')){
				r1=lfsrNext(lfsr1.getSemilla(), lfsr1.getPolinomio());
				r2=lfsrNext(lfsr2.getSemilla(), lfsr2.getPolinomio());
				
				c1=Integer.valueOf(lfsr1.getSemilla().charAt(lfsr1.getSemilla().length()-1)-'0');
				c2=Integer.valueOf(lfsr2.getSemilla().charAt(lfsr2.getSemilla().length()-1)-'0');
				c3=Integer.valueOf(lfsr3.getSemilla().charAt(lfsr3.getSemilla().length()-1)-'0');
				C=new ArrayList<Integer>();
				C.add(c1); C.add(c2); C.add(c3); 
				
				lfsr1.desplazarSemilla(r1);
				lfsr2.desplazarSemilla(r2);
			}else
			
			if((lfsr1.getBitReloj()=='0' && lfsr2.getBitReloj()=='1' && lfsr3.getBitReloj()=='0') 
					|| (lfsr1.getBitReloj()=='1' && lfsr2.getBitReloj()=='0' && lfsr3.getBitReloj()=='1')){
				r1=lfsrNext(lfsr1.getSemilla(), lfsr1.getPolinomio());
				r3=lfsrNext(lfsr3.getSemilla(), lfsr3.getPolinomio());
				
				c1=Integer.valueOf(lfsr1.getSemilla().charAt(lfsr1.getSemilla().length()-1)-'0');
				c2=Integer.valueOf(lfsr2.getSemilla().charAt(lfsr2.getSemilla().length()-1)-'0');
				c3=Integer.valueOf(lfsr3.getSemilla().charAt(lfsr3.getSemilla().length()-1)-'0');
				C=new ArrayList<Integer>();
				C.add(c1); C.add(c2); C.add(c3); 
				
				lfsr1.desplazarSemilla(r1);
				lfsr3.desplazarSemilla(r3);
			}else
			
			if((lfsr1.getBitReloj()=='1' && lfsr2.getBitReloj()=='0' && lfsr3.getBitReloj()=='0') 
					|| (lfsr1.getBitReloj()=='0' && lfsr2.getBitReloj()=='1' && lfsr3.getBitReloj()=='1')){
				r2=lfsrNext(lfsr2.getSemilla(), lfsr2.getPolinomio());
				r3=lfsrNext(lfsr3.getSemilla(), lfsr3.getPolinomio());
				
				c1=Integer.valueOf(lfsr1.getSemilla().charAt(lfsr1.getSemilla().length()-1)-'0');
				c2=Integer.valueOf(lfsr2.getSemilla().charAt(lfsr2.getSemilla().length()-1)-'0');
				c3=Integer.valueOf(lfsr3.getSemilla().charAt(lfsr3.getSemilla().length()-1)-'0');
				C=new ArrayList<Integer>();
				C.add(c1); C.add(c2); C.add(c3); 
				
				lfsr2.desplazarSemilla(r2);
				lfsr3.desplazarSemilla(r3);
			}
			salida+=String.valueOf(xor(C));
		}
		return salida;
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
	
	public ArrayList<String> LeeFichero(String nombfichero){
		ArrayList<String> C = new ArrayList<String>();
		
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;

		  try {
		     archivo = new File (nombfichero);
		     fr = new FileReader (archivo);
		     br = new BufferedReader(fr);
		
		     String linea;
		     while((linea=br.readLine())!=null)
		        C.add(linea);
		  }
		  catch(Exception e){
		     e.printStackTrace();
		  }finally{
		     try{                    
		        if( null != fr ){   
		           fr.close();     
		        }                  
		     }catch (Exception e2){ 
		        e2.printStackTrace();
		     }
		  }
		
		return C;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		a51 b=new a51(0,"","","");
		ArrayList<String> semillas = new ArrayList<String>();
		System.out.print("Introduzca el nombre del fichero con las semillas: ");
		String fentrada = lectura.readLine();
		semillas=b.LeeFichero(fentrada);
		a51 a=new a51(200,semillas.get(0),semillas.get(1),semillas.get(2));
		System.out.print("Introduzca el nombre del fichero de salida: ");
		String fsalida = lectura.readLine();
		a.EscribeFichero(fsalida,a.algoritmo());
		System.out.print("Se ha generado el fichero "+fsalida);
		
	}
}