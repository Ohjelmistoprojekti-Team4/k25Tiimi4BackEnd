package sprint1.sprint1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long productId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Must be between 2-50 characters")
    @Column(name="name")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be higher than 0")
    @DecimalMax(value = "10000.00", message = "Price must be lower than 10 000")
    @Column(name="price")
    private Double price;

    @Min(value = 0, message = "The value must be positive")
    @Column(name="amount")
    private Integer amount;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JsonIgnoreProperties ("products")
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    public Product() {}

    public Product(String name, Double price, Integer amount, Type type, Manufacturer manufacturer) {
        super();
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", type=" + type
                + ", manufacturer=" + manufacturer + "]";
    }

}
