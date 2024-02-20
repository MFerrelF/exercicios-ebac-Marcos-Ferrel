package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.IGenericJpaDAO;
import marcos.ferrel.domain.IPersistence;

/**
 * @author marcos.ferrel
 *
 * @param <T>
 */
public interface ICustomerJpaDAO<T extends IPersistence> extends IGenericJpaDAO<T,Long> {
}
