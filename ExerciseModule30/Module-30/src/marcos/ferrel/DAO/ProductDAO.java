package marcos.ferrel.DAO;

import marcos.ferrel.DAO.generics.GenericDAO;
import marcos.ferrel.domain.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class ProductDAO extends GenericDAO<Product, String> implements IProductDAO {

    public ProductDAO() {
        super();
    }

    @Override
    public Class<Product> getClassType() {
        return Product.class;
    }

    @Override
    public void updateData(Product entity, Product registeredEntity) {
        registeredEntity.setCode(entity.getCode());
        registeredEntity.setName(entity.getName());
        registeredEntity.setDescription(entity.getDescription());
        registeredEntity.setValue(entity.getValue());
    }

    @Override
    protected String getInsertedQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_PRODUCT");
        sb.append("(ID, CODE, NAME, DESCRIPTION, VALUE)");
        sb.append("VALUES (nextval('sq_product'),?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected String getRemovedQuery() {
        return "DELETE FROM TB_PRODUCT WHERE CODE = ?";
    }

    @Override
    protected String getUpdatedQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_PRODUCT");
        sb.append("SET CODE = ?,");
        sb.append("NAME = ?,");
        sb.append("DESCRIPTION = ?,");
        sb.append("VALUE = ?,");
        sb.append("WHERE CODE = ?");
        return sb.toString();
    }

    @Override
    protected void setInsertedQueryParameters(PreparedStatement insertStatement, Product entity) throws SQLException {
        insertStatement.setString(1, entity.getCode());
        insertStatement.setString(2, entity.getName());
        insertStatement.setString(3, entity.getDescription());
        insertStatement.setBigDecimal(4, entity.getValue());
    }

    @Override
    protected void setRemovedQueryParameters(PreparedStatement deleteStatement, String value) throws SQLException {
        deleteStatement.setString(1, value);

    }

    @Override
    protected void setUpdatedQueryParameters(PreparedStatement updateStatement, Product entity) throws SQLException {
        updateStatement.setString(1, entity.getCode());
        updateStatement.setString(2, entity.getName());
        updateStatement.setString(3, entity.getDescription());
        updateStatement.setBigDecimal(4, entity.getValue());
        updateStatement.setString(5, entity.getCode());
    }

    @Override
    protected void setSelectedQueryParameters(PreparedStatement deleteStatement, String value) throws SQLException {
        deleteStatement.setString(1, value);
    }
}
