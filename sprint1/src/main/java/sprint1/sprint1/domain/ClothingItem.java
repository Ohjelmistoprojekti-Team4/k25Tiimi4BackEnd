package sprint1.sprint1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "clothingId")
public class ClothingItem extends Product {

    private String color;

    @Enumerated(EnumType.STRING)
    private Size size;
    
    public ClothingItem() {}

    public ClothingItem(String name, double price, Type type, Manufacturer manufacturer, String color, Size size) {
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + "ClothingItem [color=" + color + ", size=" + size + "]";
    }

    

    

}