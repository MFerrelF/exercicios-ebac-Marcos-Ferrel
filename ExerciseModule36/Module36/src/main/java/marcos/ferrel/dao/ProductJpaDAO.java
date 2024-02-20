package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDB1DAO;
import marcos.ferrel.domain.ProductJpa;

/**
 * @author marcos.ferrel
 *
 */
public class ProductJpaDAO extends GenericJpaDB1DAO<ProductJpa, Long> implements IProductJpaDAO {

    public ProductJpaDAO() {
        super(ProductJpa.class);
    }

}
