package marcos.ferrel;
import java.util.*;

public class AverageGrades {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("**** Grades Calculator Program ****");
		System.out.println("");
		
		System.out.print("Please, input the first grade: ");
		double grade1 = kb.nextDouble();
		System.out.println(grade1);
		
		System.out.print("Please, input the first grade: ");
		double grade2 = kb.nextDouble();
		System.out.println(grade2);
		
		System.out.print("Please, input the first grade: ");
		double grade3 = kb.nextDouble();
		System.out.println(grade3);
		
		System.out.print("Please, input the first grade: ");
		double grade4 = kb.nextDouble();
		System.out.println(grade4);
		
		double average = (grade1 + grade2 + grade3 + grade4)/4;
		System.out.println("The average score of the student is: "+average);
		

	}

}
