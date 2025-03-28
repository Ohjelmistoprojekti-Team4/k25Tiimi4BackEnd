package sprint1.sprint1.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long manufacturerId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    @JsonIgnoreProperties ("manufacturer")
    private List<ClothingItem> clothes;

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

    public List<ClothingItem> getClothes() {
        return clothes;
    }

    public void setClothes(List<ClothingItem> clothes) {
        this.clothes = clothes;
    }

    @Override
    public String toString() {
        return "Manufacturer [manufacturerId=" + manufacturerId + ", name=" + name + "]";
    }

    

    



}
