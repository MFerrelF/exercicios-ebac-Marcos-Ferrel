package marcos.ferrel.services;

import marcos.ferrel.dao.CustomerDAO;
import marcos.ferrel.dao.ICustomerDAO;
import marcos.ferrel.dao.exception.KeyNotFoundException;
import marcos.ferrel.domain.Customer;

/**
 * @author marcos.ferrel
 */
public class CustomerService implements ICustomerService{

    private ICustomerDAO customerDAO;

    public CustomerService(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Boolean save(Customer customer) throws KeyNotFoundException {
        return customerDAO.register(customer);
    }

    @Override
    public Customer findByCPF(Long cpf) {
        return customerDAO.consult(cpf);
    }

    @Override
    public void remove(Long cpf) {
        customerDAO.remove(cpf);
    }

    @Override
    public void update(Customer customer) throws KeyNotFoundException {
        customerDAO.update(customer);
    }
}
