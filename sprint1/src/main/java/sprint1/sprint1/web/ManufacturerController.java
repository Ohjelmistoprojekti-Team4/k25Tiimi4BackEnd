package sprint1.sprint1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import sprint1.sprint1.domain.Manufacturer;
import sprint1.sprint1.domain.ManufacturerRepository;

@Controller
public class ManufacturerController {

    @Autowired
    ManufacturerRepository mrepository;

    @GetMapping("/manufacturers")
    public String showManufacturers(Model model) {
        model.addAttribute("manufacturers", mrepository.findAll());
        return "manufacturerlist";
    }

    @GetMapping("/addmanufacturer")
    public String addManufacturer(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "addmanufacturer";
    }

    @PostMapping("/manufacturers/save")
    public String saveManufacturer(Manufacturer manufacturer) {
        mrepository.save(manufacturer);
        return "redirect:/manufacturers";
    }
}
