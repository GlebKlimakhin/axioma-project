package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirstnameAndLastname(String firstname, String lastname);

    Optional<User> findByUsername(String username);

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Modifying
    @Query("update User u set u.rating=u.rating + ?2 where u.id = ?1")
    void updateUserRatingBy(long userId, int increment);
}
