package marcos.ferrel.services;

import marcos.ferrel.dao.exception.KeyNotFoundException;
import marcos.ferrel.domain.Customer;

/**
 * @author marcos.ferrel
 */
public interface ICustomerService {
    Boolean save(Customer customer) throws KeyNotFoundException;

    Customer findByCPF(Long cpf);

    void remove(Long cpf);

    void update(Customer customer) throws KeyNotFoundException;
}

