package marcos.ferrel.dao.generics;

import marcos.ferrel.domain.IPersistence;

import java.io.Serializable;

/**
 * @author marcos.ferrel
 *
 */
public abstract class GenericJpaDB1DAO <T extends IPersistence, E extends Serializable> extends GenericJpaDAO<T, E> {

    public GenericJpaDB1DAO(Class<T> persistenceClass) {
        super(persistenceClass, "Postgre1");
    }

}
