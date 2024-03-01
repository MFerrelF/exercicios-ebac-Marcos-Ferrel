/**
 * 
 */
package marcos.ferrel.services;

import java.util.List;

import marcos.ferrel.domain.Car;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.services.generics.IGenericService;

/**
 * @author marcos.ferrel
 * 
 */
public interface ICarService extends IGenericService<Car, Long> {
	
	Car findByCarID(Long carID) throws DAOException;
	
	List<Car> carFilter(String query);

}
