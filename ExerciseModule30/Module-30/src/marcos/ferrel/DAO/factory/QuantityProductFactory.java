package marcos.ferrel.DAO.factory;

import marcos.ferrel.domain.Product;
import marcos.ferrel.domain.QuantityProduct;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class QuantityProductFactory {

    public static QuantityProduct convert(ResultSet rs) throws SQLException {
        Product prod = ProductFactory.convert(rs);
        QuantityProduct prodQ = new QuantityProduct();
        prodQ.setProduct(prod);
        prodQ.setId(rs.getLong("ID"));
        prodQ.setQuantity(rs.getInt("QUANTIDADE"));
        prodQ.setTotalValue(rs.getBigDecimal("VALOR_TOTAL"));
        return prodQ;
    }

}
