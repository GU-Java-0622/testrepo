CREATE TABLE news
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    author  BIGINT,
    title   VARCHAR(255),
    content TEXT,
    status VARCHAR(15) not null ,
    created_at timestamp not null ,
    updated_at timestamp default current_timestamp,
    CONSTRAINT pk_news PRIMARY KEY (id)
);

insert into news(author, title, content,status,created_at)
values (1, 'Первая новость',
        'В нашем проекте заработал первый эндпоинт предоставляющий информацию для главной страницы!','ACTIVE','2022-06-26 14:33:06'),
       (1, 'Еще новость',
        'Настроен сервис Gateway. Созданы каркасы микросервисов. Настроен роутинг микросервисов через Gateway','ACTIVE','2022-06-27 14:33:06'),
       (1, 'Фронтенд',
        'Отрисована welcome страница. Настроен роутинг. Angular приложение задеплоено на севис Heroku','ACTIVE','2022-06-28 14:33:06'),
       (1, 'Фронтенд',
        'Отрисован каркас дашбоард. Организована модульная структура на основе компонентов. Модуль дашбоарда lazy load','ACTIVE','2022-06-28 14:38:06'),
       (1, 'Продолжаем',
        'Сегодня новый день и каждый продолжает работать над своей задачей!','ACTIVE','2022-06-29 14:33:06');