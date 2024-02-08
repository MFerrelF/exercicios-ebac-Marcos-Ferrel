package marcos.ferrel.dao.factory;

import marcos.ferrel.domain.CustomerJPA;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class CustomerFactory {

    public static CustomerJPA convert(ResultSet rs) throws SQLException {
        CustomerJPA customer = new CustomerJPA();
        customer.setId(rs.getLong("ID_CUSTOMER"));
        customer.setName(rs.getString(("NAME")));
        customer.setCpf(rs.getLong(("CPF")));
        customer.setPhoneNumber(rs.getLong(("PHONE_NUMBER")));
        customer.setAddress(rs.getString(("ADDRESS")));
        customer.setNumber(rs.getInt(("NUMBER")));
        customer.setCity(rs.getString(("CITY")));
        customer.setState(rs.getString(("STATE")));
        return customer;
    }

}
