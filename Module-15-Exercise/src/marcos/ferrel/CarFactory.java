package marcos.ferrel;

public abstract class CarFactory {

    public Car produce(String carModel) {

        Car car = produceCar(carModel);
        car = prepareCar(car);
        return car;

    }

    abstract Car produceCar(String carModel);

    private Car prepareCar(Car car) {

        car.engineCheck();
        car.paintCheck();
        car.utilitiesCheck();
        return car;

    }
}
