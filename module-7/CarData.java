package exercise.module7;

/**
 * This is the data class of my main class. It brings the information that'll be displayed on my program.  
 */

public class CarData {
	
	/**
	 * Set the characteristics of the vehicle.
	 * 
	 * @param brand represents the brand of the car to be sold;
	 * @param model represents the model of the car to be sold;
	 * @param color represents the color of the car to be sold;
	 * @param price brings the price;
	 * @param year the year the car was made;
	 * 
	 */

	private String brand;
	private String model;
	private String color;
	private double price;
	private int year;
	
	public CarData(String brand, String model, String color, double price, int year) {
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.price = price;
		this.year = year;
	}
	
	/**
	 * Return the input of the brand. 
	 * @return the brand.
	 */

	public String getBrand() {
		return brand;
	}

	public void setBrand() {
		System.out.println("==============================================================");
		System.out.println("Welcome to our store, CarSale, the best prices in the world!!!");
		System.out.println("==============================================================");
		System.out.println("The car brand is: "+brand);
	}
	/**
	 * Return the input of the model.
	 * @return the model;
	 */

	public String getModel() {
		return model;
	}

	public void setModel() {
		System.out.println("The car model is: "+model);
	}
	/**
	 * Return the input of the color.
	 * @return the color;
	 */

	public String getColor() {
		return color;
	}
	
	public void setColor() {
		System.out.println("The car color is: "+color);
	}
	/**
	 * Return the input of the price.
	 * @return the price;
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice() {
		System.out.println("The car price is: R$"+price);
	}
	/**
	 * Return the input of the year the car was made.
	 * @return the year;
	 */
	public int getYear() {
		return year;
	}

	public void setYear() {
		System.out.println("The year the car was made is: "+year);
	}
	
}
