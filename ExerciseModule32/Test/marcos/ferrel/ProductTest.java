package marcos.ferrel;

import marcos.ferrel.dao.IProductDAO;
import marcos.ferrel.dao.ProductDAO;
import marcos.ferrel.domain.Product;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author marcos.ferrel
 */
public class ProductTest {

    private IProductDAO productDAO;

    public ProductTest() {
        productDAO = new ProductDAO();
    }

    @Test
    public void register() {
        Product product = new Product();
        product.setCode("A1");
        product.setName("Product 1");
        product.setDescription("Product 1");
        product = productDAO.register(product);

        assertNotNull(product);
        assertNotNull(product.getId());
    }

}
