package marcos.ferrel;

import marcos.ferrel.DAO.*;
import marcos.ferrel.DAO.generics.ConnectionSingleton;
import marcos.ferrel.domain.Customer;
import marcos.ferrel.domain.Product;
import marcos.ferrel.domain.Sales;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author marcos.ferrel
 */
public class SalesDAOTest {

    private ISalesDAO salesDAO;

    private ICustomerDAO customerDAO;

    private IProductDAO productDAO;

    private Customer customer;

    private Product product;

    public SalesDAOTest() {
        salesDAO = new SalesDAO();
        customerDAO = new CustomerDAO();
        productDAO = new ProductDAO();
    }

    @Before
    public void init() throws DAOException, KeyTypeNotFound {
        this.customer = registerCustomer();
        this.product = registerProduct("A1", BigDecimal.TEN);
    }

    @After
    public void end() throws DAOException {
        removeSales();
        removeProducts();
        customerDAO.remove(this.customer.getCpf());
    }

    private void removeProducts() throws DAOException {
        Collection<Product> list = this.productDAO.findAll();
        for (Product prod : list) {
            this.productDAO.remove(prod.getCode());
        }
    }

    @Test
    public void search() throws DAOException, TableException, ExtraRegisterException, KeyTypeNotFound {
        Sales sales = createSales("A1");
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);
        Sales consultedSales = salesDAO.consult(sales.getCode());
        assertNotNull(consultedSales);
        assertEquals(sales.getCode(), consultedSales.getCode());
    }

    @Test
    public void save() throws DAOException, TableException, ExtraRegisterException, KeyTypeNotFound {
        Sales sales = createSales("A2");
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);

        assertTrue(sales.getTotalValue().equals(BigDecimal.valueOf(20)));
        assertTrue(sales.getStatus().equals(Sales.Status.STARTED));

        Sales consultedSales = salesDAO.consult(sales.getCode());
        assertTrue(consultedSales.getId() != null);
        assertEquals(sales.getCode(), consultedSales.getCode());
    }

    @Test
    public void cancelSales() throws DAOException, TableException, ExtraRegisterException, KeyTypeNotFound {
        String salesCode = "A3";
        Sales sales = createSales(salesCode);
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);
        assertNotNull(sales);
        assertEquals(salesCode, sales.getCode());

        salesDAO.cancelSales(sales);

        Sales consultedSales = salesDAO.consult(salesCode);
        assertEquals(salesCode, consultedSales.getCode());
        assertEquals(Sales.Status.CANCELED, consultedSales.getStatus());
    }

    @Test
    public void addingExtraProducts() throws DAOException, TableException, ExtraRegisterException, KeyTypeNotFound {
        String salesCode = "A5";
        Sales sales = createSales(salesCode);
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);
        assertNotNull(sales);
        assertEquals(salesCode, sales.getCode());

        Product prod = registerProduct(salesCode, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(salesCode, prod.getCode());

        Sales consultedSales = salesDAO.consult(salesCode);
        consultedSales.addProduct(prod, 1);

        assertTrue(consultedSales.getTotalProductsQuantity() == 3);
        BigDecimal totalValue = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));
        assertTrue(consultedSales.getStatus().equals(Sales.Status.STARTED));
    }

    @Test(expected = DAOException.class)
    public void saveSalesSameCode() throws DAOException, KeyTypeNotFound {
        Sales sales = createSales("A6");
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);

        Boolean ret1 = salesDAO.register(sales);
        assertFalse(ret1);
        assertTrue(sales.getStatus().equals(Sales.Status.STARTED));
    }

    @Test
    public void removeProduct() throws DAOException, TableException, ExtraRegisterException, KeyTypeNotFound {
        String salesCode = "A7";
        Sales sales = createSales(salesCode);
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);
        assertNotNull(sales);
        assertEquals(salesCode, sales.getCode());

        Product prod = registerProduct(salesCode, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(salesCode, prod.getCode());

        Sales consultedSales = salesDAO.consult(salesCode);
        consultedSales.addProduct(prod, 1);
        assertTrue(consultedSales.getTotalProductsQuantity() == 3);
        BigDecimal totalValue = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));

        consultedSales.removeProduct(prod, 1);
        assertTrue(consultedSales.getTotalProductsQuantity() == 2);
        totalValue = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));
        assertTrue(consultedSales.getStatus().equals(Sales.Status.STARTED));
    }

    @Test
    public void removeOneProduct() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        String salesCode = "A8";
        Sales sales = createSales(salesCode);
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);
        assertNotNull(sales);
        assertEquals(salesCode, sales.getCode());

        Product prod = registerProduct(salesCode, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(salesCode, prod.getCode());

        Sales consultedSales = salesDAO.consult(salesCode);
        consultedSales.addProduct(prod, 1);
        assertTrue(consultedSales.getTotalProductsQuantity() == 3);
        BigDecimal totalValue = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));

        consultedSales.removeProduct(prod, 1);
        assertTrue(consultedSales.getTotalProductsQuantity() == 2);
        totalValue = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));
        assertTrue(consultedSales.getStatus().equals(Sales.Status.STARTED));
    }

    @Test
    public void removeAllProducts() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        String salesCode = "A9";
        Sales sales = createSales(salesCode);
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);
        assertNotNull(sales);
        assertEquals(salesCode, sales.getCode());

        Product prod = registerProduct(salesCode, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(salesCode, prod.getCode());

        Sales consultedSales = salesDAO.consult(salesCode);
        consultedSales.addProduct(prod, 1);
        assertTrue(consultedSales.getTotalProductsQuantity() == 3);
        BigDecimal totalValue = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));

        consultedSales.removeAllProducts();
        assertTrue(consultedSales.getTotalProductsQuantity() == 0);
        assertTrue(consultedSales.getTotalValue().equals(BigDecimal.valueOf(0)));
        assertTrue(consultedSales.getStatus().equals(Sales.Status.STARTED));
    }

    @Test
    public void finishSales() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        String salesCode = "A10";
        Sales sales = createSales(salesCode);
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);
        assertNotNull(sales);
        assertEquals(salesCode, sales.getCode());

        salesDAO.finishSales(sales);

        Sales consultedSales = salesDAO.consult(salesCode);
        assertEquals(sales.getCode(), consultedSales.getCode());
        assertEquals(Sales.Status.CONCLUDED, consultedSales.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tryAddingProductsFinishedSales() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        String salesCode = "A11";
        Sales sales = createSales(salesCode);
        Boolean ret = salesDAO.register(sales);
        assertTrue(ret);
        assertNotNull(sales);
        assertEquals(salesCode, sales.getCode());

        salesDAO.finishSales(sales);
        Sales consultedSales = salesDAO.consult(salesCode);
        assertEquals(sales.getCode(), consultedSales.getCode());
        assertEquals(Sales.Status.CONCLUDED, consultedSales.getStatus());

        consultedSales.addProduct(this.product, 1);
    }

    private Customer registerCustomer() throws DAOException, KeyTypeNotFound {
        Customer customer = new Customer();
        customer.setCpf(12312312312L);
        customer.setName("Marcos");
        customer.setPhoneNumber(998989898L);
        customer.setAddress("Address");
        customer.setNumber(10);
        customer.setCity("Belo Horizonte");
        customer.setState("MG");
        customer.setCompanyEmployee("Y");
        customerDAO.register(customer);
        return customer;
    }

    private Product registerProduct(String code, BigDecimal value) throws DAOException, KeyTypeNotFound {
        Product product = new Product();
        product.setCode(code);
        product.setName("Product 1");
        product.setDescription("Product 1");
        product.setValue(value);
        productDAO.register(product);
        return product;
    }

    private Sales createSales(String code) {
        Sales sales = new Sales();
        sales.setCode(code);
        sales.setSalesDate(Instant.now());
        sales.setCustomer(this.customer);
        sales.setStatus(Sales.Status.STARTED);
        sales.addProduct(this.product, 2);
        return sales;
    }

    private void removeSales() throws DAOException {
        String sqlProd = "DELETE FROM TB_QUANTITY_PRODUCT";
        executeDelete(sqlProd);

        String sqlSales = "DELETE FROM TB_SALES";
        executeDelete(sqlSales);
    }

    private void executeDelete(String sql) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error removing object ", e);
        } finally {
            closeConnection(connection, statement, rs);
        }
    }

    protected Connection getConnection() throws DAOException {
        try {
            return ConnectionSingleton.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Error opening connection with the data base ", e);
        }
    }

    protected void closeConnection(Connection connection, PreparedStatement statement, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !statement.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
