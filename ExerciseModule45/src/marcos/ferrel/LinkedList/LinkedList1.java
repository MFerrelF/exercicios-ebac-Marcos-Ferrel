/**
 * 
 */
package marcos.ferrel.LinkedList;

import java.util.LinkedList;

/**
 * @author marcos.ferrel
 * 
 */
public class LinkedList1 {
	
	public static void main (String[] args) {
		
		LinkedList<Integer> p = new LinkedList<Integer>();
		p.add(1);
		p.add(2);
		p.add(3);
		p.add(4);
		
		System.out.println(p);
		
		System.out.println("");
		
		push(p);
		System.out.println(p);
		
		System.out.println("");
		
		pop(p);
		System.out.println(p);
		
		System.out.println("");
		
		insert(p);
		System.out.println(p);
		
		System.out.println("");
		
		remove(p);
		System.out.println(p);
		
		System.out.println("");
		
		elementAt(p);
		
		System.out.println("");
		
		size(p);
		
		System.out.println("");
		
		printList(p);
		
	}
	
	public Integer linkedPosition;
	
	public LinkedList1() {
		this.linkedPosition = -1;
	}
	
	public Boolean isEmpty() {
		if (this.linkedPosition == -1) {
			return true;
		}
		return false;
	}
	
	public static void push(LinkedList p) {
		p.addLast(5);
	}
	
	public static void pop(LinkedList p) {
		System.out.println(p.pollLast());
	}
	
	public static void insert(LinkedList p) {
		p.add(3, 6);
	}
	
	public static void remove(LinkedList p) {
		p.remove(3);
	}
	
	public static void elementAt(LinkedList p) {
		System.out.println(p.get(3));
	}
	
	public static void size(LinkedList p) {
		System.out.println("The size of the LinkedList is: " + p.size());
	}
	
	public static void printList(LinkedList p) {
		System.out.println(p.toString());
	}

}
