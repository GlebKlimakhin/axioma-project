package com.axioma.axiomatrainee.events.listeners;

import com.axioma.axiomatrainee.events.DoneExerciseInHomeworkEvent;
import com.axioma.axiomatrainee.model.exercises.DoneExercise;
import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.axioma.axiomatrainee.repository.*;
import com.axioma.axiomatrainee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoneExerciseHomeworkEventListener implements ApplicationListener<DoneExerciseInHomeworkEvent> {

    private final IDoneExercisesRepository doneExercisesRepository;
    private final UserService userService;
    private final IExerciseRepository exerciseRepository;

    @Override
    @Transactional
    public void onApplicationEvent(DoneExerciseInHomeworkEvent event) {
        //should add when doneExercise is not present, it is in homework attached to group
        Optional<DoneExercise> doneExercise = doneExercisesRepository.findByDoneExerciseId(event.getDoneExerciseId());
        if (doneExercise.isEmpty() && checkIfExerciseIsInHomeworkAttached(event.getDoneExerciseId().getUserid(), event.getDoneExerciseId().getExerciseId())) {
            userService.incrementRating(event.getDoneExerciseId().getUserid(), 10);
        }
    }

    private boolean checkIfExerciseIsInHomeworkAttached(Long userId, Long exerciseId) {
        userService.findById(userId)
                .getGroups()
                .stream()
                .flatMap(g -> g.getHomeworks().stream())
                .filter(h -> h.getExercises().contains(exerciseRepository.findById(exerciseId, Exercise.class)))
                .findAny().orElseThrow(() -> new UserAlreadyDoneExerciseException("User already done this exercise"));
        return true;
    }

    private static class UserAlreadyDoneExerciseException extends RuntimeException {
        public UserAlreadyDoneExerciseException(String message) {
            super(message);
        }
    }
}
