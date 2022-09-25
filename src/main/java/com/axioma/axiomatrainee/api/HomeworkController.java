package com.axioma.axiomatrainee.api;


import com.axioma.axiomatrainee.model.Homework;
import com.axioma.axiomatrainee.model.dto.HomeworkDto;
import com.axioma.axiomatrainee.requestdto.CreateHomeworkRequestDto;
import com.axioma.axiomatrainee.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/homeworks")
public class HomeworkController {

    private HomeworkService homeworkService;

    @Autowired
    public void setHomeworkService(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('teacher')")
    @ResponseStatus(HttpStatus.CREATED)
    public Homework create(@RequestBody @Valid CreateHomeworkRequestDto request) {
        return homeworkService.createHomework(request);
    }

    @GetMapping("/groupId={groupId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<HomeworkDto> findByGroupId(@PathVariable Long groupId) {
        return homeworkService.findAllByGroupId(groupId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HomeworkDto findById(@PathVariable Long id) {
        return homeworkService.findById(id);
    }

    @GetMapping("/description={description}")
    public List<HomeworkDto> findAllByDescriptionContaining(@PathVariable String description) {
        return homeworkService.findByDescriptionContaining(description);
    }

    @GetMapping("/userId={userId}")
    public Set<HomeworkDto> findAllByUserId(@PathVariable Long userId) {
        return homeworkService.findAllByUserId(userId);
    }

    @GetMapping
    public List<HomeworkDto> findAll() {
        return homeworkService.findAll();
    }

    @GetMapping("/title={title}")
    public List<HomeworkDto> findAllByTitleContaining(@PathVariable String title) {
        return homeworkService.findByTitle(title);
    }
}
