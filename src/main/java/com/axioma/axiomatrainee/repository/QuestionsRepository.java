package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByExercise(Exercise exercise);
}
