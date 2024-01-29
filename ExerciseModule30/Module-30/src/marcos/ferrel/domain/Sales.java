package marcos.ferrel.domain;

import annotations.KeyType;
import annotations.Table;
import annotations.TableColumn;
import marcos.ferrel.DAO.SalesInter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author marcos.ferrel
 */
@Table("TB_SALES")
public class Sales implements SalesInter {

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

    @TableColumn(dbName = "id", setJavaName = "setId")
    private Long id;

    @KeyType("getCode")
    @TableColumn(dbName = "code", setJavaName = "setCode")
    private String code;

    @TableColumn(dbName = "id_customer_fk", setJavaName = "setIdCustomerFk")
    private Customer customer;

    private Set<QuantityProduct> products;

    @TableColumn(dbName = "total_value", setJavaName = "setTotalValue")
    private BigDecimal totalValue;

    @TableColumn(dbName = "sales_date", setJavaName = "setSalesDate")
    private Instant salesDate;

    @TableColumn(dbName = "sales_status", setJavaName = "setSalesStatus")
    private Status status;

    public Sales() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<QuantityProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<QuantityProduct> products) {
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

    public void addProduct(Product product, Integer quantity) {
        validStatus();
        Optional<QuantityProduct> opt =
                products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();
        if (opt.isPresent()) {
            QuantityProduct quantityProduct = opt.get();
            quantityProduct.plus(quantity);
        } else {
            QuantityProduct prod = new QuantityProduct();
            prod.setProduct(product);
            prod.plus(quantity);
            products.add(prod);
        }
        recalculateTotalSales();
    }

    private void validStatus() {
        if (this.status == Status.CONCLUDED) {
            throw new UnsupportedOperationException("Not allowed to update concluded sales");
        }
    }

    public void removeProduct(Product product, Integer quantity) {
        validStatus();
        Optional<QuantityProduct> opt =
                products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();
        if (opt.isPresent()) {
            QuantityProduct quantityProduct = opt.get();
            if (quantityProduct.getQuantity() > quantity) {
                quantityProduct.remove(quantity);
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
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
        return result;
    }

    public void recalculateTotalSales() {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (QuantityProduct prod : this.products) {
            totalValue = totalValue.add(prod.getTotalValue());
        }
        this.totalValue = totalValue;
    }

}
