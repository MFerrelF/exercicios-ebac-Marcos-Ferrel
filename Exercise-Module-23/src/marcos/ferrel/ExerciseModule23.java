package marcos.ferrel;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author marcos.ferrel
 */
public class ExerciseModule23 {

    public static void main(String[] args) {

        List<Person> list = new Person().populatePerson();

        System.out.println("The list of persons are: ");
        list.stream()
                .forEach(i -> System.out.println(i.getNameGender()));
        System.out.println();

        System.out.println("The list of womans is: ");
        print();
    }

    public static void print() {
        List<Person> list = new Person().populatePerson();
        Stream<Person> stream = list.stream()
                .filter(person -> person.getNameGender().endsWith("F"));
        stream.forEach(i -> System.out.println(i.getNameGender()));

    }


}
