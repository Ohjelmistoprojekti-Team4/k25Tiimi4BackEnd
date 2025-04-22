package sprint1.sprint1.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sprint1.sprint1.domain.Food;
import sprint1.sprint1.domain.FoodRepository;

@RestController
public class FoodRestController {

    private final FoodRepository foodRepository;

    public FoodRestController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping("/api/foods")
    public @ResponseBody List<Food> getAllFoodsRest() {
        return (List<Food>) foodRepository.findAll();
    }
    
}
