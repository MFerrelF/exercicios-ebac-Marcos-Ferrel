/**
 * 
 */
package marcos.ferrel.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import marcos.ferrel.dao.generics.GenericDAO;
import marcos.ferrel.domain.Car;

/**
 * @author marcos.ferrel
 * 
 */
public class CarDAO extends GenericDAO<Car, Long> implements ICarDAO {
	
	public CarDAO() {
		super(Car.class);
	}

	@Override
	public List<Car> carFilter(String query) {
		TypedQuery<Car> tpQuery = this.entityManager.createNamedQuery("Car.findByModel", this.persistenceClass);
		tpQuery.setParameter("nome", "%" + query + "%");
		return tpQuery.getResultList();
	}	

}
