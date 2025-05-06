package sprint1.sprint1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sprint1.sprint1.domain.UserRepository;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/userlist")
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userlist";
    }
}
