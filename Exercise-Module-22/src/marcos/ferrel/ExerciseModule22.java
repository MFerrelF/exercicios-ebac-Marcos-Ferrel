package marcos.ferrel;

import java.util.List;

/**
 * @author marcos.ferrel
 */
public class ExerciseModule22 {

    public static void main(String[] args) {

        List<Person> list = new Person().populatePerson();

        System.out.println("The list of persons are: ");
        list.stream()
                .forEach(i -> System.out.println(i));
        System.out.println();



        System.out.println("The list of womans are: ");
        list.stream()
                .filter(person -> person.getNameGender().endsWith("F"))
                .forEach(i -> System.out.println(i.getNameGender()));


    }
}
