package com.karalexsandr.coreservice.service;

import com.karalexsandr.coreservice.dto.NewsDto;
import com.karalexsandr.coreservice.entity.News;
import com.karalexsandr.coreservice.exceptions.ResourceNotFoundException;
import com.karalexsandr.coreservice.repository.NewsRepository;
import com.karalexsandr.coreservice.repository.specifications.NewsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<News> findAll(String titlePart) {
        Specification<News> spec = Specification.where(null);
        if (titlePart != null) {
            spec = spec.and(NewsSpecifications.titleLike(titlePart));
        }
        return newsRepository.findAll(spec);
    }

   public void delete(Long id){
        newsRepository.deleteById(id);
   }

   public News save(News news){
        return newsRepository.save(news);
   }

   @Transactional
   public News update(NewsDto newsDto){
        News news = newsRepository.findById(newsDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Невозможно обновить новость, не надйен в базе, id: " + newsDto.getId()));
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        return news;
   }
}
