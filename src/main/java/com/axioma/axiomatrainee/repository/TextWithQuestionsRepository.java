package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextWithQuestionsRepository extends JpaRepository<Exercise, Long> {
    ExerciseType exerciseType = ExerciseType.TEXTS_WITH_QUESTIONS;

    List<Exercise> findAllByExerciseType(ExerciseType exerciseType);

    @Override
    default List<Exercise> findAll() {
        return this.findAllByExerciseType(exerciseType);
    }

}
