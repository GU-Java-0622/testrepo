package com.karalexsandr.coreservice.service;


import com.karalexsandr.coreservice.dto.LessonDto;
import com.karalexsandr.coreservice.dto.group.GroupDto;
import com.karalexsandr.coreservice.entity.Group;
import com.karalexsandr.coreservice.entity.Status;
import com.karalexsandr.coreservice.exceptions.ResourceNotFoundException;
import com.karalexsandr.coreservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GroupService {
    private static final Type listType = new TypeToken<List<GroupDto>>(){}.getType();
    private final GroupRepository groupRepository;
    private final ModelMapper mapper;

    public GroupDto findById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно найти, не надйен в базе, id: " + id));
        return mapper.map(group,GroupDto.class);
    }

    public List<GroupDto> findAll() {
        List<Group> groupList = groupRepository.findAll();
        return mapper.map(groupList, listType);
    }

    public GroupDto delete(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить студента, не надйен в базе, id: " + id));
        group.setStatus(Status.DELETED);
        groupRepository.save(group);
        return mapper.map(group,GroupDto.class);
    }


    //@Transactional
 /*   public GroupDto saveOrUpdate(@NotNull GroupDto groupDto){
        Group group;
        if (groupDto.getId()!=null){
            group  = groupRepository.findById(groupDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить студента, не надйен в базе, id: " + groupDto.getId()));
        }else {
            group = new Group();
            group.setCreatedAt(LocalDateTime.now());
            if (groupDto.getType().equals(GroupType.GENERAL.toString())) {
                if (groupDto.getStudents().size() >= 30) {
                    group.setStudents(new ArrayList<>(
                            StudentListMapper.INSTANCE.toListEntity(groupDto.getStudents())
                                    .subList(30, groupDto.getStudents().size())));
                    group.setSizeGroup(group.getStudents().size());
                }
                group.setType(GroupType.GENERAL);
            }else {
                group.setType(GroupType.PRIVATE);
            }
        }
        group.setTitle(groupDto.getTitle());
        return null;
    }*/

}
