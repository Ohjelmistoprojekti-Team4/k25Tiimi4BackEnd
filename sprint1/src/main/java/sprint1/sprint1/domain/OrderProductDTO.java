package sprint1.sprint1.domain;

public class OrderProductDTO {
    private Long productId;
    private Integer orderQuantity;

    // Getterit ja setterit
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Integer getOrderQuantity() {
        return orderQuantity;
    }
    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
