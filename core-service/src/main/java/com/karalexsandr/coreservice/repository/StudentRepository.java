package com.karalexsandr.coreservice.repository;

import com.karalexsandr.coreservice.dto.StudentDto;
import com.karalexsandr.coreservice.entity.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select s.id,s.studentName,s.groupStudent," +
            "s.createdAt,s.updateAt from Student as s where s.groupStudent.id =1")
    List<Student> findAllDto(Long groupId);
}