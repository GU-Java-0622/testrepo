package ru.geekbrains.com.service;

import ru.geekbrains.com.dto.news.NewsDtoRequest;
import ru.geekbrains.com.dto.news.NewsDtoResponse;

import java.util.List;

public interface NewsService {
    void delete(Long id);
    NewsDtoResponse saveOrUpdate(NewsDtoRequest newsDtoRequest);

    List<NewsDtoResponse> getNewsForWelcomePage();
}
