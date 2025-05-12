package sprint1.sprint1.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import sprint1.sprint1.domain.LoginRequest;
import sprint1.sprint1.domain.Role;
import sprint1.sprint1.domain.User;
import sprint1.sprint1.domain.UserRepository;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://ohjelmistoprojekti-team4.github.io/k25Tiimi4FrontEnd/"
})
public class UserRestController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
   

    public UserRestController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        
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

        user.setOrders(new ArrayList<>());
        User savedUser = userRepository.save(user);

        //onnistunut rekisteröinti
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "User registered successfully",
                        "userId", savedUser.getUserId().toString(),
                        "role", savedUser.getRole().toString()));
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(
            @org.springframework.web.bind.annotation.RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            // Use the AuthenticationManager from your SecurityConfig
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Store the security context in the session
            request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            // Get the user details from the repository
            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Return success response with user details
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "userId", user.getUserId().toString(),
                    "username", user.getUsername(),
                    "role", user.getRole().toString()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }
    }
    
    

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "User not authenticated"));
        }

        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .map(user -> {
                    // Use Map.of for static maps with limited entries
                    Map<String, Object> userDetails = Map.of(
                            "username", user.getUsername(),
                            "email", user.getEmail(),
                            "firstname", user.getFirstName(),
                            "lastname", user.getLastName());
                    return ResponseEntity.ok(userDetails);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "User not found")));
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
