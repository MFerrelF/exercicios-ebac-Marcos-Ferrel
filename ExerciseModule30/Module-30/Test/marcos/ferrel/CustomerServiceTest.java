package marcos.ferrel;

import marcos.ferrel.DAO.CustomerDAOMock;
import marcos.ferrel.DAO.ICustomerDAO;
import marcos.ferrel.domain.Customer;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.services.CustomerService;
import marcos.ferrel.services.ICustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marcos.ferrel
 */
public class CustomerServiceTest {

    private ICustomerService customerService;

    private Customer customer;

    public CustomerServiceTest() {
        ICustomerDAO dao = new CustomerDAOMock();
        customerService = new CustomerService(dao);
    }

    @Before
    public void init() {
        customer = new Customer();
        customer.setCpf(65465465410L);
        customer.setName("Marcos");
        customer.setPhoneNumber(985858585L);
        customer.setAddress("Address");
        customer.setNumber(50);
        customer.setCity("Belo Horizonte");
        customer.setState("MG");
        customer.setCompanyEmployee("Y");
    }

    @Test
    public void searchCustomer() throws DAOException {
        Customer consultedCustomer = customerService.findByCpf(customer.getCpf());
        Assert.assertNotNull(consultedCustomer);
    }

    @Test
    public void saveCustomer() throws DAOException, KeyTypeNotFound {
        Boolean ret = customerService.register((customer));
        Assert.assertTrue(ret);
    }

    @Test
    public void removeCustomer() throws DAOException {
        customerService.remove(customer.getCpf());
    }

    @Test
    public void updateCustomer() throws DAOException, KeyTypeNotFound {
        customer.setName("Marcos Ferrel");
        customerService.update(customer);
        Assert.assertEquals("Marcos Ferrel", customer.getName());
    }

}
