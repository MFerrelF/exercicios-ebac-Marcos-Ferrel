package marcos.ferrel.domain.services.generic;

import marcos.ferrel.dao.IPersistence;
import marcos.ferrel.dao.generics.IGenericJpaDAO;
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
public class GenericJPAService<T extends IPersistence, E extends Serializable> implements IGenericJpaDAO<T, E> {

    protected IGenericJpaDAO<T, E> dao;

    public GenericJPAService(IGenericJpaDAO<T, E> dao) {
        this.dao = dao;
    }

    @Override
    public T register(T entity) throws KeyTypeNotFoundException, DAOException {
        return this.dao.register(entity);
    }

    @Override
    public void remove(T entity) throws DAOException {
        this.dao.remove(entity);
    }

    @Override
    public T update(T entity) throws KeyTypeNotFoundException, DAOException {
        return this.dao.update(entity);
    }

    @Override
    public T consult(E id) throws ExtraRegisterException, TableException, DAOException {
        try {
            return this.dao.consult(id);
        } catch (ExtraRegisterException | TableException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<T> findAll() throws DAOException {
        return this.dao.findAll();
    }
}
