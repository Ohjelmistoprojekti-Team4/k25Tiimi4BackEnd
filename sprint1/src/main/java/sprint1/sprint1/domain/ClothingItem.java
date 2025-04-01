package sprint1.sprint1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ClothingItem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long clothingId;
    private String type;
    private String color;
    private double price;

    @Enumerated(EnumType.STRING)
    private Size size;
    
    @ManyToOne
    @JsonIgnoreProperties ("clothes")
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    public ClothingItem() {}

    public ClothingItem(String type, String color, Size size, double price,
            Manufacturer manufacturer) {
        super();
        this.type = type;
        this.color = color;
        this.size = size;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Long getClothingId() {
        return clothingId;
    }

    public void setClothingId(Long clothingId) {
        this.clothingId = clothingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "ClothingItem [clothingId=" + clothingId + ", type=" + type + ", color=" + color + ", size=" + size
                + ", price=" + price + ", manufacturer=" + manufacturer + "]";
    }

    
    
    



}
