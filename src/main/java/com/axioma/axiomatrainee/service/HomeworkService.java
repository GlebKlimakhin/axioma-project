package com.axioma.axiomatrainee.service;

import com.axioma.axiomatrainee.model.Homework;
import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.repository.IExerciseRepository;
import com.axioma.axiomatrainee.repository.IHomeworkRepository;
import com.axioma.axiomatrainee.requestdto.CreateHomeworkRequestDto;
import com.axioma.axiomatrainee.utill.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class HomeworkService {

    private IHomeworkRepository homeworkRepository;
    private IExerciseRepository exerciseRepository;

    @Autowired
    public void setIHomeworkRepository(IHomeworkRepository homeworkRepository, IExerciseRepository exerciseRepository) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public Homework createHomework(CreateHomeworkRequestDto createHomeworkRequestDto) {
        Homework homework = new Homework();
        Set<Exercise> requestedExercises = new HashSet<>();
        exerciseRepository.findAllByExerciseIds(createHomeworkRequestDto.getExercisesIds())
                .forEach(requestedExercises::add);
        homework.setExercises(requestedExercises);
        homework.setDescription(createHomeworkRequestDto.getDescription());
        homework.setExpirationDate(
                DateParser.parseFromUnix(createHomeworkRequestDto.getUnixExpirationDate())
        );
        return homeworkRepository.save(homework);
    }

    public Optional<Homework> findById(Long id) {
        return homeworkRepository.findById(id);
    }

    public List<Homework> findByDescriptionContaining(String description) {
        return homeworkRepository.findAllByDescriptionContainingIgnoreCase(description);
    }

    public Set<Homework> findAllByGroupId(Long groupId) {
        return homeworkRepository.findHomeworkByGroupId(groupId);
    }
}
