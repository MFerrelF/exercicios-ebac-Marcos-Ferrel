package marcos.ferrel;

public abstract class Person {
	
	private String name;
	
	private String surname;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void printPP() {
		System.out.println("The person name is: " + this.name + " " + this.surname);		
	}
	
	public void printLP() {
		System.out.println("The owner of the company is: " + name + " " + surname);
	}
}
