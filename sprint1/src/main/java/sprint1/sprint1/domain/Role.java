package sprint1.sprint1.domain;

public enum Role {

    ADMIN("Admin"),
    USER("User");

    private final String displayValue;

    private Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
