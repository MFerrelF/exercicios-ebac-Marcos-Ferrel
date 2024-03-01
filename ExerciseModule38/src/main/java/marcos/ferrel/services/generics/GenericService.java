/**
 * 
 */
package marcos.ferrel.services.generics;

import java.io.Serializable;
import java.util.Collection;

import marcos.ferrel.dao.generics.IGenericDAO;
import marcos.ferrel.domain.ICarPersistence;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.exceptions.TableException;

/**
 * @author marcos.ferrel
 * 
 */
public class GenericService <T extends ICarPersistence, E extends Serializable> implements IGenericService<T, E> {
	
	protected IGenericDAO<T, E> dao;
	
	public GenericService(IGenericDAO<T, E> dao) {
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
	public T consult(E value) throws ExtraRegisterException, TableException, DAOException {
		return this.dao.consult(value);
	}

	@Override
	public Collection<T> findAll() throws DAOException {
		return this.dao.findAll();
	}

}
