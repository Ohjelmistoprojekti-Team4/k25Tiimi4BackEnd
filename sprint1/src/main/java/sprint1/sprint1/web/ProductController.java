package sprint1.sprint1.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sprint1.sprint1.domain.ClothingItemRepository;
import sprint1.sprint1.domain.ToyRepository;

@Controller
public class ProductController {

    private final ClothingItemRepository clothingItemRepository;
    private final ToyRepository toyRepository;

    public ProductController(ClothingItemRepository clothingItemRepository,
            ToyRepository toyRepository) {
        this.clothingItemRepository = clothingItemRepository;
        this.toyRepository = toyRepository;
    }

    @GetMapping("/productlist")
    public String showProducts(Model model) {
        model.addAttribute("clothings", clothingItemRepository.findAll());
        model.addAttribute("toys", toyRepository.findAll());
        return "productlist";
    }
}
