/**
 * 
 */
package marcos.ferrel;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import marcos.ferrel.dao.CarDAO;
import marcos.ferrel.dao.ICarDAO;
import marcos.ferrel.domain.Car;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.exceptions.TableException;

/**
 * @author marcos.ferrel
 * 
 */
public class CarTest {
	
	private ICarDAO carDAO;
	
	private Random rd;
	
	public CarTest() {
		this.carDAO = new CarDAO();
		rd = new Random();
	}
	
	@After
	public void end() throws DAOException {
		Collection<Car> list = carDAO.findAll();
		list.forEach(carT -> {
			try {
				carDAO.remove(carT);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void search() throws DAOException, KeyTypeNotFoundException, TableException, ExtraRegisterException {
		Car car = createCar();
		carDAO.register(car);
		
		Car consultedCar = carDAO.consult(car.getId());
		Assert.assertNotNull(consultedCar);
	}
	
	@Test
	public void save() throws KeyTypeNotFoundException, DAOException, ExtraRegisterException, TableException {
		Car car = createCar();
		Car ret = carDAO.register(car);
		Assert.assertNotNull(ret);
		
		Car consultedCar = carDAO.consult(ret.getId());
		Assert.assertNotNull(consultedCar);
		
		carDAO.remove(car);
		
		Car consultedCar1 = carDAO.consult(ret.getId());
		Assert.assertNull(consultedCar1);
	}
	
	@Test
	public void remove() throws KeyTypeNotFoundException, DAOException, ExtraRegisterException, TableException {
		Car car = createCar();
		Car ret = carDAO.register(car);
		Assert.assertNotNull(ret);
		
		Car consultedCar = carDAO.consult(ret.getId());
		Assert.assertNotNull(consultedCar);
		
		carDAO.remove(car);
		consultedCar = carDAO.consult(car.getId());
		Assert.assertNull(consultedCar);
	}
	
	@Test
	public void update() throws KeyTypeNotFoundException, DAOException, ExtraRegisterException, TableException {
		Car car = createCar();
		Car ret = carDAO.register(car);
		Assert.assertNotNull(ret);
		
		Car consultedCar = carDAO.consult(ret.getId());
		Assert.assertNotNull(consultedCar);
		
		consultedCar.setModel("Toyota Corolla Sport");
		carDAO.update(consultedCar);
		
		Car updatedCar = carDAO.consult(consultedCar.getId());
		Assert.assertNotNull(updatedCar);
		Assert.assertEquals("Toyota Corolla Sport", updatedCar.getModel());
		
		carDAO.remove(car);
		consultedCar = carDAO.consult(updatedCar.getId());
		Assert.assertNull(consultedCar);
	}	
	
	private Car createCar() {
		Car car = new Car();
		car.setModel("Toyota Corolla");
		car.setCarID(rd.nextLong());
		car.setEngine(2.0D);
		car.setColour("Black");
		return car;
	}

}
