package marcos.ferrel.DAO;

import marcos.ferrel.DAO.generics.GenericDAO;
import marcos.ferrel.domain.Sales;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.KeyTypeNotFound;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author marcos.ferrel
 */
public class SalesDAO extends GenericDAO<Sales, String> implements ISalesDAO {
    @Override
    public void finishSales(Sales sales) throws KeyTypeNotFound, DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE TB_SALES SET SALES_STATUS = ? WHERE ID = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, Sales.Status.CONCLUDED.name());
            statement.setLong(2, sales.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating the object ", e);
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public void cancelSales(Sales sales) throws KeyTypeNotFound, DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE TB_SALES SET SALES_STATUS = ? WHERE ID = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, Sales.Status.CANCELED.name());
            statement.setLong(2, sales.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the object ", e);
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public Class<Sales> getClassType() {
        return Sales.class;
    }

    @Override
    public void updateData(Sales entity, Sales registeredEntity) {
        registeredEntity.setCode(entity.getCode());
        registeredEntity.setStatus(entity.getStatus());
    }

    @Override
    protected String getInsertedQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_SALES ");
        sb.append("(ID, CODE, ID_CUSTOMER_FK, TOTAL_VALUE, SALES_DATE, SALES_STATUS)");
        sb.append("VALUES (nextval('sq_sales'),?,?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected String getRemovedQuery() {
        throw new UnsupportedOperationException("Operation not allowed");
    }

    @Override
    protected String getUpdatedQuery() {
        throw new UnsupportedOperationException("Operation not allowed");
    }

    @Override
    protected void setInsertedQueryParameters(PreparedStatement insertStatement, Sales entity) throws SQLException {
        insertStatement.setString(1, entity.getCode());
        insertStatement.setLong(2, entity.getCustomer().getId());
        insertStatement.setBigDecimal(3, entity.getTotalValue());
        insertStatement.setTimestamp(4, Timestamp.from(entity.getSalesDate()));
        insertStatement.setString(5, entity.getStatus().name());
    }

    @Override
    protected void setRemovedQueryParameters(PreparedStatement deleteStatement, String value) throws SQLException {
        throw new UnsupportedOperationException("Operation not allowed");
    }

    @Override
    protected void setUpdatedQueryParameters(PreparedStatement updateStatement, Sales entity) throws SQLException {
        throw new UnsupportedOperationException("Operation not allowed");
    }

    @Override
    protected void setSelectedQueryParameters(PreparedStatement updateStatement, String value) throws SQLException {
        updateStatement.setString(1, value);
    }
}
