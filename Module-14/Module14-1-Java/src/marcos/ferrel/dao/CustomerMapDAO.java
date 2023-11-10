package marcos.ferrel.dao;

import marcos.ferrel.domain.Customer;

import java.util.*;


import marcos.ferrel.domain.Customer;

public class CustomerMapDAO implements ICustomerDAO {
    private Map<Long, Customer> map;

    public CustomerMapDAO() {
        map = new TreeMap<>();
    }

    @Override
    public Boolean register(Customer customer) {
        if (map.containsKey(customer.getCpf())) {
            return false;
        }
        map.put(customer.getCpf(), customer);
        return true;

    }

    @Override
    public void exclude(Long cpf) {
        Customer registeredCustomer = map.get(cpf);
        map.remove(registeredCustomer.getCpf(), registeredCustomer);

    }

    @Override
    public void alteration(Customer customer) {
        Customer registeredCustomer = map.get(customer.getCpf());
        registeredCustomer.setName(customer.getName());
        registeredCustomer.setpNumber(customer.getpNumber());
        registeredCustomer.setNumber(customer.getNumber());
        registeredCustomer.setAddress(customer.getAddress());
        registeredCustomer.setCity(customer.getCity());
        registeredCustomer.setState(customer.getState());

    }

    @Override
    public Customer consult(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Customer> findsAll() {
        return this.map.values();
    }

}

