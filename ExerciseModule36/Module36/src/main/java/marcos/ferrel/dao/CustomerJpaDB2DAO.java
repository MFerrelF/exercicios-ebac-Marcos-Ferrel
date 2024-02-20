package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDB2DAO;
import marcos.ferrel.domain.CustomerJpa;

/**
 * @author marcos.ferrel
 *
 */
public class CustomerJpaDB2DAO extends GenericJpaDB2DAO<CustomerJpa, Long> implements ICustomerJpaDAO<CustomerJpa> {

    public CustomerJpaDB2DAO() {
        super(CustomerJpa.class);
    }

}
