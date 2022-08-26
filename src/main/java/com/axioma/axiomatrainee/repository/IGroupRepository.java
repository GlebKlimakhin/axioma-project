package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IGroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByName(String name);

    List<Group> findAllByUsers(User user);

    List<Group> findAllByUsersContaining(User user);
}
