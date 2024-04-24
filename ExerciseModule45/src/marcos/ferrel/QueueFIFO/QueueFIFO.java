/**
 * 
 */
package marcos.ferrel.QueueFIFO;

import java.util.LinkedList;

/**
 * @author marcos.ferrel
 * 
 */
public class QueueFIFO {
	
	public static void main(String[] args) {
		
		LinkedList<Integer> p = new LinkedList();
		p.add(1);
		p.add(2);
		p.add(3);
		p.add(4);
		
		System.out.println(p);
		
		System.out.println("");
		
		enqueue(p);
		System.out.println(p);
		
		System.out.println("");
		
		dequeue(p);
		System.out.println(p);
		
		System.out.println("");
		
		rear(p);
		
		System.out.println("");
		
		front(p);
		
		System.out.println("");
		
		size(p);
		
		System.out.println("");
		
		System.out.println(p.isEmpty());
				
	}	
	
	public Integer fifoPosition;
	
	public QueueFIFO() {
		this.fifoPosition = -1;
	}
	
	public Boolean isEmpty() {
		if (this.fifoPosition == -1) {
			return true;
		}
		return false;
	}
	
	public static void enqueue(LinkedList p) {
		p.addFirst(6);
	}
	
	public static void dequeue(LinkedList p) {
		p.removeFirst();
	}
	
	public static void rear(LinkedList p) {
		System.out.println("The last number of the queue is: " + p.peekLast());
	}
	
	public static void front(LinkedList p) {
		System.out.println("The first number of the queue is: " + p.peekFirst());
	}
	
	public static void size(LinkedList p) {
		System.out.println("The size of the queue is: " + p.size());
	}

}
