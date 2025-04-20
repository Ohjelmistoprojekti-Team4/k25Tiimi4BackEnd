package sprint1.sprint1.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sprint1.sprint1.domain.Food;
import sprint1.sprint1.domain.FoodRepository;
import sprint1.sprint1.domain.ManufacturerRepository;
import sprint1.sprint1.domain.Type;

@Controller
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    // TODO: add foods to add form

    @PostMapping("/savefood")
    public String saveFood(@Valid @ModelAttribute Food food, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("food", food);
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
            return "addfood";
        } else {
            food.setType(Type.FOOD);
            foodRepository.save(food);
            return "redirect:/productlist";
        }
    }

    @PostMapping("/foods/delete/{id}")
    public String deleteFood(@PathVariable("id") Long foodId) {
        foodRepository.deleteById(foodId);
        return "redirect:/productlist";
    }

    @GetMapping("/foods/edit/{id}")
    public String editFood(@PathVariable("id") Long foodId, Model model) {
        Optional<Food> food = foodRepository.findById(foodId);

        if (food.isPresent()) {
            model.addAttribute("food", food.get());
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
        }

        return "editfood";
    }

    @PostMapping("/foods/edit/save")
    public String saveEditedFood(@Valid @ModelAttribute Food food, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("food", food);
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
            return "editfood";
        } else {
            food.setType(Type.FOOD);
            foodRepository.save(food);
            return "redirect:/productlist";
        }
    }
}
