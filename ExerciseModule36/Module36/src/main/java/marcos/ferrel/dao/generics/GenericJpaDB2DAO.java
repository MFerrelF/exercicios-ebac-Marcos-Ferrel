package marcos.ferrel.dao.generics;

import marcos.ferrel.domain.IPersistence;

import java.io.Serializable;

/**
 * @author marcos.ferrel
 *
 * @param <T>
 * @param <E>
 */
public abstract class GenericJpaDB2DAO <T extends IPersistence, E extends Serializable> extends GenericJpaDAO<T, E> {

    public GenericJpaDB2DAO(Class<T> persistenceClass) {
        super(persistenceClass, "Postgre2");
    }

}
