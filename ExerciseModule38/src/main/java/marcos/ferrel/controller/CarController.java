/**
 * 
 */
package marcos.ferrel.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import marcos.ferrel.domain.Car;
import marcos.ferrel.services.ICarService;

/**
 * @author marcos.ferrel
 * 
 */
@Named
@ViewScoped
public class CarController implements Serializable {

	private static final long serialVersionUID = -2610639931108133650L;
	
	private Car car;
	
	private Collection<Car> cars;
	
	@Inject
	private ICarService carService;
	
	private boolean isUpdate;
	
	@PostConstruct
	public void init() {
		try {
			this.isUpdate = false;
			this.car = new Car();
			this.cars = carService.findAll();			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error while listing Cars!!!"));
		}
	}
	
	public void cancel() {
		try {
			this.isUpdate = false;
			this.car = new Car();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error while cancelling the action!!!"));
		}
	}
	
	public void edit(Car car) {
		try {
			this.isUpdate = true;
			this.car = car;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error while removing Car!!!"));
		}
	}
	
	public void deleteCustomer(Car car) {
		try {
			carService.remove(car);
			cars.remove(car);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error while removing Car"));
		}
	}
	
	public void add() {
		try {
			carService.register(car);
			this.cars = carService.findAll();
			this.car = new Car();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error while creating Car!!!"));
		}
	}
	
	public void updateCustomer() {
		try {
			carService.update(this.car);
			cancel();
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Success while updating Car!!!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error while updating Car!!!"));
		}
	}
	public String backMainScreen() {
		return "/index.xhtml"; 
	}

	/**
	 * @return the cars
	 */
	public Collection<Car> getCars() {
		return cars;
	}

	/**
	 * @param cars the cars to set
	 */
	public void setCars(Collection<Car> cars) {
		this.cars = cars;
	}

	/**
	 * @return the carService
	 */
	public ICarService getCarService() {
		return carService;
	}

	/**
	 * @param carService the carService to set
	 */
	public void setCarService(ICarService carService) {
		this.carService = carService;
	}

	/**
	 * @return the isUpdate
	 */
	public boolean isUpdate() {
		return isUpdate;
	}

	/**
	 * @param isUpdate the isUpdate to set
	 */
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	
	

}
