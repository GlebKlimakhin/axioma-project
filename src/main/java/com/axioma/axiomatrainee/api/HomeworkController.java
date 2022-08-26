package com.axioma.axiomatrainee.api;


import com.axioma.axiomatrainee.model.Homework;
import com.axioma.axiomatrainee.requestdto.CreateHomeworkRequestDto;
import com.axioma.axiomatrainee.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public Homework create(@RequestBody CreateHomeworkRequestDto request) {
        return homeworkService.createHomework(request);
    }

    @GetMapping("/groupId={groupId}")
    public Set<Homework> findHomeWorksByGroupId(@PathVariable Long groupId) {
        return homeworkService.findAllByGroupId(groupId);
    }
}
