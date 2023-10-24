package marcos.ferrel;

import java.util.*;

public class ExerciseModule11 {

	public static void main(String[] args) {
		partOne();
		partTwo();

	}

	public static void partOne() {
		Scanner kb = new Scanner(System.in);
		List<String> list = new LinkedList<>();
		System.out.println("Please insert the first name: ");
		String name1 = kb.next();
		System.out.println("Please insert the second name: ");
		String name2 = kb.next();
		System.out.println("Please inser the third name: ");
		String name3 = kb.next();
		list.add(name1);
		list.add(name2);
		list.add(name3);
		Collections.sort(list);
		System.out.println(list);
		System.out.println(" ");

	}

	public static void partTwo() {
		List<String> mensList = new LinkedList<>();
		List<String> womensList = new LinkedList<>();
		Scanner kb = new Scanner(System.in);
		System.out.println(
				"For the second part you will need to print a name followed by their gender, e.g: \"Pedro-M\" or \"Fernanda-F\"");
		while (true) {
			System.out.println(
					"Please insert a name and a gender like the example above or \"Exit\", when its enought, to leave: ");
			String nameG = kb.nextLine();
			if (nameG.equalsIgnoreCase("exit")) {
				break;
			} else {
				String[] nameGender = nameG.split("-");
				if (nameGender.length == 2) {
					String name = nameGender[0];
					String gender = nameGender[1];
					if (gender.equalsIgnoreCase("M")) {
						mensList.add(name);
					} else if (gender.equalsIgnoreCase("F")) {
						womensList.add(name);
					}

				}

			}

		}
		Collections.sort(mensList);
		System.out.println("The list of mens is: " + mensList);
		Collections.sort(womensList);
		System.out.println("The list of womens is: " + womensList);
	}

}
