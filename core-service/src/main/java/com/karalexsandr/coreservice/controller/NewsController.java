package com.karalexsandr.coreservice.controller;


import com.karalexsandr.coreservice.dto.news.NewsDtoRequest;
import com.karalexsandr.coreservice.dto.news.NewsDtoResponse;
import com.karalexsandr.coreservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/welcome_page")
    public List<NewsDtoResponse> getNewsForWelcomePage(){
        return newsService.getNewsForWelcomePage();
    }

    @PostMapping("/save")
    public NewsDtoResponse saveOrUpdate(@RequestBody NewsDtoRequest newsDtoRequest){
       return newsService.saveOrUpdate(newsDtoRequest);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        newsService.delete(id);
    }

}
