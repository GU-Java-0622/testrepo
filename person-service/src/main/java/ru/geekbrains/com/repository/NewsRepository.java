package ru.geekbrains.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.geekbrains.com.entity.News;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

}