package ru.geekbrains.auth.services.service_impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.auth.dto.PaginationEntity;
import ru.geekbrains.auth.dto.users.UserGetAllDtoRequest;
import ru.geekbrains.auth.dto.users.UserGetAllDtoResponse;
import ru.geekbrains.auth.entityes.User;
import ru.geekbrains.auth.repositories.UserRepository;
import ru.geekbrains.auth.repositories.specification.UserSpecification;
import ru.geekbrains.auth.services.UserService;
import ru.geekbrains.auth.validators.FieldNameChecker;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PaginationEntity<UserGetAllDtoResponse> getAllUsers(UserGetAllDtoRequest param) {
        if(param.getPage()==null){
            param.setPage(1);
        }
        /*ASC по возрастанию Desc убывание*/
        Sort.Direction direction =Sort.Direction.ASC;
        if (!param.getDirectSort()){
            direction = Sort.Direction.DESC;
        }
        Sort sort;
        String field = FieldNameChecker.checkFieldName(User.class,param.getSortField());
        if (field!=null){
            sort = Sort.by(direction,param.getSortField());
        }else {
            sort = Sort.by(direction,"id");
        }

        /*Добавляем спецификацию исходя из параметров запроса*/
        Specification<User> spec;
        /*Если строка запроа пуста то спецификацию не добавляем*/
        if (param.getSearchValue()!=null){
            if(param.getSearchField().equals("id")){
                spec= Specification.where(UserSpecification.valueLikeLong(param.getSearchField(), Long.parseLong(param.getSearchValue())));
            }else{
                spec= Specification.where(UserSpecification.valueLike(param.getSearchField(), param.getSearchValue()));
            }
        }else{
            spec = Specification.where(null);
        }
        Specification<User> specStatus = Specification.where(UserSpecification.statusValue(param.getStatus()));
        Page<User> pageable = userRepository.findAll(spec.and(specStatus), PageRequest.of(param.getPage()-1, param.getItemInPage(),sort));
        List<UserGetAllDtoResponse> result = pageable.getContent().stream().map(UserGetAllDtoResponse::new).collect(Collectors.toList());
        return new PaginationEntity<>(pageable.getTotalPages(), param.getPage(), result);
    }
}
