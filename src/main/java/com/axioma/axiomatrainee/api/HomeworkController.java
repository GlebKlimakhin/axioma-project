package com.axioma.axiomatrainee.api;


import com.axioma.axiomatrainee.model.Homework;
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
    public Set<Homework> findByGroupId(@PathVariable Long groupId) {
        return homeworkService.findAllByGroupId(groupId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Homework findById(@PathVariable Long id) {
        return homeworkService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Homework with such id has not been created yet"));
    }

    @GetMapping("/description={description}")
    public List<Homework> findAllByDescriptionContaining(String description) {
        return homeworkService.findByDescriptionContaining(description);
    }
}
