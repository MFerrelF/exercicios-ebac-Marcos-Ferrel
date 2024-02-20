package marcos.ferrel.jpa;

import marcos.ferrel.dao.CustomerJpaDAO;
import marcos.ferrel.dao.CustomerJpaDB2DAO;
import marcos.ferrel.dao.ICustomerJpaDAO;
import marcos.ferrel.domain.CustomerJpa;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * @author marcos.ferrel
 *
 */
public class CustomerJpaDao2Test {

    private ICustomerJpaDAO<CustomerJpa> customerJpaDAO;

    private ICustomerJpaDAO<CustomerJpa> customerJpaDAO2;

    private Random rd;

    public CustomerJpaDao2Test() {
        this.customerJpaDAO = new CustomerJpaDAO();
        this.customerJpaDAO2 = new CustomerJpaDB2DAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<CustomerJpa> list1 = customerJpaDAO.findAll();
        remove1(list1);

        Collection<CustomerJpa> list2 = customerJpaDAO2.findAll();
        remove2(list2);
    }

    private void remove1(Collection<CustomerJpa> list) {
        list.forEach(cust -> {
            try {
                customerJpaDAO.remove(cust);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    private void remove2(Collection<CustomerJpa> list) {
        list.forEach(cust -> {
            try {
                customerJpaDAO2.remove(cust);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void searchCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        CustomerJpa customerJpa = createCustomer();
        customerJpaDAO.register(customerJpa);

        CustomerJpa consultedCustomer = customerJpaDAO.consult(customerJpa.getId());
        Assert.assertNotNull(consultedCustomer);

        customerJpa.setId(null);
        customerJpaDAO2.register(customerJpa);

        CustomerJpa consultedCustomer1 = customerJpaDAO2.consult(customerJpa.getId());
        Assert.assertNotNull(consultedCustomer1);
    }

    @Test
    public void saveCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        CustomerJpa customerJpa = createCustomer();
        CustomerJpa ret = customerJpaDAO.register(customerJpa);
        Assert.assertNotNull(ret);

        CustomerJpa consultedCustomer = customerJpaDAO.consult(ret.getId());
        Assert.assertNotNull(consultedCustomer);

        customerJpaDAO.remove(customerJpa);

        CustomerJpa consultedCustomer1 = customerJpaDAO.consult(ret.getId());
        Assert.assertNull(consultedCustomer1);
    }

    @Test
    public void removeCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        CustomerJpa customerJpa = createCustomer();
        CustomerJpa ret = customerJpaDAO.register(customerJpa);
        Assert.assertNotNull(ret);

        CustomerJpa consultedCustomer = customerJpaDAO.consult(customerJpa.getId());
        Assert.assertNotNull(consultedCustomer);

        customerJpaDAO.remove(customerJpa);
        consultedCustomer = customerJpaDAO.consult(customerJpa.getId());
        Assert.assertNull(consultedCustomer);
    }

    @Test
    public void updateCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        CustomerJpa customerJpa = createCustomer();
        CustomerJpa ret = customerJpaDAO.register(customerJpa);
        Assert.assertNotNull(ret);

        CustomerJpa consultedCustomer = customerJpaDAO.consult(customerJpa.getId());
        Assert.assertNotNull(consultedCustomer);

        consultedCustomer.setName("Marcos Ferrel");
        customerJpaDAO.update(consultedCustomer);

        CustomerJpa updatedCustomer = customerJpaDAO.consult(consultedCustomer.getId());
        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals("Marcos Ferrel", updatedCustomer.getName());

        customerJpaDAO.remove(customerJpa);
        consultedCustomer = customerJpaDAO.consult(updatedCustomer.getId());
        Assert.assertNull(consultedCustomer);
    }

    @Test
    public void findAll() throws DAOException, KeyTypeNotFound {
        CustomerJpa customerJpa = createCustomer();
        CustomerJpa ret = customerJpaDAO.register(customerJpa);
        Assert.assertNotNull(ret);

        CustomerJpa customerJpa1 = createCustomer();
        CustomerJpa ret1 = customerJpaDAO.register(customerJpa1);
        Assert.assertNotNull(ret1);

        Collection<CustomerJpa> list = customerJpaDAO.findAll();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cust -> {
            try {
                customerJpaDAO.remove(cust);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<CustomerJpa> list1 = customerJpaDAO.findAll();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }


    private CustomerJpa createCustomer() {
        CustomerJpa customerJpa = new CustomerJpa();
        customerJpa.setCpf(rd.nextLong());
        customerJpa.setName("Marcos");
        customerJpa.setPhoneNumber(9999999999L);
        customerJpa.setAddress("Address");
        customerJpa.setNumber(10);
        customerJpa.setCity("Belo Horizonte");
        customerJpa.setState("MG");
        return customerJpa;
    }

}
