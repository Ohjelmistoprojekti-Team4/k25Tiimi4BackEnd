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
import sprint1.sprint1.domain.User;
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

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
        
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        }

        return "edituser";
    }

    @PostMapping("/users/edit/save")
    public String saveEditedUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "edituser";
        } else {
            userRepository.save(user);
            return "redirect:/userlist";
        }
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/userlist";
    }

}
