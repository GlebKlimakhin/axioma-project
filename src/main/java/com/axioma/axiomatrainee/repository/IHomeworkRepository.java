package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.Homework;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface IHomeworkRepository extends JpaRepository<Homework, Long> {
    @EntityGraph(value = "Homeworks.exercises")
    List<Homework> findAllByDescriptionContainingIgnoreCase(String description);

    @EntityGraph(value = "Homeworks.exercises")
    List<Homework> findAll();

}
