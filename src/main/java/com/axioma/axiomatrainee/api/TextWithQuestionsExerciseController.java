package com.axioma.axiomatrainee.api;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import com.axioma.axiomatrainee.requestdto.SaveExerciseRequest;
import com.axioma.axiomatrainee.service.exercises.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/texts")
public class TextWithQuestionsExerciseController {

    public static final ExerciseType TYPE = ExerciseType.TEXTS_WITH_QUESTIONS;

    private final ExerciseService exerciseService;

    @PostMapping
    public Exercise save(@RequestBody SaveExerciseRequest requestDto) {
        return exerciseService.save(requestDto);
    }

    @GetMapping
    public Set<Exercise> findAllByType() {
        return exerciseService.findAllByType(TYPE);
    }
}
