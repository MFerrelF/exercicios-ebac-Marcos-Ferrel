package marcos.ferrel.domain;

import annotations.KeyType;
import annotations.Table;
import annotations.TableColumn;
import marcos.ferrel.DAO.SalesInter;

/**
 * @author marcos.ferrel
 */
@Table("TB_CUSTOMER")
public class Customer implements SalesInter {

    @TableColumn(dbName = "id", setJavaName = "setId")
    private Long id;

    @TableColumn(dbName = "name", setJavaName = "setName")
    private String name;

    @KeyType("getCpf")
    @TableColumn(dbName = "cpf", setJavaName = "setCpf")
    private Long cpf;

    @TableColumn(dbName = "phoneNumber", setJavaName = "setPhoneNumber")
    private Long phoneNumber;

    @TableColumn(dbName = "address", setJavaName = "setAddress")
    private String address;

    @TableColumn(dbName = "number", setJavaName = "setNumber")
    private Integer number;

    @TableColumn(dbName = "city", setJavaName = "setCity")
    private String city;

    @TableColumn(dbName = "state", setJavaName = "setState")
    private String state;

    @TableColumn(dbName = "companyEmployee", setJavaName = "setCompanyEmployee")
    private String companyEmployee;

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

    public String getCompanyEmployee() {
        return companyEmployee;
    }

    public void setCompanyEmployee(String companyEmployee) {
        this.companyEmployee = companyEmployee;
    }
}
