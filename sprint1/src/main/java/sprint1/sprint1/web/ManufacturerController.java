package sprint1.sprint1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sprint1.sprint1.domain.Manufacturer;
import sprint1.sprint1.domain.ManufacturerRepository;

@Controller
public class ManufacturerController {

    @Autowired
    ManufacturerRepository mrepository;

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
    public String saveManufacturer(@Valid Manufacturer manufacturer, BindingResult result) {
        if (result.hasErrors()) {
            return "addmanufacturer";
        } else {
            mrepository.save(manufacturer);
            return "redirect:/manufacturerlist";
        }
    }

    @PostMapping("/deletemanufacturer/{id}")
    public String deleteManufacturer(@PathVariable("id") Long manufacturerId) {
        mrepository.deleteById(manufacturerId);
        return "redirect:/manufacturerlist";
    }
}
