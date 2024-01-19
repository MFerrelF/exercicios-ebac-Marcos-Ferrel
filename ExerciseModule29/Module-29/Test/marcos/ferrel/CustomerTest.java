package marcos.ferrel;

import marcos.ferrel.dao.CustomerDAO;
import marcos.ferrel.dao.ICustomerDAO;
import marcos.ferrel.domain.Customer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author marcos.ferrel
 */
public class CustomerTest {

    private ICustomerDAO customerDAO;

    @Test
    public void registerTest() throws Exception {
        customerDAO = new CustomerDAO();

        Customer customer = new Customer();
        customer.setCode("10");
        customer.setName("Marcos Ferrel");
        Integer countReg = customerDAO.register(customer);
        assertTrue(countReg == 1);

        Customer customerDB = customerDAO.search("10");
        assertNotNull(customerDB);
        assertEquals(customer.getCode(), customerDB.getCode());
        assertEquals(customer.getName(), customerDB.getName());

        Integer countDel = customerDAO.remove(customerDB);
        assertTrue(countDel == 1);
    }

    @Test
    public void searchAllTest() throws Exception {
        customerDAO = new CustomerDAO();

        Customer customer = new Customer();
        customer.setCode("10");
        customer.setName("Marcos Ferrel");
        Integer countReg = customerDAO.register(customer);
        assertTrue(countReg == 1);

        Customer customers = new Customer();
        customers.setCode("20");
        customers.setName("Test");
        Integer countReg1 = customerDAO.register(customers);
        assertTrue(countReg1 == 1);

        List<Customer> list = customerDAO.searchAll();
        assertNotNull(list);
        assertEquals(2, list.size());

        int countDel = 0;
        for (Customer cust : list) {
            customerDAO.remove(cust);
            countDel++;
        }
        assertEquals(list.size(), countDel);

        list = customerDAO.searchAll();
        assertEquals(list.size(), 0);
    }

    @Test
    public void updateTest() throws Exception {
        customerDAO = new CustomerDAO();

        Customer customer = new Customer();
        customer.setCode("10");
        customer.setName("Marcos Ferrel");
        Integer countReg = customerDAO.register(customer);
        assertTrue(countReg == 1);

        Customer customerDB = customerDAO.search("10");
        assertNotNull(customerDB);
        assertEquals(customer.getCode(), customerDB.getCode());
        assertEquals(customer.getName(), customerDB.getName());

        customerDB.setCode("20");
        customerDB.setName("Amanda Chaves");
        Integer countUP = customerDAO.update(customerDB);
        assertTrue(countUP == 1);

        Customer customerDB1 = customerDAO.search("10");
        assertNull(customerDB1);

        Customer customerDB2 = customerDAO.search("20");
        assertNotNull(customerDB2);
        assertEquals(customerDB.getId(), customerDB2.getId());
        assertEquals(customerDB.getCode(), customerDB2.getCode());
        assertEquals(customerDB.getName(), customerDB2.getName());

        List<Customer> list = customerDAO.searchAll();
        for (Customer cust : list) {
            customerDAO.remove(cust);
        }
    }
}
