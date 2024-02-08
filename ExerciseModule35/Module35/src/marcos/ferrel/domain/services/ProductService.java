package marcos.ferrel.domain.services;

import marcos.ferrel.dao.generics.IGenericJpaDAO;
import marcos.ferrel.domain.ProductJPA;
import marcos.ferrel.domain.services.generic.GenericJPAService;

/**
 * @author marcos.ferrel
 */
public class ProductService extends GenericJPAService<ProductJPA, String> implements IProductService {

    public ProductService(IGenericJpaDAO<ProductJPA, String> dao) {
        super(dao);
    }

}
