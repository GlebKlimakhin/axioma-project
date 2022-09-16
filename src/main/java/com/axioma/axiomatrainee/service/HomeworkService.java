package com.axioma.axiomatrainee.service;

import com.axioma.axiomatrainee.model.Homework;
import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.repository.IExerciseRepository;
import com.axioma.axiomatrainee.repository.IGroupRepository;
import com.axioma.axiomatrainee.repository.IHomeworkRepository;
import com.axioma.axiomatrainee.requestdto.CreateHomeworkRequestDto;
import com.axioma.axiomatrainee.utill.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class HomeworkService {

    private IHomeworkRepository homeworkRepository;
    private IExerciseRepository exerciseRepository;

    private IGroupRepository groupRepository;

    @Autowired
    public void setIHomeworkRepository(IHomeworkRepository homeworkRepository, IExerciseRepository exerciseRepository, IGroupRepository groupRepository) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseRepository = exerciseRepository;
        this.groupRepository = groupRepository;
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

}
