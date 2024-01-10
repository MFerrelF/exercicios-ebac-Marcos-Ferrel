package marcos.ferrel.domain;

/**
 * @author marcos.ferrel
 */
public class Customer implements Interface{

    private String name;

    private Long cpf;

    private Long phoneNumber;

    private String address;

    private Integer number;

    private String city;

    private String state;

    public Customer(String name, String cpf, String pNumber, String address, String number, String city, String state) {

        this.name = name;
        this.cpf = Long.valueOf(cpf.trim());
        this.phoneNumber = Long.valueOf(pNumber.trim());
        this.address = address;
        this.number = Integer.valueOf(number.trim());
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
