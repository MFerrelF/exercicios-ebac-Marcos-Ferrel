package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDAO;
import marcos.ferrel.domain.CustomerJPA;

public class CustomerJpaDAO extends GenericJpaDAO<CustomerJPA, Long> implements ICustomerJpaDAO {

    public CustomerJpaDAO() {
        super(CustomerJPA.class);
    }

}
