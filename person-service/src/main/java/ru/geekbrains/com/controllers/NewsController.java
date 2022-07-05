package ru.geekbrains.com.controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.com.dto.news.NewsDtoRequest;
import ru.geekbrains.com.dto.news.NewsDtoResponse;
import ru.geekbrains.com.service.NewsService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

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
