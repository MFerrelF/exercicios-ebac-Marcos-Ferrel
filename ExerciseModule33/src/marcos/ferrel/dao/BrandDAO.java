package marcos.ferrel.dao;

import marcos.ferrel.domain.Brand;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author marcos.ferrel
 */
public class BrandDAO implements IBrandDAO {

    @Override
    public Brand register(Brand brand) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Module-33");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(brand);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return brand;
    }

}
