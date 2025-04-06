package sprint1.sprint1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "toyId")
public class Toy extends Product {

    private String material;

    public Toy() {}

    public Toy(String name, double price, Type type, Manufacturer manufacturer, String material) {
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
