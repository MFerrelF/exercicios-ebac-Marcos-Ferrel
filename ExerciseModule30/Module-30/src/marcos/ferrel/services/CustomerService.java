package marcos.ferrel.services;

import marcos.ferrel.DAO.ICustomerDAO;
import marcos.ferrel.domain.Customer;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.TableException;
import marcos.ferrel.services.generics.GenericService;

/**
 * @author marcos.ferrel
 */
public class CustomerService extends GenericService<Customer, Long> implements ICustomerService {

    public CustomerService(ICustomerDAO customerDAO) {
        super(customerDAO);
    }


    @Override
    public Customer findByCpf(Long cpf) throws DAOException {
        try {
            return this.dao.consult(cpf);
        } catch (ExtraRegisterException | TableException e) {
            e.printStackTrace();
        }
        return null;
    }
}
