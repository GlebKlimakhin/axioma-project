package com.axioma.axiomatrainee.service;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.Homework;
import com.axioma.axiomatrainee.model.user.User;
import com.axioma.axiomatrainee.repository.IGroupRepository;
import com.axioma.axiomatrainee.repository.IHomeworkRepository;
import com.axioma.axiomatrainee.repository.IUserRepository;
import com.axioma.axiomatrainee.requestdto.CreateGroupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

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

    public Group save(CreateGroupRequestDto request) {
        Group group = new Group();
        group.setName(request.getName());
        group.setUsers(userRepository.findAllByIds(request.getUserIds()));
        group.setHomeworks(null);
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
    public void insertUserIntoGroup(Long groupId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("No such user found"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("No such group found"));
        System.out.println("method:2");
        Set<User> users = group.getUsers();
            users.add(user);
        group.setUsers(users);
        groupRepository.save(group);
    }

    @Transactional
    public void deleteUserFromGroup(Long groupId, Long userId) {
        System.out.println("method:1");
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("No such user found"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("No such group found"));
        Set<User> users = group.getUsers();
        users.remove(user);
        group.setUsers(users);
        groupRepository.save(group);
    }

    public Group addHomework(Long groupId, Long homeworkId) {
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
