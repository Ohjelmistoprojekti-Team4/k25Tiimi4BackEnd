package sprint1.sprint1.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sprint1.sprint1.domain.Manufacturer;
import sprint1.sprint1.domain.ManufacturerRepository;

@CrossOrigin
@RestController
public class ManufacturerRestController {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerRestController(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/api/manufacturers")
    public @ResponseBody List<Manufacturer> getAllManufacturersRest() {
        return (List<Manufacturer>) manufacturerRepository.findAll();
    }

}
