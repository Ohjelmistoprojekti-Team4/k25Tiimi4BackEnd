
package sprint1.sprint1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import sprint1.sprint1.domain.Manufacturer;
import sprint1.sprint1.domain.ManufacturerRepository;
import sprint1.sprint1.domain.Toy;
import sprint1.sprint1.domain.Food;
import sprint1.sprint1.domain.ClothingItem;
import sprint1.sprint1.domain.ClothingSize;
import sprint1.sprint1.domain.Type;
import sprint1.sprint1.domain.FoodRepository;
import sprint1.sprint1.domain.ToyRepository;
import sprint1.sprint1.domain.ClothingItemRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@SpringBootTest
@AutoConfigureMockMvc
class ProductRestControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(ProductRestControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ClothingItemRepository clothingItemRepository;

	@Autowired
	private ToyRepository toyRepository;

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	private Manufacturer testManufacturer;

	@BeforeEach
	void setUp() {
		clothingItemRepository.deleteAll();
		toyRepository.deleteAll();
		foodRepository.deleteAll();
		manufacturerRepository.deleteAll();

		testManufacturer = manufacturerRepository.save(new Manufacturer("Test Manufacturer"));

		clothingItemRepository
				.save(new ClothingItem("Shirt", 29.99, 10, Type.CLOTHING, testManufacturer, "red", ClothingSize.M));
		toyRepository.save(new Toy("Ball", 14.99, 20, Type.TOY, testManufacturer, "plastic"));
		foodRepository.save(new Food("Dog Food", 39.99, 5, Type.FOOD, testManufacturer, "chicken"));

	}

	@Test
	void getAllProductsRest_ShouldReturnAllProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(3));

		List<ClothingItem> clothings = StreamSupport.stream(clothingItemRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		List<Toy> toys = StreamSupport.stream(toyRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		List<Food> foods = StreamSupport.stream(foodRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		int totalClothingAmount = clothings.stream().mapToInt(ClothingItem::getAmount).sum();
		int totalToyAmount = toys.stream().mapToInt(Toy::getAmount).sum();
		int totalFoodAmount = foods.stream().mapToInt(Food::getAmount).sum();

		logger.info("Vaatteiden varastosaldo: " + totalClothingAmount);
		logger.info("Lelujen varastosaldo: " + totalToyAmount);
		logger.info("Ruokien varastosaldo: " + totalFoodAmount);
	}
}

