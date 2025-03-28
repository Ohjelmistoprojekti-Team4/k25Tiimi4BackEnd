package sprint1.sprint1.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sprint1.sprint1.domain.ClothingItemRepository;

@Controller
public class ClothingItemController {


    private final ClothingItemRepository clothingItemRepository;

    public ClothingItemController(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }
    

    @GetMapping("/clothings")
    public String showClothings(Model model) {
        model.addAttribute("clothings", clothingItemRepository.findAll());
        return "clothinglist";
    }
}
