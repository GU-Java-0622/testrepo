package com.karalexsandr.coreservice.controller;

import com.karalexsandr.coreservice.converters.NewsConverter;
import com.karalexsandr.coreservice.dto.NewsDto;
import com.karalexsandr.coreservice.entity.News;
import com.karalexsandr.coreservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsService newsService;
    private final NewsConverter newsConverter;

    @GetMapping
    public List<NewsDto> findAll(@RequestParam(name = "title_part",required = false) String titlePart){
        return newsService.findAll(titlePart).stream()
                .map(newsConverter::entityYoDto)
                .collect(Collectors.toList());
    }


    @PostMapping
    public NewsDto save(@RequestBody NewsDto newsDto){
        News news = newsConverter.dtoToEntity(newsDto);
        news = newsService.save(news);
        return newsConverter.entityYoDto(news);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        newsService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody NewsDto newsDto){
        newsService.update(newsDto);
    }
}
