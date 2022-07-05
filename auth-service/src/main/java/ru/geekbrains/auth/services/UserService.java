package ru.geekbrains.auth.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.auth.entityes.User;
import ru.geekbrains.auth.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final List<User> users;


    public Optional<User> getByLogin(@NonNull String email) {
        return userRepository.findByEmail(email);
    }

}
