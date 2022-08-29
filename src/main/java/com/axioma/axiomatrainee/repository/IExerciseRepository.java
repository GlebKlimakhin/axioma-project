package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IExerciseRepository extends JpaRepository<Exercise, Long> {

    Set<Exercise> findAllByExerciseTypeEquals(ExerciseType exerciseType);

    Optional<Exercise> findByIdAndExerciseTypeEquals(Long id, ExerciseType exerciseType);

    @Query("select ex from Exercise ex where ex.id in :ids")
    Iterable<Exercise> findAllByExerciseIds(@Param("ids") Iterable<Long> ids);
}
