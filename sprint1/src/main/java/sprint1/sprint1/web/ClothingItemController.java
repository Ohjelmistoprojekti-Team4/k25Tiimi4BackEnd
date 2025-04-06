package sprint1.sprint1.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sprint1.sprint1.domain.ClothingItem;
import sprint1.sprint1.domain.ClothingItemRepository;
import sprint1.sprint1.domain.ManufacturerRepository;
import sprint1.sprint1.domain.Type;


@Controller
public class ClothingItemController {

   
    private final ClothingItemRepository clothingItemRepository;

    private final ManufacturerRepository manufacturerRepository;

    public ClothingItemController(ClothingItemRepository clothingItemRepository, 
            ManufacturerRepository manufacturerRepository) {
        this.clothingItemRepository = clothingItemRepository;
        this.manufacturerRepository = manufacturerRepository;
    }
    

    @GetMapping("/clothings")
    public String showClothings(Model model) {
        model.addAttribute("clothings", clothingItemRepository.findAll());
        return "clothingItemlist";
    }

    @GetMapping("/addclothing")
    public String addClothing(Model model) {
        model.addAttribute("clothing", new ClothingItem());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "addclothing";
    }

    @PostMapping("/addclothing")
    public String addClothingPost(@ModelAttribute ClothingItem clothingItem) {
        clothingItem.setType(Type.CLOTHING);
        clothingItemRepository.save(clothingItem);
        return "redirect:/productlist";
    }
   
    @PostMapping("/clothings/delete/{id}")
    public String deleteClothing(@PathVariable("id") Long clothingId) {
        clothingItemRepository.deleteById(clothingId);
        return "redirect:/productlist";
    }

    @GetMapping("/clothings/edit/{id}")
    public String editClothingItem(@PathVariable("id") Long clothingId, Model model) {
        Optional<ClothingItem> clothing = clothingItemRepository.findById(clothingId);

        if (clothing.isPresent()) {
            model.addAttribute("clothing", clothing.get());
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
        }

        return "editclothing";
    }

    @PostMapping("/clothings/edit/save")
    public String saveEditedClothing(@ModelAttribute ClothingItem clothingItem) {
        clothingItem.setType(Type.CLOTHING);
        clothingItemRepository.save(clothingItem);
        return "redirect:/productlist";
    }
    
}
