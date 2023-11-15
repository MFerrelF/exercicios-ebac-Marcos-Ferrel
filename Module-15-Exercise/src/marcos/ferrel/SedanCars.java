package marcos.ferrel;

public class SedanCars extends CarFactory{
    @Override
    Car produceCar(String carModel) {
        switch (carModel) {

            case "A":
                return new Jetta(1000, 50000L, "Black");
            case "B":
                return new Passat(950, 45000L, "Red");
            default:
                System.out.println("Model not available at the moment.");
                return null;
        }
    }
}
