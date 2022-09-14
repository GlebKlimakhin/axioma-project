package com.axioma.axiomatrainee.api;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.requestdto.CreateGroupRequestDto;
import com.axioma.axiomatrainee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('user')")
    public Group findById(@PathVariable Long id) {
        return groupService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('teacher')")
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('teacher')")
    public ResponseEntity<Group> save(@RequestBody @Valid CreateGroupRequestDto request) {
        return ResponseEntity.ok(groupService.save(request));
    }

    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('teacher')")
    public Group findByName(@PathVariable String name){
        return groupService.findByName(name);
    }

    @GetMapping("/userid={userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('user')")
    public List<Group> findAllByUserId(@PathVariable Long userId) {
        return groupService.findAllByUserId(userId);
    }

    @PutMapping("/insert/groupId={groupId}&userId={userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('teacher')")
    public void insertUserIntoGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        System.out.println("has entered controller method insert put groups");
        groupService.insertUserIntoGroup(groupId, userId);
    }

    @PutMapping("/delete/groupId={groupId}&userId={userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('teacher')")
    public void deleteUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        System.out.println("has entered controller method delete groups");
        groupService.deleteUserFromGroup(groupId, userId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public void deleteById(@PathVariable Long id) {
        groupService.deleteGroupById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('teacher')")
    public Group addHomework(@RequestParam Long groupId, @RequestParam Long homeworkId) {
        return groupService.addHomework(groupId, homeworkId);
    }
}
