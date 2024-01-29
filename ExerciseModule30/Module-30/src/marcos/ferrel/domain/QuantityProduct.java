package marcos.ferrel.domain;

import annotations.Table;
import annotations.TableColumn;

import java.math.BigDecimal;

/**
 * @author marcos.ferrel
 */
@Table("TB_QUANTITY_PRODUCT")
public class QuantityProduct {

    @TableColumn(dbName = "id", setJavaName = "setId")
    private Long id;

    private Product product;

    @TableColumn(dbName = "quantity", setJavaName = "setId")
    private Integer quantity;

    @TableColumn(dbName = "total_value", setJavaName = "setTotalValue")
    private BigDecimal totalValue;

    public QuantityProduct() {
        this.quantity = 0;
        this.totalValue = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public void plus(Integer quantity) {
        this.quantity += quantity;
        BigDecimal newValue = this.product.getValue().multiply(BigDecimal.valueOf(quantity));
        BigDecimal newTotal = this.totalValue.add(newValue);
        this.totalValue = newTotal;
    }

    public void remove(Integer quantity) {
        this.quantity -= quantity;
        BigDecimal newValue = this.product.getValue().multiply(BigDecimal.valueOf(quantity));
        this.totalValue = this.totalValue.subtract(newValue);
    }

}
