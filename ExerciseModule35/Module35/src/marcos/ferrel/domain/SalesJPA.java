package marcos.ferrel.domain;

import marcos.ferrel.dao.IPersistence;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author marcos.ferrel
 */
@Entity
@Table(name = "TB_SALES")
public class SalesJPA implements IPersistence {

    public enum Status {
        STARTED, CONCLUDED, CANCELED;

        public static Status getByName(String value) {
            for (Status status : Status.values()) {
                if (status.name().equals(value)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sales_seq")
    @SequenceGenerator(name="sales_seq", sequenceName="sq_sales", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODE", nullable = false, unique = true)
    private static String code;

    @ManyToOne
    @JoinColumn(name = "id_customer_fk",
        foreignKey = @ForeignKey(name = "fk_sales_customer"),
        referencedColumnName = "id", nullable = false)
    private CustomerJPA customerJPA;

    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL)
    private Set<QuantityProductJPA> products;

    @Column(name = "TOTAL_VALUE", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "SALES_DATE", nullable = false)
    private Instant salesDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALES_STATUS", nullable = false)
    private Status status;

    public SalesJPA() {
        products = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CustomerJPA getCustomerJPA() {
        return customerJPA;
    }

    public void setCustomerJPA(CustomerJPA customerJPA) {
        this.customerJPA = customerJPA;
    }

    public Set<QuantityProductJPA> getProducts() {
        return products;
    }

    public void setProducts(Set<QuantityProductJPA> products) {
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

    public void addProduct(ProductJPA product, Integer quantity) {
        validStatus();
        Optional<QuantityProductJPA> opt =
                products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();
        if (opt.isPresent()) {
            QuantityProductJPA quantityProduct = opt.get();
            quantityProduct.addQtd(quantity);
        } else {
            QuantityProductJPA productJPA = new QuantityProductJPA();
            productJPA.setSales(this);
            productJPA.setProduct(product);
            productJPA.addQtd(quantity);
            products.add(productJPA);
        }
        recalculateTotalSales();
    }

    private void validStatus() {
        if (this.status == Status.CONCLUDED) {
            throw new UnsupportedOperationException("Impossible to update a concluded sales!!!");
        }
    }

    public void removeProduct(ProductJPA product, Integer quantity) {
        validStatus();
        Optional<QuantityProductJPA> opt =
                products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();
        if (opt.isPresent()) {
            QuantityProductJPA quantityProduct = opt.get();
            if(quantityProduct.getQuantity() > quantity) {
                quantityProduct.removeQtd(quantity);
                recalculateTotalSales();
            } else {
                products.remove(quantity);
                recalculateTotalSales();
            }
        }
    }

    public void removeAllProducts() {
        validStatus();
        products.clear();
        totalValue = BigDecimal.ZERO;
    }

    public void recalculateTotalSales() {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (QuantityProductJPA product : this.products) {
            totalValue = totalValue.add(product.getTotalValue());
        }
        this.totalValue = totalValue;
    }

    public Integer getProductQuantityTotal() {
        int result = products.stream()
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
        return result;
    }

}
