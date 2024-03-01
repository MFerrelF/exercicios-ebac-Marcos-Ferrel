/**
 * 
 */
package marcos.ferrel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author marcos.ferrel
 * 
 */
@Entity
@Table(name = "TB_CAR")
@NamedQuery(name = "Car.findByModel", query = "SELECT c FROM Car c WHERE c.model LIKE :model")
public class Car implements ICarPersistence {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="car_seq")
	@SequenceGenerator(name="car_seq", sequenceName="sq_car", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "MODEL", nullable = false, length = 50)
    private String model;
	
	@Column(name = "CAR_ID", nullable = false, unique = true)
    private Long carID;	

	@Column(name = "ENGINE", nullable = false)
	private Double engine;
	
	@Column(name = "COLOUR", nullable = false, length = 20)
	private String colour;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * @return the carID
	 */
	public Long getCarID() {
		return carID;
	}

	/**
	 * @param carID the carID to set
	 */
	public void setCarID(Long carID) {
		this.carID = carID;
	}

	/**
	 * @return the engine
	 */
	public Double getEngine() {
		return engine;
	}

	/**
	 * @param engine the engine to set
	 */
	public void setEngine(Double engine) {
		this.engine = engine;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	

}
