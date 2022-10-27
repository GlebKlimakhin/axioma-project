package com.axioma.axiomatrainee.service;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.homeworks.Homework;
import com.axioma.axiomatrainee.model.dto.HomeworkDto;
import com.axioma.axiomatrainee.model.exercises.DoneExercise;
import com.axioma.axiomatrainee.model.exercises.DoneExerciseId;
import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.homeworks.RepeatRate;
import com.axioma.axiomatrainee.model.user.User;
import com.axioma.axiomatrainee.repository.IDoneExercisesRepository;
import com.axioma.axiomatrainee.repository.IExerciseRepository;
import com.axioma.axiomatrainee.repository.IGroupRepository;
import com.axioma.axiomatrainee.repository.IHomeworkRepository;
import com.axioma.axiomatrainee.requestdto.CreateHomeworkRequestDto;
import com.axioma.axiomatrainee.service.mapper.HomeworkMapper;
import com.axioma.axiomatrainee.utill.DateParser;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomeworkService {

    private final IHomeworkRepository homeworkRepository;
    private final IGroupRepository groupRepository;
    private final IDoneExercisesRepository doneExercisesRepository;
    private final HomeworkMapper homeworkMapper;

    public HomeworkService(IHomeworkRepository homeworkRepository, IGroupRepository groupRepository, IDoneExercisesRepository doneExercisesRepository, HomeworkMapper homeworkMapper) {
        this.homeworkRepository = homeworkRepository;
        this.groupRepository = groupRepository;
        this.doneExercisesRepository = doneExercisesRepository;
        this.homeworkMapper = homeworkMapper;
    }

    public List<HomeworkDto> findAll() {
        return homeworkRepository.findAll().stream().map(homeworkMapper::toDto).collect(Collectors.toList());
    }

    public List<HomeworkDto> createHomework(CreateHomeworkRequestDto request) {
        Homework homework = homeworkMapper.fromRequestDto(request);
        if(homework.getRepeatRate().equals(RepeatRate.NEVER)) {
            HomeworkDto saved = homeworkMapper.toDto(homeworkRepository.save(homework));
            return Collections.singletonList(saved);
        }
        else {
            List<Homework> partedByDays = setExpirationDates(homework);
            List<Homework> saved = homeworkRepository.saveAll(partedByDays);
            return saved.stream()
                    .map(homeworkMapper::toDto)
                    .toList();
        }
    }

    private List<Homework> setExpirationDates(Homework homework) {
        List<Homework> homeworks = Collections.nCopies(homework.getDaysToRepeat(), homework);
        int currentDay = 0;
        long currentTimeUnix = System.currentTimeMillis();
        for (Homework h : homeworks) {
            currentDay++;
            homework.setExpirationDate(DateParser.parseFromUnix(currentTimeUnix + 86400L * currentDay));
        }
        return homeworks;
    }

    public HomeworkDto findById(Long id) {
        return homeworkRepository.findById(id)
                .stream()
                .map(homeworkMapper::toDto)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<HomeworkDto> findByDescriptionContaining(String description) {
        return homeworkRepository.findAllByDescriptionContainingIgnoreCase(description)
                .stream()
                .map(homeworkMapper::toDto)
                .toList();
    }

    public Set<HomeworkDto> findAllByGroupId(Long groupId) {
        return Optional.ofNullable(groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("No such group found"))
                .getHomeworks()).orElseThrow(() -> new EntityNotFoundException("No homeworks for this group yet"))
                .stream()
                .map(homeworkMapper::toDto)
                .collect(Collectors.toSet());
    }

    public Set<HomeworkDto> findAllByUserId(Long userId) {
        return groupRepository.findAll()
                .stream()
                .filter(g -> g.getUsers()
                        .stream()
                        .anyMatch(u -> u.getId().equals(userId)))
                .flatMap(g -> g.getHomeworks().stream().distinct().map(homeworkMapper::toDto))
                .collect(Collectors.toSet());
    }

    public List<HomeworkDto> findByTitle(String title) {
        return homeworkRepository.findAllByTitleContaining(title)
                .stream()
                .map(homeworkMapper::toDto)
                .toList();
    }

    public List<DoneExerciseId> findAllUsersDone(Long groupId, Long homeworkId) {
        Set<DoneExercise> doneExercises = new HashSet<>();
        Set<Long> exerciseIds = homeworkRepository.findById(homeworkId).orElseThrow(RuntimeException::new).getExercises()
                        .stream().map(Exercise::getId).collect(Collectors.toSet());
        Set<Long> usersIds = groupRepository.findById(groupId).orElseThrow(RuntimeException::new).getUsers()
                        .stream().map(User::getId).collect(Collectors.toSet());
        for (Long exerciseId : exerciseIds) {
            for (Long userId : usersIds) {
                DoneExerciseId doneExerciseId = new DoneExerciseId(userId, exerciseId);
                doneExercisesRepository.findByDoneExerciseId(doneExerciseId).ifPresent(doneExercises::add);
            }
        }
        return doneExercises.stream().map(DoneExercise::getDoneExerciseId).toList();
    }


}
