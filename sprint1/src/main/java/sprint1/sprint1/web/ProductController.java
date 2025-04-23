package sprint1.sprint1.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sprint1.sprint1.domain.*;

@Controller
public class ProductController {

    private final ManufacturerRepository manufacturerRepository;
    private final FoodRepository foodRepository;
    private final ClothingItemRepository clothingItemRepository;
    private final ToyRepository toyRepository;

    public ProductController(ClothingItemRepository clothingItemRepository,
            ToyRepository toyRepository, FoodRepository foodRepository, ManufacturerRepository manufacturerRepository) {
        this.clothingItemRepository = clothingItemRepository;
        this.toyRepository = toyRepository;
        this.foodRepository = foodRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/productlist")
    public String showProducts(Model model) {
        model.addAttribute("clothings", clothingItemRepository.findAll());
        model.addAttribute("toys", toyRepository.findAll());
        model.addAttribute("foods", foodRepository.findAll());
        return "productlist";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productTypes", Type.values()); // Lähetetään enumit lomakkeelle
        return "selecttype"; // Tämä viittaa Thymeleaf-templaattiin
    }

    @GetMapping("/add/{type}")
    public String showAddForm(@PathVariable("type") Type type, Model model) {
        switch (type) {
            case CLOTHING -> model.addAttribute("product", new ClothingItem());
            case TOY -> model.addAttribute("product", new Toy());
            case FOOD -> model.addAttribute("product", new Food());
        }
        model.addAttribute("productType", type);
        model.addAttribute("formAction", "/add/" + type.name().toLowerCase());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "addproduct";
    }


    @PostMapping("/add/clothing")
    public String saveClothing(@ModelAttribute("product") @Valid ClothingItem clothingItem,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
            return "addproduct";
        }
        clothingItem.setType(Type.CLOTHING);
        clothingItemRepository.save(clothingItem);
        return "redirect:/productlist";
    }

    @PostMapping("/add/toy")
    public String saveToy(@ModelAttribute("product") @Valid Toy toy,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
            return "addproduct";
        }
        toy.setType(Type.TOY);
        toyRepository.save(toy);
        return "redirect:/productlist";
    }

    @PostMapping("/add/food")
    public String saveFood(@ModelAttribute("product") @Valid Food food,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
            return "addproduct";
        }
        food.setType(Type.FOOD);
        foodRepository.save(food);
        return "redirect:/productlist";
    }

}
