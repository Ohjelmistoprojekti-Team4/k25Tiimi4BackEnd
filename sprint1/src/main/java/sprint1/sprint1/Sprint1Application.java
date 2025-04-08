package sprint1.sprint1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sprint1.sprint1.domain.ClothingItem;
import sprint1.sprint1.domain.ClothingItemRepository;
import sprint1.sprint1.domain.ClothingSize;
import sprint1.sprint1.domain.Manufacturer;
import sprint1.sprint1.domain.ManufacturerRepository;
import sprint1.sprint1.domain.Toy;
import sprint1.sprint1.domain.ToyRepository;
import sprint1.sprint1.domain.Type;

@SpringBootApplication
public class Sprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sprint1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClothingItemRepository clothingItemRepository, ToyRepository toyRepository,
			ManufacturerRepository manufacturerRepository) {
				return (args) -> {
					Manufacturer rukka = manufacturerRepository.save(new Manufacturer("Rukka"));
					Manufacturer reima = manufacturerRepository.save(new Manufacturer("Reima"));
					Manufacturer rekku = manufacturerRepository.save(new Manufacturer("Rekku"));

					clothingItemRepository.save(new ClothingItem("Jacket", 20.99, Type.CLOTHING, rukka, "Red", ClothingSize.M));
					clothingItemRepository.save(new ClothingItem("Raincoat", 29.99, Type.CLOTHING, reima, "Black", ClothingSize.S));
					clothingItemRepository.save(new ClothingItem("Collar", 18.99, Type.CLOTHING, rukka, "Pink", ClothingSize.L));

					toyRepository.save(new Toy("Köysilelu", 4.99, Type.TOY, rekku, "Cotton"));
					toyRepository.save(new Toy("KONG Puppy", 8.99, Type.TOY, rekku, "Rubber"));
					
				};
		
	}

}
