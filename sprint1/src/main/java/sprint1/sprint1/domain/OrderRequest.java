package sprint1.sprint1.domain;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {
    private Long userId;
    private LocalDateTime orderDate;
    private List<OrderProductDTO> products;

    // Getterit ja setterit
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public List<OrderProductDTO> getProducts() {
        return products;
    }
    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }
}

