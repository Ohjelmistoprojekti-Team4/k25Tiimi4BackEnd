package sprint1.sprint1.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderProducts op LEFT JOIN FETCH op.product")
    List<Order> findAllWithOrderProducts();





}
