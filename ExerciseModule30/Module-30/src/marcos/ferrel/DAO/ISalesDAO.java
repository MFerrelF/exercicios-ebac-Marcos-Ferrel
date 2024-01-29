package marcos.ferrel.DAO;

import marcos.ferrel.DAO.generics.IGenericDAO;
import marcos.ferrel.domain.Sales;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.KeyTypeNotFound;

/**
 * @author marcos.ferrel
 */
public interface ISalesDAO extends IGenericDAO<Sales, String> {

    public void finishSales(Sales sales) throws KeyTypeNotFound, DAOException;

    public void cancelSales(Sales sales) throws KeyTypeNotFound, DAOException;

}
