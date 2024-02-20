package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.IGenericJpaDAO;
import marcos.ferrel.domain.SalesJpa;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.KeyTypeNotFound;

/**
 * @author marcos.ferrel
 *
 */
public interface ISalesJpaDAO extends IGenericJpaDAO<SalesJpa, Long> {

    public void finishSales(SalesJpa salesJpa) throws KeyTypeNotFound, DAOException;

    public void cancelSales(SalesJpa salesJpa) throws KeyTypeNotFound, DAOException;

    public SalesJpa consultWithCollection(Long id);

}
