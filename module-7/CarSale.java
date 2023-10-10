package exercise.module7;
/**
 * 
 * @author Marcos Ferrel Fonseca
 * @version 1.0
 * 
 */

public class CarSale {
    /**
     * Display the information to our customers.
     * @param args show all the information the customer needs about our sales.
     */
	public static void main(String[] args) {
		CarData carSales = new CarData("Ford","Focus","Black",35500.00,2015);
		carSales.setBrand();
		carSales.setModel();
		carSales.setColor();
		carSales.setPrice();
		carSales.setYear();

	}

}
