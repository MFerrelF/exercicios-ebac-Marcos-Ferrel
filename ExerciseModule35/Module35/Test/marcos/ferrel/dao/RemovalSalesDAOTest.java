package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDAO;
import marcos.ferrel.domain.SalesJPA;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.KeyTypeNotFoundException;

/**
 * @author marcos.ferrel
 */
public class RemovalSalesDAOTest extends GenericJpaDAO<SalesJPA, Long> implements ISalesJpaDAO {
    public RemovalSalesDAOTest() {
        super(SalesJPA.class);
    }

    @Override
    public void completeSales(SalesJPA sales) throws KeyTypeNotFoundException, DAOException {
        throw new UnsupportedOperationException("Operation not allowed");
    }

    @Override
    public void cancelSales(SalesJPA sales) throws KeyTypeNotFoundException, DAOException {
        throw new UnsupportedOperationException("Operation not allowed");
    }

    @Override
    public SalesJPA consultWithCollection(Long id) {
        throw new UnsupportedOperationException("Operation not allowed");
    }
}
