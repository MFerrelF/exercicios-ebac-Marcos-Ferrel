/**
 * 
 */
package marcos.ferrel.services.generics;

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
public interface IGenericService <T extends ICarPersistence, E extends Serializable> {
	
	/**
     * Method used to register a new object in the database
     *
     * @param entity to be registered
     * @return return saved object
     * @throws KeyTypeNotFoundException
	 * @throws DAOException
     */
    public T register(T entity) throws KeyTypeNotFoundException, DAOException;
    
    /**
     * Method used to remove an object from database 
     *
     * @param entity to be removed
	 * @throws DAOException
     */
    public void remove(T entity) throws DAOException;
    
    /**
     * Method used to update an object from database
     *
     * @param entity to be updated
     * @return return updated object
     * @throws TipoChaveNaoEncontradaException
	 * @throws DAOException
     */
    public T update(T entity) throws KeyTypeNotFoundException, DAOException;
    
    /**
     * Method used to consult an object from database
     *
     * @param value unique key to find an object in the database
     * @return
     * @throws MaisDeUmRegistroException 
     * @throws TableException 
     */
    public T consult(E value) throws ExtraRegisterException, TableException, DAOException;
    
    /**
     * Method used to return a list of objects from database
     *
     * @return Encountered registers
     * @throws DAOException 
     */
    public Collection<T> findAll() throws DAOException;

}
