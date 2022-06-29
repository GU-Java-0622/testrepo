package com.karalexsandr.coreservice.service;

import com.karalexsandr.coreservice.dto.news.NewsDtoRequest;
import com.karalexsandr.coreservice.dto.news.NewsDtoResponse;
import com.karalexsandr.coreservice.entity.News;

import java.util.List;

public interface NewsService {
    void delete(Long id);
    NewsDtoResponse saveOrUpdate(NewsDtoRequest newsDtoRequest);

    List<NewsDtoResponse> getNewsForWelcomePage();
}
