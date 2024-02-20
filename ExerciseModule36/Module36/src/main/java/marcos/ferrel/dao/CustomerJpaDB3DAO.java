package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDB3DAO;
import marcos.ferrel.domain.CustomerJpa;
import marcos.ferrel.domain.CustomerJpa2;

/**
 * @author marcos.ferrel
 *
 */
public class CustomerJpaDB3DAO extends GenericJpaDB3DAO<CustomerJpa2, Long> implements ICustomerJpaDAO<CustomerJpa2> {

    public CustomerJpaDB3DAO() {
        super(CustomerJpa2.class);
    }

}
