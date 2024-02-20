package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDB1DAO;
import marcos.ferrel.domain.CustomerJpa;

/**
 * @author marcos.ferrel
 *
 */
public class CustomerJpaDAO extends GenericJpaDB1DAO<CustomerJpa, Long> implements ICustomerJpaDAO<CustomerJpa> {

    public CustomerJpaDAO() {
        super(CustomerJpa.class);
    }
}
