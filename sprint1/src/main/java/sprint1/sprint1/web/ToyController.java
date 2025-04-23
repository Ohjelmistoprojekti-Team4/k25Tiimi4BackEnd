package sprint1.sprint1.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sprint1.sprint1.domain.ManufacturerRepository;
import sprint1.sprint1.domain.Toy;
import sprint1.sprint1.domain.ToyRepository;
import sprint1.sprint1.domain.Type;

@Controller
public class ToyController {

    private final ToyRepository toyRepository;

    private final ManufacturerRepository manufacturerRepository;

    public ToyController(ToyRepository toyRepository,
            ManufacturerRepository manufacturerRepository) {
        this.toyRepository = toyRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/toys")
    public String showToys(Model model) {
        model.addAttribute("toys", toyRepository.findAll());
        return "toylist";
    }

    // @GetMapping("/addtoy")
    // public String addToy(Model model) {
    //     model.addAttribute("toy", new Toy());
    //     model.addAttribute("manufacturers", manufacturerRepository.findAll());
    //     return "addtoy";
    // }

    // @PostMapping("/savetoy")
    // public String saveToy(@Valid @ModelAttribute Toy toy, BindingResult result, Model model) {
    //     if (result.hasErrors()) {
    //         model.addAttribute("toy", toy);
    //         model.addAttribute("manufacturers", manufacturerRepository.findAll());
    //         return "addtoy";
    //     } else {
    //         toy.setType(Type.TOY);
    //         toyRepository.save(toy);
    //         return "redirect:/productlist";
    //     }
    // } Tämän voi poistaa, kun kaikille tuotteille sopiva lisäyslomake on testattu

    @PostMapping("/toys/delete/{id}")
    public String deleteToy(@PathVariable("id") Long toyId) {
        toyRepository.deleteById(toyId);
        return "redirect:/productlist";
    }

    @GetMapping("/toys/edit/{id}")
    public String ediToy(@PathVariable("id") Long toyId, Model model) {
        Optional<Toy> toy = toyRepository.findById(toyId);

        if (toy.isPresent()) {
            model.addAttribute("toy", toy.get());
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
        }

        return "edittoy";
    }

    @PostMapping("/toys/edit/save")
    public String saveEditedToy(@Valid @ModelAttribute Toy toy, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("toy", toy);
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
            return "edittoy";
        } else {
            toy.setType(Type.TOY);
            toyRepository.save(toy);
            return "redirect:/productlist";
        }
    }
}
