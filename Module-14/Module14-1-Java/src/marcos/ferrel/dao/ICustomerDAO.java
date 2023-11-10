package marcos.ferrel.dao;

import marcos.ferrel.domain.Customer;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author marcos.ferrel
 */
public interface ICustomerDAO {

    public Boolean register (Customer customer);

    public void exclude(Long cpf);

    public void alteration (Customer customer);

    public Customer consult (Long cpf);

    public Collection<Customer> findsAll();
}
