//Ani
package sprint1.sprint1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index"; 
    }
}
