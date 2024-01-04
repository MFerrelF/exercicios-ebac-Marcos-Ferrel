package marcos.ferrel;

import java.util.List;

public class Person {

    private String nameGender;

    public Person() {

    }

    public Person(String nameGender) {
        this.nameGender = nameGender;
    }



    public String getNameGender() {
        return nameGender;
    }

    public void setNameGender(String nameGender) {
        this.nameGender = nameGender;
    }

    public List<Person> populatePerson() {
        Person person1 = new Person("Marcos-M");
        Person person2 = new Person("Joana-F");
        Person person3 = new Person("Jonas-M");
        Person person4 = new Person("Fernanda-F");
        return List.of(person1, person2, person3, person4);
    }

    @Override
    public String toString() {
        return nameGender;
    }
}
