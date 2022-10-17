package com.axioma.axiomatrainee.api;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import com.axioma.axiomatrainee.requestdto.SaveExerciseRequest;
import com.axioma.axiomatrainee.service.exercises.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AfterBurnerExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseType TYPE = ExerciseType.AFTERBURNER;

    public Exercise save(@RequestBody SaveExerciseRequest request) {
        return exerciseService.save(request);
    }

    public Set<Exercise> findAll() {
        return exerciseService.findAllByType(TYPE);
    }
}
