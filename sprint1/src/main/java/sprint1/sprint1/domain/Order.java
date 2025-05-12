package sprint1.sprint1.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties("orders")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonIgnoreProperties("order")

    private List<OrderProduct> orderProducts;
  
    private LocalDateTime orderDate;

    public Order() {
    }

    public Order(User user, LocalDateTime orderDate) {

        this.user = user;
        this.orderDate = orderDate;
        this.orderProducts = new ArrayList<>(); // alustetaan lista myös tässä
    }

    // getters & setters are made automatically by Lombok

}
