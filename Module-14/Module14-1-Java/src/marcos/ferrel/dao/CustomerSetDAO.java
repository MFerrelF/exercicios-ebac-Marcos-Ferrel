package marcos.ferrel.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import marcos.ferrel.domain.Customer;


public class CustomerSetDAO implements ICustomerDAO {

    private Set<Customer> set;

    public CustomerSetDAO() {
        this.set = new HashSet<>();

    }

    public Boolean register(Customer customer) {
        return this.set.add(customer);
    }

    public void exclude(Long cpf) {
        Customer customerFound = null;
        for (Customer customer : this.set) {
            if (customer.getCpf().equals(cpf)) {
                customerFound = customer;
                break;
            }
        }
        if (customerFound != null) {
            this.set.remove(customerFound);
        }
    }

    public void alteration(Customer customer) {
        if (this.set.contains(customer)) {
            for (Customer registeredCustomer : this.set) {
                if (registeredCustomer.equals(customer)) {
                    registeredCustomer.setName(customer.getName());
                    registeredCustomer.setpNumber(customer.getpNumber());
                    registeredCustomer.setNumber(customer.getNumber());
                    registeredCustomer.setAddress(customer.getAddress());
                    registeredCustomer.setCity(customer.getCity());
                    registeredCustomer.setState(customer.getState());
                    break;
                }
            }
        }
    }

    public Customer consult(Long cpf) {
        for (Customer registeredCustomer : this.set) {
            if (registeredCustomer.getCpf().equals(cpf)) {
                return registeredCustomer;
            }
        }
        return null;
    }

    public Collection<Customer> findsAll() {
        return this.set;
    }
}

