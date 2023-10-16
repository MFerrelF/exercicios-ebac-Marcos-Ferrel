package marcos.ferrel;

import java.util.*;

public class CastingWrapper {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Please insert your age: ");
		int age = kb.nextInt();
		
		Integer age1 = Integer.valueOf(age);
		
		System.out.println("Your age, using the wrapper method, is: "+age1);
		
		

	}

}
