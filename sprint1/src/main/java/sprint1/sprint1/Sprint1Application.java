package sprint1.sprint1;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            OrderRepository orderRepository,
            OrderProductRepository orderProductRepository) {
        return (args) -> {

            // only add test data if database is empty
            if (manufacturerRepository.count() == 0
                    && clothingItemRepository.count() == 0
                    && toyRepository.count() == 0
                    && foodRepository.count() == 0
                    && userRepository.count() == 0
                    && orderRepository.count() == 0
                    && orderProductRepository.count() == 0) {

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
                        "$2a$10$JD0ge/0wH26.WRos4d.u7uyptCaP2cOspQM/BaMsZ9XS8i/L1mfwC",
                        Role.ADMIN));

                userRepository.save(new User(
                        "user@example.com",
                        "Regular",
                        "User",
                        "user",
                        "$2a$10$2L3ZGyLITfNvCuD4lISHE.jMjJEiDD3scMLDxzAwvaUtUOSave0sW",
                        Role.USER));

                User testUser = userRepository.save(new User(
                        "testi@example.com",
                        "Teppo",
                        "Testaaja",
                        "testikäyttäjä",
                        "$2a$10$PQMtJIy9SC1yaxyRmLnCIOT9olxEBhTnLsJMhkH7DAYImaI5Tu9JW",
                        Role.USER));

                Order order1 = orderRepository.save(new Order(testUser, LocalDateTime.now()));
                Order order2 = orderRepository.save(new Order(testUser, LocalDateTime.now()));

                orderProductRepository.save(new OrderProduct(order1, toy1, 2));
                orderProductRepository.save(new OrderProduct(order1, clothingItem1, 1));
                orderProductRepository.save(new OrderProduct(order2, food1, 2));
                orderProductRepository.save(new OrderProduct(order2, food2, 1));

            }
        };
    }
}
