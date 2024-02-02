package marcos.ferrel.dao;

import marcos.ferrel.domain.Accessories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author marcos.ferrel
 */
public class AccessoriesDAO implements IAccessoriesDAO {
    @Override
    public Accessories register(Accessories accessories) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Module-33");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(accessories);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return accessories;
    }
}
