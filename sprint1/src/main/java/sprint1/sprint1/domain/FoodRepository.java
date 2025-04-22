package sprint1.sprint1.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Long> {
    List<Food> findByManufacturer(Manufacturer manufacturer);
}
