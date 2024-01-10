package marcos.ferrel.dao;

import marcos.ferrel.dao.exception.KeyNotFoundException;
import marcos.ferrel.domain.Customer;

import java.util.Collection;

public class CustomerDaoMock implements ICustomerDAO{

    @Override
    public Boolean register(Customer entity) throws KeyNotFoundException {
        return true;
    }

    @Override
    public void remove(Long value) {

    }

    @Override
    public void update(Customer entity) throws KeyNotFoundException {

    }

    @Override
    public Customer consult(Long value) {
        Customer customer = new Customer();
        customer.setCpf(value);
        return customer;
    }

    @Override
    public Collection<Customer> findsAll() {
        return null;
    }
}
