package marcos.ferrel.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author marcos.ferrel
 *
 */
@Entity
@Table(name = "TB_SALES")
public class SalesJpa implements IPersistence {

    public enum Status {
        STARTED, CONCLUDED, CANCELED;

        public static Status getByName(String value) {
            for (Status status : Status.values()) {
                if(status.name().equals(value)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="venda_seq")
    @SequenceGenerator(name="venda_seq", sequenceName="sq_venda", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "id_customer_fk",
            foreignKey = @ForeignKey(name = "fk_sales_customer"),
            referencedColumnName = "id", nullable = false)
    private CustomerJpa customerJpa;

    @OneToMany(mappedBy = "SALES", cascade = CascadeType.ALL)
    private Set<QuantityProductJpa> products;

    @Column(name = "TOTAL_VALUE", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "SALES_DATE", nullable = false)
    private Instant salesDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALES_STATUS", nullable = false)
    private Status status;

    public SalesJpa() {
        products = new HashSet<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CustomerJpa getCustomerJpa() {
        return customerJpa;
    }

    public void setCustomerJpa(CustomerJpa customerJpa) {
        this.customerJpa = customerJpa;
    }

    public Set<QuantityProductJpa> getProducts() {
        return products;
    }

    public void setProducts(Set<QuantityProductJpa> products) {
        this.products = products;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Instant getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Instant salesDate) {
        this.salesDate = salesDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addProduct(ProductJpa productJpa, Integer quantity) {
        validStatus();
        Optional<QuantityProductJpa> opt =
                products.stream().filter(filter -> filter.getProductJpa().getCode().equals(productJpa.getCode())).findAny();
        if (opt.isPresent()) {
            QuantityProductJpa quantityProductJpa = opt.get();
            quantityProductJpa.addQuantity(quantity);
        } else {
            QuantityProductJpa quantityProductJpa1 = new QuantityProductJpa();
            quantityProductJpa1.setSalesJpa(this);
            quantityProductJpa1.setProductJpa(productJpa);
            quantityProductJpa1.addQuantity(quantity);
            products.add(quantityProductJpa1);
        }
        recalculateTotalSales();
    }

    public void removeProduct(ProductJpa productJpa, Integer quantity) {
        validStatus();
        Optional<QuantityProductJpa> opt =
                products.stream().filter(filter -> filter.getProductJpa().getCode().equals(productJpa.getCode())).findAny();
        if (opt.isPresent()) {
            QuantityProductJpa quantityProductJpa = opt.get();
            if (quantityProductJpa.getQuantity() > quantity) {
                quantityProductJpa.removeQuantity(quantity);
                recalculateTotalSales();
            } else {
                products.remove(opt.get());
                recalculateTotalSales();
            }
        }
    }

    public void removeAllProducts() {
        validStatus();
        products.clear();
        totalValue = BigDecimal.ZERO;
    }

    public Integer getTotalProductsQuantity() {
        int result = products.stream()
                .reduce(0, (partialCountResult, product) -> partialCountResult + product.getQuantity(), Integer::sum);
        return result;
    }

    private void validStatus() {
        if (this.status == Status.CONCLUDED) {
            throw new UnsupportedOperationException("IMPOSSIBLE TO UPDATE FINISHED SALES!!!");
        }
    }

    private void recalculateTotalSales() {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (QuantityProductJpa product : this.products) {
            totalValue = totalValue.add(product.getTotalValue());
        }
        this.totalValue = totalValue;
    }

}
