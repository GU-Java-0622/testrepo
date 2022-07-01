package com.karalexsandr.coreservice.dto.group;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.karalexsandr.coreservice.dto.StudentDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GroupStudentDto {
    private Long id;
    private String title;
    private Long teacher;
    private Integer sizeGroup;
    private String status;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    @JsonBackReference
    private List<StudentDto> students;
}
