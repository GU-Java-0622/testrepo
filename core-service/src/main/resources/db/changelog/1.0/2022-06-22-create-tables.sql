CREATE TABLE news
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    author     BIGINT,
    title      VARCHAR(255),
    content    TEXT,
    status     VARCHAR(15)                             not null,
    created_at timestamp                               not null,
    updated_at timestamp default current_timestamp,
    CONSTRAINT pk_news PRIMARY KEY (id)
);

insert into news(author, title, content, status, created_at)
values (1, 'Первая новость',
        'В нашем проекте заработал первый эндпоинт предоставляющий информацию для главной страницы!', 'ACTIVE',
        '2022-06-26 14:33:06'),
       (1, 'Еще новость',
        'Настроен сервис Gateway. Созданы каркасы микросервисов. Настроен роутинг микросервисов через Gateway',
        'ACTIVE', '2022-06-27 14:33:06'),
       (1, 'Фронтенд',
        'Отрисована welcome страница. Настроен роутинг. Angular приложение задеплоено на севис Heroku', 'ACTIVE',
        '2022-06-28 14:33:06'),
       (1, 'Фронтенд',
        'Отрисован каркас дашбоард. Организована модульная структура на основе компонентов. Модуль дашбоарда lazy load',
        'ACTIVE', '2022-06-28 14:38:06'),
       (1, 'Продолжаем',
        'Сегодня новый день и каждый продолжает работать над своей задачей!', 'ACTIVE', '2022-06-29 14:33:06');


CREATE TABLE groups
(
    group_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title      VARCHAR(255),
    teacher    BIGINT,
    size_group INTEGER,
    status     VARCHAR(255),
    group_type VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    update_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_groups PRIMARY KEY (group_id)
);

CREATE TABLE lessons
(
    lesson_id  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title      VARCHAR(255),
    status     VARCHAR(255),
    time_start TIME WITHOUT TIME ZONE,
    time_end   TIME WITHOUT TIME ZONE,
    group_id   BIGINT,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    update_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_lessons PRIMARY KEY (lesson_id)
);

ALTER TABLE lessons
    ADD CONSTRAINT FK_LESSONS_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (group_id);


CREATE TABLE students
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    student    BIGINT,
    status     VARCHAR(255),
    group_id   BIGINT,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    update_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_students PRIMARY KEY (id)
);

ALTER TABLE students
    ADD CONSTRAINT FK_STUDENTS_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (group_id);

INSERT INTO groups (group_id, title, teacher, size_group, status, group_type, created_at, update_at)
VALUES (1, 'GB-Java-1752', 1, 2, 'ACTIVE', 'PRIVATE', '2022-06-29 13:43:47.000000', '2022-06-29 13:43:49.000000'),
       (2, 'GG-0021', null, 1, 'ACTIVE', 'GENERAL', '2022-06-29 13:44:09.000000', '2022-06-29 13:44:11.000000');

insert into lessons (lesson_id, title, status, time_start, time_end, group_id, created_at, update_at)
values (1, 'English', 'ACTIVE', '13:45:00', '13:45:00', 1, '2022-06-29 13:45:07.000000', '2022-06-29 13:45:09.000000'),
       (2, 'Biology', 'ACTIVE', '13:45:00', '13:45:00', 1, '2022-06-29 13:45:35.000000', '2022-06-29 13:45:37.000000'),
       (3, 'Chemistry', 'ACTIVE', '13:45:00', '13:45:00', 1, '2022-06-29 13:45:35.000000',
        '2022-06-29 13:45:37.000000');


insert into students (id, student, status, group_id, created_at, update_at)
values (1, 1, 'ACTIVE', 1, '2022-06-29 13:46:34.000000', '2022-06-29 13:46:36.000000'),
       (2, 2, 'ACTIVE', 2, '2022-06-29 13:46:41.000000', '2022-06-29 13:46:43.000000'),
       (3, 3, 'ACTIVE', 1, '2022-06-29 13:47:13.000000', '2022-06-29 13:47:14.000000');