/**
 * 
 */
package marcos.ferrel.services;

import java.util.List;

import javax.inject.Inject;

import marcos.ferrel.dao.ICarDAO;
import marcos.ferrel.domain.Car;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.TableException;
import marcos.ferrel.services.generics.GenericService;

/**
 * @author marcos.ferrel
 * 
 */
public class CarService extends GenericService<Car, Long> implements ICarService {
	
	private ICarDAO carDAO;
	
	@Inject
	public CarService(ICarDAO carDAO) {
		super(carDAO);
		this.carDAO = carDAO;
	}

	@Override
	public Car findByCarID(Long carID) throws DAOException {
		try {
			return this.dao.consult(carID);
		} catch (ExtraRegisterException | TableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Car> carFilter(String query) {
		return carDAO.carFilter(query);
	}
	
	

}
