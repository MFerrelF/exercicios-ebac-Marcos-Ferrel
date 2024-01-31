package marcos.ferrel.dao;

import marcos.ferrel.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductDAO implements IProductDAO {
    @Override
    public Product register(Product product) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Module-32");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return product;
    }
}
