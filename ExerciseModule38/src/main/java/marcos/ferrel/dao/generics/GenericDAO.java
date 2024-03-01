/**
 * 
 */
package marcos.ferrel.dao.generics;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import marcos.ferrel.domain.ICarPersistence;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.exceptions.TableException;

/**
 * @author marcos.ferrel
 * 
 */
public class GenericDAO <T extends ICarPersistence, E extends Serializable> implements IGenericDAO<T, E> {
	
	protected Class<T> persistenceClass;
	
	protected EntityManager entityManager;
	
	public GenericDAO(Class<T> persistenceClass) {
		this.persistenceClass = persistenceClass;
	}

	@Override
	public ICarPersistence register(ICarPersistence entity) throws KeyTypeNotFoundException, DAOException {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public void remove(ICarPersistence entity) throws DAOException {
		if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            T managedCustomer = entityManager.find(this.persistenceClass, entity.getId());
            if (managedCustomer != null) {
                entityManager.remove(managedCustomer);
            }
        }
	}

	@Override
	public ICarPersistence update(ICarPersistence entity) throws KeyTypeNotFoundException, DAOException {
		entity = entityManager.merge(entity);
		return entity;
	}

	@Override
	public ICarPersistence consult(Serializable value) throws ExtraRegisterException, TableException, DAOException {
		T entity = entityManager.find(this.persistenceClass, value);
		return entity;
	}

	@Override
	public Collection findAll() throws DAOException {
		List<T> list = entityManager.createQuery(getSelectSql(), this.persistenceClass).getResultList();
		return list;
	}
	
	private String getSelectSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT obj FROM ");
		sb.append(this.persistenceClass.getSimpleName());
		sb.append(" obj");
		return sb.toString();
	}
	
	

}
