package SolucionesProblemas;



/**
 * 
 * @author aa.yepes & ma.forero11
 *
 */

public class ProblemaA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
	
	/**
	 * 
	 * @param a arreglo den 4 enteros A, B, C, D
	 * @param n numero del ano actual entero.
	 * @return la Convolución ponderada. 
	 *
	 */
	public double Cponderada(int[] a,int n)
	{
		int resultado = 0;
		Integer[] r = new Integer[n+1];
		
		for (int i = 0; i < n+2; i++)
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
			else
			{
				r[i] = a[2]*r[i-2] + a[3]*r[i-1]; 
			}			
		}
		
		for (int i = 0; i < n+1; i ++)
		{
			resultado = r[i]*r[n-i];
		}

		
		return resultado;
	}

}
