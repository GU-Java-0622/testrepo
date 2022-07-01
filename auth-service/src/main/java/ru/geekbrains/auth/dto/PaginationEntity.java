package ru.geekbrains.auth.dto;


import java.util.List;


public class PaginationEntity<T> {

    private Integer totalPage;

    private Integer currentPage;

    private List<T> content;

    public PaginationEntity(Integer totalPage, Integer currentPage, List<T> content) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.content = content;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
