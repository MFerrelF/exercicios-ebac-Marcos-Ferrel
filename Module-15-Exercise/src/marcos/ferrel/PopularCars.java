package marcos.ferrel;

public class PopularCars extends  CarFactory{

    @Override
    Car produceCar(String carModel) {
        switch (carModel) {

            case "A":
                return new Gol (500, 15000L, "White");
            case "B":
                return new Polo (600, 20000L, "Blue");
            default:
                System.out.println("Model not available at the moment.");
                return null;
        }

    }
}
