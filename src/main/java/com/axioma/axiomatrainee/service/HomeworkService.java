package com.axioma.axiomatrainee.service;

import com.axioma.axiomatrainee.model.Homework;
import com.axioma.axiomatrainee.model.exercises.DoneExercise;
import com.axioma.axiomatrainee.model.exercises.DoneExerciseId;
import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.user.User;
import com.axioma.axiomatrainee.repository.IDoneExercisesRepository;
import com.axioma.axiomatrainee.repository.IExerciseRepository;
import com.axioma.axiomatrainee.repository.IGroupRepository;
import com.axioma.axiomatrainee.repository.IHomeworkRepository;
import com.axioma.axiomatrainee.requestdto.CreateHomeworkRequestDto;
import com.axioma.axiomatrainee.utill.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class HomeworkService {

    private IHomeworkRepository homeworkRepository;
    private IExerciseRepository exerciseRepository;
    private IGroupRepository groupRepository;
    private IDoneExercisesRepository doneExercisesRepository;

    public HomeworkService(IHomeworkRepository homeworkRepository, IExerciseRepository exerciseRepository, IGroupRepository groupRepository, IDoneExercisesRepository doneExercisesRepository) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseRepository = exerciseRepository;
        this.groupRepository = groupRepository;
        this.doneExercisesRepository = doneExercisesRepository;
    }

    public List<Homework> findAll() {
        return homeworkRepository.findAll();
    }

    public Homework createHomework(CreateHomeworkRequestDto request) {
        Homework homework = new Homework();
        Set<Exercise> requestedExercises = new HashSet<>();
        exerciseRepository.findAllByExerciseIds(request.getExercisesIds())
                .forEach(requestedExercises::add);
        homework.setExercises(requestedExercises);
        homework.setDescription(request.getDescription());
        homework.setExpirationDate(
                DateParser.parseFromUnix(request.getUnixExpirationDate())
        );
        homework.setGroups(
                Set.of(groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot create homework, no such group exists"))));
        return homeworkRepository.save(homework);
    }

    public Optional<Homework> findById(Long id) {
        return homeworkRepository.findById(id);
    }

    public List<Homework> findByDescriptionContaining(String description) {
        return homeworkRepository.findAllByDescriptionContainingIgnoreCase(description);
    }

    public Set<Homework> findAllByGroupId(Long groupId) {
        return Optional.ofNullable(groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("No such group found"))
                .getHomeworks()).orElseThrow(() -> new EntityNotFoundException("No homeworks for this group yet"));
    }

    public Set<Homework> findAllByUserId(Long userId) {
        return groupRepository.findAll()
                .stream()
                .filter(g -> g.getUsers()
                        .stream()
                        .anyMatch(u -> u.getId().equals(userId)))
                .flatMap(g -> g.getHomeworks().stream().distinct())
                .collect(Collectors.toSet());
    }

    public List<DoneExerciseId> findAllUsersDone(Long groupId, Long homeworkId) {
        List<DoneExercise> doneExercises = new ArrayList<>();
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
