package marcos.ferrel;

import marcos.ferrel.DAO.IProductDAO;
import marcos.ferrel.DAO.ProductDAOMock;
import marcos.ferrel.domain.Product;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.services.IProductService;
import marcos.ferrel.services.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author marcos.ferrel
 */
public class ProductServiceTest {

    private IProductService productService;

    private Product product;

    public ProductServiceTest() {
        IProductDAO dao = new ProductDAOMock();
        productService = new ProductService(dao);
    }

    @Before
    public void init() {
        product = new Product();
        product.setCode("A1");
        product.setDescription("Product 1");
        product.setName("Product 1");
        product.setValue(BigDecimal.TEN);
    }

    @Test
    public void search() throws DAOException {
        Product products = this.productService.consult(product.getCode());
        Assert.assertNotNull(products);
    }

    @Test
    public void save() throws DAOException, KeyTypeNotFound {
        Boolean ret = productService.register(product);
        Assert.assertTrue(ret);
    }

    @Test
    public void remove() throws DAOException {
        productService.remove(product.getCode());
    }

    @Test
    public void updateCustomer() throws DAOException, KeyTypeNotFound {
        product.setName("Marcos Ferrel");
        productService.update(product);
        Assert.assertEquals("Marcos Ferrel", product.getName());
    }

}
