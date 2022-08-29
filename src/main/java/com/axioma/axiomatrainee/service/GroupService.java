package com.axioma.axiomatrainee.service;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.Homework;
import com.axioma.axiomatrainee.model.user.User;
import com.axioma.axiomatrainee.repository.IGroupRepository;
import com.axioma.axiomatrainee.repository.IHomeworkRepository;
import com.axioma.axiomatrainee.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private IGroupRepository groupRepository;
    private IUserRepository userRepository;
    private IHomeworkRepository homeworkRepository;

    @Autowired
    public void setGroupRepository(IGroupRepository groupRepository, IUserRepository userRepository, IHomeworkRepository homeworkRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.homeworkRepository = homeworkRepository;
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Group findByName(String name) {
        return groupRepository.findByName(name)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Group> findAllByUserId(Long id) {
        User user = userRepository.getReferenceById(id);
        return groupRepository.findAllByUsersContaining(user);
    }

    @Transactional
    public Group insertUserIntoGroup(Long groupId, Long userId) {
        User user = userRepository.getReferenceById(userId);
        Group group = groupRepository.getReferenceById(groupId);
        Set<User> users = group.getUsers();
        users.add(user);
        group.setUsers(users);
        user.setGroups(user.getGroups()
                .stream()
                .filter(g -> !g.equals(group))
                .collect(Collectors.toSet()));
        userRepository.save(user);
        return groupRepository.save(group);
    }

    @Transactional
    public void deleteUserFromGroup(Long groupId, Long userId) {
        User user = userRepository.getReferenceById(userId);
        Group group = groupRepository.getReferenceById(groupId);
        Set<User> users = group.getUsers();
        users.remove(user);
        user.getGroups().remove(group);
        group.setUsers(users);
        userRepository.save(user);
        groupRepository.save(group);
    }

    public Group setHomeworks(Long groupId, Long homeworkId) {
        Group group = groupRepository.findById(groupId)
                        .orElseThrow(EntityNotFoundException::new);
        Homework homework = homeworkRepository.findById(homeworkId)
                        .orElseThrow(EntityNotFoundException::new);
        Set<Homework> homeworks = group.getHomeworks();
        homeworks.add(homework);
        group.setHomeworks(homeworks);
        return groupRepository.save(group);
    }

    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }

}
