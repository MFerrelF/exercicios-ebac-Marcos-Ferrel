package marcos.ferrel.dao.factory;

import marcos.ferrel.domain.ProductJPA;
import marcos.ferrel.domain.QuantityProductJPA;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class QuantityProductFactory {

    public static QuantityProductJPA convert(ResultSet rs) throws SQLException {
        ProductJPA product = ProductFactory.convert(rs);
        QuantityProductJPA prodQ = new QuantityProductJPA();
        prodQ.setProduct(product);
        prodQ.setId(rs.getLong("ID"));
        prodQ.setQuantity(rs.getInt("QUANTITY"));
        prodQ.setTotalValue(rs.getBigDecimal("TOTAL_VALUE"));
        return prodQ;
    }

}
