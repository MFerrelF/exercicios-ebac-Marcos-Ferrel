package marcos.ferrel;

import marcos.ferrel.dao.CustomerDaoMock;
import marcos.ferrel.dao.ICustomerDAO;
import marcos.ferrel.dao.exception.KeyNotFoundException;
import marcos.ferrel.domain.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marcos.ferrel
 */
public class CustomerDAOTest {

    private ICustomerDAO customerDAO;

    private Customer customer;

    public CustomerDAOTest() {
        customerDAO = new CustomerDaoMock();
    }

    @Before
    public void init() throws KeyNotFoundException {
        customer = new Customer();
        customer.setCpf(12345678910L);
        customer.setName("Marcos");
        customer.setPhoneNumber(1199998888L);
        customer.setAddress("Rua 10");
        customer.setNumber(10);
        customer.setCity("Sao Paulo");
        customer.setState("SP");

        customerDAO.register(customer);
    }

    @Test
    public void searchCustomer() {
        Customer searchedCustomer = customerDAO.consult(customer.getCpf());

        Assert.assertNotNull(searchedCustomer);
    }

    @Test
    public void saveCustomer() throws KeyNotFoundException {
        Boolean ret = customerDAO.register(customer);

        Assert.assertTrue(ret);
    }

    @Test
    public void removeCustomer() {
        customerDAO.remove(customer.getCpf());
    }

    @Test
    public void updateCustomer() throws KeyNotFoundException {
        customer.setName("Marcos Ferrel");
        customerDAO.update(customer);

        Assert.assertEquals("Marcos Ferrel", customer.getName());
    }
}
