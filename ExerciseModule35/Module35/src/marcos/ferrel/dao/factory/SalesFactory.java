package marcos.ferrel.dao.factory;

import marcos.ferrel.domain.CustomerJPA;
import marcos.ferrel.domain.SalesJPA;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class SalesFactory {

    public static SalesJPA convert(ResultSet rs) throws SQLException {
        CustomerJPA customer = CustomerFactory.convert(rs);
        SalesJPA sales = new SalesJPA();
        sales.setCustomerJPA(customer);
        sales.setId(rs.getLong("ID_SALES"));
        sales.setCode(rs.getString("CODE"));
        sales.setTotalValue(rs.getBigDecimal("TOTAL_VALUE"));
        sales.setSalesDate(rs.getTimestamp("SALES_DATE").toInstant());
        sales.setStatus(SalesJPA.Status.getByName(rs.getString("SALES_STATUS")));
        return sales;
    }

}
