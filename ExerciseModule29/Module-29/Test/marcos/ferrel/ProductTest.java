package marcos.ferrel;

import marcos.ferrel.dao.IProductDAO;
import marcos.ferrel.dao.ProductDAO;
import marcos.ferrel.domain.Product;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author marcos.ferrel
 */
public class ProductTest {

    private IProductDAO productDAO;

    @Test
    public void registerProduct() throws Exception {
        productDAO = new ProductDAO();

        Product product = new Product();
        product.setPcode("10");
        product.setPname("Product1");
        Integer countReg = productDAO.register(product);
        assertTrue(countReg == 1);

        Product productDB = productDAO.search("10");
        assertNotNull(productDB);
        assertEquals(product.getPcode(), productDB.getPcode());
        assertEquals(product.getPname(), productDB.getPname());

        Integer countDel = productDAO.remove(productDB);
        assertTrue(countDel == 1);

    }

    @Test
    public void updateTest() throws Exception {
        productDAO = new ProductDAO();

        Product product = new Product();
        product.setPcode("10");
        product.setPname("Product1");
        Integer countReg = productDAO.register(product);
        assertTrue(countReg == 1);

        Product productDB = productDAO.search("10");
        assertNotNull(productDB);
        assertEquals(product.getPcode(), productDB.getPcode());
        assertEquals(product.getPname(), productDB.getPname());

        productDB.setPcode("20");
        productDB.setPname("Amanda Chaves");
        Integer countUP = productDAO.update(productDB);
        assertTrue(countUP == 1);

        Product productDB1 = productDAO.search("10");
        assertNull(productDB1);

        Product productDB2 = productDAO.search("20");
        assertNotNull(productDB2);
        assertEquals(productDB.getId(), productDB2.getId());
        assertEquals(productDB.getPcode(), productDB2.getPcode());
        assertEquals(productDB.getPname(), productDB2.getPname());

        List<Product> list = productDAO.searchAll();
        for (Product prod : list) {
            productDAO.remove(prod);
        }
    }

    @Test
    public void searchAllTest() throws Exception {
        productDAO = new ProductDAO();

        Product product = new Product();
        product.setPcode("10");
        product.setPname("Marcos Ferrel");
        Integer countReg = productDAO.register(product);
        assertTrue(countReg == 1);

        Product products = new Product();
        products.setPcode("20");
        products.setPname("Test");
        Integer countReg1 = productDAO.register(products);
        assertTrue(countReg1 == 1);

        List<Product> list = productDAO.searchAll();
        assertNotNull(list);
        assertEquals(2, list.size());

        int countDel = 0;
        for (Product prod : list) {
            productDAO.remove(prod);
            countDel++;
        }
        assertEquals(list.size(), countDel);

        list = productDAO.searchAll();
        assertEquals(list.size(), 0);
    }
}
