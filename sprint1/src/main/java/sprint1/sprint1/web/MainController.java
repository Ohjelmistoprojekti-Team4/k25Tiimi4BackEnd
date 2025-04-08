//Ani
package sprint1.sprint1.web;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    @GetMapping({ "/", "/index" })
    // "/index" ei toimi
    public String showIndexPage() {
        return "index";
    }
}
