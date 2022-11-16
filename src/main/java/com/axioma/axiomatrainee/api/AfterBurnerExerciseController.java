package com.axioma.axiomatrainee.api;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import com.axioma.axiomatrainee.requestdto.SaveExerciseRequest;
import com.axioma.axiomatrainee.service.exercises.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/afterburner")
@RequiredArgsConstructor
public class AfterBurnerExerciseController {
    private final ExerciseService exerciseService;
    private final ExerciseType TYPE = ExerciseType.AFTERBURNER;

    @PostMapping
    public Exercise save(@RequestBody SaveExerciseRequest request) {
        return exerciseService.save(request);
    }

    @GetMapping
    public Set<Exercise> findAll() {
        return exerciseService.findAllByType(TYPE);
    }

    @GetMapping("/{id}")
    public Exercise findById(@PathVariable Long id) {
        return exerciseService.findById(id, TYPE)
                .orElseThrow(ExerciseNotFoundException::new);
    }

    private static class ExerciseNotFoundException extends RuntimeException {
        public ExerciseNotFoundException() {
        }

        public ExerciseNotFoundException(String message) {
            super(message);
        }
    }
}
