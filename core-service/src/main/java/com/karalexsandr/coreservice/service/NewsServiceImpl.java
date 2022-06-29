package com.karalexsandr.coreservice.service;

import com.karalexsandr.coreservice.dto.news.NewsDtoRequest;
import com.karalexsandr.coreservice.dto.news.NewsDtoResponse;
import com.karalexsandr.coreservice.entity.News;
import com.karalexsandr.coreservice.entity.NewsStatus;
import com.karalexsandr.coreservice.exceptions.ResourceNotFoundException;
import com.karalexsandr.coreservice.repository.NewsRepository;
import com.karalexsandr.coreservice.repository.specifications.NewsSpecifications;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{
    private final String GET_LAST_FOUR_NEWS ="select new com.karalexsandr.coreservice.dto.news.NewsDtoResponse" +
            "(n.id,n.author,n.title,n.content,n.createdAt,n.updatedAt) " +
            "from News as n where n.status=: param order by n.createdAt desc";
    private final NewsRepository newsRepository;
    private final EntityManager entityManager;

   public void delete(Long id){
        News news = newsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Невозможно удалить новость, не надйен в базе, id: "+id));
        news.setStatus(NewsStatus.DELETED);
        newsRepository.save(news);
   }
    @Transactional
   public NewsDtoResponse saveOrUpdate(@NotNull NewsDtoRequest newsDtoRequest){
       News news;
       if(newsDtoRequest.getId()!=null){
           news = newsRepository.findById(newsDtoRequest.getId())
                   .orElseThrow(()-> new ResourceNotFoundException("Невозможно обновить новость, не надйен в базе, id: " + newsDtoRequest.getId()));
       }else {
           news = new News();
           news.setCreatedAt(LocalDateTime.now());
       }
        news.setStatus(NewsStatus.ACTIVE);
        news.setAuthor(newsDtoRequest.getAuthorId());//поидее id надо брать из заголовка авторизации в HttpRequest
        news.setTitle(newsDtoRequest.getTitle());
        news.setContent(newsDtoRequest.getContent());
        newsRepository.save(news);
        return new NewsDtoResponse(news);
   }

    @Override
    public List<NewsDtoResponse> getNewsForWelcomePage() {
        Query query = entityManager.createQuery(GET_LAST_FOUR_NEWS);
        query.setParameter("param",NewsStatus.ACTIVE);
        query.setMaxResults(4);
        return query.getResultList();
    }

}
