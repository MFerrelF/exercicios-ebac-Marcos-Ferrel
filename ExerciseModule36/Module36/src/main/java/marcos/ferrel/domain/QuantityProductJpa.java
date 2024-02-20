package marcos.ferrel.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author marcos.ferrel
 */
public class QuantityProductJpa {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="quantity_product_seq")
    @SequenceGenerator(name="quantity_product_seq", sequenceName="sq_quantity_product", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductJpa productJpa;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "TOTAL_VALUE", nullable = false)
    private BigDecimal totalValue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sales_fk",
            foreignKey = @ForeignKey(name = "fk_quantity_prooduct_sales"),
            referencedColumnName = "id", nullable = false)
    private SalesJpa salesJpa;

    public QuantityProductJpa() {
        this.quantity = 0;
        this.totalValue = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductJpa getProductJpa() {
        return productJpa;
    }

    public void setProductJpa(ProductJpa productJpa) {
        this.productJpa = productJpa;
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

    public SalesJpa getSalesJpa() {
        return salesJpa;
    }

    public void setSalesJpa(SalesJpa salesJpa) {
        this.salesJpa = salesJpa;
    }

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
        BigDecimal newPrice = this.productJpa.getPrice().multiply(BigDecimal.valueOf(quantity));
        BigDecimal newTotal = this.totalValue.add(newPrice);
        this.totalValue = newTotal;
    }

    public void removeQuantity(Integer quantity) {
        this.quantity -= quantity;
        BigDecimal newPrice = this.productJpa.getPrice().multiply(BigDecimal.valueOf(quantity));
        this.totalValue = this.totalValue.subtract(newPrice);
    }

}
