package marcos.ferrel;

import marcos.ferrel.dao.CustomerJpaDAO;
import marcos.ferrel.dao.ICustomerJpaDAO;
import marcos.ferrel.domain.CustomerJPA;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.ExtraRegisterException;
import marcos.ferrel.domain.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.domain.exceptions.TableException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * @author marcos.ferrel
 */
public class CustomerJpaDAOTest {

    private ICustomerJpaDAO customerJpaDAO;

    private Random rd;

    public CustomerJpaDAOTest() {
        this.customerJpaDAO = new CustomerJpaDAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<CustomerJPA> list = customerJpaDAO.findAll();
        list.forEach(cust -> {
            try {
                customerJpaDAO.remove(cust);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void search() throws DAOException, KeyTypeNotFoundException, TableException, ExtraRegisterException {
        CustomerJPA customerJPA = createCustomer();
        customerJpaDAO.register(customerJPA);

        CustomerJPA consultedCustomer = customerJpaDAO.consult(customerJPA.getId());
        Assert.assertNotNull(consultedCustomer);
    }

    @Test
    public void save() throws DAOException, KeyTypeNotFoundException, TableException, ExtraRegisterException {
        CustomerJPA customerJPA = createCustomer();
        CustomerJPA ret = customerJpaDAO.register(customerJPA);
        Assert.assertNotNull(ret);

        CustomerJPA consultedCustomer = customerJpaDAO.consult(ret.getId());
        Assert.assertNotNull(consultedCustomer);

        customerJpaDAO.remove(customerJPA);

        CustomerJPA consultedCustomer1 = customerJpaDAO.consult(ret.getId());
        Assert.assertNull(consultedCustomer1);
    }

    @Test
    public void remove() throws DAOException, KeyTypeNotFoundException, TableException, ExtraRegisterException {
        CustomerJPA customerJPA = createCustomer();
        CustomerJPA ret = customerJpaDAO.register(customerJPA);
        Assert.assertNotNull(ret);

        CustomerJPA consultedCustomer = customerJpaDAO.consult(ret.getId());
        Assert.assertNotNull(consultedCustomer);

        customerJpaDAO.remove(customerJPA);
        consultedCustomer = customerJpaDAO.consult(customerJPA.getId());
        Assert.assertNull(consultedCustomer);
    }

    @Test
    public void update() throws DAOException, TableException, ExtraRegisterException, KeyTypeNotFoundException {
        CustomerJPA customerJPA = createCustomer();
        CustomerJPA ret = customerJpaDAO.register(customerJPA);
        Assert.assertNotNull(ret);

        CustomerJPA consultedCustomer = customerJpaDAO.consult(ret.getId());
        Assert.assertNotNull(consultedCustomer);

        consultedCustomer.setName("Marcos Ferrel");
        customerJpaDAO.update(consultedCustomer);

        CustomerJPA updatedCustomer = customerJpaDAO.consult(consultedCustomer.getId());
        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals("Marcos Ferrel", updatedCustomer.getName());

        customerJpaDAO.remove(customerJPA);
        consultedCustomer = customerJpaDAO.consult(updatedCustomer.getId());
        Assert.assertNull(consultedCustomer);
    }

    @Test
    public void searchAll() throws DAOException, KeyTypeNotFoundException {
        CustomerJPA customerJPA = createCustomer();
        CustomerJPA ret = customerJpaDAO.register(customerJPA);
        Assert.assertNotNull(ret);

        CustomerJPA customerJPA1 = createCustomer();
        CustomerJPA ret1 = customerJpaDAO.register(customerJPA1);
        Assert.assertNotNull(ret1);

        Collection<CustomerJPA> list = customerJpaDAO.findAll();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cust -> {
            try {
                customerJpaDAO.remove(cust);
            } catch (DAOException e) {
                e.printStackTrace();;
            }
        });

        Collection<CustomerJPA> list1 = customerJpaDAO.findAll();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }

    private CustomerJPA createCustomer() {
        CustomerJPA customerJPA = new CustomerJPA();
        customerJPA.setCpf(rd.nextLong());
        customerJPA.setName("Marcos");
        customerJPA.setPhoneNumber(999881630L);
        customerJPA.setAddress("Address");
        customerJPA.setNumber(10);
        customerJPA.setCity("Belo Horizonte");
        customerJPA.setState("Minas Gerais");
        return customerJPA;
    }

}
