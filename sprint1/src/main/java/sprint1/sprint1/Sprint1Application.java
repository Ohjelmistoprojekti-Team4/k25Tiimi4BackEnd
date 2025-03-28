package sprint1.sprint1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sprint1.sprint1.domain.ClothingItem;
import sprint1.sprint1.domain.ClothingItemRepository;
import sprint1.sprint1.domain.Manufacturer;
import sprint1.sprint1.domain.ManufacturerRepository;

@SpringBootApplication
public class Sprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sprint1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClothingItemRepository clothingItemRepository,
			ManufacturerRepository manufacturerRepository) {
				return (args) -> {
					Manufacturer rukka = manufacturerRepository.save(new Manufacturer("Rukka"));
					Manufacturer reima = manufacturerRepository.save(new Manufacturer("Reima"));
					Manufacturer nike = manufacturerRepository.save(new Manufacturer("Nike"));

					clothingItemRepository.save(new ClothingItem("Jacket", "Red", "M", 99.99, rukka));
					clothingItemRepository.save(new ClothingItem("Pants", "Blue", "L", 49.99, rukka));		
					clothingItemRepository.save(new ClothingItem("Collar", "Black", "S", 19.99, reima));
				};
		
	}

}
