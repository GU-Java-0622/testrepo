package ru.geekbrains.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.auth.dto.JwtRequest;
import ru.geekbrains.auth.dto.JwtResponse;
import ru.geekbrains.auth.dto.RefreshJwtRequest;
import ru.geekbrains.auth.services.AuthService;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Сервис авторизации и аутентификации", description = "Методы работы с сервисом авторизации и аутентификации")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Запрос на авторизацию пользователя",
            responses = {
                    @ApiResponse(
                            description = "Пользователь авторизован", responseCode = "200"
                    )
            }
    )

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @Operation(
            summary = "Запрос на получение auth-токена",
            responses = {
                    @ApiResponse(
                            description = "Токен получен", responseCode = "200"
                    )
            }
    )

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @Operation(
            summary = "Запрос на получение auth-токена и refresh-токена",
            responses = {
                    @ApiResponse(
                            description = "Токены получены", responseCode = "200"
                    )
            }
    )

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}