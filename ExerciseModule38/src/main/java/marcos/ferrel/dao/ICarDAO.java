/**
 * 
 */
package marcos.ferrel.dao;

import java.util.List;

import marcos.ferrel.dao.generics.IGenericDAO;
import marcos.ferrel.domain.Car;

/**
 * @author marcos.ferrel
 * 
 */
public interface ICarDAO extends IGenericDAO<Car, Long> {
	
	List<Car> carFilter(String query);

}
