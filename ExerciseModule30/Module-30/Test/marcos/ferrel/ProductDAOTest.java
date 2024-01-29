package marcos.ferrel;

import marcos.ferrel.DAO.IProductDAO;
import marcos.ferrel.DAO.ProductDAO;
import marcos.ferrel.domain.Product;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author marcos.ferrel
 */
public class ProductDAOTest {

    private IProductDAO productDAO;

    public ProductDAOTest() {
        productDAO = new ProductDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<Product> list = productDAO.findAll();
        list.forEach(prod -> {
            try {
                productDAO.remove(prod.getCode());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    private Product createProduct(String code) throws DAOException, KeyTypeNotFound {
        Product product = new Product();
        product.setCode(code);
        product.setName("Product 1");
        product.setDescription("Product 1");
        product.setValue(BigDecimal.TEN);
        productDAO.register(product);
        return product;
    }

    private void remove(String value) throws DAOException {
        this.productDAO.remove(value);
    }

    @Test
    public void search() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        Product product = createProduct("A1");
        assertNotNull(product);
        Product productDB = this.productDAO.consult(product.getCode());
        assertNotNull(productDB);
        remove(productDB.getCode());
    }

    @Test
    public void save() throws DAOException, KeyTypeNotFound {
        Product product = createProduct("A2");
        assertNotNull(product);
        remove(product.getCode());
    }

    @Test
    public void remove() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        Product product = createProduct("A3");
        assertNotNull(product);
        remove(product.getCode());
        Product productDB = this.productDAO.consult(product.getCode());
        assertNull(productDB);
    }

    @Test
    public void updateCustomer() throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        Product product = createProduct("A4");
        product.setName("Marcos Ferrel");
        productDAO.update(product);
        Product productDB = this.productDAO.consult(product.getCode());
        assertNotNull(productDB);
        Assert.assertEquals("Marcos Ferrel", productDB.getName());

        remove(product.getCode());
        Product productDB1 = this.productDAO.consult(product.getCode());
        assertNull(productDB1);
    }

    @Test
    public void findAll() throws DAOException, KeyTypeNotFound {
        createProduct("A5");
        createProduct(("A6"));
        Collection<Product> list = productDAO.findAll();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        for (Product prod : list) {
            remove(prod.getCode());
        }

        list = productDAO.findAll();
        assertTrue(list != null);
        assertTrue(list.size() == 0);
    }

}
