package marcos.ferrel;

public abstract class Car {

    private int horsePower;
    private long carPrice;
    private String color;

    public Car(int horsePower, long carPrice, String color) {

        this.horsePower = horsePower;
        this.carPrice = carPrice;
        this.color = color;

    }

    public void readyToSell() {
        System.out.println("The model is a Volkswagen " + getClass().getSimpleName() + " is ready to be sold for " + carPrice + ".");
        System.out.println("All parts are checked and ready to work.");
    }

    public void engineCheck() {
        System.out.println("The car engine was checked and achieved a potency of " + horsePower + " horse power.");
    }

    public void paintCheck() {
        System.out.println("The car paint was checked and its shining. Its color is " + color + ".");
    }

    public void utilitiesCheck() {
        System.out.println("All utilities were checked and ready to perform.");
    }



}
