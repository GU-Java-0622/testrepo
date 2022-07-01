package com.karalexsandr.coreservice.controller;

import com.karalexsandr.coreservice.dto.group.GroupDto;
import com.karalexsandr.coreservice.entity.Group;
import com.karalexsandr.coreservice.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;


    @GetMapping
    public List<GroupDto> findAll(){
        return groupService.findAll();
    }

    @GetMapping("/{id}")
    public GroupDto findById(@PathVariable Long id){
        return groupService.findById(id);
    }
}
