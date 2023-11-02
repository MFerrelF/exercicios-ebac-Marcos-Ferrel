package marcos.ferrel;

public class IdentificationProgram {

	public static void main(String[] args) {
		PhysicalPerson person = new PhysicalPerson();
		person.setName("Marcos");
		person.setSurname("Ferrel");
		person.setCpf("123.456.789-42");
		person.printPP();
		System.out.println("The person identification is: " + person.getCpf());
		System.out.println(" ");
		
		LegalPerson company = new LegalPerson();
		company.setName("Marcos");
		company.setSurname("Ferrel");
		company.setSocialReason("Ferrari Inc.");
		company.setCnpj("321.654.987/0001");
		System.out.println("The company name is: " + company.getSocialReason());		
		System.out.println("The company identification is: " + company.getCnpj());
		company.printLP();
	}

}
