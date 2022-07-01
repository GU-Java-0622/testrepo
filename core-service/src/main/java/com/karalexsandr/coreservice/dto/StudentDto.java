package com.karalexsandr.coreservice.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.karalexsandr.coreservice.dto.group.GroupStudentDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDto {
    private Long id;
    private Long studentName;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    @JsonBackReference
    private GroupStudentDto group;




}
