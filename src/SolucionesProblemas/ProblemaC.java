package SolucionesProblemas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author aa.yepes & ma.forero11
 *
 */
public class ProblemaC {

	public static void main(String[] args) throws Exception {
		ProblemaC prob = new ProblemaC();
		try {
			InputStreamReader is= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);

			System.out.println("Introduzca su cadena y pulse enter");

			String linea = br.readLine();
			int n = 0;
			int k = 0;	
			int numeroLineas = 0;
			
			while(linea!=null && linea.length()>0) {

				String [] condiciones = linea.split(" ");
				String letras = "";
				for (int i = 0; i < condiciones.length; i++) {
					if( n==0 || k==0) {
						if( n == 0 ) {
							n = Integer.parseInt(condiciones[i]);
						}
						else if( k == 0 ) {
							k = Integer.parseInt(condiciones[i]);
							linea = br.readLine();
						}
						else {
							System.out.println("Formato desconocido");
							is.close();
							br.close();
						}
					}
					if( n!=0 && k!=0 ) {				
						while(numeroLineas < n) {
							String [] filas = linea.split("");
							if (filas.length != k )
							{
								System.out.println("Longitud de cadenas no es igual a k");
								is.close();
								br.close();
							}
							for(int w=0;w<filas.length;w++){
								letras+=filas[w];
							}
							numeroLineas++;							
							linea = br.readLine();
						}						
					}
				}
				String cadenaFinal = prob.minimal(n, k, letras);
				System.out.println(cadenaFinal);
				n = 0; k = 0;				
				linea = br.readLine();				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * reconstruir texto
	 * @param pN 
	 * @param pK 
	 * @param letras 
	 * @return texto mínimo reconstruido
	 */
	public String minimal(int pN, int pK, String cadenas) {
		String cadenaFinal = "";
		String[] cad2 = cadenas.split("");
		String[] rta2 = new String[pN];
		for( int i=0 ; i< pN ; i++ ) {
			rta2[i]="";
		}
		
		int j = 0;
		
		int c = 0;
		
		for( int i=1 ; i< pN+1 ; i++ ) {
			for (j = c; j < (i*pK); j++) {
				rta2[i-1] += cad2[j];	
			}
			c = j;
		}

		String [] validar = new String[cad2.length];
		for(int n=0; n< validar.length; n++) {
			validar[n] = cad2[n];
		}
		
		int k = cad2.length-1;
		int i = 0;
		while (i < k) {
			if(validar[i].equalsIgnoreCase(validar[i+1])) {
				for (int o = i + 2; o < k+1; o++ ) {
					String just = validar[o];
					validar[o-1] = just;
				}
				validar[k] = "";
				String cadena = "";

				for(String letra: validar) {	
					cadena += letra;
				}
				
				int r = 0;
				for(int m=0; m < pN ; m++ ) {
					if(!cadena.contains(rta2[m])) {
						m = pN + 1;
						r=m;
					}
				}
				
				if(r == pN) {	
					for(int n=0; n < validar.length; n++) {
						cad2[n] = validar[n];
					}
					k--;
				}
				else {
					for(int n=0; n < cad2.length; n++) {
						validar[n] = cad2[n];
					}
					i++;
				}
			}
			else
				i++;
		}

		int contar=0;
		for(int u=0; u < cad2.length; u++) {
			validar[u] =  cad2[u];
			if(!validar[u].equalsIgnoreCase(""))
				contar++;
		}
		k = pK;
		for (i = k; i > 0; i--) {
			for(int p=0; p <= contar-i; p++)
			{
				String cadenaMin = "";
				String cadenaMax = "";
				for(j = p; j < contar; j++)
				{	
					if(j==p)
					{
						for (int q = 0; q < p; q++) {
							cadenaMax += validar[q];
						}
					}
					if(j<i+p)
						cadenaMin += validar[j];
					else
						cadenaMax += validar[j];
				}
				if(cadenaMax.contains(cadenaMin))
				{
					int m=0;
					for(; m < pN ; m++ )
					{
						if(!cadenaMax.contains(rta2[m]))
						{
							m = pN + 1;
						}
					}
					if(m == pN)
					{	
						int n=0;
						for(; n < cadenaMax.length(); n++) {
							cad2[n] = cadenaMax.substring(n, n + 1);
							validar[n] = cadenaMax.substring(n, n + 1);
						}
						cad2[n] = "";
						validar[n] = "";
						contar = cadenaMax.length();
						p=-1;
					}
				}
			}
		}

		for(int n=0; n < cad2.length; n++) {
			validar[n] =  cad2[n];
		}
		int tomaLetras = 1;
		String cambia = "";
		while(tomaLetras<contar)
		{
			cambia = "";
			for(i = contar - 1; i>=tomaLetras; i-- )
			{
				if(cambia.equalsIgnoreCase(""))
				{
					k=i;
					for (j = 0; j < tomaLetras; j++)
					{
						cambia += cad2[k+1-tomaLetras];
						k++;
					}
					k=i-tomaLetras;
				}
				validar[i] = cad2[k];
				k--;
			}
			for(int n=0; n < cambia.length(); n++) {
				validar[n] = cambia.substring(n, n + 1);
			}

			String cadena = "";
			for(String letra: validar)
			{	
				cadena += letra;
			}
			int m=0;
			for(; m < pN ; m++ )
			{
				if(!cadena.contains(rta2[m]))
				{
					m = pN + 1;
				}
			}
			if(m == pN)
			{	
				for(int n=0; n < validar.length; n++) {
					cad2[n] = validar[n];
				}

				k = pK;
				for (i = k; i > 0; i--) {
					for(int p=0; p <= contar-i; p++)
					{
						String cadenaMin = "";
						String cadenaMax = "";
						for(j = p; j < contar; j++)
						{	
							if(j==p)
							{
								for (int q = 0; q < p; q++) {
									cadenaMax += validar[q];
								}
							}
							if(j<i+p)
								cadenaMin += validar[j];
							else
								cadenaMax += validar[j];
						}
						if(cadenaMax.contains(cadenaMin))
						{
							m=0;
							for(; m < pN ; m++ )
							{
								if(!cadenaMax.contains(rta2[m]))
								{
									m = pN + 1;
								}
							}
							if(m == pN)
							{	
								int n=0;
								for(; n < cadenaMax.length(); n++) {
									cad2[n] = cadenaMax.substring(n, n + 1);
									validar[n] = cadenaMax.substring(n, n + 1);
								}
								cad2[n] = "";
								validar[n] = "";
								contar = cadenaMax.length();
								p=-1;
							}
						}
					}
				}
				tomaLetras++;
			}
			else
			{
				for(int n=0; n < contar; n++) {
					validar[n] =  cad2[n];
				}
				tomaLetras++;
			}			
		}

		System.out.println("");
		for(i = 0; i < contar; i++)
		{
			System.out.print(cad2[i]);
		}

		return cadenaFinal;
	}
}
