package sprint1.sprint1.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long manufacturerId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Must be between 2-50 characters")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    @JsonIgnoreProperties ("manufacturer")
    private List<Product> products;

    public Manufacturer() {}

    public Manufacturer(String name) {
        super();
        this.name = name;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setClothes(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Manufacturer [manufacturerId=" + manufacturerId + ", name=" + name + "]";
    }

    

    



}
