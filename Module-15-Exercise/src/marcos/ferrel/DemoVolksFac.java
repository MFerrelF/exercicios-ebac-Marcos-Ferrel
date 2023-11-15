package marcos.ferrel;

public class DemoVolksFac {

    public static void main(String[] args) {
        System.out.println("***** Welcome to our new Volkswagen Factory opening! *****");
        System.out.println("");
        Customer firstCustomer = new Customer("C", false);
        CarFactory carFactory = getCarModel(firstCustomer);
        Car firstCar = carFactory.produce(firstCustomer.getCarModel());
        firstCar.readyToSell();

    }

    private static CarFactory getCarModel(Customer customer) {

        if (customer.carType()) {
            return new PopularCars();
        } else {
            return new SedanCars();
        }

    }
}
