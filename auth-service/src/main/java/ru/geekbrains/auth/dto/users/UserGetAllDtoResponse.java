package ru.geekbrains.auth.dto.users;


import ru.geekbrains.auth.entityes.User;
import ru.geekbrains.auth.entityes.UserStatus;

public class UserGetAllDtoResponse {
    private Long id;
    private String firstname;
    private String surname;
    private String lastname;
    private String password;
    private String email;
    private UserStatus status;
    private boolean isEmailVerified =false;

    public UserGetAllDtoResponse(Long id, String firstname, String surname, String lastname, String password, String email) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }
    public UserGetAllDtoResponse(User usr) {
        this.id = usr.getId();
        this.firstname = usr.getFirstname();
        this.surname = usr.getSurname();
        this.lastname = usr.getLastname();
        this.email = usr.getEmail();
        this.status = usr.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

}