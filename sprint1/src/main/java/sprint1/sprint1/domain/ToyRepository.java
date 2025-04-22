package sprint1.sprint1.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ToyRepository extends CrudRepository<Toy, Long> {
    List<Toy> findByManufacturer(Manufacturer manufacturer);
}
