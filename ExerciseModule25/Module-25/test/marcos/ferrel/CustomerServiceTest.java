package marcos.ferrel;

import marcos.ferrel.dao.CustomerDaoMock;
import marcos.ferrel.dao.ICustomerDAO;
import marcos.ferrel.dao.exception.KeyNotFoundException;
import marcos.ferrel.domain.Customer;
import marcos.ferrel.services.CustomerService;
import marcos.ferrel.services.ICustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * author marcos.ferrel
 */
public class CustomerServiceTest {

    private ICustomerService customerService;

    private Customer customer;

    public CustomerServiceTest() {
        ICustomerDAO dao = new CustomerDaoMock();
        customerService = new CustomerService(dao);
    }

    @Before
    public void init() {
        customer = new Customer();
        customer.setCpf(12345678910L);
        customer.setName("Marcos");
        customer.setPhoneNumber(1199998888L);
        customer.setAddress("Rua 10");
        customer.setNumber(10);
        customer.setCity("Sao Paulo");
        customer.setState("SP");
    }

    @Test
    public void searchCustomer() {
        Customer searchedCustomer = customerService.findByCPF(customer.getCpf());

        Assert.assertNotNull(searchedCustomer);
    }

    @Test
    public void saveCustomer() throws KeyNotFoundException {
        Boolean ret = customerService.save(customer);

        Assert.assertTrue(ret);
    }

    @Test
    public void removeCustomer() {
        customerService.remove(customer.getCpf());
    }

    @Test
    public void updateCustomer() throws KeyNotFoundException {
        customer.setName("Marcos Ferrel");
        customerService.update(customer);

        Assert.assertEquals("Marcos Ferrel", customer.getName());
    }
}
