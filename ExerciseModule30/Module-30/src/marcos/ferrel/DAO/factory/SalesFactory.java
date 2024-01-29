package marcos.ferrel.DAO.factory;

import marcos.ferrel.domain.Customer;
import marcos.ferrel.domain.Sales;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class SalesFactory {

    public static Sales convert(ResultSet rs) throws SQLException {
        Customer customer = CustomerFactory.convert(rs);
        Sales sales = new Sales();
        sales.setCustomer(customer);
        sales.setId(rs.getLong("ID_SALES"));
        sales.setCode("CODE");
        sales.setTotalValue(rs.getBigDecimal("TOTAL_VALUE"));
        sales.setSalesDate(rs.getTimestamp("SALES_DATE").toInstant());
        sales.setStatus(Sales.Status.getByName(rs.getString("SALES_STATUS")));
        return sales;
    }

}
