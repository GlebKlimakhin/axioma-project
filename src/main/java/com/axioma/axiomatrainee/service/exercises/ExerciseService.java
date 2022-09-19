package com.axioma.axiomatrainee.service.exercises;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import com.axioma.axiomatrainee.repository.IExerciseRepository;
import com.axioma.axiomatrainee.requestdto.SaveExerciseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExerciseService {

    private IExerciseRepository exerciseRepository;

    @Autowired
    public void setExerciseRepository(IExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Optional<Exercise> findById(Long id, ExerciseType type) {
        return exerciseRepository.findByIdAndExerciseTypeEquals(id, type);
    }

    public void deleteById(Long id) {
        exerciseRepository.deleteById(id);
    }

    public Exercise save(SaveExerciseRequest request) {
        Exercise exercise = Exercise.builder()
                        .exerciseType(request.getType())
                        .name(request.getName())
                        .difficulty(request.getDifficulty())
                        .data(request.getData())
                        .build();
        return exerciseRepository.save(exercise);
    }

    public Set<Exercise> findAllByType(ExerciseType exerciseType) {
        return exerciseRepository.findAllByExerciseTypeEquals(exerciseType);
    }
}
