package marcos.ferrel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author marcos.ferrel
 */

public class CarProgram {

    /**
     *@param args
     */
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        System.out.println("***** Welcome to our Car Display *****");
        System.out.println("Select from one of our available brands: ");
        System.out.println("");
        System.out.println("1- Honda");
        System.out.println("2- Fiat");
        System.out.println("3- Volkswagen");
        System.out.println("4- Toyota");
        int choice = kb.nextInt();


        if (choice == 1) {
            List<String> listHonda = new ArrayList<>();
            listHonda.add("Honda Civic");
            listHonda.add("Honda Fit");
            listHonda.add("Honda Accord");
            System.out.println("***** Honda Models *****");
            print(listHonda);
        } else if (choice == 2) {
            List<String> listFiat = new ArrayList<>();
            listFiat.add("Fiat Marea");
            listFiat.add("Fiat Uno");
            listFiat.add("Fiat Strada");
            System.out.println("***** Fiat Models *****");
            print(listFiat);
        } else if (choice == 3) {
            List<String> listVolks = new ArrayList<>();
            listVolks.add("Volkswagen Gol");
            listVolks.add("Volkswagen Polo");
            listVolks.add("Volkswagen Passat");
            System.out.println("***** Volkswagen Models *****");
            print(listVolks);
        } else if (choice == 4) {
            List<String> listToyota = new ArrayList<>();
            listToyota.add("Toyota Corolla");
            listToyota.add("Toyota Camry");
            listToyota.add("Toyota Hilux");
            System.out.println("***** Toyota Models *****");
            print(listToyota);
        } else {
            System.out.println("Option not available at the moment.");
        }

}

    /**
     * @method print generic method for list printing
     * @param list
     */
    private static void print(List<String> list) {
        for (String st : list) {
            System.out.println(st);
            System.out.println("");
        }
    }
}
