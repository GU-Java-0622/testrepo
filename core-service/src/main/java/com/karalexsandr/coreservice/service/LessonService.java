package com.karalexsandr.coreservice.service;


import com.karalexsandr.coreservice.dto.LessonDto;
import com.karalexsandr.coreservice.entity.Group;
import com.karalexsandr.coreservice.entity.Lesson;
import com.karalexsandr.coreservice.entity.Status;
import com.karalexsandr.coreservice.exceptions.ResourceNotFoundException;
import com.karalexsandr.coreservice.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LessonService {
    private static final Type listType = new TypeToken<List<LessonDto>>(){}.getType();
    private final LessonRepository lessonRepository;
    private final ModelMapper mapper;

    public Lesson save(LessonDto lessonDto){
        Lesson lesson = mapper.map(lessonDto, Lesson.class);
        return lessonRepository.save(lesson);
    }
    public LessonDto findById(Long id){
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить урок, не надйен в базе, id: " + id));
        return mapper.map(lesson,LessonDto.class);
    }

    public LessonDto delete(Long id){
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить урок, не надйен в базе, id: " + id));
        lesson.setStatus(Status.DELETED);
        lessonRepository.save(lesson);
        return mapper.map(lesson,LessonDto.class);
    }

    public LessonDto update(LessonDto lessonDto){
        Lesson lesson = lessonRepository.findById(lessonDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить урок, не надйен в базе, id: " + lessonDto.getId()));
        lesson.setTitle(lessonDto.getTitle());
        lesson.setTimeStart(lessonDto.getTimeStart());
        lesson.setTimeEnd(lessonDto.getTimeEnd());
        lesson.setStatus(Enum.valueOf(Status.class,lessonDto.getStatus()));
        lesson.setGroupLesson(mapper.map(lessonDto.getGroupLesson(), Group.class));
        lessonRepository.save(lesson);
        return mapper.map(lesson,LessonDto.class);
    }


    public List<LessonDto> findByGroupId(Long groupId) {
        List<Lesson> lessons = lessonRepository.findAllByGroupLessonId(groupId);
        return mapper.map(lessons,listType);
    }

    public List<LessonDto> findAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return mapper.map(lessons, listType);
    }
}
