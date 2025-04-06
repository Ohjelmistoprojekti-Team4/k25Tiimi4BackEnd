package sprint1.sprint1.domain;

public enum Type {
    TOY("Toy"),
    CLOTHING("Clothing");

    private final String displayValue;
    
    private Type(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
