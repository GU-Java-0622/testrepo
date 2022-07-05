package ru.geekbrains.com.dto.news;

import ru.geekbrains.com.entity.News;

import java.time.LocalDateTime;


public class NewsDtoResponse {
    private Long id;
    private Long author;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

    public NewsDtoResponse (News news){
        this.id = news.getId();
        this.author = news.getAuthor();
        this.title = news.getTitle();
        this.content = news.getContent();
        this.created = news.getCreatedAt();
        this.updated = news.getUpdatedAt();

    }

    public NewsDtoResponse(Long id, Long author, String title, String content, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public NewsDtoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
