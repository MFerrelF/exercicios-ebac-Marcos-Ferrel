package marcos.ferrel.domain;

import javax.persistence.*;

/**
 * @author marcos.ferrel
 */
@Entity
@Table(name = "TB_CAR")
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="car_seq")
    @SequenceGenerator(name="car_seq", sequenceName="sq_car", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODE", length = 10, nullable = false, unique = true)
    private String code;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_brand_fk",
        foreignKey = @ForeignKey(name = "fk_brand_car"),
        referencedColumnName = "id", nullable = false)
    private Brand brand;

    @OneToOne(mappedBy = "car")
    private Accessories accessories;

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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }
}
