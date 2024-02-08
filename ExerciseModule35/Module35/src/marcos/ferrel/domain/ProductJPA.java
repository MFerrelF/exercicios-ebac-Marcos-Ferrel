package marcos.ferrel.domain;

import marcos.ferrel.dao.IPersistence;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author marcos.ferrel
 */
@Entity
@Table(name = "TB_PROODUCT")
public class ProductJPA implements IPersistence {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="product_seq")
    @SequenceGenerator(name="product_seq", sequenceName="sq_product", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "CODE", nullable = false, length = 10, unique = true)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false, length = 50)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
