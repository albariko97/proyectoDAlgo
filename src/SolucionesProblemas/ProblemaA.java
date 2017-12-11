package SolucionesProblemas;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * 
 * @author aa.yepes & ma.forero11
 *
 */

public class ProblemaA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProblemaA prob = new ProblemaA();
		try (
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				){
			System.out.println("Introduzca la entrada");
			String in = br.readLine();
			String[] parts = in.split(" ");
			if(parts.length!=5){
				System.out.println("Formato inválido");
				is.close();
				br.close();
			}
			else{
				int n = Integer.parseInt(parts[0]);
				double[] arr = new double[4];

				arr[0]=Double.parseDouble(parts[1]);
				arr[1]=Double.parseDouble(parts[2]);
				arr[2]=Double.parseDouble(parts[3]);
				arr[3]=Double.parseDouble(parts[4]);
				System.out.println(Cponderada(arr, n));
			}

		}
		catch(Exception e){
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * @param a arreglo den 4 enteros A, B, C, D
	 * @param n numero del ano actual entero.
	 * @return la Convolución ponderada. 
	 *
	 */
	public static double Cponderada(double[] a,int n)
	{
		double resultado = 0;
		double[] r = new double[n+1];

		for (int i = 0; i <= n; i++)
		{
			if(i < 2)
			{
				if (i == 0)
				{
					r[i] = a[0];
				}
				else
				{
					r[i] = a[1];
				}

			}
			else if (i >= 2)
			{
		
				r[i] = a[2]*r[i-2] + a[3]*r[i-1]; 
			}			
		}

		for (int i = 0; i <= n; i ++)
		{
		
			resultado +=  r[i]*r[n-i];
		
			System.out.println("-------------------");
			System.out.println("-------  "+  resultado  +"   -------");
		}


		return resultado;
	}

}
