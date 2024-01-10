package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericDAO;
import marcos.ferrel.domain.Customer;

public class CustomerDAO extends GenericDAO<Customer> implements ICustomerDAO{

    public CustomerDAO() {
        super();
    }

    @Override
    public Class<Customer> getClassType() {
        return null;
    }

    @Override
    public void updateData(Customer entity, Customer registeredEntity) {

    }
}
