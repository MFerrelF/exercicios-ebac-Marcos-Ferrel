package marcos.ferrel.dao.factory;

import marcos.ferrel.domain.ProductJPA;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class ProductFactory {

    public static ProductJPA convert(ResultSet rs) throws SQLException {
        ProductJPA product = new ProductJPA();
        product.setId(rs.getLong("ID_PRODUCT"));
        product.setCode(rs.getString("CODE"));
        product.setName(rs.getString("NAME"));
        product.setDescription(rs.getString("DESCRIPTION"));
        product.setPrice(rs.getBigDecimal("PRICE"));
        return product;
    }

}
