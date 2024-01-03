package marcos.ferrel;

import java.util.*;

/**
 * @author marcos.ferrel
 */
public class ExerciseModule22a {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        List<String> mensList = new LinkedList<>();
        List<String> womensList = new LinkedList<>();

        System.out.println("Please insert a group of names followed by their gender, e.g: \"Pedro-M\" or \"Fernanda-F\"\" and separated by \",\"");
        String input = kb.next();
        String[] groupNames = input.split(",");
        System.out.println("The list of names and gender is: ");
        for (int i = 0; i < groupNames.length; i++) {
            Arrays.sort(groupNames);
            System.out.println(groupNames[i]);
            String[] nameGender = groupNames[i].split("-");
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
        System.out.println(" ");
        Collections.sort(womensList);
        womensList.stream()
                .forEach(i -> System.out.println(i));
    }
}
