package marcos.ferrel;

public class Customer {

    private String carModel;
    private boolean carType;

    public Customer (String carModel, boolean carType) {

        this.carModel = carModel;
        this.carType = carType;

    }

    public String getCarModel() {
        return carModel;
    }

    public boolean carType() {
        return carType;
    }

}
