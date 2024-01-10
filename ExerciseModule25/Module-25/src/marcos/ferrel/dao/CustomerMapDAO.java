package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericDAO;
import marcos.ferrel.domain.Customer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author marcos.ferrel
 */
public class CustomerMapDAO extends GenericDAO<Customer> implements ICustomerDAO {

    public CustomerMapDAO() {
        super();
        Map<Long, Customer> inMap = (Map<Long, Customer>) SingletonMap.getInstance().getMap().get(getClassType());
        if (inMap == null) {
            inMap = new HashMap<>();
            SingletonMap.getInstance().getMap().put(getClassType(), inMap);
        }
    }

    @Override
    public Class<Customer> getClassType() {
        return Customer.class;
    }

    @Override
    public void updateData(Customer customer, Customer registeredEntity) {
        registeredEntity.setName(customer.getName());
        registeredEntity.setCpf(customer.getCpf());
        registeredEntity.setAddress(customer.getAddress());
        registeredEntity.setNumber(customer.getNumber());
        registeredEntity.setPhoneNumber(customer.getPhoneNumber());
        registeredEntity.setCity(customer.getCity());
        registeredEntity.setState(customer.getState());
    }


}
