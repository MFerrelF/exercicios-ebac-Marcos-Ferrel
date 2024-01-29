package marcos.ferrel.DAO;

import marcos.ferrel.domain.Customer;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;

import java.util.Collection;

/**
 * @author marcos.ferrel
 */
public class CustomerDAOMock implements ICustomerDAO {
    @Override
    public Boolean register(Customer entity) throws KeyTypeNotFound, DAOException {
        return true;
    }

    @Override
    public void remove(Long value) throws DAOException {

    }

    @Override
    public void update(Customer entity) throws KeyTypeNotFound, DAOException {

    }

    @Override
    public Customer consult(Long value) throws ExtraRegisterException, TableException, DAOException {
        Customer customer = new Customer();
        customer.setCpf(value);
        return customer;
    }

    @Override
    public Collection<Customer> findAll() throws DAOException {
        return null;
    }
}
