package sprint1.sprint1.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sprint1.sprint1.domain.ClothingItem;
import sprint1.sprint1.domain.ClothingItemRepository;
import sprint1.sprint1.domain.Food;
import sprint1.sprint1.domain.FoodRepository;
import sprint1.sprint1.domain.Product;
import sprint1.sprint1.domain.Toy;
import sprint1.sprint1.domain.ToyRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductRestController {

    private final FoodRepository foodRepository;

    private final ClothingItemRepository clothingItemRepository;
    private final ToyRepository toyRepository;

    public ProductRestController(ClothingItemRepository clothingItemRepository, ToyRepository toyRepository, FoodRepository foodRepository) {
        this.clothingItemRepository = clothingItemRepository;
        this.toyRepository = toyRepository;
        this.foodRepository = foodRepository;
    }

    @GetMapping("/api/products")
    public @ResponseBody List<Product> getAllProductsRest() {
        List<ClothingItem> clothes = (List<ClothingItem>) clothingItemRepository.findAll();
        List<Toy> toys = (List<Toy>) toyRepository.findAll();
        List<Food> foods = (List<Food>) foodRepository.findAll();

        List<Product> products = new ArrayList<>();
        products.addAll(clothes);
        products.addAll(toys);
        products.addAll(foods);
        return products;
    }

}
