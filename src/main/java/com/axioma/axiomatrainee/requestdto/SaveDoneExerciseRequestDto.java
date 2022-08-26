package com.axioma.axiomatrainee.requestdto;

import com.axioma.axiomatrainee.model.exercises.DoneExerciseId;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveDoneExerciseRequestDto {

    DoneExerciseId doneExerciseId;
    Integer score;
    ExerciseType exerciseType;
}
