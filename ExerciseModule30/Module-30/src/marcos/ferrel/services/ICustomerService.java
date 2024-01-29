package marcos.ferrel.services;

import marcos.ferrel.domain.Customer;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.services.generics.IGenericService;

/**
 * @author marcos.ferrel
 */
public interface ICustomerService extends IGenericService<Customer, Long> {

    Customer findByCpf(Long cpf) throws DAOException;
}
