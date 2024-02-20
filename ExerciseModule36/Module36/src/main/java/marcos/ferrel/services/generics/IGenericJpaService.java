package marcos.ferrel.services.generics;

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
 * @param <T>
 * @param <E>
 */
public interface IGenericJpaService<T extends IPersistence, E extends Serializable> {

    public T register(T entity) throws KeyTypeNotFound, DAOException;

    public void remove(T entity) throws DAOException;

    public T update(T entity) throws KeyTypeNotFound, DAOException;

    public T consult(E value) throws ExtraRegisterException, TableException, DAOException;

    public Collection<T> findAll() throws DAOException;

}
