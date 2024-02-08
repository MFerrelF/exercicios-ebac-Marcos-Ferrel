package marcos.ferrel.domain.services;

import marcos.ferrel.domain.CustomerJPA;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.services.generic.IGenericJpaService;

/**
 * @author marcos.ferrel
 */
public interface ICustomerService extends IGenericJpaService<CustomerJPA, Long> {

        CustomerJPA findByCpf(Long cpf) throws DAOException;

}
