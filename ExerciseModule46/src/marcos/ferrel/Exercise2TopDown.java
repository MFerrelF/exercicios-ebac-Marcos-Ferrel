/**
 * 
 */
package marcos.ferrel;

/**
 * @author marcos.ferrel
 * 
 */
public class Exercise2TopDown {
	
	public static void main(String[] args) {		
		
		System.out.println(findElementDP(30));
		
		//Nao é possível se calcular o fatorial de 100 pois o número se torna muito extenso para a variavel tipo "int".
		//A resposta sempre tende a 0.
		//Exercise 3 - The time complexity is O(n).
		
	}
	
	private static final int MAX_ELEMENTS = 200;
	private static final int[] fat = new int[MAX_ELEMENTS];
	
	public static int findElementDP(int n) {		
		for(int i = 0; i < MAX_ELEMENTS; i++) {
			fat[i] = 1;
		}		
		return findElement(n);
	}

	private static int findElement(int n) {
		if(fat[n] == 1) {
			if(n <= 2) {
				fat[n] = n;
			} else {
				for(int i = 1; i <= n; i++) {
					fat[n] = fat[n] * i;
				}
			}			
		}
		return fat[n];
	}

}
