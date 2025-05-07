package sprint1.sprint1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_product")
@Getter
@Setter

public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonIgnoreProperties("orderProducts")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonIgnoreProperties("orderProducts")
    private Product product;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer orderQuantity;

    public OrderProduct() {}

    public OrderProduct(Order order, Product product, Integer orderQuantity) {
        this.order = order;
        this.product = product;
        this.orderQuantity = orderQuantity;
    }

    //getters & setters  are made automatically by Lombok
}
