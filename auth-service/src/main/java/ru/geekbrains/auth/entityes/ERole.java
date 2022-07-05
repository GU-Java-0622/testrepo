package ru.geekbrains.auth.entityes;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum ERole implements GrantedAuthority {


    ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");


    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
