package com.karalexsandr.coreservice.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.karalexsandr.coreservice.dto.group.GroupDto;
import com.karalexsandr.coreservice.dto.group.GroupLessonDto;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class LessonDto {
    private Long id;
    private String title;
    private String status;
    private LocalTime timeStart;    // начало урока
    private LocalTime timeEnd;// конец урока
    @JsonManagedReference
    private GroupLessonDto groupLesson;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
