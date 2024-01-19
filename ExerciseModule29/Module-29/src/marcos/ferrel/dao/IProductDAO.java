package marcos.ferrel.dao;

import marcos.ferrel.domain.Customer;
import marcos.ferrel.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    Integer register(Product product) throws Exception;

    Product search(String pcode) throws Exception;

    Integer remove(Product product) throws Exception;

    Integer update(Product product) throws Exception;

    public List<Product> searchAll() throws Exception;
}
