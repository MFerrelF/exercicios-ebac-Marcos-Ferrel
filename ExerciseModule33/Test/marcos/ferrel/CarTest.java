package marcos.ferrel;

import marcos.ferrel.dao.*;
import marcos.ferrel.domain.Accessories;
import marcos.ferrel.domain.Brand;
import marcos.ferrel.domain.Car;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author marcos.ferrel
 */
public class CarTest {

    private ICarDAO carDAO;

    private IBrandDAO brandDAO;

    private IAccessoriesDAO accessoriesDAO;

    public CarTest() {
        carDAO = new CarDAO();
        brandDAO = new BrandDAO();
        accessoriesDAO = new AccessoriesDAO();
    }

    @Test
    public void register() {
        Brand brand = registerBrand("A1");
        Accessories accessories = registerAccessories("A1");

        Car car = new Car();
        car.setCode("A1");
        car.setName("Gol");
        car.setDescription("Gol 1.0 flex");
        car.setBrand(brand);
        car.setAccessories(accessories);

        accessories.setCar(car);
        car = carDAO.register(car);

        assertNotNull(car);
        assertNotNull(car.getId());

        Car carDB = carDAO.findByBrand(brand);
        assertNotNull(carDB);
        assertEquals(car.getId(), carDB.getId());
    }

    private Brand registerBrand(String code) {
        Brand brand = new Brand();
        brand.setCode(code);
        brand.setName("Volkswagen");
        brand.setDescription("Volkswagen populars");
        return brandDAO.register(brand);
    }

    private Accessories registerAccessories(String code) {
        Accessories accessories = registerAccessories("A1");
        accessories.setCode(code);
        accessories.setName("Air Conditioner");
        accessories.setDescription("Volkswagen Gol Air Conditioner");
        return accessoriesDAO.register(accessories);
    }

}
