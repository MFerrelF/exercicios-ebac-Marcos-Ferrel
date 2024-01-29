package marcos.ferrel.DAO.generics;

import marcos.ferrel.DAO.SalesInter;
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
public interface IGenericDAO <T extends SalesInter, E extends Serializable> {

    public Boolean register(T entity) throws KeyTypeNotFound, DAOException;

    public void remove(E value) throws DAOException;

    public void update(T entity) throws KeyTypeNotFound, DAOException;

    public T consult(E value) throws ExtraRegisterException, TableException, DAOException;

    public Collection<T> findAll() throws DAOException;

}
