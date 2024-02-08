package marcos.ferrel.dao.generics;

import marcos.ferrel.dao.IPersistence;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.ExtraRegisterException;
import marcos.ferrel.domain.exceptions.KeyTypeNotFoundException;
import marcos.ferrel.domain.exceptions.TableException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author marcos.ferrel
 */
public class GenericJpaDAO <T extends IPersistence, E extends Serializable> implements IGenericJpaDAO<T, E> {

    protected EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    private Class<T> persistenceClass;

    public GenericJpaDAO(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    @Override
    public T register(T entity) throws KeyTypeNotFoundException, DAOException {
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
    public T update(T entity) throws KeyTypeNotFoundException, DAOException {
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
        entityManagerFactory = Persistence.createEntityManagerFactory("Module35");
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

}
