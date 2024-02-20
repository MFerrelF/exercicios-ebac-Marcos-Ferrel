package marcos.ferrel.dao.generics;

import marcos.ferrel.domain.IPersistence;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.ExtraRegisterException;
import marcos.ferrel.exceptions.KeyTypeNotFound;
import marcos.ferrel.exceptions.TableException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author marcos.ferrel
 *
 */
public class GenericJpaDAO <T extends IPersistence, E extends Serializable> implements IGenericJpaDAO <T,E> {

    private static final String PERSISTENCE_UNIT_NAME = "Postgre1";

    protected EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    private Class<T> persistenceClass;

    private String persistenceUnitName;

    public GenericJpaDAO(Class<T> persistenceClass, String persistenceUnitName) {
        this.persistenceClass = persistenceClass;
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    public T register(T entity) throws KeyTypeNotFound, DAOException {
        openConnection();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public void remove(T entity) throws DAOException {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        closeConnection();
    }

    @Override
    public T update(T entity) throws KeyTypeNotFound, DAOException {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public T consult(E value) throws ExtraRegisterException, TableException, DAOException {
        openConnection();
        T entity = entityManager.find(this.persistenceClass, value);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public Collection<T> findAll() throws DAOException {
        openConnection();
        List<T> list = entityManager.createQuery(getSelectSql(), this.persistenceClass).getResultList();
        closeConnection();
        return list;
    }

    protected void openConnection() {
        entityManagerFactory = Persistence.createEntityManagerFactory(getPersistenceUnitName());
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    protected void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

    private String getSelectSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT obj FROM ");
        sb.append(this.persistenceClass.getSimpleName());
        sb.append(" obj");
        return sb.toString();
    }

    private String getPersistenceUnitName() {
        if (persistenceUnitName != null && !"".equals(persistenceUnitName)) {
            return persistenceUnitName;
        } else {
            return PERSISTENCE_UNIT_NAME;
        }
    }

}
