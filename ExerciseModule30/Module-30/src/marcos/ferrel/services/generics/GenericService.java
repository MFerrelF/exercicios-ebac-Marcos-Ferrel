package marcos.ferrel.services.generics;

import marcos.ferrel.DAO.SalesInter;
import marcos.ferrel.DAO.generics.IGenericDAO;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author marcos.ferrel
 * @param <T>
 * @param <E>
 */
public abstract class GenericService<T extends SalesInter, E extends Serializable> implements IGenericService<T, E> {

    protected IGenericDAO<T,E> dao;

    public GenericService(IGenericDAO<T,E> dao) {
        this.dao = dao;
    }

    public Boolean register(T entity) throws DAOException, KeyTypeNotFound {
        return this.dao.register(entity);
    }

    public void remove(E value) throws DAOException {
        this.dao.remove(value);
    }

    public void update(T entity) throws DAOException, KeyTypeNotFound {
        this.dao.update(entity);
    }

    public T consult(E value) throws DAOException {
        try {
            return this.dao.consult(value);
        } catch (ExtraRegisterException | TableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<T> findAll() throws DAOException {
        return this.dao.findAll();
    }

}
