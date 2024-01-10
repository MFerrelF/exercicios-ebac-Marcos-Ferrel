package marcos.ferrel.dao.generics;

import marcos.ferrel.dao.SingletonMap;
import marcos.ferrel.dao.annotation.KeyType;
import marcos.ferrel.dao.exception.KeyNotFoundException;
import marcos.ferrel.domain.Interface;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @author marcos.ferrel
 */
public abstract class GenericDAO<T extends Interface> implements IGenericDAO<T> {

    private SingletonMap singletonMap;

    public abstract Class<T> getClassType();

    public abstract void updateData(T entity, T registeredEntity);

    public GenericDAO() {
        this.singletonMap = SingletonMap.getInstance();
    }

    public Long getKey(T entity) throws KeyNotFoundException {
        Field[] fields = entity.getClass().getDeclaredFields();
        Long returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(KeyType.class)){
                KeyType keyType = field.getAnnotation(KeyType.class);
                String nameMethod = keyType.value();
                try {
                    Method method = entity.getClass().getMethod(nameMethod);
                    returnValue = (Long) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new KeyNotFoundException("Main object key " + entity.getClass() + " not found.", e);
                }
            }
        }
        if (returnValue == null) {
            String msg = "Main object key " + entity.getClass() + " not found.";
            System.out.println("***** Error *****");
            throw new KeyNotFoundException(msg);
        }
        return null;
    }

    public Boolean register(T entity) throws KeyNotFoundException {
        Map<Long, T> inMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        Long key = getKey(entity);
        if (inMap.containsKey(key)) {
            return false;
        }
        inMap.put(key, entity);
        return true;
    }

    public void remove(Long value) {
        Map<Long, T> inMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        T registeredObject = inMap.get(value);
        if (registeredObject != null) {
            inMap.remove(value, registeredObject);
        }
    }

    public void update(T entity) throws KeyNotFoundException {
        Map<Long, T> inMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        Long key = getKey(entity);
        T registeredObject = inMap.get(key);
        if (registeredObject != null) {
            updateData(entity, registeredObject);
        }
    }

    public T consult(Long value) {
        Map<Long, T> inMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        return inMap.get(value);
    }

    public Collection<T> findsAll() {
        Map<Long, T> inMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        return inMap.values();
    }

}
