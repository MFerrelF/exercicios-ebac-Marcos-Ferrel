package marcos.ferrel.DAO;

import marcos.ferrel.domain.Product;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;

import java.util.Collection;

/**
 * @author marcos.ferrel
 */
public class ProductDAOMock implements IProductDAO {
    @Override
    public Boolean register(Product entity) throws KeyTypeNotFound, DAOException {
        return true;
    }

    @Override
    public void remove(String value) throws DAOException {

    }

    @Override
    public void update(Product entity) throws KeyTypeNotFound, DAOException {

    }

    @Override
    public Product consult(String value) throws ExtraRegisterException, TableException, DAOException {
        Product product = new Product();
        product.setCode(value);
        return product;
    }

    @Override
    public Collection<Product> findAll() throws DAOException {
        return null;
    }
}
