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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long productId;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Type type; 
    
    @ManyToOne
    @JsonIgnoreProperties ("products")
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    public Product() {}

    public Product(String name, double price, Type type, Manufacturer manufacturer) {
        super();
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
