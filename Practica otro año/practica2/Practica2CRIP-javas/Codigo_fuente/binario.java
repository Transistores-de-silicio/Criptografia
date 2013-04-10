import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class binario {
	private String numero;
	
	binario(){
		numero = "";
	}
	
	binario(String num){
		numero = num;
	}
	
	int tam(){
		return numero.length();
	}
	
	char valor(int pos){
		return numero.charAt(pos);
	}

	String cadena(){
		return numero;
	}
	
	binario suma(binario aux){
		binario sol = new binario();
		
		String cad = "";
		String cadena = "";
		
		int mayor,menor;
		
		if(tam()> aux.tam()){
			mayor=tam()-1;
			menor=aux.tam()-1;
			
			while(mayor >= 0){
				
				if(menor >= 0){
					if(valor(mayor) == '1' && aux.valor(menor) == '1')
						cad+= '1';
					else if(valor(mayor) == '1' && aux.valor(menor) == '0')
						cad+= '1';
					else if(valor(mayor) == '0' && aux.valor(menor) == '1')
						cad+= '1';
					else if(valor(mayor) == '0' && aux.valor(menor) == '0')
						cad+='0';
					menor--;
				}
				else{
					cad += valor(mayor);
				}
				mayor--;
				
			}
		}
		else{
			menor=tam()-1;
			mayor=aux.tam()-1;
			
			while(mayor >= 0){
				
				if(menor >= 0){
					if(valor(menor) == '1' && aux.valor(mayor) == '1')
						cad+= '1';
					else if(valor(menor) == '1' && aux.valor(mayor) == '0')
						cad+= '1';
					else if(valor(menor) == '0' && aux.valor(mayor) == '1')
						cad+= '1';
					else if(valor(menor) == '0' && aux.valor(mayor) == '0')
						cad+='0';
					menor--;
				}
				else{
					cad += aux.valor(mayor);
				}
				mayor--;
				
			}
		}
		
		for(int j=cad.length()-1; j>=0 ; j--)
			cadena += cad.charAt(j);
		
		sol = new binario(cadena);
		
		return sol;
	}
	
	binario multiplicacion(binario aux){
		
		binario sol = new binario();
		binario a,b,c;
		String cad,cad_aux;
		String cadena = "";
		
		for(int i=aux.tam()-1; i >= 0; i--){
			cad = "";
			for(int j=tam()-1; j >= 0; j--){
				if(valor(j) == '1' && aux.valor(i) == '1')
					cad+= '1';
				else 
					cad+= '0';
			}

			if(i == aux.tam()-1)
				for(int j=cad.length()-1; j>=0 ; j--)
					cadena += cad.charAt(j);
			
			else{

				cad_aux = "";
				for(int j=cad.length()-1; j>=0 ; j--)
					cad_aux += cad.charAt(j);

				for(int k=0; k < aux.tam() - i - 1; k++)
					cad_aux += '0';
				a = new binario(cad_aux);
				b = new binario(cadena);
				c = a.suma(b);
				cadena = c.cadena();
				
			}


				
		}
		


		
		sol = new binario(cadena);
		
		return sol;
		
		
	}
	
	char multiplicacion(){
		for(int i=0; i < numero.length(); i++){
			if(numero.charAt(i) == '0')
				return '0';
		}
		return '1';
	}
	
	void imprimir(){

		for(int i=0; i < tam(); i++){
			System.out.print(valor(i));
		}
		System.out.println();
	}
	
}
