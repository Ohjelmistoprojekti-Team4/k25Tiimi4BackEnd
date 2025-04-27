package sprint1.sprint1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import sprint1.sprint1.domain.ClothingItem;
import sprint1.sprint1.domain.ClothingItemRepository;
import sprint1.sprint1.domain.ClothingSize;
import sprint1.sprint1.domain.Food;
import sprint1.sprint1.domain.FoodRepository;
import sprint1.sprint1.domain.Manufacturer;
import sprint1.sprint1.domain.ManufacturerRepository;
import sprint1.sprint1.domain.Role;
import sprint1.sprint1.domain.Toy;
import sprint1.sprint1.domain.ToyRepository;
import sprint1.sprint1.domain.Type;
import sprint1.sprint1.domain.User;
import sprint1.sprint1.domain.UserRepository;

@SpringBootApplication
public class Sprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sprint1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClothingItemRepository clothingItemRepository, ToyRepository toyRepository,
			FoodRepository foodRepository,
			ManufacturerRepository manufacturerRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		return (args) -> {

			if (manufacturerRepository.count() == 0 &&
					clothingItemRepository.count() == 0 &&
					toyRepository.count() == 0 &&
					foodRepository.count() == 0 &&
					userRepository.count() == 0) {

				System.out.println("Creating default data...");

				Manufacturer rukka = manufacturerRepository.save(new Manufacturer("Rukka"));
				Manufacturer reima = manufacturerRepository.save(new Manufacturer("Reima"));
				Manufacturer rekku = manufacturerRepository.save(new Manufacturer("Rekku"));

				clothingItemRepository
						.save(new ClothingItem("Jacket", 20.99, Type.CLOTHING, rukka, "Red", ClothingSize.M));
				clothingItemRepository
						.save(new ClothingItem("Raincoat", 29.99, Type.CLOTHING, reima, "Black", ClothingSize.S));
				clothingItemRepository
						.save(new ClothingItem("Collar", 18.99, Type.CLOTHING, rukka, "Pink", ClothingSize.L));

				toyRepository.save(new Toy("Rope Toy", 4.99, Type.TOY, rekku, "Cotton"));
				toyRepository.save(new Toy("KONG Puppy", 8.99, Type.TOY, rekku, "Rubber"));

				foodRepository.save(new Food("Crunchy Chicken Bites", 10.99, Type.FOOD, rekku, "Chicken"));
				foodRepository.save(new Food("Premium Salmon Delight", 14.99, Type.FOOD, rekku, "Salmon"));

				if (userRepository.findByUsername("admin").isEmpty()) {
					userRepository.save(new User(
							"admin@example.com", 
							"Admin",
							"User",
							"admin",
							passwordEncoder.encode("admin"),
							Role.ADMIN));
					System.out.println("Admin user created with username: admin and password: admin");
				}

				if (userRepository.findByUsername("user").isEmpty()) {
					userRepository.save(new User(
							"user@example.com",
							"Regular",
							"User",
							"user",
							passwordEncoder.encode("user"),
							Role.USER));
					System.out.println("User created with username: user and password: user");
				}

			}

		};
	}
}
