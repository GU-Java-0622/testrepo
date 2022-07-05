package ru.geekbrains.auth.controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.auth.repositories.UserRepository;
import ru.geekbrains.auth.entityes.User;
import ru.geekbrains.auth.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/delete")
    public void deleteUser(@RequestParam String username) {
        Optional<User> user = userRepository.findByFirstname(username);
        if (Optional.ofNullable(user).isPresent()) {
            userRepository.deleteById(user.get().getId());
        }
    }

}
