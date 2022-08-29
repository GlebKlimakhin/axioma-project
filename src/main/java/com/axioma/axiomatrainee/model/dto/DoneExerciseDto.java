package com.axioma.axiomatrainee.model.dto;

import com.axioma.axiomatrainee.model.exercises.DoneExerciseId;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoneExerciseDto {

    DoneExerciseId doneExerciseId;

    Integer score;

    ExerciseType exerciseType;

    Long unixDate;

}
