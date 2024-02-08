package marcos.ferrel;

import marcos.ferrel.dao.*;
import marcos.ferrel.domain.CustomerJPA;
import marcos.ferrel.domain.ProductJPA;
import marcos.ferrel.domain.SalesJPA;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.ExtraRegisterException;
import marcos.ferrel.domain.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.domain.exceptions.TableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author marcos.ferrel
 */
public class SalesJpaDAOTest {

    private ISalesJpaDAO salesJpaDAO;

    private ISalesJpaDAO removalSalesDAO;

    private ICustomerJpaDAO customerJpaDAO;

    private IProductJpaDAO productJpaDAO;

    private Random rd;

    private CustomerJPA customerJPA;

    private ProductJPA productJPA;

    public SalesJpaDAOTest() {
        this.salesJpaDAO = new SalesJpaDAO();
        removalSalesDAO = new RemovalSalesDAOTest();
        this.customerJpaDAO = new CustomerJpaDAO();
        this.customerJPA = new CustomerJPA();
        this.productJPA = new ProductJPA();
        rd = new Random();
    }

    @Before
    public void init() throws DAOException, KeyTypeNotFoundException {
        this.customerJPA = registerCustomer();
        this.productJPA = registerProduct("A1", BigDecimal.TEN);
    }

    @After
    public void end() throws DAOException {
        removeSales();
        removeProducts();
        customerJpaDAO.remove(this.customerJPA);
    }

    @Test
    public void search() throws DAOException, KeyTypeNotFoundException, TableException, ExtraRegisterException {
        SalesJPA salesJPA = createSales("A1");
        SalesJPA ret = salesJpaDAO.register(salesJPA);
        assertNotNull(ret);
        SalesJPA consultedSales = salesJpaDAO.consult(salesJPA.getId());
        assertNotNull(consultedSales);
        assertEquals(salesJPA.getCode(), consultedSales.getCode());
    }

    @Test
    public void save() throws DAOException, TableException, ExtraRegisterException, KeyTypeNotFoundException {
        SalesJPA salesJPA = createSales("A2");
        SalesJPA ret = salesJpaDAO.register(salesJPA);
        assertNotNull(ret);

        assertTrue(salesJPA.getTotalValue().equals(BigDecimal.valueOf(20)));
        assertTrue(salesJPA.getStatus().equals(SalesJPA.Status.STARTED));

        SalesJPA consultedSales = salesJpaDAO.consult(salesJPA.getId());
        assertTrue(consultedSales.getId() != null);
        assertEquals(salesJPA.getCode(), consultedSales.getCode());
    }

    @Test
    public void cancelSales() throws DAOException, KeyTypeNotFoundException, TableException, ExtraRegisterException {
        String salesCode = "A3";
        SalesJPA salesJPA = createSales(salesCode);
        SalesJPA ret = salesJpaDAO.register(salesJPA);
        assertNotNull(ret);
        assertNotNull(salesJPA);
        assertEquals(salesCode, salesJPA.getCode());

        ret.setStatus(SalesJPA.Status.CANCELED );
        salesJpaDAO.cancelSales(salesJPA);

        SalesJPA consultedSales = salesJpaDAO.consult(salesJPA.getId());
        assertEquals(salesCode, consultedSales.getCode());
        assertEquals(SalesJPA.Status.CANCELED, consultedSales.getStatus());
    }

    @Test
    public void addingSameProducts() throws DAOException, KeyTypeNotFoundException {
        String salesCode = "A4";
        SalesJPA salesJPA = createSales(salesCode);
        SalesJPA ret = salesJpaDAO.register(salesJPA);
        assertNotNull(ret);
        assertNotNull(salesJPA);
        assertEquals(salesCode, salesJPA.getCode());

        SalesJPA consultedSales = salesJpaDAO.consultWithCollection(salesJPA.getId());
        consultedSales.addProduct(productJPA, 1);

        assertTrue(consultedSales.getProductQuantityTotal() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(valorTotal));
        assertTrue(consultedSales.getStatus().equals(SalesJPA.Status.STARTED));
    }

