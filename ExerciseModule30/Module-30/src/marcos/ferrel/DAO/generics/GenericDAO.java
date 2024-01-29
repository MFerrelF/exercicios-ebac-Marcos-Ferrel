package marcos.ferrel.DAO.generics;

import annotations.KeyType;
import annotations.Table;
import annotations.TableColumn;
import marcos.ferrel.DAO.SalesInter;
import marcos.ferrel.exceptions.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author marcos.ferrel
 * @param <T>
 * @param <E>
 */
public abstract class GenericDAO<T extends SalesInter, E extends Serializable> implements IGenericDAO<T,E> {

    public abstract Class<T> getClassType();

    public abstract void updateData(T entity, T registeredEntity);

    protected abstract String getInsertedQuery();

    protected abstract String getRemovedQuery();

    protected abstract String getUpdatedQuery();

    protected abstract void setInsertedQueryParameters(PreparedStatement insertStatement, T entity) throws SQLException;

    protected abstract void setRemovedQueryParameters(PreparedStatement deleteStatement, E value) throws SQLException;

    protected abstract void setUpdatedQueryParameters(PreparedStatement updateStatement, T entity) throws SQLException;

    protected abstract void setSelectedQueryParameters(PreparedStatement updateStatement, E value) throws SQLException;

    public GenericDAO() {

    }

    public E getKey(T entity) throws KeyTypeNotFound {
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(KeyType.class)) {
                KeyType keyType = field.getAnnotation(KeyType.class);
                String methodName = keyType.value();
                try {
                    Method method = entity.getClass().getMethod(methodName);
                    returnValue = (E) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new KeyTypeNotFound("Main object key " + entity.getClass() + " not found", e);
                }
            }
        }
        if (returnValue == null) {
            String msg = "Main object key " + entity.getClass() + " not found";
            System.out.println("***** Error *****" + msg);
            throw new KeyTypeNotFound(msg);
        }
        return null;
    }

    @Override
    public Boolean register(T entity) throws KeyTypeNotFound, DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(getInsertedQuery(), Statement.RETURN_GENERATED_KEYS);
            setInsertedQueryParameters(statement, entity);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        SalesInter inter = (SalesInter) entity;
                        inter.setId(rs.getLong(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while registering the object ", e);
        } finally {
            closeConnection(connection, statement, null);
        }
        return false;
    }

    @Override
    public void remove(E value) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getRemovedQuery());
            setRemovedQueryParameters(statement, value);
            int rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while removing object ", e);
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public void update(T entity) throws KeyTypeNotFound, DAOException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getUpdatedQuery());
            setUpdatedQueryParameters(statement, entity);
            int rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while updating the object ", e);
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public T consult(E value) throws ExtraRegisterException, TableException, DAOException {
        try {
            validExtraRegister(value);
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE " + getKeyFieldName((getClassType() + " = ?").getClass()));
            setSelectedQueryParameters(statement, value);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                T entity = getClassType().getConstructor(null).newInstance(null);
                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(TableColumn.class)) {
                        TableColumn column = field.getAnnotation(TableColumn.class);
                        String dbName = column.dbName();
                        String javaSetName = column.setJavaName();
                        Class<?> classField = field.getType();
                        try {
                            Method method = entity.getClass().getMethod(javaSetName, classField);
                            setValueByType(entity, method, classField, rs, dbName);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            throw new DAOException("Error consulting the object ", e);
                        } catch (ElementTypeNotKnownException e) {
                            throw new DAOException("Error consulting the object ", e);
                        }
                    }
                }
                return entity;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | NoSuchMethodException | SecurityException | KeyTypeNotFound e) {
            throw new DAOException("Error consulting the object ", e);
        }
        return null;
    }

    @Override
    public Collection<T> findAll() throws DAOException {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM " + getTableName());
            rs = statement.executeQuery();
            while (rs.next()) {
                T entity = getClassType().getConstructor(null).newInstance(null);
                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(TableColumn.class)) {
                        TableColumn column = field.getAnnotation(TableColumn.class);
                        String dbName = column.dbName();
                        String setJavaName = column.setJavaName();
                        Class<?> classField = field.getType();
                        try {
                            Method method = entity.getClass().getMethod(setJavaName, classField);
                            setValueByType(entity, method, classField, rs, dbName);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            throw new DAOException("Error while listing objects ", e);
                        } catch (ElementTypeNotKnownException e) {
                            throw new DAOException("Error while listing objects ", e);
                        }
                    }
                }
                list.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | TableException e) {
            throw new DAOException("Error while listing objects ", e);
        } finally {
            closeConnection(connection, statement, rs);
        }
        return list;
    }

    protected Connection getConnection() throws DAOException {
        try {
            return ConnectionSingleton.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Error while opening connection with database ", e);
        }
    }

    protected void closeConnection(Connection connection, PreparedStatement statement, ResultSet rs) {
        try {
            if (rs!= null && !rs.isClosed()) {
                rs.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private Long validExtraRegister(E value) throws DAOException, KeyTypeNotFound, TableException, ExtraRegisterException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Long count = null;
        try {
            statement = connection.prepareStatement("SELECT COUNT(*) FROM " + getTableName() + " WHERE " + getKeyFieldName(getClassType()) + " = ?");
            setSelectedQueryParameters(statement, value);
            rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getLong(1);
                if (count > 1) {
                    throw new ExtraRegisterException("Found an extra register of " + getTableName());
                }
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, rs);
        }
        return count;
    }

    private String getTableName() throws TableException {
        if (getClassType().isAnnotationPresent(Table.class)) {
            Table table = getClassType().getAnnotation(Table.class);
            return table.value();
        } else {
            throw new TableException("Table type " + getClassType().getName() + " not found");
        }
    }

    public String getKeyFieldName(Class clazz) throws KeyTypeNotFound {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(KeyType.class) && field.isAnnotationPresent(TableColumn.class)) {
                TableColumn column = field.getAnnotation(TableColumn.class);
                return column.dbName();
            }
        }
        return null;
    }

    private void setValueByType(T entity, Method method, Class<?> classField, ResultSet rs, String fieldName) throws SQLException, InvocationTargetException, IllegalAccessException, ElementTypeNotKnownException, IllegalArgumentException {
        if (classField.equals(Integer.class)) {
            Integer val = rs.getInt(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(Long.class)) {
            Long val = rs.getLong(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(Double.class)) {
            Double val = rs.getDouble(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(Short.class)) {
            Short val = rs.getShort(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(BigDecimal.class)) {
            BigDecimal val = rs.getBigDecimal(fieldName);
            method.invoke(entity, val);
        } else if (classField.equals(String.class)) {
            String val = rs.getString(fieldName);
            method.invoke(entity, val);
        } else {
            throw new ElementTypeNotKnownException("Class type not known: " + classField);
        }
    }

}
