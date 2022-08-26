package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface IHomeworkRepository extends JpaRepository<Homework, Long> {

    @Query(
            value = "select h.* from homeworks h " +
                    "join groups_homeworks gh on " +
                    "h.id=gh.homework_id "  +
                    "where h.id in " +
                    "(select homework_id " +
                    "from groups_homeworks gh" +
                    "join groups g on " +
                    "gh.group_id = g.group_id)",
            nativeQuery = true)
    Set<Homework> findHomeworkByGroupId(Long groupId);
}
