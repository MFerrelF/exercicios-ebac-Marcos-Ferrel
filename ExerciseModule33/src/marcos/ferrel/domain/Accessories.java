package marcos.ferrel.domain;

import javax.persistence.*;

/**
 * @author marcos.ferrel
 */
@Entity
@Table(name = "TB_ACCESSORIES")
public class Accessories {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="course_seq")
    @SequenceGenerator(name="course_seq", sequenceName="sq_course", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODE", length = 10, nullable = false, unique = true)
    private String code;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;

    @Column(name = "PRICE", length = 50, nullable = false)
    private Double price;

    @OneToOne
    @JoinColumn(name = "id_car_fk",
        foreignKey = @ForeignKey(name = "fk_car_accessories"),
        referencedColumnName = "id", nullable = false)
    private Car car;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
