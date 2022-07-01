package com.karalexsandr.coreservice.repository;

import com.karalexsandr.coreservice.dto.group.GroupDto;
import com.karalexsandr.coreservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

}