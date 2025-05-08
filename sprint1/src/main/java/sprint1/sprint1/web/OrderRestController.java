package sprint1.sprint1.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sprint1.sprint1.domain.Order;
import sprint1.sprint1.domain.OrderProduct;
import sprint1.sprint1.domain.OrderProductDTO;
import sprint1.sprint1.domain.OrderRepository;
import sprint1.sprint1.domain.OrderRequest;
import sprint1.sprint1.domain.Product;
import sprint1.sprint1.domain.ProductRepository;
import sprint1.sprint1.domain.User;
import sprint1.sprint1.domain.UserRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderRestController(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        // 1. Find the user
        Optional<User> userOpt = userRepository.findById(orderRequest.getUserId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();

        // 2. Creates new order
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        // 3. Create OrderProduct-list of DTOs
        List<OrderProduct> orderProducts = new ArrayList<>();

        for (OrderProductDTO dto : orderRequest.getProducts()) {
            Optional<Product> productOpt = productRepository.findById(dto.getProductId());
            if (productOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Product not found: ID " + dto.getProductId());
            }

            Product product = productOpt.get();

            if (product.getAmount() < dto.getOrderQuantity()) {
                return ResponseEntity.badRequest().body("Not enough stock for product: " + product.getName());
            }

            product.setAmount(product.getAmount() - dto.getOrderQuantity());

            productRepository.save(product);

            // Create OrderProduct and add it to list
            OrderProduct op = new OrderProduct();
            op.setOrder(order);
            op.setProduct(product);
            op.setOrderQuantity(dto.getOrderQuantity());

            orderProducts.add(op);
        }

        // 4. Add ordered products to order
        order.setOrderProducts(orderProducts);

        // 5. Save order (cascade saves OrderProducts also)
        Order savedOrder = orderRepository.save(order);

        return ResponseEntity.ok(savedOrder);
    }
}




