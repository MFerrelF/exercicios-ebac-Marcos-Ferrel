package marcos.ferrel.cars;

import marcos.ferrel.cars.generic.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marcos.ferrel
 */

public class CarProgram {

    public static void main(String[] args) {

        System.out.println("***** List of Cars *****");
        System.out.println("");

        List<ICar> cars =new ArrayList<>();
        cars.add(new Civic());
        cars.add(new Marea());
        cars.add(new Uno());
        cars.add(new Mustang());
        printCars(cars);

    }

    private static void printCars(List<? extends ICar> list) {
        for (ICar car : list) {
            System.out.println(car.getClass().getSimpleName());
        }
    }
}
