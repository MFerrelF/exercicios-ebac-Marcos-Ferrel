package marcos.ferrel.dao;

import marcos.ferrel.domain.Brand;
import marcos.ferrel.domain.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author marcos.ferrel
 */
public class CarDAO implements ICarDAO {
    @Override
    public Car register(Car car) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Module-33");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return car;
    }

    @Override
    public Car findByBrand(Brand brand) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Module-33");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT m FROM Car m ");
        sb.append("INNER JOIN Brand b on b = m.brand");
        sb.append("WHERE b = : brand");

        entityManager.getTransaction().begin();
        TypedQuery<Car> query =
                entityManager.createQuery(sb.toString(), Car.class);
        query.setParameter("brand", brand);
        Car car = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return car;
    }
}
