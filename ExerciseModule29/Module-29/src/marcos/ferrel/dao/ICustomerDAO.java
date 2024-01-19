package marcos.ferrel.dao;

import marcos.ferrel.domain.Customer;

import java.util.List;

/**
 * @author marcos.ferrel
 */
public interface ICustomerDAO {

    public Integer register(Customer customer) throws Exception;

    public Integer update(Customer customer) throws Exception;

    public Customer search(String code) throws Exception;

    public List<Customer> searchAll() throws Exception;

    public Integer remove(Customer customer) throws Exception;

}
