/**
 * 
 */
package marcos.ferrel.piles;

import java.util.LinkedList;

/**
 * @author marcos.ferrel
 * 
 */
public class Piles {
	
	public static void main(String[] args) {
		
		LinkedList<Integer> p = new LinkedList();
		p.push(1);
		p.push(2);
		p.push(3);
		p.push(4);
		
		System.out.println(p);
		
		System.out.println("");
		
		push(p);		
		System.out.println(p);
		
		System.out.println("");
		
		size(p);
		
		System.out.println("");	
		
		top(p);
		
		System.out.println("");
		
		System.out.println(p.isEmpty());
		
		System.out.println("");
		
		pop(p);
		
		System.out.println("");
		
		System.out.println(p);
		
	}
	
	public Integer pilePosition;
	
	public Piles() {
		this.pilePosition = -1;
	}
	
	public Boolean isEmpty() {
		if (this.pilePosition == -1) {
			return true;
		}
		return false;
	}
	
	public static void top(LinkedList p) {
		System.out.println("The last number added to the pile is: " + p.peek());
	}
	
	public static void pop(LinkedList p) {
		System.out.println(p.poll());
	}
	
	public static void push(LinkedList p) {
		p.push(5);
	}
	
	public static void size(LinkedList p) {
		System.out.println("The size of the queue is: " + p.size());
	}
	
}
