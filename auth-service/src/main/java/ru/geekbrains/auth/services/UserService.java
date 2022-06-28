package ru.geekbrains.auth.services;

import ru.geekbrains.auth.dto.PaginationEntity;
import ru.geekbrains.auth.dto.users.UserGetAllDtoRequest;
import ru.geekbrains.auth.dto.users.UserGetAllDtoResponse;

import java.util.List;

public interface UserService {
    PaginationEntity<UserGetAllDtoResponse> getAllUsers(UserGetAllDtoRequest param);
}
