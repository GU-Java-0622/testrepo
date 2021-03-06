CREATE TABLE schedules
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    group_id              BIGINT,
    learning_programme_id BIGINT,
    lesson_id             BIGINT,
    started_at            TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_schedules PRIMARY KEY (id)
);

ALTER TABLE schedules
    ADD CONSTRAINT FK_SCHEDULES_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE schedules
    ADD CONSTRAINT FK_SCHEDULES_ON_LEARNING_PROGRAMME FOREIGN KEY (learning_programme_id) REFERENCES learning_programmes (id);

ALTER TABLE schedules
    ADD CONSTRAINT FK_SCHEDULES_ON_LESSON FOREIGN KEY (lesson_id) REFERENCES lessons (id);