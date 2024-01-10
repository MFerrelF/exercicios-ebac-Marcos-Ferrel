package marcos.ferrel.dao.generics;

import marcos.ferrel.dao.exception.KeyNotFoundException;
import marcos.ferrel.domain.Interface;

import java.util.Collection;

/**
 * @author marcos.ferrel
 * @param <T>
 */
public interface IGenericDAO <T extends Interface> {

    public Boolean register(T entity) throws KeyNotFoundException;

    public void remove(Long value);

    public void update(T entity) throws KeyNotFoundException;

    public T consult(Long value);

    public Collection<T> findsAll();
}
