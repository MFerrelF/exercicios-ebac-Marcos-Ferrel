package marcos.ferrel.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author marcos.ferrel
 */
@Entity
@Table(name = "TB_QUANTITY_PRODUCT")
public class QuantityProductJPA {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sales_seq")
    @SequenceGenerator(name="sales_seq", sequenceName="sq_sales", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductJPA product;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "TOTAL_VALUE", nullable = false)
    private BigDecimal totalValue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sales_fk",
        foreignKey = @ForeignKey(name = "fk_prodcut_quantity_sales"),
        referencedColumnName = "id", nullable = false)
    private SalesJPA sales;

    public QuantityProductJPA() {
        this.quantity = 0;
        this.totalValue = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductJPA getProduct() {
        return product;
    }

    public void setProduct(ProductJPA product) {
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

    public SalesJPA getSales() {
        return sales;
    }

    public void setSales(SalesJPA sales) {
        this.sales = sales;
    }

    public void addQtd(Integer quantity) {
        this.quantity += quantity;
        BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
        BigDecimal newTotal = this.totalValue.add(newValue);
        this.totalValue = newTotal;
    }

    public void removeQtd(Integer quantity) {
        this.quantity -= quantity;
        BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
        this.totalValue = this.totalValue.subtract(newValue);
    }

}
