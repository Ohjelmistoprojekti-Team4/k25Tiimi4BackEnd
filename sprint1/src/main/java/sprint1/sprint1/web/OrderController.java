package sprint1.sprint1.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sprint1.sprint1.domain.Order;
import sprint1.sprint1.domain.OrderRepository;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orderlist")
    public String showOrders(Model model) {
        List<Order> orders = orderRepository.findAllWithOrderProducts();
        model.addAttribute("orders", orders);
        System.out.println("Ladattiin tilauksia: " + orders.size());
        return "orderlist"; 
    }
    

}
