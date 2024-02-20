package marcos.ferrel.services.generics;

import marcos.ferrel.dao.generics.IGenericJpaDAO;
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
public class GenericJpaService<T extends IPersistence, E extends Serializable> implements IGenericJpaService<T, E> {

    protected IGenericJpaDAO<T, E> dao;

    public GenericJpaService(IGenericJpaDAO<T, E> dao) {
        this.dao = dao;
    }


    @Override
    public T register(T entity) throws KeyTypeNotFound, DAOException {
        return this.dao.register(entity);
    }

    @Override
    public void remove(T entity) throws DAOException {
        this.dao.remove(entity);
    }

    @Override
    public T update(T entity) throws KeyTypeNotFound, DAOException {
        return this.dao.update(entity);
    }

    @Override
    public T consult(E value) throws ExtraRegisterException, TableException, DAOException {
        return this.dao.consult(value);
    }

    @Override
    public Collection<T> findAll() throws DAOException {
        return this.dao.findAll();
    }
}
