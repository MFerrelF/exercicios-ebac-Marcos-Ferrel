package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDAO;
import marcos.ferrel.domain.ProductJPA;

/**
 * @author marcos.ferrel
 */
public class ProductJpaDAO extends GenericJpaDAO<ProductJPA, Long> implements IProductJpaDAO {

    public ProductJpaDAO() {
        super(ProductJPA.class);
    }

}
