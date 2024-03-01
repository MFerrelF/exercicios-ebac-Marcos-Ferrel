/**
 * 
 */
package marcos.ferrel.dao.generics;

import java.io.Serializable;
import java.util.Collection;

import marcos.ferrel.domain.ICarPersistence;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.exceptions.TableException;

/**
 * @author marcos.ferrel
 * 
 */
public interface IGenericDAO <T extends ICarPersistence, E extends Serializable> {
	
	/**
	 * Method used to register a new Car
	 * 
	 * @param entity to be registered
	 * @return return saved object
	 * @throws KeyTypeNotFoundException
	 * @throws DAOException
	 */
	public T register(T entity) throws KeyTypeNotFoundException, DAOException;
	
	/**
	 * Method used to remove a Car
	 * 
	 * @param entity to be removed
	 * @throws DAOException
	 */
	public void remove(T entity) throws DAOException;
	
	/**
	 * Method used to update an entry
	 * 
	 * @param entity to be updated
	 * @return return an updated object
	 * @throws KeyTypeNotFoundException
	 * @throws DAOException
	 */
	public T update(T entity) throws KeyTypeNotFoundException, DAOException;
	
	/**
	 * Method used to consult an entry
	 * 
	 * @param id unique key used to find an entry
	 * @return return a consulted object
	 * @throws ExtraRegisterException
	 * @throws TableException
	 * @throws DAOException
	 */
	public T consult (E id) throws ExtraRegisterException, TableException, DAOException;
	
	/**
	 * Method used to consult a list of objects
	 * 
	 * @return list of object from the database
	 * @throws DAOException
	 */
	public Collection<T> findAll() throws DAOException;

}
