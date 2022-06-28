package ru.geekbrains.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getall")
    public List<User> getCurrentUserOrders() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/delete")
    public void deleteUser(@RequestParam String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (Optional.ofNullable(user).isPresent()) {
            userRepository.deleteById(user.get().getId());
        }
    }

}
