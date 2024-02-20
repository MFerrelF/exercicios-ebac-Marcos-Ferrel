package marcos.ferrel.dao.generics;

import marcos.ferrel.domain.IPersistence;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author marcos.ferrel
 *
 */
public interface IGenericJpaDAO <T extends IPersistence, E extends Serializable> {

    /**
     * Method responsible for registering a customer on the DataBase
     *
     * @param entity
     * @return
     * @throws KeyTypeNotFound
     * @throws DAOException
     */
    public T register(T entity) throws KeyTypeNotFound, DAOException;

    /**
     * Method responsible for removing a customer from the DataBase
     *
     * @param entity
     * @throws DAOException
     */
    public void remove(T entity) throws DAOException;

    /**
     * Method responsible for updating an entry from the DataBase
     *
     * @param entity
     * @return
     * @throws KeyTypeNotFound
     * @throws DAOException
     */
    public T update(T entity) throws KeyTypeNotFound, DAOException;

    /**
     * Method responsible for consulting any entry on the DataBase
     *
     * @param id
     * @return
     * @throws ExtraRegisterException
     * @throws TableException
     * @throws DAOException
     */
    public T consult(E id) throws ExtraRegisterException, TableException, DAOException;

    /**
     * Method responsible for returning a Collection of data from a DataBase
     *
     * @return
     * @throws DAOException
     */
    public Collection<T> findAll() throws DAOException;

}
