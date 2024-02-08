package marcos.ferrel.domain;

import marcos.ferrel.dao.IPersistence;

import javax.persistence.*;

/**
 * @author marcos.ferrel
 */
@Entity
@Table(name = "TB_CUSTOMER")
public class CustomerJPA implements IPersistence {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="customer_seq")
    @SequenceGenerator(name="customer_seq", sequenceName="sq_customer", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "CPF", nullable = false, unique = true)
    private Long cpf;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private Long phoneNumber;

    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @Column(name = "NUMBER", nullable = false)
    private Integer number;

    @Column(name = "CITY", nullable = false, length = 50)
    private String city;

    @Column(name = "STATE", nullable = false, length = 50)
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
