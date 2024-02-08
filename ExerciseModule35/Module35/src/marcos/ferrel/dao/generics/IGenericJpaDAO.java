package marcos.ferrel.dao.generics;

import marcos.ferrel.dao.IPersistence;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.ExtraRegisterException;
import marcos.ferrel.domain.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.domain.exceptions.TableException;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author marcos.ferrel
 * @param <T>
 * @param <E>
 */
public interface IGenericJpaDAO <T extends IPersistence, E extends Serializable> {

    /**
     * Method responsible for registering in the database
     *
     * @param entity to be registered
     * @return registered obj
     * @throws KeyTypeNotFoundException
     * @throws DAOException
     */
    public T register(T entity) throws KeyTypeNotFoundException, DAOException;

    /**
     * Method responsible for removing in the database
     *
     * @param entity to be removed
     * @throws DAOException
     */
    public void remove(T entity) throws DAOException;


    /**
     * Method responsible to update any data in the database
     *
     * @param entity to be updated
     * @return updated obj
     * @throws KeyTypeNotFoundException
     * @throws DAOException
     */
    public T update(T entity) throws KeyTypeNotFoundException, DAOException;

    /**
     * Method responsible for consulting in the database
     *
     * @param id unique data to be consulted
     * @return consulted obj
     * @throws ExtraRegisterException
     * @throws TableException
     * @throws DAOException
     */
    public T consult(E id) throws ExtraRegisterException, TableException, DAOException;

    /**
     * Method responsible for returning a group of objects in the database
     *
     * @return group of specific objects
     * @throws DAOException
     */
    public Collection<T> findAll() throws DAOException;

}
