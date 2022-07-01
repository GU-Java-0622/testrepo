package com.karalexsandr.coreservice.controller;

import com.karalexsandr.coreservice.dto.LessonDto;
import com.karalexsandr.coreservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/{id}")
    public LessonDto findById(@PathVariable Long id){
        return lessonService.findById(id);
    }

    @GetMapping
    public List<LessonDto> findAll(){
        return lessonService.findAll();
    }
}
