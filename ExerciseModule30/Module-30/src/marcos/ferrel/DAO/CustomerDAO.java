package marcos.ferrel.DAO;

import marcos.ferrel.DAO.generics.GenericDAO;
import marcos.ferrel.domain.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class CustomerDAO extends GenericDAO<Customer, Long> implements ICustomerDAO{

    public CustomerDAO() {
        super();
    }

    @Override
    public Class<Customer> getClassType() {
        return Customer.class;
    }

    @Override
    public void updateData(Customer entity, Customer registeredEntity) {
        registeredEntity.setName(entity.getName());
        registeredEntity.setCpf(entity.getCpf());
        registeredEntity.setPhoneNumber(entity.getPhoneNumber());
        registeredEntity.setAddress(entity.getAddress());
        registeredEntity.setNumber(entity.getNumber());
        registeredEntity.setCity(entity.getCity());
        registeredEntity.setState(entity.getState());
        registeredEntity.setCompanyEmployee(entity.getCompanyEmployee());
    }

    @Override
    protected String getInsertedQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_CUSTOMER ");
        sb.append("(ID, NOME, CPF, TEL, ENDERECO, NUMERO, CIDADE, ESTADO, COMPANYEMPLOYEE)");
        sb.append("VALUES (nextval('sq_cliente'),?,?,?,?,?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected String getRemovedQuery() {
        return "DELETE FROM TB_CUSTOMER WHERE CPF = ?";
    }

    @Override
    protected String getUpdatedQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_CUSTOMER ");
        sb.append("SET NAME = ?,");
        sb.append("PHONENUMBER = ?,");
        sb.append("ADDRESS = ?,");
        sb.append("NUMBER = ?,");
        sb.append("CITY = ?,");
        sb.append("STATE = ?");
        sb.append("COMPANYEMPLOYEE = ?");
        sb.append(" WHERE CPF = ?");
        return sb.toString();
    }

    @Override
    protected void setInsertedQueryParameters(PreparedStatement insertStatement, Customer entity) throws SQLException {
        insertStatement.setString(1, entity.getName());
        insertStatement.setLong(2, entity.getCpf());
        insertStatement.setLong(3, entity.getPhoneNumber());
        insertStatement.setString(4, entity.getAddress());
        insertStatement.setLong(5, entity.getNumber());
        insertStatement.setString(6, entity.getCity());
        insertStatement.setString(7, entity.getState());
        insertStatement.setString(8, entity.getCompanyEmployee());
    }

    @Override
    protected void setRemovedQueryParameters(PreparedStatement deleteStatement, Long value) throws SQLException {
        deleteStatement.setLong(1, value);
    }

    @Override
    protected void setUpdatedQueryParameters(PreparedStatement updateStatement, Customer entity) throws SQLException {
        updateStatement.setString(1, entity.getName());
        updateStatement.setLong(2, entity.getPhoneNumber());
        updateStatement.setString(3, entity.getAddress());
        updateStatement.setLong(4, entity.getNumber());
        updateStatement.setString(5, entity.getCity());
        updateStatement.setString(6, entity.getState());
        updateStatement.setLong(7, entity.getCpf());
        updateStatement.setString(8, entity.getCompanyEmployee());
    }

    @Override
    protected void setSelectedQueryParameters(PreparedStatement selectStatement, Long value) throws SQLException {
        selectStatement.setLong(1, value);
    }
}
