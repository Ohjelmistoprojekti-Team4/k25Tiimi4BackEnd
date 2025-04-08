package sprint1.sprint1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "toyId")
public class Toy extends Product {

    @NotBlank(message = "Material is required")
    @Size(min = 2, max = 20, message = "Must be between 2-20 characters")
    private String material;

    public Toy() {}

    public Toy(String name, Double price, Type type, Manufacturer manufacturer, String material) {
        super(name, price, type, manufacturer);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString() + " Toy [material=" + material + "]";
    }

}
