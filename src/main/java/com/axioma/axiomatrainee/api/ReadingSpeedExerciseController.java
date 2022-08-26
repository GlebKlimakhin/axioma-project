package com.axioma.axiomatrainee.api;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import com.axioma.axiomatrainee.requestdto.SaveExerciseRequest;
import com.axioma.axiomatrainee.service.exercises.ExerciseService;
import com.axioma.axiomatrainee.service.exercises.RandomExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/reading")
public class ReadingSpeedExerciseController {

    public static final ExerciseType TYPE = ExerciseType.READING_SPEED;

    private ExerciseService exerciseService;
    private RandomExerciseService randomExerciseService;

    @Autowired
    public void setService(ExerciseService service, RandomExerciseService randomExerciseService) {
        this.exerciseService = service;
        this.randomExerciseService = randomExerciseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('user')")
    public Set<Exercise> findAll() {
        return exerciseService.findAllByType(TYPE);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('user')")
    public Exercise findById(@PathVariable Long id) {
        return exerciseService.findById(id, TYPE).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('teacher')")
    public Exercise save(@RequestBody @Valid SaveExerciseRequest request) {
        return exerciseService.save(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public void deleteById(Long id) {
        exerciseService.findAllByType(TYPE).forEach(e -> {
            if(e.getId().equals(id)) {
                exerciseService.deleteById(id);
            }
        });
    }

    @GetMapping("/random/userId={userId}")
    @PreAuthorize("hasAuthority('user')")
    public Exercise findRandomByUserId(@PathVariable Long userId) {
        return randomExerciseService.findRandomByUserId(userId, TYPE);
    }

}
