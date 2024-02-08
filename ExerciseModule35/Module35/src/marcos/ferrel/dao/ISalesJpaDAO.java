package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.IGenericJpaDAO;
import marcos.ferrel.domain.SalesJPA;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.KeyTypeNotFoundException;

/**
 * @author marcos.ferrel
 */
public interface ISalesJpaDAO extends IGenericJpaDAO<SalesJPA, Long> {

    public void completeSales(SalesJPA sales) throws KeyTypeNotFoundException, DAOException;

    public void cancelSales(SalesJPA sales) throws KeyTypeNotFoundException, DAOException;

    public SalesJPA consultWithCollection(Long id);

}
