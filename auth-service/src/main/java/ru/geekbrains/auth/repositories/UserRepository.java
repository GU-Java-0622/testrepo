package ru.geekbrains.auth.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.auth.entityes.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByFirstname(String userName);
}
