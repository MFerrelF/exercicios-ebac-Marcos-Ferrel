package marcos.ferrel.dao;

import marcos.ferrel.domain.Customer;
import marcos.ferrel.generics.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcos.ferrel
 */
public class CustomerDAO implements ICustomerDAO{
    @Override
    public Integer register(Customer customer) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlInsert();
            statement = connection.prepareStatement(sql);
            addInsertParameters(statement, customer);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public Integer update(Customer customer) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlUpdate();
            statement = connection.prepareStatement(sql);
            addUpdateParameters(statement, customer);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public Customer search(String code) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlSelected();
            statement = connection.prepareStatement(sql);
            addParameterSelected(statement, code);
            rs = statement.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                Long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String cd = rs.getString("CODE");
                customer.setId(id);
                customer.setName(name);
                customer.setCode(cd);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, rs);
        }
        return customer;
    }

    @Override
    public Integer remove(Customer customer) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlDeleted();
            statement = connection.prepareStatement(sql);
            addDeletedParameter(statement, customer);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public List<Customer> searchAll() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Customer> list = new ArrayList<>();
        Customer customer = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlSelectedAll();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                Long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String cd = rs.getString("CODE");
                customer.setId(id);
                customer.setName(name);
                customer.setCode(cd);
                list.add(customer);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, rs);
        }
        return list;
    }

    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_CUSTOMER1 (ID, CODE, NAME) ");
        sb.append("VALUES (nextval('SQ_CUSTOMER1'),?,?)");
        return sb.toString();
    }

    private void addInsertParameters(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getCode());
        statement.setString(2, customer.getName());
    }

    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_CUSTOMER1 ");
        sb.append("SET NAME = ?, CODE = ? ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    private void addUpdateParameters(PreparedStatement statement, Customer customer) throws SQLException{
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getCode());
        statement.setLong(3, customer.getId());
    }

    private String getSqlSelected() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_CUSTOMER1");
        sb.append("WHERE CODE = ?");
        return sb.toString();
    }

    private void addParameterSelected(PreparedStatement statement, String code) throws SQLException {
        statement.setString(1, code);
    }

    private String getSqlSelectedAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_CUSTOMER1");
        return sb.toString();
    }

    private String getSqlDeleted() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM TB_CUSTOMER1 ");
        sb.append("WHERE CODE = ?");
        return sb.toString();
    }

    private void addDeletedParameter(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getCode());
    }

    private void closeConnection(Connection connection, PreparedStatement statement, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
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

}
