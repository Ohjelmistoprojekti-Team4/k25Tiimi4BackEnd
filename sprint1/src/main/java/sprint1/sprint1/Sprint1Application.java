package sprint1.sprint1;

import java.time.LocalDateTime;

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
import sprint1.sprint1.domain.Order;
import sprint1.sprint1.domain.OrderProduct;
import sprint1.sprint1.domain.OrderProductRepository;
import sprint1.sprint1.domain.OrderRepository;
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
            PasswordEncoder passwordEncoder, OrderRepository orderRepository,
            OrderProductRepository orderProductRepository) {
        return (args) -> {

            System.out.println("Creating default data...");

            Manufacturer rukka = manufacturerRepository.save(new Manufacturer("Rukka"));
            Manufacturer reima = manufacturerRepository.save(new Manufacturer("Reima"));
            Manufacturer rekku = manufacturerRepository.save(new Manufacturer("Rekku"));

            ClothingItem clothingItem1 = clothingItemRepository
                    .save(new ClothingItem("Jacket", 20.99, 3, Type.CLOTHING, rukka, "Red", ClothingSize.M));
            clothingItemRepository
                    .save(new ClothingItem("Raincoat", 29.99, 10, Type.CLOTHING, reima, "Black", ClothingSize.S));
            clothingItemRepository
                    .save(new ClothingItem("Collar", 18.99, 8, Type.CLOTHING, rukka, "Pink", ClothingSize.L));

            Toy toy1 = toyRepository.save(new Toy("Rope Toy", 4.99, 15, Type.TOY, rekku, "Cotton"));
            toyRepository.save(new Toy("KONG Puppy", 8.99, 22, Type.TOY, rekku, "Rubber"));

            Food food1 = foodRepository
                    .save(new Food("Crunchy Chicken Bites", 10.99, 20, Type.FOOD, rekku, "Chicken"));
            Food food2 = foodRepository
                    .save(new Food("Premium Salmon Delight", 14.99, 30, Type.FOOD, rekku, "Salmon"));

            userRepository.save(new User(
                    "admin@example.com",
                    "Esimerkki",
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin"),
                    Role.ADMIN));
            System.out.println("Admin user created with username: admin and password: admin");

            userRepository.save(new User(
                    "user@example.com",
                    "Regular",
                    "User",
                    "user",
                    passwordEncoder.encode("user"),
                    Role.USER));
            System.out.println("User created with username: user and password: user");

            User testUser = userRepository.save(new User(
                    "testi@example.com",
                    "Teppo",
                    "Testaaja",
                    "testikäyttäjä",
                    passwordEncoder.encode("password123"),
                    Role.USER));

            Order order1 = orderRepository.save(new Order(testUser, LocalDateTime.now()));
            Order order2 = orderRepository.save(new Order(testUser, LocalDateTime.now()));

            orderProductRepository.save(new OrderProduct(order1, toy1, 2));
            orderProductRepository.save(new OrderProduct(order1, clothingItem1, 1));
            orderProductRepository.save(new OrderProduct(order2, food1, 2));
            orderProductRepository.save(new OrderProduct(order2, food2, 1));

        };
    }
}
