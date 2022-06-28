package com.karalexsandr.coreservice.converters;

import com.karalexsandr.coreservice.dto.NewsDto;
import com.karalexsandr.coreservice.entity.News;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {
    public News dtoToEntity(NewsDto newsDto) {
        return new News(newsDto.getId(), newsDto.getAuthor(), newsDto.getTitle(), newsDto.getContent());
    }

    public NewsDto entityYoDto(News news){
        return new NewsDto(news.getId(), news.getAuthor(),news.getTitle(),news.getContent());
    }
}
