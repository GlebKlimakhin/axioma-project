package com.axioma.axiomatrainee.service;

import com.axioma.axiomatrainee.model.projections.UserView;
import com.axioma.axiomatrainee.model.user.Role;
import com.axioma.axiomatrainee.model.user.Status;
import com.axioma.axiomatrainee.model.user.User;
import com.axioma.axiomatrainee.repository.IUserRepository;
import com.axioma.axiomatrainee.requestdto.SaveUserRequestDto;
import com.axioma.axiomatrainee.requestdto.UpdateUserRequestDto;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {

    private IUserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


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

    public List<UserView> findAllByIds(Iterable<Long> ids) {
        return userRepository.findAllByIdIn(ids, UserView.class);
    }

    public User save(SaveUserRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setGroups(request.getGroups());
        user.setRole(request.getRole());
        user.setStatus(Status.ACTIVE);
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    public User updateUser(Long userId, UpdateUserRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.setUsername(requestDto.getUsername());
        user.setRole(requestDto.getRole());
        user.setFirstname(requestDto.getFirstname());
        user.setLastname(requestDto.getLastname());
        user.setStatus(requestDto.getStatus());
        user.setEmail(requestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(requestDto.getPassword()));
        return userRepository.save(user);
    }

    public void incrementRating(Long userId, int increment) {
        userRepository.updateUserRatingBy(userId, increment);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
