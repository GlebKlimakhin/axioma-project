package com.axioma.axiomatrainee.service.mapper;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.dto.HomeworkDto;
import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.homeworks.Homework;
import com.axioma.axiomatrainee.repository.IExerciseRepository;
import com.axioma.axiomatrainee.repository.IGroupRepository;
import com.axioma.axiomatrainee.requestdto.CreateHomeworkRequestDto;
import com.axioma.axiomatrainee.utill.DateParser;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Set;

@Component
public class HomeworkMapper {

    @Autowired
    private IExerciseRepository exerciseRepository;

    @Autowired
    private IGroupRepository groupRepository;

    public Homework fromRequestDto(CreateHomeworkRequestDto requestDto) {
        Set<Exercise> exerciseSet = Sets.newHashSet(exerciseRepository.findAllByExerciseIds(requestDto.getExercisesIds()));
        Group group = groupRepository.findById(requestDto.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("No such group"));

        return Homework.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .exercises(exerciseSet)
                .group(group)
                .creationDate(new Date())
                .repeatRate(requestDto.getRepeatRate())
                .daysToRepeat(requestDto.getDaysToRepeat())
                .build();
    }

    public HomeworkDto toDto(Homework homework) {
        return HomeworkDto.builder()
                .id(homework.getId())
                .title(homework.getTitle())
                .description(homework.getDescription())
                .exercises(homework.getExercises())
                .group(homework.getGroup())
                .creationDate(DateParser.parseFromDate(homework.getCreationDate()))
                .expirationDate(DateParser.parseFromDate(homework.getExpirationDate()))
                .repeatRate(homework.getRepeatRate())
                .daysToRepeat(homework.getDaysToRepeat())
                .build();
    }
}
