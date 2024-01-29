package marcos.ferrel.DAO.factory;

import marcos.ferrel.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class CustomerFactory {

    public static Customer convert(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("ID_CUSTOMER"));
        customer.setName(rs.getString("NAME"));
        customer.setCpf(rs.getLong("CPF"));
        customer.setPhoneNumber(rs.getLong("PHONENUMBER"));
        customer.setAddress(rs.getString("ADDRESS"));
        customer.setNumber(rs.getInt("NUMBER"));
        customer.setCity(rs.getString("CITY"));
        customer.setState(rs.getString("STATE"));
        customer.setCompanyEmployee("COMPANYEMPLOYEE");
        return customer;
    }
}
