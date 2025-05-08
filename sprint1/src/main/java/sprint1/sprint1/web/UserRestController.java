package sprint1.sprint1.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import sprint1.sprint1.domain.Role;
import sprint1.sprint1.domain.User;
import sprint1.sprint1.domain.UserRepository;

import java.util.Map;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserRestController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   

    public UserRestController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        
    }

    //rekisteröinti
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        //Jos käyttäjänm username on jo tietokannassa -> error
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Username already exists"));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        userRepository.save(user);

        //onnistunut rekisteröinti
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "User registered successfully"));
    }
    
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(Authentication authentication) {
        String username = authentication.getName(); 
        return userRepository.findByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteUserAccount(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok(Map.of("message", "User account deleted successfully"));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "User not found")));
    }

   

    
}
