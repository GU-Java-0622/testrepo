package com.karalexsandr.coreservice.service;


import com.karalexsandr.coreservice.dto.LessonDto;
import com.karalexsandr.coreservice.dto.StudentDto;
import com.karalexsandr.coreservice.entity.Status;
import com.karalexsandr.coreservice.entity.Student;
import com.karalexsandr.coreservice.exceptions.ResourceNotFoundException;
import com.karalexsandr.coreservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {
    private static final Type listType = new TypeToken<List<StudentDto>>(){}.getType();
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    public StudentDto findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно найти, не надйен в базе, id: " + id));
        System.out.println("STUDENT-------------------------" + student);
        return mapper.map(student,StudentDto.class);
    }

    public Student save(StudentDto studentDto) {
        return studentRepository.save(mapper.map(studentDto,Student.class));
    }

    public List<StudentDto> findAll(){
        List<Student> studentList = studentRepository.findAll();
        return mapper.map(studentList,listType);
    }


    public void delete(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить студента, не надйен в базе, id: " + id));
        student.setStatus(Status.DELETED);
        studentRepository.save(student);
    }

    public StudentDto update(StudentDto studentDto){
        Student student = studentRepository.findById(studentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить студента, не надйен в базе, id: " + studentDto.getId()));
        student.setStatus(Enum.valueOf(Status.class,studentDto.getStatus()));
        studentRepository.save(student);
        return mapper.map(student,StudentDto.class);
    }

}
