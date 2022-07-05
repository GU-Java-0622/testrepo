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

}
