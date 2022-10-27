package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.homeworks.Homework;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHomeworkRepository extends JpaRepository<Homework, Long> {
    @EntityGraph(value = "Homeworks.exercises")
    List<Homework> findAllByDescriptionContainingIgnoreCase(String description);
    List<Homework> findAllByTitleContaining(String title);

}
