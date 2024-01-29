package marcos.ferrel.domain;

import annotations.KeyType;
import annotations.Table;
import annotations.TableColumn;
import marcos.ferrel.DAO.SalesInter;

import java.math.BigDecimal;

/**
 * @author marcos.ferrel
 */
@Table("TB_PRODUCT")
public class Product implements SalesInter {

    @TableColumn(dbName = "id", setJavaName = "setId")
    private Long id;

    @KeyType("getCode")
    @TableColumn(dbName = "code", setJavaName = "setCode")
    private String code;

    @TableColumn(dbName = "name", setJavaName = "setName")
    private String name;

    @TableColumn(dbName = "description", setJavaName = "setDescription")
    private String description;

    @TableColumn(dbName = "value", setJavaName = "setValue")
    private BigDecimal value;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
