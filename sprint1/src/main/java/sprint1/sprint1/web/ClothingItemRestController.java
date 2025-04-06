package sprint1.sprint1.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sprint1.sprint1.domain.ClothingItem;
import sprint1.sprint1.domain.ClothingItemRepository;

@CrossOrigin
@RestController
public class ClothingItemRestController {

    private final ClothingItemRepository clothingItemRepository;

    public ClothingItemRestController(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }

    @GetMapping("/api/clothes")
    public @ResponseBody List<ClothingItem> getAllClothesRest() {
        return (List<ClothingItem>) clothingItemRepository.findAll();
    }

}
