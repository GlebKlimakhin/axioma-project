package com.axioma.axiomatrainee.service.exercises;

import com.axioma.axiomatrainee.model.dto.DoneExerciseDto;
import com.axioma.axiomatrainee.model.exercises.DoneExercise;
import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class RandomExerciseService {

    private ExerciseService exerciseService;
    private DoneExerciseService doneExerciseService;

    @Autowired
    public RandomExerciseService(ExerciseService exerciseService, DoneExerciseService doneExerciseService) {
        this.exerciseService = exerciseService;
        this.doneExerciseService = doneExerciseService;
    }

    public Exercise findRandomByUserId(Long userId, ExerciseType type) {
        Set<Exercise> all = exerciseService.findAllByType(type);
        List<DoneExerciseDto> doneByUser = doneExerciseService.findAllByUserId(userId);
        return removeDoneExercises(all, doneByUser).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("You have done everything!"));
    }
    
    private Set<Exercise> removeDoneExercises(Set<Exercise> exercises, List<DoneExerciseDto> done) {
        Iterator<Exercise> iterator = exercises.iterator();
        for (int i = 0; i < done.size(); i++) {
            while (iterator.hasNext()) {
                if(done.get(i).getDoneExerciseId().getExerciseId().equals(iterator.next().getId())) {
                    iterator.remove();
                }
            }
        }
        return exercises;
    }
}
