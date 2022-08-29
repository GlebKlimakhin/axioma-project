package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.exercises.DoneExercise;
import com.axioma.axiomatrainee.model.exercises.DoneExerciseId;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDoneExercisesRepository extends JpaRepository<DoneExercise, DoneExerciseId> {
    List<DoneExercise> findAllByDoneExerciseId_UseridAndExerciseType(Long userId, ExerciseType exerciseType);

    Optional<DoneExercise> findByDoneExerciseId(DoneExerciseId id);

    List<DoneExercise> findAllByDoneExerciseId_Userid(Long userId);
}
