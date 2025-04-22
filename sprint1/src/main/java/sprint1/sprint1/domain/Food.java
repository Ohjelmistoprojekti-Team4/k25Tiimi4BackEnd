package sprint1.sprint1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "foodId")
public class Food extends Product {

    @NotBlank(message = "Flavor is required")
    @Size(min = 2, max = 30, message = "Must be between 2-30 characters")
    private String flavor;

    public Food() {}

    public Food(String name, Double price, Type type, Manufacturer manufacturer, String flavor) {
        super(name, price, type, manufacturer);
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return super.toString() + "Food [flavor=" + flavor + "]";
    }
    
}
