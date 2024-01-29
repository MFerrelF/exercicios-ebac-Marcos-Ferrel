package marcos.ferrel;

import marcos.ferrel.DAO.CustomerDAO;
import marcos.ferrel.DAO.ICustomerDAO;
import marcos.ferrel.domain.Customer;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class CustomerDAOTest {

    private ICustomerDAO customerDAO;

    public CustomerDAOTest() {
        customerDAO = new CustomerDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<Customer> list = customerDAO.findAll();
        list.forEach(cust -> {
            try {
                customerDAO.remove(cust.getCpf());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void searchCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        Customer customer = new Customer();
        customer.setCpf(12312312310L);
        customer.setName("Marcos");
        customer.setPhoneNumber(998989898L);
        customer.setAddress("Address");
        customer.setNumber(10);
        customer.setCity("Belo Horizonte");
        customer.setState("MG");
        customer.setCompanyEmployee("Y");
        customerDAO.register(customer);

        Customer consultedCustomer = customerDAO.consult(customer.getCpf());
        Assert.assertNotNull(consultedCustomer);

        customerDAO.remove(customer.getCpf());
    }

    @Test
    public void saveCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        Customer customer = new Customer();
        customer.setCpf(45645645610L);
        customer.setName("Marcos");
        customer.setPhoneNumber(987878787L);
        customer.setAddress("Address");
        customer.setNumber(20);
        customer.setCity("Belo Horizonte");
        customer.setState("MG");
        customer.setCompanyEmployee("Y");
        Boolean ret = customerDAO.register(customer);
        assertTrue(ret);

        Customer consultedCustomer = customerDAO.consult(customer.getCpf());
        Assert.assertNotNull(consultedCustomer);

        customerDAO.remove(customer.getCpf());
    }

    @Test
    public void removeCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        Customer customer = new Customer();
        customer.setCpf(78978978910L);
        customer.setName("Marcos");
        customer.setPhoneNumber(985858585L);
        customer.setAddress("Address");
        customer.setNumber(30);
        customer.setCity("Belo Horizonte");
        customer.setState("MG");
        customer.setCompanyEmployee("Y");
        Boolean ret = customerDAO.register(customer);
        assertTrue(ret);

        Customer consultedCustomer = customerDAO.consult(customer.getCpf());
        Assert.assertNotNull(consultedCustomer);

        customerDAO.remove(customer.getCpf());
        consultedCustomer = customerDAO.consult(customer.getCpf());
        Assert.assertNull(consultedCustomer);
    }

    @Test
    public void updateCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        Customer customer = new Customer();
        customer.setCpf(32132132110L);
        customer.setName("Marcos");
        customer.setPhoneNumber(986868686L);
        customer.setAddress("Address");
        customer.setNumber(40);
        customer.setCity("Belo Horizonte");
        customer.setState("MG");
        customer.setCompanyEmployee("Y");
        Boolean ret = customerDAO.register(customer);
        assertTrue(ret);

        Customer consultedCustomer = customerDAO.consult(customer.getCpf());
        Assert.assertNotNull(consultedCustomer);

        consultedCustomer.setName("Marcos Ferrel");
        customerDAO.update(consultedCustomer);

        Customer updatedCustomer = customerDAO.consult(consultedCustomer.getCpf());
        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals("Marcos Ferrel", updatedCustomer.getName());

        customerDAO.remove(customer.getCpf());
        consultedCustomer = customerDAO.consult(customer.getCpf());
        Assert.assertNull(consultedCustomer);
    }

    @Test
    public void findAll() throws DAOException, KeyTypeNotFound {
        Customer customer = new Customer();
        customer.setCpf(65465465410L);
        customer.setName("Marcos");
        customer.setPhoneNumber(985858585L);
        customer.setAddress("Address");
        customer.setNumber(50);
        customer.setCity("Belo Horizonte");
        customer.setState("MG");
        customer.setCompanyEmployee("Y");
        Boolean ret = customerDAO.register(customer);
        assertTrue(ret);

        Customer customer1 = new Customer();
        customer1.setCpf(32132132110L);
        customer1.setName("Marcos");
        customer1.setPhoneNumber(986868686L);
        customer1.setAddress("Address");
        customer1.setNumber(50);
        customer1.setCity("Belo Horizonte");
        customer1.setState("MG");
        customer.setCompanyEmployee("Y");
        Boolean ret1 = customerDAO.register(customer);
        assertTrue(ret1);

        Collection<Customer> list = customerDAO.findAll();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cust -> {
            try {
                customerDAO.remove(cust.getCpf());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });

        Collection<Customer> list1 = customerDAO.findAll();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }

}
