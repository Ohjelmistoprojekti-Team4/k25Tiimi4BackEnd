package sprint1.sprint1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import sprint1.sprint1.domain.ClothingItem;
import sprint1.sprint1.domain.ClothingItemRepository;
import sprint1.sprint1.domain.Food;
import sprint1.sprint1.domain.FoodRepository;
import sprint1.sprint1.domain.Manufacturer;
import sprint1.sprint1.domain.ManufacturerRepository;
import sprint1.sprint1.domain.Toy;
import sprint1.sprint1.domain.ToyRepository;

@Controller
public class ManufacturerController {

    @Autowired
    ManufacturerRepository mrepository;

    @Autowired
    ToyRepository toyRepository;
    @Autowired
    ClothingItemRepository clothingItemRepository;
    @Autowired
    FoodRepository foodRepository;

    @GetMapping("/manufacturerlist")
    public String showManufacturers(Model model) {
        model.addAttribute("manufacturers", mrepository.findAll());
        return "manufacturerlist";
    }

    @GetMapping("/addmanufacturer")
    public String addManufacturer(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "addmanufacturer";
    }

    @PostMapping("/savemanufacturer")
    public String saveManufacturer(@Valid Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturer", manufacturer);
            return "addmanufacturer";
        } else {
            mrepository.save(manufacturer);
            return "redirect:/manufacturerlist";
        }
    }

    /*
     * @PostMapping("/deletemanufacturer/{id}")
     * public String deleteManufacturer(@PathVariable("id") Long manufacturerId) {
     * mrepository.deleteById(manufacturerId);
     * return "redirect:/manufacturerlist";
     * }
     */

    @GetMapping("/manufacturer/{id}")
    public String viewManufacturerProducts(@PathVariable("id") Long id, Model model) {
        Manufacturer manufacturer = mrepository.findById(id).orElse(null);
        List<Toy> toys = toyRepository.findByManufacturer(manufacturer);
        List<ClothingItem> clothingItems = clothingItemRepository.findByManufacturer(manufacturer);
        List<Food> food = foodRepository.findByManufacturer(manufacturer);
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("toys", toys);
        model.addAttribute("clothingItems", clothingItems);
        model.addAttribute("food", food);
        return "products-by-manufacturer";
    }

    @PostMapping("/deletemanufacturer/{id}")
    public String deleteManufacturer(@PathVariable("id") Long manufacturerId, RedirectAttributes redirectAttributes) {
        boolean hasProducts = toyRepository.existsByManufacturer_ManufacturerId(manufacturerId) ||
                clothingItemRepository.existsByManufacturer_ManufacturerId(manufacturerId) ||
                foodRepository.existsByManufacturer_ManufacturerId(manufacturerId);

        if (hasProducts) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Can't delete manufacturer that has products in database");
            return "redirect:/manufacturerlist";
        }

        mrepository.deleteById(manufacturerId);
        return "redirect:/manufacturerlist";
    }

}
