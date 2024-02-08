package marcos.ferrel.domain.services;

import marcos.ferrel.dao.ICustomerJpaDAO;
import marcos.ferrel.domain.CustomerJPA;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.ExtraRegisterException;
import marcos.ferrel.domain.exceptions.TableException;
import marcos.ferrel.domain.services.generic.GenericJPAService;

/**
 * @author marcos.ferrel
 */
public class CustomerService extends GenericJPAService<CustomerJPA, Long> implements ICustomerService {

    public CustomerService(ICustomerJpaDAO customerJpaDAO) {
        super(customerJpaDAO);
    }

    @Override
    public CustomerJPA findByCpf(Long cpf) throws DAOException {
        try {
            return this.dao.consult(cpf);
        } catch (ExtraRegisterException | TableException e) {
            e.printStackTrace();
        }
        return null;
    }
}
