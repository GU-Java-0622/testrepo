package ru.geekbrains.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.auth.entityes.ERole;
import ru.geekbrains.auth.entityes.Role;
import ru.geekbrains.auth.entityes.User;
import ru.geekbrains.auth.entityes.UserStatus;
import ru.geekbrains.auth.payload.request.LoginRequest;
import ru.geekbrains.auth.payload.request.SignupRequest;
import ru.geekbrains.auth.payload.response.JwtResponse;
import ru.geekbrains.auth.payload.response.MessageResponse;
import ru.geekbrains.auth.repositories.RoleRepository;
import ru.geekbrains.auth.repositories.UserRepository;
import ru.geekbrains.auth.security.jwt.JwtUtils;
import ru.geekbrains.auth.security.services.UserDetailsImpl;

import javax.security.auth.message.AuthException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Сервис авторизации и аутентификации", description = "Методы работы с сервисом авторизации и аутентификации")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }


    @Operation(
            summary = "Запрос на авторизацию пользователя",
            responses = {
                    @ApiResponse(
                            description = "Пользователь авторизован", responseCode = "200"
                    )
            }
    )

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws AuthException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));
    }

    @Operation(
            summary = "Запрос на регистрацию пользователя",
            responses = {
                    @ApiResponse(
                            description = "Пользователь зарегистрирован", responseCode = "200"
                    )
            }
    )

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getFirstname(), signUpRequest.getSurname(), signUpRequest.getLastname(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail(), UserStatus.ACTIVE);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "teacher":
                        Role modRole = roleRepository.findByName(ERole.ROLE_TEACHER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

//    @Operation(
//            summary = "Запрос на получение auth-токена",
//            responses = {
//                    @ApiResponse(
//                            description = "Токен получен", responseCode = "200"
//                    )
//            }
//    )

//    @PostMapping("token")
//    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
//        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
//        return ResponseEntity.ok(token);
//    }
//
//    @Operation(
//            summary = "Запрос на получение auth-токена и refresh-токена",
//            responses = {
//                    @ApiResponse(
//                            description = "Токены получены", responseCode = "200"
//                    )
//            }
//    )
//
//    @PostMapping("refresh")
//    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
//        final JwtResponse token = authService.refresh(request.getRefreshToken());
//        return ResponseEntity.ok(token);
//    }


}