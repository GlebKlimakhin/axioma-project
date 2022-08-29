package com.axioma.axiomatrainee.service;

import com.axioma.axiomatrainee.model.user.Role;
import com.axioma.axiomatrainee.model.user.Status;
import com.axioma.axiomatrainee.model.user.User;
import com.axioma.axiomatrainee.repository.IUserRepository;
import com.axioma.axiomatrainee.requestdto.SaveUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private IUserRepository userRepository;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public User save(SaveUserRequestDto request) {
        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setGroups(request.getGroups());
        user.setRole(request.getRole());
        user.setStatus(Status.ACTIVE);
        return userRepository.save(user);
    }

    public User updateUserRole(Long userId, Role role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No such user found"));
        user.setRole(role);
        return userRepository.save(user);
    }

    public void incrementRating(Long userId, int increment) {
        userRepository.updateUserRatingBy(userId, increment);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
