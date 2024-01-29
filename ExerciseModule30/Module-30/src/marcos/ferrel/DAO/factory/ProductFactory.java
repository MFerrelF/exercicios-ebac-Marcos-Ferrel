package marcos.ferrel.DAO.factory;

import marcos.ferrel.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class ProductFactory {

    public static Product convert(ResultSet rs) throws SQLException {
        Product prod = new Product();
        prod.setId(rs.getLong("ID_PRODUCT"));
        prod.setCode(rs.getString("CODE"));
        prod.setName(rs.getString("NAME"));
        prod.setDescription(rs.getString("DESCRIPTION"));
        prod.setValue(rs.getBigDecimal("VALUE"));
        return prod;
    }

}
