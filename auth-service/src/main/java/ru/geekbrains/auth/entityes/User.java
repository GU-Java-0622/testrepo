package ru.geekbrains.auth.entityes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")/*имя*/
    private String firstname;

    @Column(name = "surname")/*фамилия*/
    private String surname;

    @Column(name = "lastname")/*отчество*/
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> ERoles = new HashSet<>();

}
