package marcos.ferrel.dao;

import marcos.ferrel.domain.Brand;
import marcos.ferrel.domain.Car;

/**
 * @author marcos.ferrel
 */
public interface ICarDAO {
    Car register(Car car);

    Car findByBrand(Brand brand);

}
