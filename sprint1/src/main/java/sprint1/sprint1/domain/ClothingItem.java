package sprint1.sprint1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "clothingId")
public class ClothingItem extends Product {

    @NotBlank(message = "Color is required")
    @Size(min = 2, max = 20, message = "Must be between 2-20 characters")
    private String color;

    @Enumerated(EnumType.STRING)
    private ClothingSize size;
    
    public ClothingItem() {}

    public ClothingItem(String name, Double price, Type type, Manufacturer manufacturer, String color, ClothingSize size) {
        super(name, price, type, manufacturer);
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ClothingSize getSize() {
        return size;
    }

    public void setSize(ClothingSize size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + "ClothingItem [color=" + color + ", size=" + size + "]";
    }

}