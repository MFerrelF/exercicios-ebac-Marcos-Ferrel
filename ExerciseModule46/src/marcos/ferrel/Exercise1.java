/**
 * 
 */
package marcos.ferrel;

/**
 * @author marcos.ferrel
 * 
 */
public class Exercise1 {
	
	public static void main (String[] args) {
		
		int n = 30;
		
		System.out.println("Element " + n + ": " + findElement(n));
		
		//Nao é possível se calcular o fatorial de 100 pois o número se torna muito extenso para a variavel tipo "int".
		//A resposta sempre tende a 0.
		//Exercise 3 - The time complexity is O(2^n).
	}
	
	private static int fat = 1;
	
	public static int findElement(int n) {
		
		if(n <= 2) {
			return n;
		}
		for(int i = 1; i <= n; i++) {
			fat = fat * i;
		}
		return fat;
	}

}
