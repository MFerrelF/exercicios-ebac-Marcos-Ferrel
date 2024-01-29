package marcos.ferrel.services;

import marcos.ferrel.DAO.IProductDAO;
import marcos.ferrel.DAO.generics.IGenericDAO;
import marcos.ferrel.domain.Product;
import marcos.ferrel.services.generics.GenericService;

/**
 * @author marcos.ferrel
 */
public class ProductService extends GenericService<Product, String> implements IProductService {
    public ProductService(IProductDAO dao) {
        super(dao);
    }
}
