package com.axioma.axiomatrainee.events;

import com.axioma.axiomatrainee.model.exercises.DoneExerciseId;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DoneExerciseInHomeworkEvent extends ApplicationEvent {

    private DoneExerciseId doneExerciseId;

    public DoneExerciseInHomeworkEvent(DoneExerciseId doneExerciseId) {
        super(doneExerciseId);
    }
}
