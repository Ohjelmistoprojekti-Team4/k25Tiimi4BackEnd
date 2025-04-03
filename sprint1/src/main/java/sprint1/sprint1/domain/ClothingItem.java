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

    @Enumerated(EnumType.STRING)
    private Type type;

    private String color;
    private double price;

    
    // TODO: siirrä Vaate-entiteettiin
    @Enumerated(EnumType.STRING)
    private Size size;
    
    @ManyToOne
    @JsonIgnoreProperties ("clothes")
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    public ClothingItem() {}

    public ClothingItem(Type type, String color, Size size, double price, Manufacturer manufacturer) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        return "ClothingItem [clothingId=" + clothingId + ", type=" + type + ", color=" + color + ", price=" + price + ", manufacturer=" + manufacturer + "]";
    }

    
    
    



}
