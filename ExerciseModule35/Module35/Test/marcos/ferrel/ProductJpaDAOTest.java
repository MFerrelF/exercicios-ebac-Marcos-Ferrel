package marcos.ferrel;

import marcos.ferrel.dao.IProductJpaDAO;
import marcos.ferrel.dao.ProductJpaDAO;
import marcos.ferrel.domain.ProductJPA;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.ExtraRegisterException;
import marcos.ferrel.domain.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.domain.exceptions.TableException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author marcos.ferrel
 */
public class ProductJpaDAOTest {

    private IProductJpaDAO productJpaDAO;

    public ProductJpaDAOTest() {
        this.productJpaDAO = new ProductJpaDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<ProductJPA> list = productJpaDAO.findAll();
        list.forEach(prod -> {
            try {
                productJpaDAO.remove(prod);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void search() throws DAOException, TableException, ExtraRegisterException, KeyTypeNotFoundException {
        ProductJPA productJPA = createProduct("A1");
        assertNotNull(productJPA);
        ProductJPA productDB = this.productJpaDAO.consult(productJPA.getId());
        assertNotNull(productDB);
    }

    @Test
    public void save() throws DAOException, KeyTypeNotFoundException {
        ProductJPA productJPA = createProduct("A2");
        assertNotNull(productJPA);
    }

    @Test
    public void remove() throws DAOException, KeyTypeNotFoundException, TableException, ExtraRegisterException {
        ProductJPA productJPA = createProduct("A3");
        assertNotNull(productJPA);
        this.productJpaDAO.remove(productJPA);
        ProductJPA productDB = this.productJpaDAO.consult(productJPA.getId());
        assertNull(productDB);
    }

    @Test
    public void updateProduct() throws DAOException, KeyTypeNotFoundException, TableException, ExtraRegisterException {
        ProductJPA productJPA = createProduct("A4");
        productJPA.setName("Marcos Ferrel");
        productJpaDAO.update(productJPA);
        ProductJPA productDB = this.productJpaDAO.consult(productJPA.getId());
        assertNotNull(productDB);
        Assert.assertEquals("Marcos Ferrel", productDB.getName());
    }

    @Test
    public void searchAll() throws DAOException, KeyTypeNotFoundException {
        createProduct("A5");
        createProduct("A6");
        Collection<ProductJPA> list = productJpaDAO.findAll();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        for (ProductJPA productJPA : list) {
            this.productJpaDAO.remove(productJPA);
        }

        list = productJpaDAO.findAll();
        assertTrue(list != null);
        assertTrue(list.size() == 0);
    }

    private ProductJPA createProduct(String code) throws DAOException, KeyTypeNotFoundException {
        ProductJPA productJPA = new ProductJPA();
        productJPA.setCode(code);
        productJPA.setName("Product 1");
        productJPA.setDescription("Product 1");
        productJPA.setPrice(BigDecimal.TEN);
        productJpaDAO.register(productJPA);
        return productJPA;
    }

}
