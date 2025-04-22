package sprint1.sprint1.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClothingItemRepository extends CrudRepository<ClothingItem, Long> {
    List<ClothingItem> findByManufacturer(Manufacturer manufacturer);
}
