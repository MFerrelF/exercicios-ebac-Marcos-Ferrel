package marcos.ferrel.dao;

import marcos.ferrel.domain.Customer;
import marcos.ferrel.domain.Product;
import marcos.ferrel.generics.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    @Override
    public Integer register(Product product) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlInsert();
            statement = connection.prepareStatement(sql);
            addInsertParameters(statement, product);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public Product search(String pcode) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Product product = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlSelected();
            statement = connection.prepareStatement(sql);
            addParameterSelected(statement, pcode);
            rs = statement.executeQuery();

            if (rs.next()) {
                product = new Product();
                Long id = rs.getLong("ID");
                String name = rs.getString("PNAME");
                String cd = rs.getString("PCODE");
                product.setId(id);
                product.setPname(name);
                product.setPcode(cd);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, rs);
        }
        return product;
    }

    @Override
    public Integer remove(Product product) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlDeleted();
            statement = connection.prepareStatement(sql);
            addDeletedParameter(statement, product);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public Integer update(Product product) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlUpdate();
            statement = connection.prepareStatement(sql);
            addUpdateParameters(statement, product);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, statement, null);
        }
    }

    @Override
    public List<Product> searchAll() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        Product product = null;
        try {
            connection = ConnectionSingleton.getConnection();
            String sql = getSqlSelectedAll();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                product = new Product();
                Long id = rs.getLong("ID");
                String name = rs.getString("PNAME");
                String cd = rs.getString("PCODE");
                product.setId(id);
                product.setPname(name);
                product.setPcode(cd);
                list.add(product);
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
        sb.append("INSERT INTO TB_PRODUCT (ID, PCODE, PNAME) ");
        sb.append("VALUES (nextval('SQ_PRODUCT'),?,?)");
        return sb.toString();
    }

    private void addInsertParameters(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getPcode());
        statement.setString(2, product.getPname());
    }

    private String getSqlSelected() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_PRODUCT");
        sb.append("WHERE PCODE = ?");
        return sb.toString();
    }

    private void addParameterSelected(PreparedStatement statement, String pcode) throws SQLException {
        statement.setString(1, pcode);
    }

    private String getSqlDeleted() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM TB_PRODUCT ");
        sb.append("WHERE CODE = ?");
        return sb.toString();
    }

    private void addDeletedParameter(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getPcode());
    }

    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_PRODUCT ");
        sb.append("SET PNAME = ?, PCODE = ? ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    private void addUpdateParameters(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getPname());
        statement.setString(2, product.getPcode());
        statement.setLong(3, product.getId());
    }
    private String getSqlSelectedAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_PRODUCT");
        return sb.toString();
    }


    private void closeConnection(Connection connection, PreparedStatement statement, Object o) {
    }

    
}
