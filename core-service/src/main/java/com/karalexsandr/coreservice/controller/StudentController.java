package com.karalexsandr.coreservice.controller;


import com.karalexsandr.coreservice.dto.StudentDto;
import com.karalexsandr.coreservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/find/{id}")
    public StudentDto findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @GetMapping
    public List<StudentDto> findAll(){
        return studentService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        studentService.delete(id);
    }
}
