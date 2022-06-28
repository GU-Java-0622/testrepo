package ru.geekbrains.auth.repositories.specification;


import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.auth.entityes.User;
import ru.geekbrains.auth.entityes.UserStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Set;

public class UserSpecification {
    public static Specification<User> valueLike(String field, String value) {
        return (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> criteriaBuilder.like(root.get(field), String.format("%%%s%%", value));
    }
    public static Specification<User> statusValue( Set<UserStatus> value) {
        return (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> criteriaBuilder.in(root.get("status")).value(value);
    }
}