    @Test
    public void addingDifferentProducts() throws DAOException, KeyTypeNotFoundException {
        String salesCode = "A5";
        SalesJPA salesJPA = createSales(salesCode);
        SalesJPA ret = salesJpaDAO.register(salesJPA);
        assertNotNull(ret);
        assertNotNull(salesJPA);
        assertEquals(salesCode, salesJPA.getCode());

        ProductJPA product = registerProduct(salesCode, BigDecimal.valueOf(50));
        assertNotNull(product);
        assertEquals(salesCode, product.getCode());

        SalesJPA consultedSales = salesJpaDAO.consultWithCollection(salesJPA.getId());
        consultedSales.addProduct(product, 1);

        assertTrue(consultedSales.getProductQuantityTotal() == 3);
        BigDecimal totalValue = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));
        assertTrue(consultedSales.getStatus().equals(SalesJPA.Status.STARTED));
    }

    @Test(expected = DAOException.class)
    public void saveSalesWithSameCode() throws DAOException, KeyTypeNotFoundException {
        SalesJPA salesJPA = createSales("A6");
        SalesJPA ret = salesJpaDAO.register(salesJPA);
        assertNotNull(ret);

        SalesJPA salesJPA1 = createSales("A6");
        SalesJPA ret1 = salesJpaDAO.register(salesJPA1);
        assertNull(ret1);
        assertTrue(salesJPA.getStatus().equals(SalesJPA.Status.STARTED));
    }

    @Test
    public void removeProduct() throws DAOException, KeyTypeNotFoundException {
        String salesCode = "A7";
        SalesJPA salesJPA = createSales(salesCode);
        SalesJPA ret = salesJpaDAO.register(salesJPA);
        assertNotNull(ret);
        assertNotNull(salesJPA);
        assertEquals(salesCode, salesJPA.getCode());

        ProductJPA product = registerProduct(salesCode, BigDecimal.valueOf(50));
        assertNotNull(product);
        assertEquals(salesCode, product.getCode());

        SalesJPA consultedSales = salesJpaDAO.consultWithCollection(salesJPA.getId());
        consultedSales.addProduct(product, 1);
        assertTrue(consultedSales.getProductQuantityTotal() == 3);
        BigDecimal totalValue = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));


        consultedSales.removeProduct(product, 1);
        assertTrue(consultedSales.getProductQuantityTotal() == 2);
        totalValue = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));
        assertTrue(consultedSales.getStatus().equals(SalesJPA.Status.STARTED));
    }

    @Test
    public void removeAllProducts() throws DAOException, KeyTypeNotFoundException {
        String salesCode = "A8";
        SalesJPA salesJPA = createSales(salesCode);
        SalesJPA ret = salesJpaDAO.register(salesJPA);
        assertNotNull(ret);
        assertNotNull(salesJPA);
        assertEquals(salesCode, salesJPA.getCode());

        ProductJPA product = registerProduct(salesCode, BigDecimal.valueOf(50));
        assertNotNull(product);
        assertEquals(salesCode, product.getCode());

        SalesJPA consultedSales = salesJpaDAO.consultWithCollection(salesJPA.getId());
        consultedSales.addProduct(product, 1);
        assertTrue(consultedSales.getProductQuantityTotal() == 3);
        BigDecimal totalValue = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(consultedSales.getTotalValue().equals(totalValue));


        consultedSales.removeAllProducts();
        assertTrue(consultedSales.getProductQuantityTotal() == 0);
        assertTrue(consultedSales.getTotalValue().equals(BigDecimal.valueOf(0)));
        assertTrue(consultedSales.getStatus().equals(SalesJPA.Status.STARTED));
    }

    @Test
    public void finishSales() throws DAOException, KeyTypeNotFoundException {
        String salesCode = "A9";
        SalesJPA salesJPA1 = createSales(salesCode);
        SalesJPA ret = salesJpaDAO.register(salesJPA1);
        assertNotNull(ret);
        assertNotNull(salesJPA1);
        assertEquals(salesCode, salesJPA1.getCode());

        salesJPA1.setStatus(SalesJPA.Status.CONCLUDED);
        salesJpaDAO.completeSales(salesJPA1);

        SalesJPA consultedSales = salesJpaDAO.consultWithCollection(salesJPA1.getId());
        assertEquals(salesJPA1.getCode(), consultedSales.getCode());
        assertEquals(SalesJPA.Status.CONCLUDED, consultedSales.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addProductAfterSales() throws DAOException, KeyTypeNotFoundException {
        String salesCode = "A10";
        SalesJPA salesJPA1 = createSales(salesCode);
        SalesJPA ret = salesJpaDAO.register(salesJPA1);
        assertNotNull(ret);
        assertNotNull(salesJPA1);
        assertEquals(salesCode, salesJPA1.getCode());

        salesJPA1.setStatus(SalesJPA.Status.CONCLUDED);
        salesJpaDAO.completeSales(salesJPA1);

        SalesJPA consultedSales = salesJpaDAO.consultWithCollection(salesJPA1.getId());
        assertEquals(salesJPA1.getCode(), consultedSales.getCode());
        assertEquals(SalesJPA.Status.CONCLUDED, consultedSales.getStatus());

        consultedSales.addProduct(this.productJPA, 1);
    }

    private CustomerJPA registerCustomer() throws DAOException, KeyTypeNotFoundException {
        CustomerJPA customerJPA1 = new CustomerJPA();
        customerJPA1.setCpf(rd.nextLong());
        customerJPA1.setName("Marcos");
        customerJPA1.setPhoneNumber(999881030L);
        customerJPA1.setAddress("Address");
        customerJPA1.setNumber(10);
        customerJPA1.setCity("Belo Horizonte");
        customerJPA1.setState("Minas Gerais");
        customerJpaDAO.register(customerJPA1);
        return customerJPA1;
    }

    private ProductJPA registerProduct(String code, BigDecimal value) throws DAOException, KeyTypeNotFoundException {
        ProductJPA productJPA1 = new ProductJPA();
        productJPA1.setCode(code);
        productJPA1.setName("Product 1");
        productJPA1.setDescription("Product 1");
        productJPA1.setPrice(value);
        productJpaDAO.register(productJPA1);
        return productJPA1;
    }

    private void removeSales() throws DAOException {
        Collection<SalesJPA> list = this.removalSalesDAO.findAll();
        list.forEach(product -> {
            try {
                this.removalSalesDAO.remove(product);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    private void removeProducts() throws DAOException {
        Collection<ProductJPA> list = this.productJpaDAO.findAll();
        list.forEach(product -> {
            try {
                this.productJpaDAO.remove(product);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    private SalesJPA createSales(String code) {
        SalesJPA salesJPA = new SalesJPA();
        salesJPA.setCode(code);
        salesJPA.setSalesDate(Instant.now());
        salesJPA.setCustomerJPA(this.customerJPA);
        salesJPA.setStatus(SalesJPA.Status.STARTED);
        salesJPA.addProduct(this.productJPA, 2);
        return salesJPA;
    }

}
